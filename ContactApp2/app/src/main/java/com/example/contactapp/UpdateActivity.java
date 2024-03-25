package com.example.contactapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtMobile;
    private Button btnSave;
    private Contact mContact;
    private ImageView btn_Close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtMobile = findViewById(R.id.edt_mobile);

        btnSave = findViewById(R.id.btn_save);

        mContact = (Contact) getIntent().getExtras().get("contact");
        if (mContact != null) {
            edtName.setText(mContact.getName());
            edtEmail.setText(mContact.getEmail());
            edtMobile.setText(mContact.getMobile());
        }

        btn_Close = findViewById(R.id.btn_Close);
        btn_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                Toast.makeText(UpdateActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact();
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }

    private void updateContact() {
        mContact.setName(edtName.getText().toString());
        mContact.setEmail(edtEmail.getText().toString());
        mContact.setMobile(edtMobile.getText().toString());

        AppDatabase.getInstance(this).contactDao().update(mContact);

        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
        finish();
    }
}