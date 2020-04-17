package com.example.app.Activites;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.app.Adapter.Exam_Adapter;
import com.example.app.Model.Exam_Model_List;
import com.example.app.R;
import com.example.app.utils.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Examinations extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Exam_Adapter mAdapter;
    List<Exam_Model_List> rowListItem;
    private JSONArray result;
    public static final String JSON_ARRAY = "result";

    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    AutoCompleteTextView edit_search;
    LinearLayout empty_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examinations);
        initToolbar();

        showDialog();

        empty_view  = (LinearLayout) findViewById(R.id.empty_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        edit_search=(AutoCompleteTextView)findViewById( R.id.et_search);
        rowListItem=new ArrayList<Exam_Model_List>();


    }

    private void initToolbar() {
        getSupportActionBar().setTitle("Examinations");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.colorPrimary);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {
        progressDialog = new ProgressDialog(Examinations.this);
        progressDialog.setTitle("Exams");
        progressDialog.setMessage("Please wait while showing Data ...  ");
        progressDialog.setCancelable(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        ProgressBar progressbar=(ProgressBar)progressDialog.findViewById(android.R.id.progress);
        progressbar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#FF7043"), android.graphics.PorterDuff.Mode.SRC_IN);
    }


    private void getCategory(JSONArray j){
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                rowListItem.add(new Exam_Model_List(json.getString("exam_id"),
                        json.getString("duration"),
                        json.getString("passmark"),
                        json.getString("re_take_after"),
                        json.getString("exam_title"),
                        json.getString("type"),
                        json.getString("exam_fee"),
                        json.getString("dept_name"),
                        json.getString("class_name"),
                        json.getString("subject"),
                        json.getString("deadline"),
                        json.getString("exam_status"),
                        json.getString("next_retake"),
                        json.getString("quetions"),
                        json.getString("exam_attended"),
                        json.getString("student_retake"),
                        json.getString("exam_allowed"),
                        json.getString("next_retake_b"),
                        json.getString("terms")

                ) );

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
