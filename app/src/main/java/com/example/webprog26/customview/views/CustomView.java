package com.example.webprog26.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

import com.example.webprog26.customview.interfaces.OnColorChangeCallback;
import com.example.webprog26.customview.makers.PathCreator;
import com.example.webprog26.customview.threads.ViewColorChanger;

/**
 * Created by webprog26 on 12.12.2016.
 */

public abstract class CustomView extends View implements OnColorChangeCallback{

    private static final int DEFAULT_WIDTH = 100;
    private static final int DEFAULT_HEIGHT = 100;

    private static final String SUPER_STATE = "super_state";
    private static final String CURRENT_COLOR = "current_color";

    private int shapeWidth = DEFAULT_WIDTH;
    private int shapeHeight = DEFAULT_HEIGHT;

    private int mBackgroundColor;
    private Paint mPaint;
    private ViewColorChanger mViewColorChanger;

    @SuppressWarnings("deprecation")
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPaint = initPaint();
        this.mViewColorChanger = createViewColorChanger();
        this.mBackgroundColor = getResources().getColor(android.R.color.black);
        initAttrs(attrs);
    }

    /**
     * Inits {@link CustomView} with XML-parameters
     * @param attrs {@link AttributeSet}
     */
    protected abstract void initAttrs(AttributeSet attrs);

    /**
     * Creates new {@link Paint} instance
     * @return Paint
     */
    protected abstract Paint initPaint();

    /**
     * Creates new {@link ViewColorChanger} which is actually a new {@link Thread}
     * that gives {@link CustomView} new random background color via existing interval in seconds
     * @return ViewColorChanger
     */
    protected abstract ViewColorChanger createViewColorChanger();

    /**
     * Should return one of {@link PathCreator} constants to draw any of possible shapes
     * @return String
     */
    protected abstract String setShapeType();

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mViewColorChanger.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(mViewColorChanger != null){
            if(mViewColorChanger.isAlive()){
                mViewColorChanger.setShouldStop(!mViewColorChanger.isShouldStop());
                mViewColorChanger.interrupt();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mBackgroundColor);
        try{
            canvas.drawPath(new PathCreator().getPath(setShapeType(), shapeWidth, shapeHeight), mPaint);
        } catch (Exception e){
            e.getMessage();
        }
    }

    /**
     * Returns current background color of {@link CustomView}
     * @return int
     */
    public synchronized int getBackgroundColor() {
        return mBackgroundColor;
    }

    /**
     * Sets new background color to {@link CustomView} and calls it's parent {@link android.view.ViewGroup}
     * to redraw this {@link CustomView}
     * @param backgroundColor int
     */
    public synchronized void setBackgroundColor(int backgroundColor) {
        this.mBackgroundColor = backgroundColor;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int contentWidth = shapeWidth;

        int minWidth = contentWidth + getPaddingLeft() + getPaddingRight();
        int w = resolveSizeAndState(minWidth, widthMeasureSpec, 0);

        int minHeight = shapeHeight + getPaddingBottom() + getPaddingTop();
        int h = resolveSizeAndState(minHeight, heightMeasureSpec, 0);
        setMeasuredDimension(w, h);
    }

    @Override
    public void onColorChange(final int color) {
        this.post(new Runnable() {
            @Override
            public void run() {
                setBackgroundColor(color);
            }
        });
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(SUPER_STATE, super.onSaveInstanceState());
        bundle.putInt(CURRENT_COLOR, getBackgroundColor());

        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if(state instanceof Bundle){
            Bundle bundle = (Bundle) state;
            setBackgroundColor(bundle.getInt(CURRENT_COLOR));
            state = bundle.getParcelable(SUPER_STATE);
        }
        super.onRestoreInstanceState(state);
    }
}
