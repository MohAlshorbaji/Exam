package com.example.app.Activites;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;


import com.example.app.R;
import com.example.app.utils.Tools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class DemoEnquiry extends AppCompatActivity {

    EditText edit_your_name,edit_org_name,edit_contact,edit_city,edit_email,edit_website,edit_address,edit_courses,edit_message;
    Button bt_submit;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_enquiry);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        initToolbar();

        edit_your_name = (EditText) findViewById(R.id.edit_your_name);
        edit_org_name = (EditText) findViewById(R.id.edit_org_name);
        edit_contact = (EditText) findViewById(R.id.edit_contact);
        edit_city = (EditText) findViewById(R.id.edit_city);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_website = (EditText) findViewById(R.id.edit_website);
        edit_address = (EditText) findViewById(R.id.edit_address);
        edit_courses = (EditText) findViewById(R.id.edit_courses);
        edit_message = (EditText) findViewById(R.id.edit_message);

        bt_submit = (Button) findViewById(R.id.bt_submit);

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    AddEnquiry();
                }
            }
        });

    }

    private void initToolbar() {

        getSupportActionBar().setTitle("Demo & Enquiry");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.colorPrimary);
    }



   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean validate() {
        boolean valid = true;

        String fullname = edit_your_name.getText().toString();
        String contact = edit_contact.getText().toString();
        String email = edit_email.getText().toString();
        String message = edit_message.getText().toString();
        String courses = edit_courses.getText().toString();
        String org_name = edit_org_name.getText().toString();
        String city = edit_city.getText().toString();
        String address = edit_address.getText().toString();

        if (message.isEmpty()) {
            edit_message.setError("Enter Message");
            valid = false;
        } else {
            edit_message.setError(null);
        }
        if (fullname.isEmpty()) {
            edit_your_name.setError("Enter your name");
            valid = false;
        } else {
            edit_your_name.setError(null);
        }
        if (contact.isEmpty()) {
            edit_contact.setError("Enter Contact Number");
            valid = false;
        } else {
            edit_contact.setError(null);
        }

        if (TextUtils.isEmpty(email)) {
            edit_email.setError("Field required");
            valid = false;
        } else if (!isEmailValid(email)) {
            edit_email.setError("Invalid email");
            valid = false;
        }else {
            edit_email.setError(null);
        }

        if (courses.isEmpty()) {
            edit_courses.setError("Enter Courses");
            valid = false;
        } else {
            edit_courses.setError(null);
        }
        if (org_name.isEmpty()) {
            edit_org_name.setError("Enter Organization name ");
            valid = false;
        } else {
            edit_org_name.setError(null);
        }
        if (city.isEmpty()) {
            edit_city.setError("Enter City");
            valid = false;
        } else {
            edit_city.setError(null);
        }
        if (address.isEmpty()) {
            edit_address.setError("Enter Address");
            valid = false;
        } else {
            edit_address.setError(null);
        }
        return valid;
    }
    private boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public void emptyInputEditText(){
         edit_your_name.setText(null);
         edit_org_name.setText(null);
         edit_contact.setText(null);
         edit_city.setText(null);
         edit_email.setText(null);
         edit_website.setText(null);
         edit_address.setText(null);
         edit_courses.setText(null);
         edit_message.setText(null);
    }

    private void AddEnquiry() {


        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        sharedPreferences=getApplicationContext().getSharedPreferences("Mydata",MODE_PRIVATE);
        sharedPreferences.edit();
        String student_id= sharedPreferences.getString("idtag",null);

        String fullname = edit_your_name.getText().toString();
        String org_name = edit_org_name.getText().toString();
        String contact = edit_contact.getText().toString();
        String city = edit_city.getText().toString();
        String email = edit_email.getText().toString();
        String website = edit_website.getText().toString();
        String address = edit_address.getText().toString();
        String courses = edit_courses.getText().toString();
        String message = edit_message.getText().toString();


    }


}
