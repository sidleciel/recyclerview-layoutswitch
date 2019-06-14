package com.sc.demo.layoutswitch;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ViewSizeChangeAnimation extends Animation implements Animation.AnimationListener {
    int  initialHeight;
    int  targetHeight;
    int  initialWidth;
    int  targetWidth;
    View view;

    public ViewSizeChangeAnimation(View view, int targetWidth, int targetHeight) {
        this.view = view;
        this.targetHeight = targetHeight;
        this.targetWidth = targetWidth;
        setAnimationListener(this);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        view.getLayoutParams().height = initialHeight + (int) ((targetHeight - initialHeight) * interpolatedTime);
        view.getLayoutParams().width = initialWidth + (int) ((targetWidth - initialWidth) * interpolatedTime);
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        this.initialHeight = height;
        this.initialWidth = width;
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }

    public void resetInitial() {
        view.getLayoutParams().height = initialHeight;
        view.getLayoutParams().width = initialWidth;
        view.requestLayout();
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        resetInitial();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
