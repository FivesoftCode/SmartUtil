package com.fivesoft.smartutil;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.OpenableColumns;
import android.webkit.MimeTypeMap;

import com.google.common.io.ByteStreams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

public class FileUtil {


    /**
     * Returns file name with extension. (ex. "hello_world.txt")
     * @param uri The uri of the file.
     * @param context Context necessary to get content resolver.
     * @return Name of the file.
     */

    public static String getName(Uri uri, Context context){
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = context.getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    /**
     * Returns file extension with dot. (ex. ".txt")
     * @param uri The uri of the file.
     * @param context Context necessary to get content resolver.
     * @return File extension. (ex. ".txt")
     */

    public static String getExtension(Uri uri, Context context){
        String name = getName(uri, context);
        int index = name.lastIndexOf(".");

        if(index == -1)
            return null;

        return name.substring(index);
    }

    /**
     * Returns size (in bytes) of the file.
     * @param uri The uri of the file.
     * @param context Context necessary to get content resolver.
     * @return Size of the file. (in bytes)
     */

    public static int getSize(Uri uri, Context context){
        try {
            return getBytes(uri, context).length;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Creates copy of the file src at dst.
     * @param src Source file.
     * @param dst Destination file.
     */

    public static void copy(File src, File dst) {
        try {
            FileChannel inChannel = new FileInputStream(src).getChannel();
            FileChannel outChannel = new FileOutputStream(dst).getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
            inChannel.close();
            if (outChannel != null)
                outChannel.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Returns mime type of the file. (ex. "image/*")
     * @param uri The uri of the file.
     * @param context Context necessary to get content resolver.
     * @return Mime type of the file.
     */

    public static String getMimeType(Uri uri, Context context) {
        String mimeType;
        if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme())) {
            ContentResolver cr = context.getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                    .toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase());
        }
        return mimeType;
    }

    /**
     * Reads file bytes to array.
     * @param uri The uri of the file.
     * @param context Context necessary to get content resolver.
     * @return Byte array.
     */

    public static byte[] getBytes(Uri uri, Context context){
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            return ByteStreams.toByteArray(inputStream);
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Creates chooser to handel send action.
     * @param uri The uri of the file you want to share.
     * @param title The title of the chooser.
     * @param activity Activity from which the chooser will be created.
     */

    public static void share(Uri uri, String title, Activity activity){
        shareInternal(uri, title, activity);
    }

    private static void shareInternal(Uri uri, String title, Activity activity){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //Uri uri = Uri.parse("file://" + path);
        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType(getMimeType(uri, activity));
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, title);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        activity.startActivity(Intent.createChooser(shareIntent, title));
    }
}
