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

import com.midterm.trinhhuynam.Question;
import com.midterm.trinhhuynam.R;
import com.midterm.trinhhuynam.database.QuestionDatabase;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{
    private List<Question> questionList;
    private IClickItemContact iClickItemContact;
    public void setData(List<Question> list) {
        this.questionList = list;
        notifyDataSetChanged();
    }

    public interface IClickItemContact {
        void updateContact(Question question);
    }
    public QuestionAdapter(List<Question> questionList, IClickItemContact iClickItemContact) {
        this.questionList = questionList;
        this.iClickItemContact = iClickItemContact;
    }


    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(questionList.get(position).getTitle());
        holder.tvRes.setText(questionList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private static final int MY_REQUEST_CODE = 10;
        public TextView tvName;
        public TextView tvRes;
        public ViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_resultName);
            tvRes = (TextView) view.findViewById(R.id.tv_resultVal);

        }

        private void startActivityForResult(Intent intent, int myRequestCode) {
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        Question question = questionList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemContact.updateContact(question);
            }
        });
    }
}
