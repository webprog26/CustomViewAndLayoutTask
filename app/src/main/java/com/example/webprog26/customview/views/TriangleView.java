package com.example.webprog26.customview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.example.webprog26.customview.R;
import com.example.webprog26.customview.makers.PathCreator;
import com.example.webprog26.customview.threads.ViewColorChanger;

/**
 * Created by webprog26 on 12.12.2016.
 */

public class TriangleView extends CustomView {

    public TriangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.TriangleView, 0, 0);

        try {
            super.setBackgroundColor(typedArray.getInt(R.styleable.TriangleView_backgroundColor, Color.GREEN));
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    protected Paint initPaint() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    @Override
    protected ViewColorChanger createViewColorChanger() {
        return new ViewColorChanger(this);
    }

    @Override
    protected String setShapeType() {
        return PathCreator.TRIANGLE;
    }
}
