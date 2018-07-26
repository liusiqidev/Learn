package com.lsq.learn.myView.mView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.lsq.learn.myView.utils.DrawUtil;

/**
 * Created by lsq on 2018/7/18.
 */

public class Job07View extends View {
    private Bitmap bitmap = DrawUtil.getAvatar(getResources(), (int) DrawUtil.dptoPixel(150));
    private int side=bitmap.getHeight()/2;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int OFFSET= (int) DrawUtil.dptoPixel(30);
    private Camera camera = new Camera();
    private Matrix cameraMatrix=new Matrix();
    private Matrix matrix=new Matrix();
    private static final String TAG = "Job07View";

    Rect rect = new Rect(50, 50, 150, 250);
    public Job07View(Context context) {
        super(context);
    }

    public Job07View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Job07View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setColor(Color.parseColor("#76FF03"));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(DrawUtil.dptoPixel(1));

//        camera.rotateX(20);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        unit3d
        rect = new Rect(50, 0, 150, getHeight());
        matrix.setSinCos(1, 0, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        canvas.concat(matrix);
//        canvas.drawBitmap(bitmap, matrix, paint);
        canvas.drawRect(rect, paint);
    }
}
