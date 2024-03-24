package com.example.contactapp;

import static androidx.core.app.ActivityCompat.startActivityForResult;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{
    private ArrayList<Contact> contactList;
    private IClickItemContact iClickItemContact;


    public interface IClickItemContact {
        void updateContact(Contact contact);
    }
    public ContactsAdapter(ArrayList<Contact> contactList, IClickItemContact iClickItemContact) {
        this.contactList = contactList;
        this.iClickItemContact = iClickItemContact;
    }


    @NonNull
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_row_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(contactList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private static final int MY_REQUEST_CODE = 10;
        private Button btnEdit;
        public TextView tvName;
        public ViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            btnEdit = (Button) view.findViewById(R.id.btn_edit);

        }

        private void clickUpdateContact(View v, Contact contact) {
            Intent intent = new Intent(v.getContext(), UpdateActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("contact", contact);
            intent.putExtras(bundle);
            startActivityForResult(intent, MY_REQUEST_CODE);
        }

        private void startActivityForResult(Intent intent, int myRequestCode) {
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        Contact contact = contactList.get(position);
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemContact.updateContact(contact);
            }
        });
    }
}
