package com.fivesoft.smartutil;

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

}
