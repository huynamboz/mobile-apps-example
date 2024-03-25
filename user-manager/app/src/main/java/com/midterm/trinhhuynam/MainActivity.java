package com.midterm.trinhhuynam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.midterm.trinhhuynam.database.QuestionDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Question> mListQuestion;
    private TextView tvQuestion;
    private Button btnPrev;
    private Button btnNext;
    private Button btnTrue;
    private Button btnFalse;
    private Button btnSubmit;
    private int currentQuestion;

    private int MY_REQUEST_CODE = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniUi();
        setTitle("GeoQuiz");
        mListQuestion = new ArrayList<>();
        mListQuestion.add(new Question("La co VN co 2 mau dung khong", "yes", " "));
        mListQuestion.add(new Question("Apple he dieu hanh android", "no", " "));
        mListQuestion.add(new Question("Android la xin nhat", "no", " "));
        mListQuestion.add(new Question("HTML khong phai ngon ngu lap trinh", "yes", " "));
        mListQuestion.add(new Question("CSS khong phai ngon ngu lap trinh", "yes", " "));
//        QuestionDatabase.getInstance(this).questionDAO().insertQuestion(mListQuestion.get(0));
        QuestionDatabase.getInstance(this).questionDAO().insertAllQuestion(mListQuestion);

        currentQuestion = 1;
        loadData(currentQuestion);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNext();
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePrev();
            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = mListQuestion.get(currentQuestion);
                if (question.getAns() == "no") {
                    handleFalse(btnFalse);
                    saveData("CORRECT");
                } else {
                    saveData("WRONG");
                    handleTrue(btnFalse);
                }
            }
        });
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = mListQuestion.get(currentQuestion);
                if (question.getAns() == "yes") {
                    handleFalse(btnTrue);
                    saveData("CORRECT");
                } else {
                    handleTrue(btnTrue);
                    saveData("WRONG");
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivityForResult(intent, MY_REQUEST_CODE);
            }
        });
    }


    private void loadData(int index) {
            Question question = QuestionDatabase.getInstance(this).questionDAO().getQuesByID(currentQuestion);
            if (question != null) {
                String cur = Integer.toString(currentQuestion);
                Toast.makeText(this, cur, Toast.LENGTH_SHORT).show();
                tvQuestion.setText(question.getTitle().toString());
                btnFalse.setBackgroundColor(Color.parseColor("#5c7099"));
                btnTrue.setBackgroundColor(Color.parseColor("#5c7099"));
            } else {
                Toast.makeText(this, "NULL ", Toast.LENGTH_SHORT).show();
            }
//        Log.d("DEBUG", question.getTitle());
//            Toast.makeText(this, question.getTitle(), Toast.LENGTH_SHORT).show();
//        tvQuestion.setText(question.getTitle().toString());

    }
    private void iniUi() {
        tvQuestion = findViewById(R.id.tv_question);
        btnTrue = findViewById(R.id.btn_true);
        btnFalse = findViewById(R.id.btn_false);
        btnNext = findViewById(R.id.btn_next);
        btnPrev = findViewById(R.id.btn_prev);
        btnSubmit = findViewById(R.id.btn_submit);
    }

    private void handleNext() {
        currentQuestion++;
        if (currentQuestion > mListQuestion.size() -1) currentQuestion = 1;
        loadData(currentQuestion);
    }

    private void handlePrev() {
        currentQuestion--;
        if (currentQuestion < 1) currentQuestion = mListQuestion.size() -1;
        loadData(currentQuestion);
    }

    private void handleTrue(Button btn) {
        btn.setBackgroundColor(Color.parseColor("#16b67c"));
    }
    private void handleFalse(Button btn) {
        btn.setBackgroundColor(Color.parseColor("#ff0000"));
    }

    private void saveData(String val) {
        Question question = QuestionDatabase.getInstance(this).questionDAO().getQuesByID(currentQuestion);
        question.setUserAns(val);
        QuestionDatabase.getInstance(this).questionDAO().update(question);
    }
    private void onSubmit() {
    }
}