package com.example.final_game.Memorize;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_game.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressLint("ViewConstructor")
public class MemoryView extends View {
  /** Array List of all cards displayed on the screen */
  List<PlayingCard> cardArray;
  /** Array List of all card images displayed on the screen */
  List<int[]> imageArray;
  /** Player of the Game */
  MemorizePlayer player;
  /** First and Second selected indexes of the cardArray */
  int firstSelect, secondSelect;
  /** Denotes which number of the card selected */
  int firstCard, secondCard;
  /** Denotes which card is being selected (Whether its the first card selection or second) */
  int cardNum = 1;
  /** The back image of all the cards */
  int cardBackView;
  String theme;

  int level;
  public static final String SHARED_PREFS = "sharedPrefs";
  public static final String POINTS1 = "points1";
  public static final String TIME1 = "time1";
  public static final String POINTS2 = "points2";
  public static final String TIME2 = "time2";
  public static final String MOVES_LEFT2 = "movesLeft2";
  public static final String CARDS_LEFT2 = "cardsLeft2";
  public static final String POINTS3 = "points3";
  public static final String TIME3 = "time3";
  public static final String MOVES_LEFT3 = "movesLeft3";
  public static final String CARDS_LEFT3 = "cardsLeft3";
  SharedPreferences sharedPreferences;
  /**
   * Constructor initializes a new player, a layout of playable, shuffled cards, sets off a timer,
   * and adds clickable functionality to the cards in the layout.
   */
  MemoryView(AppCompatActivity context, String t, int l) {
    super(context);
    level = l;
    player =
        new MemorizePlayer(
            (TextView) context.findViewById(R.id.text_moves),
            (TextView) context.findViewById(R.id.text_points),
            (TextView) context.findViewById(R.id.level),
            (Chronometer) context.findViewById(R.id.stopWatch));
    this.player.setLevel(level);
    this.initializeCardArray(context);
    if (level == 1) {
      player.setMovesVisibility();
    }
    theme = t;
    if (theme.equals("L")) {
      initializeImages("L");
      cardBackView = R.drawable.bv_00;
    } else if (theme.equals("D")) {
      initializeImages("D");
      cardBackView = R.drawable.bv_01;
    }
    sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
    show();
    if (level == 3) {
      player.getChronometer().setCountDown(true);
      long timeInMilli = 60 * 1000;
      player.getChronometer().setBase(SystemClock.elapsedRealtime() + timeInMilli);
      player.getChronometer().start();
      // check every one second whether the timer hits 0 or not.
      Handler handler = new Handler();
      handler.postDelayed(
          new Runnable() {
            @Override
            public void run() {
              checkEnd3();
            }
          },
          1000);
    } else {
      player.getChronometer().setBase(SystemClock.elapsedRealtime());
      player.getChronometer().start();
    }
    setOnClick();
  }

  void initializeCardArray(AppCompatActivity context) {
    cardArray = new ArrayList<>();
    cardArray.add(new PlayingCard((ImageView) context.findViewById(R.id.iv_11)));
    cardArray.add(new PlayingCard((ImageView) context.findViewById(R.id.iv_12)));
    cardArray.add(new PlayingCard((ImageView) context.findViewById(R.id.iv_13)));
    cardArray.add(new PlayingCard((ImageView) context.findViewById(R.id.iv_14)));
    cardArray.add(new PlayingCard((ImageView) context.findViewById(R.id.iv_21)));
    cardArray.add(new PlayingCard((ImageView) context.findViewById(R.id.iv_22)));
    cardArray.add(new PlayingCard((ImageView) context.findViewById(R.id.iv_23)));
    cardArray.add(new PlayingCard((ImageView) context.findViewById(R.id.iv_24)));
    cardArray.add(new PlayingCard((ImageView) context.findViewById(R.id.iv_31)));
    cardArray.add(new PlayingCard((ImageView) context.findViewById(R.id.iv_32)));
    cardArray.add(new PlayingCard((ImageView) context.findViewById(R.id.iv_33)));
    cardArray.add(new PlayingCard((ImageView) context.findViewById(R.id.iv_34)));
    cardArray.add(new PlayingCard((ImageView) context.findViewById(R.id.iv_41)));
    cardArray.add(new PlayingCard((ImageView) context.findViewById(R.id.iv_42)));
    cardArray.add(new PlayingCard((ImageView) context.findViewById(R.id.iv_43)));
    cardArray.add(new PlayingCard((ImageView) context.findViewById(R.id.iv_44)));
  }

  /** Initialize + shuffle an array of card placeholders corresponding to respective card images. */
  void initializeImages(String theme) {
    imageArray = new ArrayList<>();
    if (theme.equals("L")) {
      int[] arr1 = new int[] {101, R.drawable.fv_image101};
      imageArray.add(arr1);
      int[] arr2 = new int[] {102, R.drawable.fv_image102};
      imageArray.add(arr2);
      int[] arr3 = new int[] {103, R.drawable.fv_image103};
      imageArray.add(arr3);
      int[] arr4 = new int[] {104, R.drawable.fv_image104};
      imageArray.add(arr4);
      int[] arr5 = new int[] {105, R.drawable.fv_image105};
      imageArray.add(arr5);
      int[] arr6 = new int[] {106, R.drawable.fv_image106};
      imageArray.add(arr6);
      int[] arr7 = new int[] {107, R.drawable.fv_image107};
      imageArray.add(arr7);
      int[] arr8 = new int[] {108, R.drawable.fv_image108};
      imageArray.add(arr8);
      int[] arr9 = new int[] {201, R.drawable.fv_image101};
      imageArray.add(arr9);
      int[] arr10 = new int[] {202, R.drawable.fv_image102};
      imageArray.add(arr10);
      int[] arr11 = new int[] {203, R.drawable.fv_image103};
      imageArray.add(arr11);
      int[] arr12 = new int[] {204, R.drawable.fv_image104};
      imageArray.add(arr12);
      int[] arr13 = new int[] {205, R.drawable.fv_image105};
      imageArray.add(arr13);
      int[] arr14 = new int[] {206, R.drawable.fv_image106};
      imageArray.add(arr14);
      int[] arr15 = new int[] {207, R.drawable.fv_image107};
      imageArray.add(arr15);
      int[] arr16 = new int[] {208, R.drawable.fv_image108};
      imageArray.add(arr16);
    } else {
      int[] arr1 = new int[] {101, R.drawable.fv_image1d1};
      imageArray.add(arr1);
      int[] arr2 = new int[] {102, R.drawable.fv_image1d2};
      imageArray.add(arr2);
      int[] arr3 = new int[] {103, R.drawable.fv_image1d3};
      imageArray.add(arr3);
      int[] arr4 = new int[] {104, R.drawable.fv_image1d4};
      imageArray.add(arr4);
      int[] arr5 = new int[] {105, R.drawable.fv_image1d5};
      imageArray.add(arr5);
      int[] arr6 = new int[] {106, R.drawable.fv_image1d6};
      imageArray.add(arr6);
      int[] arr7 = new int[] {107, R.drawable.fv_image1d7};
      imageArray.add(arr7);
      int[] arr8 = new int[] {108, R.drawable.fv_image1d8};
      imageArray.add(arr8);
      int[] arr9 = new int[] {201, R.drawable.fv_image1d1};
      imageArray.add(arr9);
      int[] arr10 = new int[] {202, R.drawable.fv_image1d2};
      imageArray.add(arr10);
      int[] arr11 = new int[] {203, R.drawable.fv_image1d3};
      imageArray.add(arr11);
      int[] arr12 = new int[] {204, R.drawable.fv_image1d4};
      imageArray.add(arr12);
      int[] arr13 = new int[] {205, R.drawable.fv_image1d5};
      imageArray.add(arr13);
      int[] arr14 = new int[] {206, R.drawable.fv_image1d6};
      imageArray.add(arr14);
      int[] arr15 = new int[] {207, R.drawable.fv_image1d7};
      imageArray.add(arr15);
      int[] arr16 = new int[] {208, R.drawable.fv_image1d8};
      imageArray.add(arr16);
    }
    Collections.shuffle(imageArray);
  }

  /** Set an on click listener on the image view of all the cards in cardArray */
  // https://developer.android.com/reference/android/view/View.OnClickListener used to learn.
  void setOnClick() {
    for (final PlayingCard card : cardArray) {
      card.getImageView()
          .setOnClickListener(
              new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  setSelection(card);
                }
              });
    }
  }

  /** Show the cards to the player for a few seconds, then flip them over and make them playable. */
  void show() {
    for (PlayingCard playCard : cardArray) {
      playCard.set_enable(false);
    }
    for (PlayingCard playCard : cardArray) {
      int image = imageArray.get(cardArray.indexOf(playCard))[1];
      playCard.setImage(image);
    }
    Handler handler = new Handler();
    handler.postDelayed(
        new Runnable() {
          @Override
          public void run() {
            for (PlayingCard playCard : cardArray) {
              playCard.setImage(cardBackView);
            }
            for (PlayingCard playCard : cardArray) {
              playCard.set_enable(true);
            }
          }
        },
        2500);
  }

  /**
   * Temporarily stores selected cards and makes them unresponsive until all selections have been
   * made so a comparison can be performed.
   */
  private void setSelection(PlayingCard c) {
    // Set the image of card to the image view
    int image = imageArray.get(cardArray.indexOf(c))[1];
    c.setImage(image);
    // check the selection of two cards and store them temporarily
    if (cardNum == 1) {
      firstCard = imageArray.get(cardArray.indexOf(c))[0];
      if (firstCard > 200) {
        firstCard = firstCard - 100;
      }
      cardNum = 2;
      firstSelect = this.cardArray.indexOf(c);
      // Make this card unresponsive
      c.set_enable(false);

    } else {
      secondCard = imageArray.get(cardArray.indexOf(c))[0];
      if (secondCard > 200) {
        secondCard = secondCard - 100;
      }
      cardNum = 1;
      secondSelect = this.cardArray.indexOf(c);
      // Make all cards unresponsive
      for (PlayingCard playCard : cardArray) {
        playCard.set_enable(false);
      }
      // https://developer.android.com/reference/android/os/Handler.html &
      // https://stackoverflow.com/questions/15136199/when-to-use-handler-post-when-to-new-thread
      // After selecting the two cards delay the game by 400 milliseconds to check whether the two
      // cards match or not and proceed ahead.
      Handler handler = new Handler();
      handler.postDelayed(
          new Runnable() {
            @Override
            public void run() {
              compare();
            }
          },
          400);
    }
  }

  /**
   * Compare selected cards to see if they match and adjust player's points and the selected cards'
   * visibility in the layout accordingly.
   */
  private void compare() {
    // If card matches make them invisible
    if (firstCard == secondCard) {
      cardArray.get(firstSelect).setVisibility();
      cardArray.get(secondSelect).setVisibility();
      // Increase the points by 2 for correct match
      player.increasePlayerPoints();
      player.setTextPoints();
    } else {
      // Decrease the points by 1 for incorrect match
      player.decreasePlayerPoints();
      player.setTextPoints();
      // Load back the front images again if
      for (PlayingCard card : cardArray) {
        card.setImage(cardBackView);
      }
    }
    player.decreasePlayerMoves();
    player.setTextMoves();
    // Make all cards responsive again
    for (PlayingCard card : cardArray) {
      card.set_enable(true);
    }
    if (level == 1) {
      endGame1(checkVisibility());
    } else if (level == 2) {
      endGame2(checkEnd2());
    } else {
      checkEnd3();
    }
  }

  /** Return whether or not all cards on screen are invisible. */
  boolean checkVisibility() {
    for (PlayingCard item : cardArray) {
      if (item.getVisibility() != View.INVISIBLE) {
        return false;
      }
    }
    return true;
  }

  /** Check if end-game conditions have been met. */
  protected boolean checkEnd2() {
    return player.getMovesLeft() == 0 || checkVisibility();
  }

  /** Check if end-game conditions have been met. */
  protected void checkEnd3() {
    player
        .getChronometer()
        .setOnChronometerTickListener(
            new Chronometer.OnChronometerTickListener() {
              @Override
              public void onChronometerTick(Chronometer chronometer) {
                CharSequence elapsedMillis = chronometer.getText();
                if ((elapsedMillis).equals("00:00")) {
                  endGame3(true);
                }
              }
            });
    endGame3(checkEnd2());
  }

  /**
   * End game if all end-game conditions have been met, track player stats, and navigate to
   * game-over screen.
   */
  protected void endGame1(boolean check) {
    int playerPoints = player.getPlayerPoints();
    if (check) {
      CharSequence elapsedMillis = player.getChronometer().getText();
      player.getChronometer().stop();
      Intent intent = new Intent(getContext(), Memory1Activity.class);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString(POINTS1, String.valueOf(playerPoints));
      editor.putString(TIME1, String.valueOf(elapsedMillis));
      editor.apply();
      if (theme.equals("L")) {
        intent.putExtra("Theme?", "Light");
      } else {
        intent.putExtra("Theme?", "Dark");
      }
      getContext().startActivity(intent);
    }
  }

  protected void endGame2(boolean check) {
    int playerMoves = player.getMovesLeft();
    int playerPoints = player.getPlayerPoints();
    boolean bool1 = checkVisibility();
    if (check) {
      CharSequence elapsedMillis = player.getChronometer().getText();
      player.getChronometer().stop();
      Intent intent = new Intent(getContext(), Memory1Activity.class);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString(POINTS2, String.valueOf(playerPoints));
      editor.putString(TIME2, String.valueOf(elapsedMillis));
      editor.putString(MOVES_LEFT2, String.valueOf(playerMoves));
      if (bool1) {
        editor.putString(CARDS_LEFT2, "NO");
      } else {
        editor.putString(CARDS_LEFT2, "YES");
      }
      editor.apply();
      if (theme.equals("L")) {
        intent.putExtra("Theme?", "Light");
      } else {
        intent.putExtra("Theme?", "Dark");
      }
      getContext().startActivity(intent);
      //      if (bool1) {
      //        Intent intent2 = new Intent(getContext(), Memory3Activity.class);
      //        if (theme.equals("Light")) {
      //          intent2.putExtra("Theme???", "Light");
      //        } else {
      //          intent2.putExtra("Theme???", "Dark");
      //        }
      //        getContext().startActivity(intent2);
      //      }
      //      else {
      //        Intent intent1 = new Intent(getContext(), MemoryOverActivity.class);
      //        getContext().startActivity(intent1);
      //      }
    }
  }

  protected void endGame3(boolean check) {
    int playerMoves = player.getMovesLeft();
    int playerPoints = player.getPlayerPoints();
    boolean bool1 = checkVisibility();
    if (check) {
      CharSequence elapsedMillis = player.getChronometer().getText();
      player.getChronometer().stop();
      Intent intent = new Intent(getContext(), MemoryOverActivity.class);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString(POINTS3, String.valueOf(playerPoints));
      editor.putString(TIME3, String.valueOf(elapsedMillis));
      editor.putString(MOVES_LEFT3, String.valueOf(playerMoves));
      if (bool1) {
        editor.putString(CARDS_LEFT3, "NO");
      } else {
        editor.putString(CARDS_LEFT3, "YES");
      }
      editor.apply();
      getContext().startActivity(intent);
    }
  }
}
