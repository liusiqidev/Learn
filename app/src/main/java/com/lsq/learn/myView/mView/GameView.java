package com.lsq.learn.myView.mView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by lsq on 2017/8/15.
 */

public class GameView extends View {
    int miCount=0;
    int y=0;
    public GameView(Context context) {
        super(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (miCount<100){
            miCount++;
        }else {
            miCount=0;
        }
        Paint paint=new Paint();
        switch (miCount%4){
            case 0:
                paint.setColor(Color.BLUE);
                break;
            case 1:
                paint.setColor(Color.RED);
                break;
            case 2:
                paint.setColor(Color.GRAY);
                break;
            case 3:
                paint.setColor(Color.GREEN);
                break;
            default:
                paint.setColor(Color.WHITE);
                break;
        }
        canvas.drawRect((320-80)/2,y,(320-80)/2+80,y+40,paint);
    }
}
