package com.example.webprog26.customview.viewgroups;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.webprog26.customview.click_handlers.LayoutClickHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by webprog26 on 12.12.2016.
 */

public abstract class CustomRelativeLayout extends RelativeLayout {

    private Map<Integer, Float> mYCoordinatesMap;
    private boolean isDownward = false;

    public CustomRelativeLayout(Context context) {
        super(context);
        init();
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * Creates new {@link Map} to store Y-coordinates of {@link com.example.webprog26.customview.views.CustomView}
     * objects and sets OnClick & OnLongClick listeners
     */
    protected void init(){
        mYCoordinatesMap = new HashMap<>();
        LayoutClickHandler layoutClickHandler = new LayoutClickHandler(this, mYCoordinatesMap, isDownward);
        setOnClickListener(layoutClickHandler);
        setOnLongClickListener(layoutClickHandler);
    }
}
