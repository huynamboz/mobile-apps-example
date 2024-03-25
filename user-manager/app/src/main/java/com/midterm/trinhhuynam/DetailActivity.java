package com.midterm.trinhhuynam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.contactapp.QuestionAdapter;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private com.example.contactapp.QuestionAdapter questionAdapter;
    private List<Question> mListQus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Result");
//        questionAdapter = new QuestionAdapter(mListQus, new QuestionAdapter.IClickItemContact() {
//            @Override
//            public void updateContact(Question question) {
//            }
//        });
    }

}