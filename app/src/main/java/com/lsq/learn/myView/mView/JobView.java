package com.lsq.learn.myView.mView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.lsq.learn.R;
import com.lsq.learn.myView.utils.DrawUtil;

/**
 * Created by lsq on 18-7-17.
 */

public class JobView extends View {
    private static float PADDING = DrawUtil.dptoPixel(10);
    private Paint headpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint textpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint dasePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int HEAD_WIDTH = (int) DrawUtil.dptoPixel(150);
    private int HEAD_HEIGHT = (int) DrawUtil.dptoPixel(50);
    private PorterDuffXfermode xfermode;
    private String text = "Curabitur mollis dui a elit varius ullamcorper. Pellentesque vestibulum id purus quis mattis. Vestibulum lorem mauris, tempus vel blandit eu, eleifend eu nisi. Maecenas accumsan nisi nec mi luctus blandit. Ut vitae condimentum arcu. Quisque in nisi lacus. Vestibulum pretium, metus ut consectetur aliquam, sapien turpis faucibus libero, eget vehicula enim erat vel mauris. In faucibus condimentum ipsum ac facilisis. Cras molestie gravida tortor vel vulputate. Curabitur at cursus orci, at pulvinar ligula. Maecenas ornare fringilla elit, vitae laoreet augue sagittis vitae. Aenean massa magna, aliquet in fermentum at, egestas et neque. Fusce aliquam, ligula eget pharetra varius, dui justo molestie mauris, vitae sagittis velit urna vitae justo. Nam sagittis pharetra aliquet. ";
    private Paint.FontMetrics textFountMetrics;

    private float[] measuredTextWidth = new float[1];

    private static final String TAG = "JobView";

    public JobView(Context context) {
        super(context);
    }

    public JobView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public JobView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText(canvas);
        drawHead(canvas);
//        drawDash(canvas);

    }

    {
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        textFountMetrics = new Paint.FontMetrics();
    }

    private void drawHead(Canvas canvase) {
        //绘制圆头像
        //底色
        canvase.drawCircle(getWidth() / 2, HEAD_HEIGHT + HEAD_WIDTH / 2, HEAD_WIDTH / 2, headpaint);
        //圆头像
        int save = canvase.saveLayer((getWidth() - HEAD_WIDTH) / 2, HEAD_HEIGHT, (getWidth() + HEAD_WIDTH) / 2, +HEAD_HEIGHT + HEAD_WIDTH, headpaint);

        canvase.drawCircle(getWidth() / 2, HEAD_HEIGHT + HEAD_WIDTH / 2, HEAD_WIDTH / 2 - PADDING, headpaint);

        headpaint.setXfermode(xfermode);

        canvase.drawBitmap(getBitmap((int) (HEAD_WIDTH - 2 * PADDING)), (getWidth() - HEAD_WIDTH) / 2 + PADDING, HEAD_HEIGHT + PADDING, headpaint);
        headpaint.setXfermode(null);
        canvase.restoreToCount(save);
    }

    private void drawText(Canvas canvase) {
        //绘制文字
        textpaint.setTextSize(DrawUtil.dptoPixel(15));
        textpaint.setColor(Color.parseColor("#5e35b1"));
        textpaint.getFontMetrics(textFountMetrics);

        canvase.drawText(text, 0, -textFountMetrics.top, textpaint);
        float y = -textFountMetrics.top;
        int count = 0;
        int start = 0;
        int totalWidth = 0;
        if (text.length() > 0) {
            do {
                Log.e(TAG, "drawText: " + (y + textFountMetrics.top) + "  " + (y + textFountMetrics.bottom) + "  " + HEAD_HEIGHT + "  " + (HEAD_WIDTH + HEAD_HEIGHT));
                Log.e(TAG, "drawText: " + ((y + textFountMetrics.top) > HEAD_HEIGHT));
                Log.e(TAG, "drawText: " + ((y + textFountMetrics.bottom) < (HEAD_WIDTH + HEAD_HEIGHT)));

                if ((y + textFountMetrics.bottom) < HEAD_HEIGHT || (y + textFountMetrics.top) > (HEAD_WIDTH + HEAD_HEIGHT)) {
                    count = textpaint.breakText(text, start, text.length(), true, getWidth(), measuredTextWidth);
                    canvase.drawText(text, start, start + count, 0, y, textpaint);
                    start += count;
                } else {
                    //画左边
                    if (y - (HEAD_HEIGHT + HEAD_WIDTH / 2) < HEAD_HEIGHT + HEAD_WIDTH / 2 && y - (HEAD_HEIGHT + HEAD_WIDTH / 2) < 0) {
                        //左上
                        totalWidth = (int) (((getWidth() - HEAD_WIDTH) / 2 + (HEAD_WIDTH / 2 - Math.sqrt((HEAD_WIDTH / 2) * (HEAD_WIDTH / 2) - (y+textFountMetrics.bottom - (HEAD_HEIGHT + HEAD_WIDTH / 2)) * (y+textFountMetrics.bottom - (HEAD_HEIGHT + HEAD_WIDTH / 2))))) - DrawUtil.dptoPixel(1));
                    } else if (y - (HEAD_HEIGHT + HEAD_WIDTH / 2) < HEAD_HEIGHT + HEAD_WIDTH / 2 && y - (HEAD_HEIGHT + HEAD_WIDTH / 2) > 0) {
                        //左下
                        totalWidth = (int) (((getWidth() - HEAD_WIDTH) / 2 + (HEAD_WIDTH / 2 - Math.sqrt((HEAD_WIDTH / 2) * (HEAD_WIDTH / 2) - (y+textFountMetrics.top - (HEAD_HEIGHT + HEAD_WIDTH / 2)) * (y+textFountMetrics.top - (HEAD_HEIGHT + HEAD_WIDTH / 2))))) - DrawUtil.dptoPixel(1));
                    } else {
                        //左中
                        totalWidth = (getWidth() - HEAD_WIDTH) / 2;
                    }
                    count = textpaint.breakText(text, start, text.length(), true, totalWidth, measuredTextWidth);
                    canvase.drawText(text, start, start + count, 0, y, textpaint);
                    start += count;
                    //画右边
                    if (start < text.length()) {
                        if (y - (HEAD_HEIGHT + HEAD_WIDTH / 2) < HEAD_HEIGHT + HEAD_WIDTH / 2 && y - (HEAD_HEIGHT + HEAD_WIDTH / 2) < 0) {
                            //右上
                            totalWidth = (int) (((getWidth() - HEAD_WIDTH) / 2 + (HEAD_WIDTH / 2 - Math.sqrt((HEAD_WIDTH / 2) * (HEAD_WIDTH / 2) - (y+textFountMetrics.bottom - (HEAD_HEIGHT + HEAD_WIDTH / 2)) * (y+textFountMetrics.bottom - (HEAD_HEIGHT + HEAD_WIDTH / 2))))) - DrawUtil.dptoPixel(1));
                        } else if (y - (HEAD_HEIGHT + HEAD_WIDTH / 2) < HEAD_HEIGHT + HEAD_WIDTH / 2 && y - (HEAD_HEIGHT + HEAD_WIDTH / 2) > 0) {
                            //右下
                            totalWidth = (int) (((getWidth() - HEAD_WIDTH) / 2 + (HEAD_WIDTH / 2 - Math.sqrt((HEAD_WIDTH / 2) * (HEAD_WIDTH / 2) - (y+textFountMetrics.top - (HEAD_HEIGHT + HEAD_WIDTH / 2)) * (y+textFountMetrics.top - (HEAD_HEIGHT + HEAD_WIDTH / 2))))) - DrawUtil.dptoPixel(1));
                        } else {
                            //右中
                            totalWidth = (getWidth() - HEAD_WIDTH) / 2;
                        }

                        count = textpaint.breakText(text, start, text.length(), true, totalWidth, measuredTextWidth);
                        canvase.drawText(text, start, start + count, getWidth()-totalWidth, y, textpaint);
                        start += count;
                    }
                }
                y += textpaint.getFontSpacing();
            }
            while (count > 0 && start < text.length());
        }

    }

    private void drawDash(Canvas canvase) {
        //绘制仪表盘
    }

    private Bitmap getBitmap(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.head, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.drawable.head, options);
    }

}
