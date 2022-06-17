package com.example.relaxify;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Locale;

public class Session extends AppCompatActivity {
    public long startTime;
    public long finalStartTime;
    private TextView countDown;
    private CountDownTimer myTimer;
    private boolean isRunning;
    private Button start;
    private Button reset;
    public long timeLeft;
    public String remainingString;
    private MediaPlayer myPlayer;
    public int dur = 180000; //default to three minutes

    public void createPlayer(){
        if (dur == 180000){
            myPlayer = MediaPlayer.create(Session.this, R.raw.threemins);
        }
        else if (dur == 300000){
            myPlayer = MediaPlayer.create(Session.this, R.raw.fivemins);
        }
        else{
            myPlayer = MediaPlayer.create(Session.this, R.raw.tenmins);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        Intent receivedIntent = getIntent();
        dur = Integer.valueOf(receivedIntent.getStringExtra("duration"))*60000;
        createPlayer();
        timeLeft = new Long(dur);
        finalStartTime = timeLeft;
        start = findViewById(R.id.start);
        reset = findViewById(R.id.stop);
        countDown = findViewById(R.id.timer);


        reset.setVisibility(View.INVISIBLE);

        //----------------- onclicks------------------p
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                } }});

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer(); }});
        update();
    }
        //-----------------------------------------------------
    @Override
    public void onBackPressed(){
        pauseTimer();
        Intent home = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(home);
    }
    private void startTimer() {
        myTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long untilFinished) {
                timeLeft = untilFinished;
                update();
            }

            @Override
            public void onFinish() {
                isRunning = false;
                start.setText("Start Session");
                start.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.VISIBLE);
            }
        }.start();
        myPlayer.start();

        start.setText("Pause Session");
        isRunning = true;
        reset.setVisibility(View.VISIBLE);
    }

    private void pauseTimer() {
        myTimer.cancel();
        myPlayer.pause();
        start.setText("Continue Session");
        isRunning = false;
        reset.setVisibility(View.VISIBLE);

    }

    private void update() {
        int mins = (int) (timeLeft / 1000) / 60;
        int secs = (int) (timeLeft / 1000) % 60;
        remainingString = String.format(Locale.getDefault(), "%02d:%02d", mins, secs);
        countDown.setText(remainingString);
    }

    private void stopTimer() {
        myPlayer.stop();
        isRunning = false;
        Intent postSesh = new Intent(getApplicationContext(), PostSession.class);
        long timePassed = finalStartTime - timeLeft;
        int mins = (int) (timePassed / 1000) / 60;
        int secs = (int) (timePassed / 1000) % 60;
        String myStr = String.format(Locale.getDefault(), "%02d:%02d", mins, secs);

        postSesh.putExtra("duration", myStr);
        timeLeft = startTime;
        reset.setVisibility(View.INVISIBLE);
        start.setVisibility(View.VISIBLE);
        createPlayer();
        timeLeft = new Long(dur);
        start.setText("Start Session");
        update();
        startActivity(postSesh);
    }
}