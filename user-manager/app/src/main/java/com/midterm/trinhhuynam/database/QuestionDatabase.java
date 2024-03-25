package com.midterm.trinhhuynam.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.midterm.trinhhuynam.Question;

@Database(entities = {Question.class}, version = 1)
public abstract class QuestionDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "user.db";
    private static QuestionDatabase instance;

    public static synchronized QuestionDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), QuestionDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract QuestionDAO questionDAO();
}
