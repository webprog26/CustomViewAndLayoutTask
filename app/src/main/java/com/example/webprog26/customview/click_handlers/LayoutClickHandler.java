package com.example.webprog26.customview.click_handlers;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import java.util.Map;

/**
 * Created by webprog26 on 12.12.2016.
 */

public class LayoutClickHandler implements View.OnClickListener, View.OnLongClickListener{

    /**
     * Handles onClick & OnLongClick events
     */

    private static final long ANIMATION_DURATION = 4000;
    private static final long ROTATION_ANIMATION_DURATION = 8000;
    private static final float ROTATION_ANIMATION_DEGREES = 720;
    private static final String Y_TRANSLATION = "y";
    private static final String ROTATION = "rotation";

    private ViewGroup mViewGroup;
    private Map<Integer, Float> mYCoordinatesMap;
    private boolean isDownward;

    public LayoutClickHandler(ViewGroup mViewGroup, Map<Integer, Float> mYCoordinatesMap, boolean isDownward) {
        this.mViewGroup = mViewGroup;
        this.mYCoordinatesMap = mYCoordinatesMap;
        this.isDownward = isDownward;
    }

    @Override
    public void onClick(View view) {
        if(isDownward){
            return;
        }
        ObjectAnimator translationAnimator;
        int layoutHeight = mViewGroup.getHeight();
        int layoutPaddingBottom = mViewGroup.getPaddingBottom();

        for(int i = 0; i < mViewGroup.getChildCount(); i++){
            View viewToAnimate = mViewGroup.getChildAt(i);
            int viewToAnimateHeight = viewToAnimate.getHeight();
            float viewToAnimateY = viewToAnimate.getY();
            mYCoordinatesMap.put(viewToAnimate.getId(), viewToAnimateY);
            translationAnimator = ObjectAnimator.ofFloat(viewToAnimate, Y_TRANSLATION, viewToAnimateY, layoutHeight - (viewToAnimateHeight + layoutPaddingBottom));
            translationAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            translationAnimator.setDuration(ANIMATION_DURATION);

            ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(viewToAnimate, ROTATION, viewToAnimate.getX(), ROTATION_ANIMATION_DEGREES);
            rotationAnimator.setDuration(ROTATION_ANIMATION_DURATION);
            rotationAnimator.setInterpolator(new OvershootInterpolator());
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(translationAnimator).with(rotationAnimator);
            animatorSet.start();
        }
        isDownward = true;
    }

    @Override
    public boolean onLongClick(View view) {
        if(!isDownward){
            return false;
        }
        ObjectAnimator animator;
        for(int i = 0; i < mViewGroup.getChildCount(); i++){
            View viewToAnimate = mViewGroup.getChildAt(i);
            animator = ObjectAnimator.ofFloat(viewToAnimate, Y_TRANSLATION, viewToAnimate.getY(), mYCoordinatesMap.get(viewToAnimate.getId()));
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(ANIMATION_DURATION);
            animator.start();
        }
        isDownward = false;
        return true;
    }
}
