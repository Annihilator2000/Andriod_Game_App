package com.example.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;

import java.util.Date;

public class GameView extends View {
  // This will be the custom view class for Game3
  TrueBlue tb; // TrueBlue class
  Tower cn; // Tower class
  Handler handler;
  Runnable runnable;
  final int delayNum = 30;
  Bitmap background;
  Display display;
  Point point;
  int screenWidth, screenHeight; // Height and Width of device.
  Rect rect;
  /** The score paint to display the score. */
  private Paint scorePaint = new Paint();
  /** The score variable that keeps track of current score */
  private int score = 0;

  private float currTime;
  long start;

  public GameView(Context context) {
    super(context);
    handler = new Handler();
    runnable =
        new Runnable() {
          @Override
          public void run() {
            invalidate(); // This should call onDraw.
          }
        };
    background = BitmapFactory.decodeResource(getResources(), R.drawable.game3_background);
    display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
    point = new Point();
    display.getSize(point);
    screenWidth = point.x;
    screenHeight = point.y;
    rect = new Rect(0, 0, screenWidth, screenHeight);
    cn = new Tower(this); // created CN Tower
    cn.moveTower(); // moves CN Tower
    tb = new TrueBlue(this); // creates TrueBlue
    Date startDate = new Date();
    start = startDate.getTime();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    // This is where we will draw our view for Game3.
    canvas.drawBitmap(background, null, rect, null);

    // true blue falls
    if (tb.getState()) {
      tb.drawTBRect(canvas);
      //animate tb
      tb.animateTB();
      //cause tb to fall
      tb.tbFall();
      //draw the towers
      cn.drawTower(canvas); // Endless number of CN Tower is created.
    }
    // displays true blue in the center
    tb.drawTB(canvas);

    handler.postDelayed(runnable, delayNum);

  }

  // When you tap the screen
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();
    tb.tbOnTouch(action);
    return true;
  }

  public int getScreenWidth() {
    return screenWidth;
  }

  public int getScreenHeight() {
    return screenHeight;
  }

  public void gameOver(){
    tb.setState();
    Date finalDate = new Date();
    currTime = (finalDate.getTime() - start) / 1000F;
    Intent intent = new Intent(getContext(), Game3OverActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

    //intent.putExtra("Score", score);

    intent.putExtra("Time", currTime);
    getContext().startActivity(intent);
  }

}
