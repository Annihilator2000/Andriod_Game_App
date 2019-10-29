package com.example.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class DonutView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;
    private AntManager manager;
    Donut donutNew;
    private boolean touch = false;
    private Ant removedAnt;
    private int antSpeed = 10;
    private Paint scorePaint = new Paint();
    private int score;
    private Paint livesPaint = new Paint();
    private int lives = 10;



    public DonutView(Context context) {
        super(context);
        this.setFocusable(true);
        this.getHolder().addCallback(this);
        score = 0;
    }

    public void update() {
        Bitmap antBitmap1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.ant);
        if (touch){
            manager.ants.remove(removedAnt);
            if (manager.ants.size() < 2) {
                antSpeed += 2;
                manager.createAnts(antBitmap1,this, antSpeed);
            }
            System.out.println(manager.ants.size());
        } else {
          manager.update();
        }

        if (lives == 0) {

            gameThread.setRunning(false);

            Intent newGameintent = new Intent(getContext(), GameOverActivity.class);

            newGameintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            newGameintent.putExtra("Score", score);

            getContext().startActivity(newGameintent);

        }
    }

    public void decreaseLife() {
        if (lives - 1 >= 0){
            lives--;
        }

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(-3355444);
        scorePaint.setColor(-16776961);
        scorePaint.setTextSize(80);
        scorePaint.setUnderlineText(true);
        canvas.drawText("Score : " + score , 20, 60, scorePaint);
        livesPaint.setColor(-65536);
        livesPaint.setTextSize(70);
        canvas.drawText("Lives Left : " + lives , 500, 60, livesPaint);
        donutNew.draw(canvas);
        manager.draw(canvas);

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Bitmap antBitmap1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.ant);


        Bitmap donutBitmap1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.donut);
        donutNew = new Donut(donutBitmap1, this.getWidth()/2 - donutBitmap1.getWidth()/2, 10, this );

        manager = new AntManager();
        manager.createAnts(antBitmap1, this, antSpeed);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            double buttonX = event.getX();
            double buttonY = event.getY();

            for (int i = 0; i < manager.ants.size(); i++){
                if (manager.ants.get(i).getX() < buttonX && buttonX< manager.ants.get(i).getX() + manager.ants.get(i).getWidth()
                && manager.ants.get(i).getY() < buttonY && buttonY< manager.ants.get(i).getY() + manager.ants.get(i).getHeight()){
                    touch = true;
                    removedAnt = manager.ants.get(i);
                    score +=  10;
                    return true;

                }
            }
            touch = false;
            return false;
        } else {
            touch = false;
            return false;
        }
    }

    public int getScore(){
        return score;
    }

    public int getLives(){
        return lives;
    }
}
