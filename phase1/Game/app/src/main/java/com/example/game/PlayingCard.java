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

  PlayingCard(ImageView iv, int card_num) {
    this.cardNum = card_num;
    this.imageview = iv;
  }
  /** Set an on click listener on the image view of the card */
  // https://developer.android.com/reference/android/view/View.OnClickListener used to learn.
  void setOnClick() {
    this.imageview.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            // Set the image of card to the image view
            imageview.setImageResource(cardNum);
          }
        });
  }
  /** Set the front view of the card to the image */
  void setImage() {
    this.imageview.setImageResource(cardNum);
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