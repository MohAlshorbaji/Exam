package com.example.app.Activites;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.example.app.R;
import com.example.app.utils.Tools;



public class Assessment_Record extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    TextView txt_exam_name,txt_student_name,txt_right_que,txt_wrong_que,txt_score,txt_result;
    String student_id,exam_id,exam_title,student_class,first_name,last_name;

    AppCompatButton btn_review,btn_dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_assessment__record);
        initToolbar();
        txt_exam_name= (TextView) findViewById(R.id.txt_exam_name);
        txt_student_name= (TextView) findViewById(R.id.txt_student_name);
        txt_right_que= (TextView) findViewById(R.id.txt_right_que);
        txt_wrong_que= (TextView) findViewById(R.id.txt_wrong_que);
        txt_score= (TextView) findViewById(R.id.txt_score);
        txt_result= (TextView) findViewById(R.id.txt_result);
        btn_review= (AppCompatButton) findViewById(R.id.btn_review);
        btn_dashboard= (AppCompatButton) findViewById(R.id.btn_dashboard);


        sharedPreferences=getApplicationContext().getSharedPreferences("Mydata",MODE_PRIVATE);
        sharedPreferences.edit();
         student_class= sharedPreferences.getString("student_class",null);
        student_id= sharedPreferences.getString("idtag",null);
        first_name= sharedPreferences.getString("first_name",null);
        last_name= sharedPreferences.getString("last_name",null);
        exam_id= sharedPreferences.getString("exam_id",null);
        exam_title= sharedPreferences.getString("exam_title",null);



        txt_exam_name.setText(exam_id+" : "+exam_title);
        txt_student_name.setText(student_id+" : "+first_name+" "+ last_name);

        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Assessment_Record.this, KeyReview.class);
                startActivity(i);
            }
        });
        btn_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Assessment_Record.this, com.example.app.Activities.Activites.MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }


    private void initToolbar() {

        getSupportActionBar().setTitle(exam_title);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.colorPrimary);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent i = new Intent(Assessment_Record.this, com.example.app.Activities.Activites.MainActivity.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(Assessment_Record.this, com.example.app.Activities.Activites.MainActivity.class);
        startActivity(i);
        finish();
    }

}
