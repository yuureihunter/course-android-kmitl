package com.example.student.roomdemo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by student on 11/3/2017 AD.
 */

@Database(entities = {MessageInfo.class}, version = 1)
public abstract class MessageDB extends RoomDatabase {

    public abstract MessageInfoDAO getMessageInfoDAO();

}
