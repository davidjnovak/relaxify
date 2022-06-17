package com.example.relaxify;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class JournalList extends AppCompatActivity {
    DatabaseHelper myHelper;
    ListView listView;
//    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_list);
        listView = (ListView) findViewById(R.id.journals);
        myHelper = new DatabaseHelper(this);
        populateListView();
    }

    private void populateListView() {
        Cursor data = myHelper.getData();
        ArrayList<String> listData = new ArrayList<>();

        while (data.moveToNext()) {
            listData.add(data.getString(1));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String data = adapterView.getItemAtPosition(i).toString();

                Log.d(TAG, "onItemClick: You Clicked on " + data);

                Cursor cursorData = myHelper.getItemID(data); //get the id associated with that data
                int itemID = -1;
                while (cursorData.moveToNext()) {
                    itemID = cursorData.getInt(0);
                }
                myHelper.deleteData(itemID,data);
                listData.remove(i);
                adapter.notifyDataSetChanged();
            //                adapter.notifyDataSetChanged();

            }
        });
    }
}