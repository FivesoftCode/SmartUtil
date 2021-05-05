package com.fivesoft.smartutil;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.OpenableColumns;
import android.webkit.MimeTypeMap;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import com.google.common.io.ByteStreams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.List;

public class FileUtil {

    public static final String CACHE_FOLDER_NAME = "File_Util_Caches";

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
     * @param sourceUri Source file uri.
     * @param dst Destination file.
     * @return Returns destination file object or null
     * when an error occurred.
     */

    public static File copy(Uri sourceUri, File dst, Context context) {
        return copyInternal(sourceUri, context, dst.getPath());
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
     * @param shareMessage The message which will be attached to sending file.
     * @param activity Activity from which the chooser will be created.
     *
     *
     * <pre>
     * <h1>Sharing files</h1>
     * <h3>Step 1</h3>
     * In your AndroidManifest.xml in "application" tag add provider tag:
     * <pre>
     *      {@code
     * <provider
     * android:name="androidx.core.content.FileProvider"
     * android:authorities="TYPE_HERE_YOUR_APP_PACKAGE_NAME.provider"
     * android:exported="false"
     * android:grantUriPermissions="true">
     * <meta-data
     *    android:name="android.support.FILE_PROVIDER_PATHS"
     *    android:resource="@xml/provider_paths" />
     * </provider>
     * }
     * </pre>
     * <h3>Step 2</h3>
     * Create provider_paths.xml file in res/xml folder:
     * <pre>
     *     {@code
     * <?xml version="1.0" encoding="utf-8"?>
     * <paths>
     *    <files-path path="."
     *    name="File_Util_Caches" />
     * </paths>
     *     }
     * </pre>
     *
     * <h3>Step 3</h3>
     * Share the file:
     * <pre>
     *     {@code
     * Uri fileToShare = FileUtil.cacheFile(YOUR_FILE_URI, "TYPE_HERE_YOUR_APP_PACKAGE_NAME.provider", this);
     * FileUtil.share(fileToShare, "Share File", "Look! It is awesome!", this);
     *     }
     * </pre>
     * <h3>Step 4</h3>
     * Don't forget to {@link #clearCache(Context)} from time to time.
     * <br><br>
     * <b>That's it!</b>
     * </pre>
     */

    public static void share(@NonNull Uri uri, String title, String shareMessage, @NonNull Activity activity){
        shareInternal(uri, title, shareMessage, activity);
    }

    private static void shareInternal(Uri uri, String title, String shareMessage, Activity activity){
        Intent shareIntent = new Intent("android.intent.action.SEND");
        shareIntent.setType(getMimeType(uri, activity));
        shareIntent.putExtra("android.intent.extra.TEXT", shareMessage);
        shareIntent.putExtra("android.intent.extra.STREAM", uri);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Intent chooser = Intent.createChooser(shareIntent, title);

        List<ResolveInfo> resInfoList = activity.getPackageManager().queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            activity.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }

        activity.startActivity(chooser);
    }

    /**
     * Creates copy of the file in app private files directory.
     * @param uri The uri of original file.
     * @param authority Provider authority. The value which you pass in AndroidManifest.xml
     *                  at application -> provider -> android:authorities
     *                  Usually it is YOUR_APP_PACKAGE + ".provider".
     * @param context Context necessary to get ContentResolver.
     * @return The uri of file copy or null if any errors occurred.
     */

    public static Uri cacheFile(Uri uri, String authority, Context context){
        File cacheFolder = new File(context.getFilesDir(), CACHE_FOLDER_NAME);
        cacheFolder.mkdirs();
        File cachedFile = new File(cacheFolder, FileUtil.getName(uri, context));
        copy(uri, cachedFile, context);
        return FileProvider.getUriForFile(context, authority, cachedFile);
    }

    /**
     * Deletes all files created when using {@link #cacheFile(Uri, String, Context)}
     * @param context Context necessary to perform this operation.
     */

    public static void clearCache(Context context){
        deleteFileOrFolder(new File(context.getFilesDir(), CACHE_FOLDER_NAME));
    }

    /**
     * Deletes file weather path leads to a file or deletes directory
     * if path leads to a folder.
     * @param fileOrDirectory File you want to delete.
     */

    public static void deleteFileOrFolder(File fileOrDirectory) {
        try {
            if (fileOrDirectory.isDirectory())
                for (File child : fileOrDirectory.listFiles())
                    deleteFileOrFolder(child);

            fileOrDirectory.delete();
        } catch (Exception e){
            e.printStackTrace();
            L.log(e);
        }
    }

    private static File copyInternal(Uri uri, Context context, String dest) {

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(context.getContentResolver().openInputStream(uri));
            bos = new BufferedOutputStream(new FileOutputStream(dest, false));
            byte[] buf = new byte[1024];
            bis.read(buf);
            do {
                bos.write(buf);
            } while(bis.read(buf) != -1);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
                L.log(e);
            }
        }
        return new File(dest);
    }


}
