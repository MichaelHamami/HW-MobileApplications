package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class activity_main extends AppCompatActivity {

    private ProgressBar main_PB_p1_hp;
    private ProgressBar main_PB_p2_hp;

    private Button main_BTN_p1_attack_1;
    private Button main_BTN_p1_attack_2;
    private Button main_BTN_p1_attack_3;

    private Button main_BTN_p2_attack_1;
    private Button main_BTN_p2_attack_2;
    private Button main_BTN_p2_attack_3;

    private final int PLAYER_ONE = 1;

    private final int HIGH_DAMAGE = 50;
    private final int MEDIUM_DAMAGE = 25;
    private final int LOW_DAMAGE = 10;

    private int player_turn = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();

        main_BTN_p1_attack_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attack(HIGH_DAMAGE);
            }
        });
        main_BTN_p1_attack_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attack(MEDIUM_DAMAGE);
            }
        });
        main_BTN_p1_attack_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attack(LOW_DAMAGE);
            }
        });


        main_BTN_p2_attack_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attack(HIGH_DAMAGE);
            }
        });
        main_BTN_p2_attack_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attack(MEDIUM_DAMAGE);
            }
        });
        main_BTN_p2_attack_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attack(LOW_DAMAGE);

            }
        });

    }

    private void attack(int damage) {
        if (player_turn == PLAYER_ONE) {
            main_PB_p2_hp.setProgress(main_PB_p2_hp.getProgress() - damage);
            lowHp(main_PB_p2_hp);


        }
        else {
            main_PB_p1_hp.setProgress(main_PB_p1_hp.getProgress() - damage);
            lowHp(main_PB_p1_hp);

                    }
        switchPlayers();
        checkIsGameOver();
    }

    private void switchPlayers() {
        if(player_turn == PLAYER_ONE) {
            disableButtons(main_BTN_p1_attack_1, main_BTN_p1_attack_2, main_BTN_p1_attack_3);
            enableButtons(main_BTN_p2_attack_1, main_BTN_p2_attack_2, main_BTN_p2_attack_3);
        }
        else {
            disableButtons(main_BTN_p2_attack_1, main_BTN_p2_attack_2, main_BTN_p2_attack_3);
            enableButtons(main_BTN_p1_attack_1, main_BTN_p1_attack_2, main_BTN_p1_attack_3);
        }
        player_turn = (player_turn % 2) + 1;
    }

    private void enableButtons(Button... buttons) {
        for (Button btn : buttons) {
            btn.setEnabled(true);
            btn.setBackgroundColor(getColor(R.color.activeButton));
        }
    }

    private void disableButtons(Button... buttons) {
        for (Button btn : buttons) {
            btn.setEnabled(false);
            btn.setBackgroundColor(getColor(R.color.inactiveButton));

        }
    }

    private void setUpViews() {

        main_PB_p1_hp = findViewById(R.id.main_PB_p1_hp);
        main_PB_p2_hp = findViewById(R.id.main_PB_p2_hp);


        main_BTN_p1_attack_1 = findViewById(R.id.main_BTN_p1_attack_1);
        main_BTN_p1_attack_2 = findViewById(R.id.main_BTN_p1_attack_2);
        main_BTN_p1_attack_3 = findViewById(R.id.main_BTN_p1_attack_3);

        main_BTN_p2_attack_1 = findViewById(R.id.main_BTN_p2_attack_1);
        main_BTN_p2_attack_2 = findViewById(R.id.main_BTN_p2_attack_2);
        main_BTN_p2_attack_3 = findViewById(R.id.main_BTN_p2_attack_3);

    }

    private void lowHp(ProgressBar pb) {
        if (pb.getProgress() < pb.getMax() / 3){
            pb.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }

    }

    private void checkIsGameOver () {
        if (main_PB_p1_hp.getProgress() == 0 || main_PB_p2_hp.getProgress() == 0) {
            Toast.makeText(getBaseContext(), "Game Over", Toast.LENGTH_SHORT).show();
        }
    }
}