package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    private EditText edtName;
    private Button btnSave;
    private Contact mContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edtName = findViewById(R.id.edt_name);
        btnSave = findViewById(R.id.btn_save);

        mContact = (Contact) getIntent().getExtras().get("contact");
        if (mContact != null) {
            edtName.setText(mContact.getName());
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact();
            }
        });
    }

    private void updateContact() {
        mContact.setName("test");

        AppDatabase.getinstance(this).contactDao().update(mContact);

        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
    }
}