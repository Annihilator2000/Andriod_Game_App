package com.example.final_game.AntCrusher;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import com.example.final_game.R;

import java.util.Date;

public class AntPresenter{

    private AntManagerFactory antManagerFactory;
    private DonutView donutView;
    private boolean touch = false;
    private GameCreature removedAnt;
    private int score;
    private int level = 1;
    private int lives = 10;
    private int antGenerationSpeed = 10;

    public AntPresenter(AntManagerFactory antManagerFactory, DonutView donutView) {
        this.antManagerFactory = antManagerFactory;
        this.donutView = donutView;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            double buttonX = event.getX();
            double buttonY = event.getY();

            for (int i = 0; i < antManagerFactory.size(); i++) {
                if (antManagerFactory.getCreatures().get(i).getX() < buttonX
                        && buttonX < antManagerFactory.getCreatures().get(i).getX() + antManagerFactory.getCreatures().get(i).getWidth()
                        && antManagerFactory.getCreatures().get(i).getY() < buttonY
                        && buttonY < antManagerFactory.getCreatures().get(i).getY() + antManagerFactory.getCreatures().get(i).getHeight()) {
                    touch = true;
                    donutView.play();
                    removedAnt = antManagerFactory.getCreatures().get(i);
                    this.update();
                    score += 10;
                    donutView.setScore(score);
                    return true;
                }
            }
            touch = false;
            return false;
        } else {
            touch = false;
            return false;
        }
    }

    public void update() {
        Bitmap antBitmap1 = BitmapFactory.decodeResource(donutView.getResources(), R.drawable.ant);
        if (touch) {
            antManagerFactory.removeCreature(removedAnt);
            if (antManagerFactory.size() == 0) {
                level += 1;
                donutView.setLevel(level);
                donutView.getGameThread().setRunning(false);

                Intent levelIntent = new Intent(donutView.getContext(), AntLevelActivity.class);
                levelIntent.putExtra("Level", level);

                antGenerationSpeed += 2;
                donutView.setSpeed(antGenerationSpeed);
                antManagerFactory.createCreature("Ant", antBitmap1, donutView, antGenerationSpeed);
                donutView.startNewActivity(levelIntent);

            }
        } else {
            antManagerFactory.update();
        }

        if (lives == 0) {

            donutView.getGameThread().setRunning(false);
            Date finalDate = new Date();
            float currTime = (finalDate.getTime() - donutView.initialTime) / 1000F;
            Intent newGameintent = new Intent(donutView.getContext(), AntOverActivity.class);

            newGameintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            newGameintent.putExtra("Score", score);
            newGameintent.putExtra("Time", currTime);
            newGameintent.putExtra("Level", level);

            donutView.startNewActivity(newGameintent);
        }

        for (int i = 0; i < antManagerFactory.size(); i++) {
            GameCreature ant = antManagerFactory.getCreatures().get(i);
            if (ant.getY() < 500) {
                ant.setSpeedPos(ant.getSpeed() + 1, 3000);
                lives -= 1;
                donutView.decreaseLife();
            }
        }

    }

}
