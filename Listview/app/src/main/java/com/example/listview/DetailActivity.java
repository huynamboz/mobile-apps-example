package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView tvDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetail = findViewById(R.id.tv_detail);
        Intent receivedIntent = getIntent();
        if(receivedIntent != null) {
            String data = receivedIntent.getStringExtra("number");
            tvDetail.setText(data);
        }
    }
}