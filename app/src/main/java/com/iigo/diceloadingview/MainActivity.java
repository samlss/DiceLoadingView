package com.iigo.diceloadingview;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.iigo.library.DiceLoadingView;
import com.iigo.library.DiceView;

public class MainActivity extends AppCompatActivity {
    private DiceLoadingView diceLoadingView1;
    private DiceLoadingView diceLoadingView2;
    private DiceLoadingView diceLoadingView3;
    private DiceLoadingView diceLoadingView4;
    private DiceLoadingView diceLoadingView5;
    private DiceLoadingView diceLoadingView6;
    private DiceLoadingView diceLoadingView7;
    private DiceLoadingView diceLoadingView8;
    private DiceLoadingView diceLoadingView9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceLoadingView1 = findViewById(R.id.dlv_loading1);
        diceLoadingView2 = findViewById(R.id.dlv_loading2);
        diceLoadingView3 = findViewById(R.id.dlv_loading3);
        diceLoadingView4 = findViewById(R.id.dlv_loading4);
        diceLoadingView5 = findViewById(R.id.dlv_loading5);
        diceLoadingView6 = findViewById(R.id.dlv_loading6);
        diceLoadingView7 = findViewById(R.id.dlv_loading7);
        diceLoadingView8 = findViewById(R.id.dlv_loading8);
        diceLoadingView9 = findViewById(R.id.dlv_loading9);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        diceLoadingView1.release();
        diceLoadingView2.release();
        diceLoadingView3.release();
        diceLoadingView4.release();
        diceLoadingView5.release();
        diceLoadingView6.release();
        diceLoadingView7.release();
        diceLoadingView8.release();
        diceLoadingView9.release();
    }

    public void onStart(View view) {
        diceLoadingView1.start();
        diceLoadingView2.start();
        diceLoadingView3.start();
        diceLoadingView4.start();
        diceLoadingView5.start();
        diceLoadingView6.start();
        diceLoadingView7.start();
        diceLoadingView8.start();
        diceLoadingView9.start();
    }

    public void onStop(View view) {
        diceLoadingView1.stop();
        diceLoadingView2.stop();
        diceLoadingView3.stop();
        diceLoadingView4.stop();
        diceLoadingView5.stop();
        diceLoadingView6.stop();
        diceLoadingView7.stop();
        diceLoadingView8.stop();
        diceLoadingView9.stop();
    }

    public void onResume(View view) {
        diceLoadingView1.resume();
        diceLoadingView2.resume();
        diceLoadingView3.resume();
        diceLoadingView4.resume();
        diceLoadingView5.resume();
        diceLoadingView6.resume();
        diceLoadingView7.resume();
        diceLoadingView8.resume();
        diceLoadingView9.resume();
    }

    public void onPause(View view) {
        diceLoadingView1.pause();
        diceLoadingView2.pause();
        diceLoadingView3.pause();
        diceLoadingView4.pause();
        diceLoadingView5.pause();
        diceLoadingView6.pause();
        diceLoadingView7.pause();
        diceLoadingView8.pause();
        diceLoadingView9.pause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        diceLoadingView2.setDuration(3000);
        diceLoadingView2.setInterpolator(new AnticipateOvershootInterpolator());
        diceLoadingView2.setFirstSideDiceNumber(2);
        diceLoadingView2.setFirstSidePointColor(Color.parseColor("#FF7D81"));
        diceLoadingView2.setFirstSideDiceBgColor(Color.WHITE);
        diceLoadingView2.setFirstSideDiceBorderColor(Color.GRAY);

        diceLoadingView2.setSecondSideDiceNumber(3);
        diceLoadingView2.setSecondSidePointColor(Color.BLUE);
        diceLoadingView2.setSecondSideDiceBgColor(Color.WHITE);
        diceLoadingView2.setSecondSideDiceBorderColor(Color.BLUE);

        diceLoadingView2.setThirdSideDiceNumber(4);
        diceLoadingView2.setThirdSidePointColor(Color.GREEN);
        diceLoadingView2.setThirdSideDiceBgColor(Color.WHITE);
        diceLoadingView2.setThirdSideDiceBorderColor(Color.GREEN);

        diceLoadingView2.setFourthSideDiceNumber(5);
        diceLoadingView2.setFourthSidePointColor(Color.RED);
        diceLoadingView2.setFourthSideDiceBgColor(Color.WHITE);
        diceLoadingView2.setFourthSideDiceBorderColor(Color.RED);
        return super.onKeyDown(keyCode, event);
    }

    public void onStartDiceView(View view) {
        startActivity(new Intent(this, DiceViewActivity.class));
    }
}

