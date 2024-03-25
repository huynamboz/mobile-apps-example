package com.midterm.trinhhuynam;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "question")

public class Question {
    @PrimaryKey(autoGenerate = true)

    private int id;
    @ColumnInfo

    private String title;
    @ColumnInfo

    private String ans; //yes or no
    @ColumnInfo
    private String userAns;

    public Question(String title, String ans, String userAns) {
        this.title = title;
        this.ans = ans;
        this.userAns = userAns;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getUserAns() {
        return userAns;
    }

    public void setUserAns(String userAns) {
        this.userAns = userAns;
    }
}
