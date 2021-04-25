package com.fivesoft.smartutil;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import static com.fivesoft.smartutil.ViewUtil.getViewHeight;
import static com.fivesoft.smartutil.ViewUtil.getViewWidth;
import static com.fivesoft.smartutil.ViewUtil.setViewHeight;
import static com.fivesoft.smartutil.ViewUtil.setViewWidth;

@SuppressWarnings("unused")
public class Animator {

    public static void shakeAnimation(View view){
        ObjectAnimator
                .ofFloat(view, "translationX", 0, 25, -25, 25, -25,15, -15, 6, -6, 0)
                .setDuration(400)
                .start();
    }

    public static void flipAnimation(ImageView icon, int destIcon){
        RotateAnimation rotate1 = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate1.setDuration(150);
        rotate1.setInterpolator(new AccelerateInterpolator());
        rotate1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                RotateAnimation rotate2 = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate2.setDuration(150);
                rotate2.setInterpolator(new DecelerateInterpolator());
                icon.setImageResource(destIcon);
                icon.setTag(destIcon);
                icon.startAnimation(rotate2);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        icon.startAnimation(rotate1);
    }

    public static void widthAnimation(View view, int targetWidth){
        int viewWidth = view.getWidth();

        ValueAnimator animator1 = ValueAnimator.ofInt(viewWidth, targetWidth);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.addUpdateListener(animation -> setViewWidth(view, (int) animation.getAnimatedValue()));
        animator1.start();
    }

    public static void heightAnimation(View view, int targetHeight){
        int viewHeight = view.getHeight();

        ValueAnimator animator1 = ValueAnimator.ofInt(viewHeight, targetHeight);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.addUpdateListener(animation -> setViewHeight(view, (int) animation.getAnimatedValue()));
        animator1.start();
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
        animator2.addUpdateListener(animation -> setViewWidth(view, (int) animation.getAnimatedValue()));

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
        animator2.addUpdateListener(animation -> setViewWidth(view, (int) animation.getAnimatedValue()));

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
