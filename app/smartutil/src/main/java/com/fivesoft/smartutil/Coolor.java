package com.fivesoft.smartutil;

import android.graphics.Color;

import java.util.Random;

public class Coolor {

    /**
     * Converts int color to hex string.
     * ex. 0x000000 will be "#000000"
     * <br>
     * <br>
     * <b>WARNING!</b>
     * Alpha chanel is ignored.
     * @param color int color.
     * @return Hex color string.
     */

    public static String toHex(int color){
        return String.format("#%06X", (0xFFFFFF & color));
    }

    /**
     * Converts hex string color to int color.
     * ex. "#000000" will be 0x000000
     * <br>
     * <br>
     * <b>WARNING!</b>
     * Alpha chanel is ignored.
     * @param hexString hex color string.
     * @return int color.
     */

    public static int fromHex(String hexString){
        return Color.parseColor(hexString);
    }

    /**
     * Returns true whether color is dark.
     * @param color Your color.
     * @return true whether color is dark.
     */

    public static boolean isDark(int color){
        return !((1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255) < 0.5);
    }

    public static int red(int color){
        return Color.red(color);
    }

    public static int green(int color){
        return Color.green(color);
    }

    public static int blue(int color){
        return Color.blue(color);
    }

    public static int alpha(int color){
        return Color.alpha(color);
    }

    public static int getRedGreenScaleColor(int percent) {

        if (percent > 100) {
            percent = 100;
        } else if (percent < 0) {
            percent = 0;
        }
        int r, g;
        int b = 54;
        int prc = percent;
        if (prc < 50) {
            r = 250;
            g = 40 + (prc * 3);
        } else {
            r = 250 - (prc - 50) * 4;
            g = 190;
        }

        return Color.parseColor(String.format("#%02x%02x%02x", r, g, b));
    }

    public static int getGreyScaleColor(int percent) {

        int c = 255 - (255 * percent / 100);

        return Color.parseColor(String.format("#%02x%02x%02x", c, c, c));
    }

    /**
     * Generates random color.
     * @return random color.
     */

    public static int random(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    /**
     * Makes color darker.
     * @param color Original color.
     * @param light Lower factor - darker color. Shouldn't be greater than
     * @return Darker color.
     */

    public static int darken(int color, float light){
        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * light);
        int g = Math.round(Color.green(color) * light);
        int b = Math.round(Color.blue(color) * light);
        return Color.argb(a,
                Math.max(Math.min(r,255), 0),
                Math.max(Math.min(g,255), 0),
                Math.max(Math.min(b,255), 0)
        );
    }

}
