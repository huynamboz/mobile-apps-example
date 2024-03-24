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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    private ActivityMainBinding binding;
    private ArrayList<Contact> contactList;
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
        contactAdapter = new ContactsAdapter(contactList, new ContactsAdapter.IClickItemContact() {
            @Override
            public void updateContact(Contact contact) {
                clickUpdateContact(contact);
            }
        });
        binding.rvContacts.setAdapter(contactAdapter);

        contactList.add(new Contact("Trinh Huy Nam", "09324234", "asdasd@gmail.com"));
        contactList.add(new Contact("Nguyen van b", "09324234", "asssd@gmail.com"));
        contactList.add(new Contact("Nguyen van c", "09324234", "a234asd@gmail.com"));
        contactAdapter.notifyDataSetChanged();


//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
////                appDatabase = AppDatabase.getinstance(getApplicationContext());
////                contactDao = appDatabase.contactDao();
////                contactDao.insert(new Contact("Nguyen van A", "09324234", "asdasd@gmail.com"));
//            }
//        });
    }
    private void clickUpdateContact(Contact contact) {
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
//        must bundle and put, if not will crash
        Bundle bundle = new Bundle();
        bundle.putSerializable("contact", contact);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
