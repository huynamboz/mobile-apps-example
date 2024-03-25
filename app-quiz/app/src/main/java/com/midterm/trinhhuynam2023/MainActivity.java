package com.midterm.trinhhuynam2023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.midterm.trinhhuynam2023.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtAddr;
    private EditText edtAge;
    private Button btnAdd;
    private RecyclerView rcvUser;
    private UserAdapter userAdapter;
    private List<User> mListUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        initUi();

        userAdapter = new UserAdapter(mListUser, new UserAdapter.iClickItemUser() {
            @Override
            public void viewDetail(User user) {
                clickToViewDetail(user);
            }
        });


        mListUser = new ArrayList<>();
        userAdapter.setData(mListUser);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(linearLayoutManager);

        rcvUser.setAdapter(userAdapter);

//        handle database
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
        if (savedInstanceState != null) {
            mListUser = (ArrayList)savedInstanceState.getParcelableArrayList("users");
        }
    }

    private void initUi() {
        edtName = findViewById(R.id.edt_name);
        edtAddr = findViewById(R.id.edt_addr);
        edtAge = findViewById(R.id.edt_age);
        btnAdd = findViewById(R.id.btn_add);
        rcvUser = findViewById(R.id.rcv_user);
    }

    private void addUser() {
        String strName = edtName.getText().toString().trim();
        String strAge = edtAge.getText().toString().trim();
        String strAddr = edtAddr.getText().toString().trim();

        if (TextUtils.isEmpty(strAddr) || TextUtils.isEmpty(strName) || TextUtils.isEmpty(strAge)) {
            Toast.makeText(MainActivity.this, "Please input all", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(strName, strAddr, strAge);
        UserDatabase.getInstance(this).userDAO().insertUser(user);
        Toast.makeText(MainActivity.this, "Insert ok", Toast.LENGTH_SHORT).show();

        edtAddr.setText("");
        edtName.setText("");
        edtAge.setText("");

        hideSoftKeyboard();

        mListUser = UserDatabase.getInstance(this).userDAO()
                .getListUser();

        userAdapter.setData(mListUser);
    }

    public void hideSoftKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public void clickToViewDetail(User user) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//        must bundle and put, if not will crash
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        intent.putExtras(bundle);
        startActivity(intent);
        Toast.makeText(MainActivity.this, "Please input all", Toast.LENGTH_SHORT).show();

    }



}