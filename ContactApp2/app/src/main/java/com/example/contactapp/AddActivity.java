package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtMobile;
    private EditText edtEmail;
    private Button btnAdd;
    private List<Contact> mListContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnAdd = findViewById(R.id.btn_add_save);
        edtEmail = findViewById(R.id.edt_name);
        edtMobile = findViewById(R.id.edt_name);
        edtName = findViewById(R.id.edt_name);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }
    private void addUser() {
        String strName = edtName.getText().toString().trim();
        String strMobi = edtMobile.getText().toString().trim();
        String strEmail = edtEmail.getText().toString().trim();

        if (TextUtils.isEmpty(strMobi) || TextUtils.isEmpty(strName) || TextUtils.isEmpty(strEmail)) {
            Toast.makeText(this, "Please input all", Toast.LENGTH_SHORT).show();
            return;
        }

        Contact contact = new Contact(strName, strMobi, strEmail);
        AppDatabase.getInstance(this).contactDao().insert(contact);
        Toast.makeText(this, "Insert ok", Toast.LENGTH_SHORT).show();

        //hideSoftKeyboard();

        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
        finish();
    }
}