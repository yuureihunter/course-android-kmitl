package com.example.student.roomdemo;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MessageDB messageDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageDB = Room.databaseBuilder(this, MessageDB.class, "MESSAGE").build();


        new AsyncTask<Void, Void, MessageInfo>(){

            @Override
            protected MessageInfo doInBackground(Void... voids) {
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setText("Hello");
                messageInfo.setTime(new Date().toString());

                messageDB.getMessageInfoDAO().insert(messageInfo);

                return null;
            }
        }.execute();

        Button showMessageButton = findViewById(R.id.showMessageButton);
        showMessageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        new AsyncTask<Void, Void, List<MessageInfo>>(){

            @Override
            protected List<MessageInfo> doInBackground(Void... voids) {
                List<MessageInfo> result = messageDB.getMessageInfoDAO().findAll();
                return result;
            }

            @Override
            protected void onPostExecute(List<MessageInfo> messageInfos) {
                ArrayAdapter<MessageInfo> adapter = new ArrayAdapter<MessageInfo>(
                        MainActivity.this, android.R.layout.simple_list_item_1, messageInfos);
                super.onPostExecute(messageInfos);

                ListView messageList = findViewById(R.id.messageList);
                messageList.setAdapter(adapter);
            }
        }.execute();



    }
}
