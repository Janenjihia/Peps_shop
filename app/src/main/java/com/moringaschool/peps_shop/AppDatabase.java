package com.moringaschool.peps_shop;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "HMal_DB";
    private static AppDatabase INSTANCE;
    public abstract ProductDAO productDAO();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DB_NAME).allowMainThreadQueries().build();
        return INSTANCE;
    }

    public static void closeConnection() {
        if (INSTANCE != null) INSTANCE.close();
    }