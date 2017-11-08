package com.example.pimpavee.moneyflow;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
interface MoneyInfoDAO {

    @Insert
    void insert(MoneyInfo money);

    @Query("SELECT * FROM MONEY_INFO")
    List<MoneyInfo> findAll();

    @Delete
    void deleteMoney(MoneyInfo money);

    @Update
    void updateMoney(MoneyInfo money);

    @Query("SELECT IFNULL(sum(amount), 0) FROM `MONEY_INFO` WHERE type = 'INCOME'")
    double getIncomeSummary();

    @Query("SELECT IFNULL(IFNULL(sum(amount), 0) - " +
            "IFNULL((SELECT sum(amount) FROM `MONEY_INFO` WHERE type = 'EXPENSE'), 0), 0)" +
            " FROM `MONEY_INFO` WHERE type = 'INCOME'")
    double getSummary();
}
