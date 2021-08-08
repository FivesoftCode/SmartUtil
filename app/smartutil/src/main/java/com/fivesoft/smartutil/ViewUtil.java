package com.fivesoft.smartutil;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;

@SuppressWarnings("unused")
public class ViewUtil {

    public static void setViewHeight(View view, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
        view.setLayoutParams(params);
    }

    public static void setViewWidth(View view, int width) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        view.setLayoutParams(params);
    }

    public static void setMargins(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    public static void setMarginLeft(View view, int left){
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, p.topMargin, p.rightMargin, p.bottomMargin);
            view.requestLayout();
        }
    }

    public static void setMarginRight(View view, int right){
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(p.leftMargin, p.topMargin, right, p.bottomMargin);
            view.requestLayout();
        }
    }

    public static void setMarginBottom(View view, int bottom){
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(p.leftMargin, p.topMargin, p.rightMargin, bottom);
            view.requestLayout();
        }
    }

    public static void setMarginTop(View view, int top){
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(p.leftMargin, top, p.rightMargin, p.bottomMargin);
            view.requestLayout();
        }
    }

    public static void setPadding(View view, int left, int top, int right, int bottom){
        view.setPadding(left, top, right, bottom);
    }

    public static void setPaddingLeft(View view, int left){
        setPadding(view, left, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    }

    public static void setPaddingRight(View view, int right){
        setPadding(view, view.getPaddingLeft(), view.getPaddingTop(), right, view.getPaddingBottom());
    }

    public static void setPaddingTop(View view, int top){
        setPadding(view, view.getPaddingTop(), top, view.getPaddingRight(), view.getPaddingBottom());
    }

    public static void setPaddingBottom(View view, int left){
        setPadding(view, left, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    }

    public static int getViewHeight(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(Screen.getWidth(view.getContext()), View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
        return view.getMeasuredHeight();
    }

    public static int getViewWidth(View view) {
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(Screen.getHeight(view.getContext()), View.MeasureSpec.AT_MOST);
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
        return view.getMeasuredWidth();
    }

    public static int getMarginLeft(View view){
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            return p.leftMargin;
        }
        return 0;
    }

    public static int getMarginRight(View view){
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            return p.rightMargin;
        }
        return 0;
    }

    public static int getMarginTop(View view){
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            return p.topMargin;
        }
        return 0;
    }

    public static int getMarginBottom(View view){
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            return p.bottomMargin;
        }
        return 0;
    }

    public static Bitmap getViewBitmap(View view){
        Bitmap b = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        view.draw(c);
        return b;
    }
}
