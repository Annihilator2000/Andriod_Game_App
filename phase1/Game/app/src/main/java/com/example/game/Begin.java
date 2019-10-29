package com.example.game;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;

class Begin extends Activity{

    GameView gameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
    }

}
