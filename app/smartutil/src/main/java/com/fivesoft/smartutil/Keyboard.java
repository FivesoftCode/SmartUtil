package com.fivesoft.smartutil;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Keyboard {

    /**
     * Hides keyboard.
     * @param activity Running activity.
     */

    public static void hide(Activity activity){
        if(isVisible(activity)) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            View view = activity.getCurrentFocus();
            if (view == null) {
                view = new View(activity);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Shows keyboard.
     * @param activity Running activity.
     */

    public static void show(Activity activity){
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /**
     * Checks if keyboard is visible now.
     * @param activity Running activity.
     * @return true if keyboard is visible.
     */

    public static boolean isVisible(Activity activity){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isAcceptingText();
    }
}
