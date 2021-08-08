package com.fivesoft.smartutil;

import android.util.Log;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * Debug your app easily.
 */

public class L {

    /**
     * Shows message in Logcat with priority {@link android.util.Log#ASSERT}
     * @param message The message you want to print in log. It can be any type of object.
     */

    public static void log(Object message){
        Log.println(Log.ASSERT, "5S Log", String.valueOf(message));
    }

    /**
     * Clearly shows an array in Logcat with priority {@link android.util.Log#ASSERT}
     * @param list The list you want to print in log.
     */

    public static void logList(Collection<?> list){

        if(list == null){
            log("YOUR LIST: is null");
            return;
        }

        Iterator<?> iterator = list.iterator();
        StringBuilder res = new StringBuilder();

        int size = 0;

        while(true){
            if(iterator.hasNext()){
                size++;
                res.append("\n");
                res.append(size).append(". ");
                res.append(iterator.next());
            } else {
                break;
            }
        }

        Log.println(Log.ASSERT, "5S Log",
                "YOUR LIST: (size: " + size + ")" + res.toString()
                );

    }

    /**
     * Shows passed objects in Logcat with priority {@link android.util.Log#ASSERT}<br>
     * in pattern: TAG1 + ": " + OBJECT1 + TAG2 + ": " + OBJECT2 + ...
     * <br>
     * <b>Example:</b>
     * <br>
     * <pre>
     *     {@code L.log("Apples", 10, "Bananas", 4, "Holy cows", "45");}
     * </pre>
     * Gives:<br>
     * <pre>
     * {@code "Apples: 10
     *         Bananas: 4
     *         Holy cows: 45"}
     * </pre>
     * @param message your message
     */

    public static void log(Object... message){
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < message.length; i+=2) {

            if(i != 0)
                builder.append("\n");

            builder.append(message[i]).append(": ");
            if(i + 1 < message.length)
                builder.append(message[i + 1]);
        }

        Log.println(Log.ASSERT, "5S Log", builder.toString());
    }

}
