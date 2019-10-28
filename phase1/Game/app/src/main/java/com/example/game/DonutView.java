package com.example.game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class DonutView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;
    private Ant ant;
    private Ant ant2;
    private Donut donut;

    public DonutView(Context context) {
        super(context);
        this.setFocusable(true);
        this.getHolder().addCallback(this);
    }

    public void update() {
        ant.update();
        ant2.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(-1);
        ant.draw(canvas);
        ant2.draw(canvas);
        donut.draw(canvas);


    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Bitmap antBitmap1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.ant);
        this.ant = new Ant(antBitmap1,400,400,this);
//        your addition
        Bitmap donutBitmap1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.donut);
        Bitmap ant2Bitmap1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.ant);
        this.ant2 = new Ant(ant2Bitmap1, 600, 400, this);

        this.donut = new Donut(donutBitmap1, 300, 400, this);
        gameThread = new GameThread(this.getHolder(),this);
        gameThread.setRunning(true);
        gameThread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while(retry){
            try{
                gameThread.setRunning(false);
                gameThread.join();
            } catch (Exception e) { e.printStackTrace(); }
            retry = false;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

}
