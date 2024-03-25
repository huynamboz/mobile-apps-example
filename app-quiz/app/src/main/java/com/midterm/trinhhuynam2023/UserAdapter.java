package com.midterm.trinhhuynam2023;

import static androidx.core.app.ActivityCompat.startActivityForResult;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> mListUser;
    public void setData(List<User> list) {
        this.mListUser = list;
        notifyDataSetChanged();
    }
    private iClickItemUser iClickItemUser;

    public UserAdapter(List<User> mListUser, iClickItemUser iClickItemUser) {
        this.mListUser = mListUser;
        this.iClickItemUser = iClickItemUser;
    }

    public interface iClickItemUser {
        void viewDetail(User user);
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mListUser.get(position);
        if (user == null) return;

        holder.tvName.setText(user.getName());
        holder.tvAddr.setText(user.getAddress());
        holder.tvAge.setText(user.getAge());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemUser.viewDetail(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListUser != null) {
            return mListUser.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvAddr;
        private TextView tvAge;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvAddr = itemView.findViewById(R.id.tv_addr);
            tvAge = itemView.findViewById(R.id.tv_age);
        }
    }
}
