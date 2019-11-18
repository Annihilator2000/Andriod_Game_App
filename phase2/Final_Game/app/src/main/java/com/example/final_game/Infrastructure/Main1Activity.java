package com.example.final_game.Infrastructure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.final_game.AntCrusher.AntCrusherCustomize;
import com.example.final_game.Memorize.MemoryBegin;
import com.example.final_game.R;
import com.example.final_game.TrueBlueAdventure.Main4Activity;

public class Main1Activity extends AppCompatActivity {

  public DataBaseHelper gameDb;
  public String username;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_main);
    username = getIntent().getStringExtra("Name");
    gameDb = new DataBaseHelper(this);
  }

  public void game_Activities(View view) {
    Intent intent1 = new Intent(this, MemoryBegin.class);
    Intent intent2 = new Intent(this, AntCrusherCustomize.class);
    Intent intent3 = new Intent(this, Main4Activity.class);
    Intent intent4 = new Intent(this, InformationActivity.class);
    if (R.id.button1 == view.getId()) startActivity(intent1);
    else if (R.id.button5 == view.getId()) startActivity(intent2);
    else if (R.id.button6 == view.getId()) startActivity(intent3);
    else if (R.id.button7 == view.getId()) startActivity(intent4);
  }
}
