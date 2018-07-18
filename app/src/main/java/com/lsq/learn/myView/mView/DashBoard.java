package com.lsq.learn.myView.mView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.lsq.learn.myView.utils.DrawUtil;

/**
 * 仪表盘
 * Created by lsq on 18-7-17.
 */

public class DashBoard extends View {
    //边距
    private static float PADDING = DrawUtil.dptoPixel(5);
    //


    public DashBoard(Context context) {
        super(context);
    }

    public DashBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DashBoard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
