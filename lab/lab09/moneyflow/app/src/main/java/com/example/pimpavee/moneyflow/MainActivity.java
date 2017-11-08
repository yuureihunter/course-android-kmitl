package com.example.pimpavee.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ListView.OnItemClickListener {

    private MoneyDB moneyDB;
    private int id;
    double summary, incomeSum;
    private String type, order, amount;
    private List<MoneyInfo> moneyList;
    TextView textViewSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moneyDB = Room.databaseBuilder(this, MoneyDB.class, "MONEY").build();

        textViewSum = findViewById(R.id.textViewSum);

        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, AddActivity.class));
    }

    @Override
    protected void onPostResume() {
        new AsyncTask<Void, Void, List<MoneyInfo>>(){

            @Override
            protected List<MoneyInfo> doInBackground(Void... voids) {
                List<MoneyInfo> result = moneyDB.getMoneyInfoDAO().findAll();

                incomeSum = moneyDB.getMoneyInfoDAO().getIncomeSummary();
                summary = moneyDB.getMoneyInfoDAO().getSummary();

                return result;
            }

            @Override
            protected void onPostExecute(List<MoneyInfo> moneyInfos) {
                ArrayAdapter<MoneyInfo> adapter = new ArrayAdapter<MoneyInfo>(
                        MainActivity.this, android.R.layout.simple_list_item_1, moneyInfos);

                ListView moneyList = findViewById(R.id.moneyList);
                moneyList.setAdapter(adapter);
                moneyList.setOnItemClickListener(MainActivity.this);
                textViewSum.setText(String.format("%s", summary));
                balanceTextColor();

                super.onPostExecute(moneyInfos);

            }
        }.execute();

        super.onPostResume();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
        new AsyncTask<Void, Void, MoneyInfo>() {

            @Override
            protected MoneyInfo doInBackground(Void... voids) {
                moneyList = moneyDB.getMoneyInfoDAO().findAll();
                id = moneyList.get(i).getId();
                type = moneyList.get(i).getType();
                order = moneyList.get(i).getOrder();
                amount = moneyList.get(i).getAmount();

                return null;
            }

            @Override
            protected void onPostExecute(MoneyInfo moneyInfo) {
                super.onPostExecute(moneyInfo);

                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("type", type);
                intent.putExtra("order", order);
                intent.putExtra("amount", amount);
                startActivity(intent);
            }
        }.execute();
    }

    public void balanceTextColor (){
        double percent = (summary / incomeSum) * 100;

        if (percent >= 50){
            textViewSum.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.textGreen));
        }else if (percent >= 25){
            textViewSum.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.textYellow));
        }else {
            textViewSum.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.textRed));
        }


    }
}
