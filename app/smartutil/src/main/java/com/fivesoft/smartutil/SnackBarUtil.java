package com.fivesoft.smartutil;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class SnackBarUtil {

    public static void showSnackBar(View view, String message, int duration, int marginBottom) {

        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.setDuration(duration);

        final View snackBarView = snackbar.getView();
        snackBarView.setTranslationY(-(marginBottom));

        snackbar.show();
    }

    public static void showSnackBar(View view, String message, int duration) {
        Snackbar sb1 = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        sb1.setDuration(duration);
        sb1.show();
    }

}
