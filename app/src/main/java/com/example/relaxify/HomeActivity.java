package com.example.relaxify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Relaxify");

        //start three minute session
        Button startSession = (Button) findViewById(R.id.button1);
        startSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sessionIntent = new Intent(getApplicationContext(), Session.class);
                sessionIntent.putExtra("duration", "3");
                startActivity(sessionIntent);
            }
        });

        //start 5 minute session
        Button startSession2 = (Button) findViewById(R.id.button2);
        startSession2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sessionIntent = new Intent(getApplicationContext(), Session.class);
                sessionIntent.putExtra("duration", "5");
                startActivity(sessionIntent);
            }
        });

        //start 10 minute session
        Button startSession3 = (Button) findViewById(R.id.button3);
        startSession3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sessionIntent = new Intent(getApplicationContext(), Session.class);
                sessionIntent.putExtra("duration", "10");
                startActivity(sessionIntent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.about_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.subitem:
                //open site
                Intent aboutIntent = new Intent(getApplicationContext(), About.class);
                startActivity(aboutIntent);
                return true;
            case R.id.subitem1:
                Intent journalIntent = new Intent(getApplicationContext(), JournalList.class);
                startActivity(journalIntent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
