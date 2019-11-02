package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Game3OverActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game3_over);
    Button mainMenu = findViewById(R.id.menu3);
    Button rePlayGame = findViewById(R.id.play3_again);
    //String showScore = getIntent().getExtras().get("Score").toString();
    String showTime = getIntent().getExtras().get("Time").toString();
    TextView score = findViewById(R.id.score3);
    TextView time = findViewById(R.id.time3);
    rePlayGame.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent2 = new Intent(Game3OverActivity.this, Main4Activity.class);
            startActivity(intent2);
          }
        });

    mainMenu.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent3 = new Intent(Game3OverActivity.this, Main1Activity.class);
            startActivity(intent3);
          }
        });
      //score.setText("Score: " + showScore);
      time.setText("Time taken: " + showTime + " s");
  }
}
