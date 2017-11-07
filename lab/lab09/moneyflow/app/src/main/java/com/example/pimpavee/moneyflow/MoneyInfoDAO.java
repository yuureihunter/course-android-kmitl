package com.example.pimpavee.moneyflow;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
interface MoneyInfoDAO {

    @Insert
    void insert(MoneyInfo money);

    @Query("SELECT * FROM MONEY_INFO")
    List<MoneyInfo> findAll();
}
