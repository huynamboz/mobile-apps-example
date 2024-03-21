package com.example.contactapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = Contact.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();
    private static AppDatabase instant;
    public static AppDatabase getinstance(Context context) {
        if (instant == null) {
            instant = Room.databaseBuilder(context,
                    AppDatabase.class, "contacts").build();
        }
        return instant;
    }
}
