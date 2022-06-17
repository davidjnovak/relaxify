package com.example.relaxify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class PostSession extends AppCompatActivity {
    DatabaseHelper myHelper;
    public String escapeData(String data){

        data = data.replaceAll("'", "");
        data = data.replaceAll("/", "");

        System.out.println(data);
        return data;
    }
    @Override
    public void onBackPressed(){
        Intent home = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(home);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_session);
        Intent recievedIntent = getIntent();
        myHelper = new DatabaseHelper(this);
        String stringData;

        //data to be added to database------------
        String time = recievedIntent.getStringExtra("duration");
        EditText journal = (EditText) findViewById(R.id.journal);
        String currentTime = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        TextView summary = (TextView) findViewById(R.id.summary);
        summary.setText("Congrats!\nYou meditated for " + time);


        //----------------Onclicks-------------------
        Button addButton = (Button) findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (journal.length() != 0){
                    String stringData = time + "\n" + escapeData(journal.getText().toString());
                    addData(stringData);
                    journal.setText("");
                }
                else{
                    makeToast("You must add a journal.");
                }
            }});
        Button viewButton = (Button) findViewById(R.id.view);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent journalIntent = new Intent(getApplicationContext(), JournalList.class);
                startActivity(journalIntent);
            }});
        //----------------------------
    }
    public void addData(String data){
        if (myHelper!= null){
            boolean insert = myHelper.addData(data);
            if (insert){
                makeToast("Entry Added!");
            }
            else{
                makeToast("Unable to Add Your Journal. Sorry!");
        }}
    }
    public void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}