package com.example.final_game.Infrastructure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.final_game.R;

public class InformationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_information);
  }

  public void level_Select(View view) {
    Intent intent1 = new Intent(this, Main1Activity.class);
    if (R.id.level_select == view.getId()) startActivity(intent1);
  }
}
