package com.fivesoft.smartutil;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

@SuppressWarnings("unused")
public class Animator {

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

    public static void shakeAnimation(View view){
        ObjectAnimator
                .ofFloat(view, "translationX", 0, 25, -25, 25, -25,15, -15, 6, -6, 0)
                .setDuration(400)
                .start();
    }

    public static void simpleShowAnimation(View view, boolean changeVisibility) {

        if(changeVisibility)
            view.setVisibility(View.VISIBLE);
        int viewHeight = getViewHeight(view);
        int viewWidth = getViewWidth(view);

        ValueAnimator animator1 = ValueAnimator.ofInt(0, viewHeight);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.addUpdateListener(animation -> ViewUtil.setViewHeight(view, (int) animation.getAnimatedValue()));

        ValueAnimator animator2 = ValueAnimator.ofInt(0, viewWidth);
        animator2.setDuration(300);
        animator2.setInterpolator(new AccelerateDecelerateInterpolator());
        animator2.addUpdateListener(animation -> ViewUtil.setViewWidth(view, (int) animation.getAnimatedValue()));

        animator1.start();
        animator2.start();
    }

    public static void simpleHideAnimation(View view, boolean changeVisibility) {

        if(changeVisibility)
            view.setVisibility(View.VISIBLE);
        int viewHeight = getViewHeight(view);
        int viewWidth = getViewWidth(view);

        ValueAnimator animator1 = ValueAnimator.ofInt(viewHeight, 0);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.addUpdateListener(animation -> {
            ViewUtil.setViewHeight(view, (int) animation.getAnimatedValue());
            if ((int) animation.getAnimatedValue() == 0 && changeVisibility) {
                view.setVisibility(View.GONE);
            }
        });

        ValueAnimator animator2 = ValueAnimator.ofInt(viewWidth, 0);
        animator2.setDuration(300);
        animator2.setInterpolator(new AccelerateDecelerateInterpolator());
        animator2.addUpdateListener(animation -> ViewUtil.setViewWidth(view, (int) animation.getAnimatedValue()));

        animator1.start();
        animator2.start();
    }

    public static void showUsingOnlyHeight(View view, boolean changeVisibility) {
        if(changeVisibility)
            view.setVisibility(View.VISIBLE);
        int viewHeight = getViewHeight(view);

        ValueAnimator animator1 = ValueAnimator.ofInt(0, viewHeight);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.addUpdateListener(animation -> ViewUtil.setViewHeight(view, (int) animation.getAnimatedValue()));
        animator1.start();
    }

    public static void hideUsingOnlyHeight(View view, boolean changeVisibility) {
        int viewHeight = getViewHeight(view);

        ValueAnimator animator1 = ValueAnimator.ofInt(viewHeight, 0);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.addUpdateListener(animation -> {
            ViewUtil.setViewHeight(view, (int) animation.getAnimatedValue());
            if ((int) animation.getAnimatedValue() == 0 && changeVisibility) {
                view.setVisibility(View.GONE);
            }
        });

        animator1.start();
    }

}
