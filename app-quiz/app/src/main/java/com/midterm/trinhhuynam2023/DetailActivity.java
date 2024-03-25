package com.midterm.trinhhuynam2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView detailName;
    private TextView detailAge;
    private TextView detailAddr;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailName = findViewById(R.id.detail_name);
        detailAge = findViewById(R.id.detail_age);
        detailAddr = findViewById(R.id.detail_addr);

        User user = (User) getIntent().getExtras().get("user");

        detailName.setText(user.getName().toString());
        detailAge.setText(user.getAge().toString());
        detailAddr.setText(user.getAddress().toString());

        toolbar = findViewById(R.id.toolbar);
        setBackButtonOnToolbar(toolbar, DetailActivity.this);
    }

    public void setBackButtonOnToolbar(Toolbar toolbar, AppCompatActivity appCompatActivity) {
//        appCompatActivity.setSupportActionBar(toolbar);
//        if (appCompatActivity.getSupportActionBar() != null) {
//            appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            appCompatActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
//        }
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}