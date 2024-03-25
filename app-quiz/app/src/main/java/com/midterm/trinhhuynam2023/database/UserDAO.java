package com.midterm.trinhhuynam2023.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.midterm.trinhhuynam2023.User;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insertUser(User user);
    @Query("SELECT * FROM user")
    List<User> getListUser();
}
