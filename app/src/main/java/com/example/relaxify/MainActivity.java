package com.example.relaxify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                finish();
            }
        }, 2000);
    }
//    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//            .setSmallIcon(R.drawable.splashscreen)
//            .setContentTitle("Relaxify")
//            .setContentText("Don't forget to meditate today!")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
}