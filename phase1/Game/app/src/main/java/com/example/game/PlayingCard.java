package com.example.game;

// https://developer.android.com/reference/android/widget/ImageView and
// https://developer.android.com/reference/android/view/View used to learn.
import android.view.View;
import android.widget.ImageView;

/** Playing card that needs to be matched with the other card. */
public class PlayingCard {
  /** The Image of the PlayingCard view on the screen */
  private ImageView imageview;


  /** The actual image stored in an integer */
  private int cardNum;

  PlayingCard(int card_num, ImageView iv) {
    this.cardNum = card_num;
    this.imageview = iv;
  }
  /** get the ImageView of the card */
  ImageView getImageview() {
    return this.imageview;
  }

  /** get the number of the card */
  int getCardNum() {
    return this.cardNum;
  }

  /** Set the front view of the card to the image */
  void setImage(int actualImage) {
    this.imageview.setImageResource(actualImage);
  }

  /** Set whether the card will be visible on screen or not */
  void setVisibility() {
    this.imageview.setVisibility(View.INVISIBLE);
  }

  /** get the visibility of the playing card */
  int getVisibility() {
    return this.imageview.getVisibility();
  }

  /** Set whether the view of the card; front or back view. */
  void set_enable(boolean bool) {
    this.imageview.setEnabled(bool);
  }
}
