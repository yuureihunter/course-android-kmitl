package com.example.pimpavee.moneyflow;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MoneyDB moneyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moneyDB = Room.databaseBuilder(this, MoneyDB.class, "MONEY").build();

        new AsyncTask<Void, Void, List<MoneyInfo>>(){

            @Override
            protected List<MoneyInfo> doInBackground(Void... voids) {
                List<MoneyInfo> result = moneyDB.getMoneyInfoDAO().findAll();

                return result;
            }

            @Override
            protected void onPostExecute(List<MoneyInfo> moneyInfos) {
                ArrayAdapter<MoneyInfo> adapter = new ArrayAdapter<MoneyInfo>(
                        MainActivity.this, android.R.layout.simple_list_item_1, moneyInfos);
                super.onPostExecute(moneyInfos);

                ListView moneyList = findViewById(R.id.moneyList);
                moneyList.setAdapter(adapter);
            }
        }.execute();

        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

    }
}
