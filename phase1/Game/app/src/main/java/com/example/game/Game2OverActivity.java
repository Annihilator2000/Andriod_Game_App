package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Game2OverActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game2_over);
    String showMoves = getIntent().getExtras().get("Moves Left").toString();
    String cardsLeft = getIntent().getExtras().get("Cards Left To Match?").toString();

    Button mainMenu = findViewById(R.id.main);
    Button restart = findViewById(R.id.playAgain);
    TextView gameStatus = findViewById(R.id.status);
    TextView movesLeft = findViewById(R.id.moves_left);

    restart.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent1 = new Intent(Game2OverActivity.this, MemoryBegin.class);
            startActivity(intent1);
          }
        });

    mainMenu.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent3 = new Intent(Game2OverActivity.this, MainActivity.class);
            startActivity(intent3);
          }
        });
    if ((cardsLeft.equals("NO"))) {
      gameStatus.setText("YOU WON!!");
    } else if (cardsLeft.equals("YES") && showMoves.equals("0")) {
      gameStatus.setText("YOU LOST :(");
    }

    movesLeft.setText("Moves Left: " + showMoves);
  }
}
