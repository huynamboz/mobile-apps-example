package com.midterm.trinhhuynam.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.midterm.trinhhuynam.Question;

import java.util.List;

@Dao
public interface QuestionDAO {
    @Insert
    void insertQuestion(Question question);
    @Insert
    void insertAllQuestion(List<Question> question);
    @Query("SELECT * FROM question")
    List<Question> getAll();
    @Query("SELECT * FROM question WHERE id = :id")
    Question getQuesByID(int id);
    @Update
    void update(Question question);
    @Update
    void updateAll(List<Question> question);
}
