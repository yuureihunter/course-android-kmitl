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
    String type = "INCOME";
    Button buttonIncome, buttonExpense, buttonSubmit;
    EditText editTextOrder, editTextAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        moneyDB = Room.databaseBuilder(this, MoneyDB.class, "MONEY").build();

        buttonIncome = findViewById(R.id.buttonIncome);
        buttonExpense = findViewById(R.id.buttonExpense);
        editTextOrder = findViewById(R.id.editTextOrder);
        editTextAmount = findViewById(R.id.editTextAmount);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonIncome.setOnClickListener(this);
        buttonExpense.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);

//        new AsyncTask<Void, Void, MoneyInfo>() {
//            @Override
//            protected MoneyInfo doInBackground(Void... voids) {
//                MoneyInfo moneyInfo = new MoneyInfo();
//                moneyInfo.setType(type);
//                moneyInfo.setOrder(editTextOrder.toString());
//                moneyInfo.setAmount(editTextAmount.toString());
//
//                moneyDB.getMoneyInfoDAO().insert(moneyInfo);
//
//                return null;
//            }
//        }.execute();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonIncome){
            type = "INCOME";
        }else if (view.getId() == R.id.buttonExpense){
            type = "EXPENSE";
        }else {
            new AsyncTask<Void, Void, MoneyInfo>() {
                @Override
                protected MoneyInfo doInBackground(Void... voids) {
                    MoneyInfo moneyInfo = new MoneyInfo();
                    moneyInfo.setType(type);
                    moneyInfo.setOrder(editTextOrder.getText().toString());
                    moneyInfo.setAmount(editTextAmount.getText().toString());

                    moneyDB.getMoneyInfoDAO().insert(moneyInfo);

                    return null;
                }
            }.execute();
            finish();
        }
    }
}
