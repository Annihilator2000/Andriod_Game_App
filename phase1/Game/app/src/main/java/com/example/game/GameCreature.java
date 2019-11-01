package com.example.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
/** Super class for all creatures and items created.*/
public class GameCreature {

  protected Bitmap image;
  private final int width;
  private final int height;
  private int x;
  private int y;

  public GameCreature(Bitmap image, int x, int y) {
    this.image = image;
    this.x = x;
    this.y = y;
    this.width = image.getWidth();
    this.height = image.getHeight();
  }
  /** Getter for width of creature.*/
  public int getWidth() {
    return width;
  }
  /** Getter for height of creature.*/
  public int getHeight() {
    return height;
  }
  /** Setter for X coordinate of creature.*/
  public void setX(int x) {
    this.x = x;
  }
  /** Setter for Y coordinate of creature.*/
  public void setY(int y) {
    this.y = y;
  }
  /** Getter for X coordinate of creature.*/
  public int getX() {
    return x;
  }
  /** Getter for Y coordinate of creature.*/
  public int getY() {
    return y;
  }
}
