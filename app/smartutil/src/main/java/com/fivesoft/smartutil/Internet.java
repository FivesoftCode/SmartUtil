package com.fivesoft.smartutil;

import android.content.Context;
import android.net.ConnectivityManager;

public class Internet {

    /**
     * Returns weather device has network access now.
     */

    public static boolean isAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
