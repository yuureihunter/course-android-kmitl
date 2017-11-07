package com.example.pimpavee.moneyflow;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    MoneyDB moneyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        moneyDB = Room.databaseBuilder(this, MoneyDB.class, "MONEY").build();

        Button buttonIncome = findViewById(R.id.buttonIncome);
        Button buttonExpense = findViewById(R.id.buttonExpense);
        final EditText editTextOrder = findViewById(R.id.editTextOrder);
        final EditText editTextAmount = findViewById(R.id.editTextAmount);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);

        new AsyncTask<Void, Void, MoneyInfo>() {
            @Override
            protected MoneyInfo doInBackground(Void... voids) {
                MoneyInfo moneyInfo = new MoneyInfo();
                moneyInfo.setOrder(editTextOrder.toString());
                moneyInfo.setAmount(editTextAmount.toString());

                moneyDB.getMoneyInfoDAO().insert(moneyInfo);

                return null;
            }
        }.execute();

    }

    @Override
    public void onClick(View view) {

    }
}
