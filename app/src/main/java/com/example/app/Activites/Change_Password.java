package com.example.app.Activites;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;


public class Change_Password extends AppCompatActivity {

    private View parent_view;
    EditText oldPass,newPass,re_nrePass;
    Button Submit;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Dialog dialog;
    EditText text_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__password);

        parent_view = findViewById(android.R.id.content);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        oldPass=(EditText)findViewById(R.id.editOldpass);
        newPass=(EditText)findViewById(R.id.editNewPass);
        re_nrePass=(EditText)findViewById(R.id.editRenewpass);
        Submit=(Button)findViewById(R.id.buttonPISubmit);
        Submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    // Toast.makeText(Activity_Change_Password.this,"Enter right data!!!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    SavePassWord();
                }
            }
        } );
    }

    public boolean validate() {
        boolean valid = true;

        String old_password = oldPass.getText().toString();
        String new_password = newPass.getText().toString();
        String reEnterPassword = re_nrePass.getText().toString();
        sharedPreferences=getApplicationContext().getSharedPreferences("Mydata",MODE_PRIVATE);
        sharedPreferences.edit();
        String oldpass_tag= sharedPreferences.getString("password",null);
        Log.e("thanks", oldpass_tag+"//"+old_password + "/////" + new_password + "////" + reEnterPassword );
        if (old_password.isEmpty() || !(old_password.equals(oldpass_tag))){
            oldPass.setError("Old Password not match");
            valid = false;
        } else {
            oldPass.setError(null);
        }
        if (new_password.isEmpty() || new_password.length() < 4 || new_password.length() > 10) {
            newPass.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            newPass.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(new_password))) {
            re_nrePass.setError("Password Do not match");
            valid = false;
        } else {
            re_nrePass.setError(null);
        }

        return valid;
    }
    private void emptyInputEditText() {
        oldPass.setText(null);
        newPass.setText(null);
        re_nrePass.setText(null);
    }
    private void SavePassWord() {
        sharedPreferences=getApplicationContext().getSharedPreferences("Mydata",MODE_PRIVATE);
        sharedPreferences.edit();
        final String student_id= sharedPreferences.getString("idtag",null);
        final String passwordlog= sharedPreferences.getString("password",null);
        final String new_pass = newPass.getText().toString();


        Log.e("new_pass", new_pass);


    }


}
