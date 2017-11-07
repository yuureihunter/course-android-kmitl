package com.example.pimpavee.moneyflow;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {MoneyInfo.class}, version = 1)
public abstract class MoneyDB extends RoomDatabase {

    public abstract MoneyInfoDAO getMoneyInfoDAO();
}
