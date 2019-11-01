package com.example.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/** GameThread class which manages threading, updating and calling draw. */
public class GameThread extends Thread {

  /** The canvas container. */
  private SurfaceHolder surfaceHolder;
  /** Where the ants,dounut and the game surface is drawn. */
  private DonutView gameView;
  /** The canvas on which to draw the fish tank. */
  private static Canvas canvas;
  /** Whether the thread is running. */
  private boolean running;

  /**
   * Construct the thread.
   *
   * @param surfaceHolder the canvas container.
   * @param gameView where the ants, donut, score etc are drawn.
   */
  GameThread(SurfaceHolder surfaceHolder, DonutView gameView) {
    super();
    this.surfaceHolder = surfaceHolder;
    this.gameView = gameView;
  }

  /**
   * The run method of the thread.
   *
   * <p>While the thread is running it continuously update the gameView and draws the updated one.
   */
  @Override
  public void run() {
    while (running) {
      canvas = null;
      try {
        canvas = this.surfaceHolder.lockCanvas();
        synchronized (surfaceHolder) {
          this.gameView.update();
          this.gameView.draw(canvas);
          System.out.print("thread running");
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (canvas != null) {
          try {
            surfaceHolder.unlockCanvasAndPost(canvas);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  void setRunning(boolean running) {
    this.running = running;
  }
}
