package com.example.game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler; 

public class GameView extends View {
  //This will be the custom view class for Game3

  Handler handler;
  Runnable runnable;
  final int delayNum = 30;
  Bitmap background;
  Display display;
  Point point;
  int screenWidth, screenHeight; //Height and Width of device.
  Rect rect;
  Bitmap[] tb;  //all the frames of trueblue
  int tbFrame = 0; // the current frame for true blue
  int velocity = 0;
  int gravity = 3;
  int tbX, tbY; // the x and y position of true blue
  public GameView(Context context) {
    super(context);
    handler = new Handler();
    runnable = new Runnable() {
      @Override
      public void run() {
        invalidate(); //This should call onDraw.
      }
    };
    background = BitmapFactory.decodeResource(getResources(), R.drawable.game3_background);
    display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
    point = new Point();
    display.getSize(point);
    screenWidth = point.x;
    screenHeight = point.y;
    rect = new Rect(0, 0, screenWidth, screenHeight);
    tb = new Bitmap[3];
    tb[0] = BitmapFactory.decodeResource(getResources(), R.drawable.trueblue_frame_0);
    tb[1] = BitmapFactory.decodeResource(getResources(), R.drawable.trueblue_frame_1);
    tb[2] = BitmapFactory.decodeResource(getResources(), R.drawable.trueblue_frame_2);
    tbX = screenWidth/2 - tb[0].getWidth()/2;
    tbY = screenHeight/2 - tb[0].getHeight()/2;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //This is where we will draw our view for Game3.
    canvas.drawBitmap(background, null, rect, null);

    // animate true blue
    if(tbFrame == 0){
      tbFrame = 1;
    }
    else if(tbFrame == 1){
      tbFrame = 2;
    }
    else{
      tbFrame = 0;
    }
    // true blue falls

    if(tbY < screenHeight - tb[0].getHeight()){
      velocity += gravity;
      tbY += velocity;
    }

    //displays true blue in the center
    canvas.drawBitmap(tb[tbFrame], tbX,tbY,null);
    handler.postDelayed(runnable, delayNum);
  }
  // When you tap the screen
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();
    if (action == MotionEvent.ACTION_DOWN){  // if the Tap is detected on the screen
      velocity = -30; // increase true blue's upward velocity
    }
    return true;
  }
}