package com.lsq.learn.myView.utils;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * 绘制的工具类
 * Created by lsq on 18-7-17.
 */

public class DrawUtil {
    public static float dptoPixel (int dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp, Resources.getSystem().getDisplayMetrics());
    }
}
