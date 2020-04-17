package com.example.app.Activites;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.CountDownTimer;

import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.example.app.Model.Quetion_Model_List;
import com.example.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Assessment extends AppCompatActivity {

    List<Quetion_Model_List> rowListItem;
    private JSONArray result;
    public static final String JSON_ARRAY = "result";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    EditText edit_fill_answer;
    public TextView txt_quetion;
    RadioGroup radiogrp;
    RadioButton radio1,radio2,radio3,radio4;

    TextView txt_timer;
    LinearLayout empty_view;
    String student_id,exam_id,exam_title,exam_duration;

    Button btn_Previous,btn_Next,btn_submit;
    int i=0;
    String realaAns,student_retake;

    ProgressDialog progressDialog;
    private boolean isCanceled = false;

    AppCompatCheckBox check_invalid;
    int count;

    ImageView image_quetion,image_op1,image_op2,image_op3,image_op4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_assessment);

        sharedPreferences=getApplicationContext().getSharedPreferences("Mydata",MODE_PRIVATE);
        sharedPreferences.edit();
        String student_class= sharedPreferences.getString("student_class",null);
         student_id= sharedPreferences.getString("idtag",null);
        exam_id= sharedPreferences.getString("exam_id",null);
        exam_title= sharedPreferences.getString("exam_title",null);
        String exam_type= sharedPreferences.getString("exam_type",null);
        String exam_fee= sharedPreferences.getString("exam_fee",null);
        String exam_deadline= sharedPreferences.getString("exam_deadline",null);
        String exam_department= sharedPreferences.getString("exam_department",null);
        String exam_class= sharedPreferences.getString("exam_class",null);
        exam_duration= sharedPreferences.getString("exam_duration",null);
        String exam_subject= sharedPreferences.getString("exam_subject",null);
        String exam_passmark= sharedPreferences.getString("exam_passmark",null);
        String exam_retake= sharedPreferences.getString("exam_retake",null);
        String exam_status= sharedPreferences.getString("exam_status",null);
        String next_retake= sharedPreferences.getString("next_retake",null);
        String quetions= sharedPreferences.getString("quetions",null);
        String exam_attended= sharedPreferences.getString("exam_attended",null);
         student_retake= sharedPreferences.getString("student_retake",null);
        String exam_allowed= sharedPreferences.getString("exam_allowed",null);
        String next_retake_b= sharedPreferences.getString("next_retake_b",null);


        txt_quetion = (TextView) findViewById(R.id.txt_quetion);
        radiogrp = (RadioGroup) findViewById(R.id.radiogrp);
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio2);
        radio3 = (RadioButton) findViewById(R.id.radio3);
        radio4 = (RadioButton) findViewById(R.id.radio4);
        edit_fill_answer= (EditText) findViewById(R.id.edit_fill_answer);

        image_quetion = (ImageView) findViewById(R.id.image_quetion);
        image_op1 = (ImageView) findViewById(R.id.image_op1);
        image_op2 = (ImageView) findViewById(R.id.image_op2);
        image_op3 = (ImageView) findViewById(R.id.image_op3);
        image_op4 = (ImageView) findViewById(R.id.image_op4);


        btn_submit= (Button) findViewById(R.id.btn_submit);
        btn_Previous = (Button) findViewById(R.id.btn_Previous);
        btn_Next = (Button) findViewById(R.id.btn_Next);
        txt_timer=(TextView)findViewById( R.id.txt_timer);
        int minutes = Integer.parseInt(exam_duration);

        check_invalid = (AppCompatCheckBox) findViewById(R.id.check_invalid);


        new CountDownTimer(60*minutes*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                if(isCanceled)
                {
                    //If the user request to cancel or paused the
                    //CountDownTimer we will cancel the current instance
                    cancel();
                }else {
                    long millis = millisUntilFinished;
                    //Convert milliseconds into hour,minute and seconds
                    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                    txt_timer.setText(hms);//set text
                }
            }
            public void onFinish() {
                txt_timer.setText("TIME'S UP!!");
            }
        }.start();


        rowListItem=new ArrayList<Quetion_Model_List>();

        if(i==0){
            btn_Previous.setVisibility(View.GONE);
        }else{
            btn_Previous.setVisibility(View.VISIBLE);
        }



    }
}
