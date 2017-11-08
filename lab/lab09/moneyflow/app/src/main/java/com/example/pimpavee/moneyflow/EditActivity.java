package com.example.pimpavee.moneyflow;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class EditActivity extends AppCompatActivity implements View.OnClickListener{

    MoneyDB moneyDB;
    String type, order, amount;
    Button buttonIncome, buttonExpense, buttonSubmit, buttonDelete, buttonCancle;
    EditText editTextOrder, editTextAmount;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        i = getIntent().getIntExtra("id", 0);
        type = getIntent().getStringExtra("type");
        order = getIntent().getStringExtra("order");
        amount = getIntent().getStringExtra("amount");

        moneyDB = Room.databaseBuilder(this, MoneyDB.class, "MONEY").build();

        buttonIncome = findViewById(R.id.buttonIncome);
        buttonExpense = findViewById(R.id.buttonExpense);
        editTextOrder = findViewById(R.id.editTextOrder);
        editTextAmount = findViewById(R.id.editTextAmount);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonCancle = findViewById(R.id.buttonCancle);

        buttonIncome.setOnClickListener(this);
        buttonExpense.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonCancle.setOnClickListener(this);

        editTextOrder.setText(order);
        editTextAmount.setText(amount);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonIncome){
            type = "INCOME";
        }else if (view.getId() == R.id.buttonExpense){
            type = "EXPENSE";
        }else if (view.getId() == R.id.buttonDelete) {
            new AsyncTask<Void, Void, MoneyInfo>() {
                @Override
                protected MoneyInfo doInBackground(Void... voids) {
                    MoneyInfo moneyInfo = new MoneyInfo();
                    moneyInfo.setId(i);
                    moneyDB.getMoneyInfoDAO().deleteMoney(moneyInfo);

                    return null;
                }
            }.execute();
            finish();
        }else if (view.getId() == R.id.buttonSubmit){
            new AsyncTask<Void, Void, MoneyInfo>() {
                @Override
                protected MoneyInfo doInBackground(Void... voids) {
                    MoneyInfo moneyInfo = new MoneyInfo();
                    moneyInfo.setId(i);
                    moneyInfo.setType(type);
                    moneyInfo.setOrder(editTextOrder.getText().toString());
                    moneyInfo.setAmount(editTextAmount.getText().toString());

                    moneyDB.getMoneyInfoDAO().updateMoney(moneyInfo);

                    return null;
                }
            }.execute();
            finish();
        } else {
            finish();
        }
    }
}
