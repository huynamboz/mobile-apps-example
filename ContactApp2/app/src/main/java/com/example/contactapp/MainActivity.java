package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactapp.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    private ActivityMainBinding binding;
    private List<Contact> contactList;
    private FloatingActionButton showAdd;
    private ContactsAdapter contactAdapter;
    private AppDatabase appDatabase;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        binding.rvContacts.setLayoutManager(new LinearLayoutManager(this));
        contactList = new ArrayList<Contact>();

        showAdd = findViewById(R.id.btn_add);
        showAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("contact", contact);
//                intent.putExtras(bundle);
                startActivityForResult(intent, MY_REQUEST_CODE);
            }
        });
        contactAdapter = new ContactsAdapter(contactList, new ContactsAdapter.IClickItemContact() {
            @Override
            public void updateContact(Contact contact) {
                clickUpdateContact(contact);
            }
        });

        binding.rvContacts.setAdapter(contactAdapter);

        loadData();

    }
    private void clickUpdateContact(Contact contact) {
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("contact", contact);
        intent.putExtras(bundle);
//        startActivity(intent);
        startActivityForResult(intent, MY_REQUEST_CODE);
    }

    private void loadData() {
        Toast.makeText(this, " Loaded", Toast.LENGTH_SHORT).show();
        contactList = AppDatabase.getInstance(this).contactDao().getAll();
        contactAdapter.setData(contactList);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            loadData();
        }
    }
}
