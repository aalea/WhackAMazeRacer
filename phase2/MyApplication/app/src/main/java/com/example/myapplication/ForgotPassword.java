package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.UserInfo.UserManager;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void PasswordCheck(View view){
        UserManager userManager = new UserManager();
        EditText username = findViewById(R.id.UsernameCheck);
        Object validate = userManager.getPasswordFromFile(getApplicationContext(),
                username.getText().toString());
        if(validate instanceof String){
            TextView Password = findViewById(R.id.PasswordCheck);
            String text = "Your Password is: " + validate.toString();
            Password.setText(text);
        }else{
            username.setError("Incorrect Username");
        }
    }



    public void backToLogin(View view){
        finish();
    }
}
