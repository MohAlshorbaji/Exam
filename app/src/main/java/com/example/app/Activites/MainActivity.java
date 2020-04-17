package com.example.app.Activities.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ViewGroup;

import com.example.app.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
RecyclerView mrecycleview;
FirebaseDatabase database;
DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mrecycleview = findViewById(R.id.recycleview);
        mrecycleview.setHasFixedSize(true);
        mrecycleview.setLayoutManager( new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
    reference = database.getReference("video");

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Member, ViewHolder>firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Member, ViewHolder>(Member.class, R.layout.row, ViewHolder.class, reference) {

                    @Override
                    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Member model) {
                        holder.setvideo(getApplication(),model.getTitle(),model.getUrl());
                    }

                    @NonNull
                    @Override
                    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        return null;
                    }
                };
        mrecycleview.setAdapter(firebaseRecyclerAdapter);
    }
}
