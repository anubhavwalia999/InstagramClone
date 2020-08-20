package com.example.instalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.ParseUser;

public class HomeActivity extends AppCompatActivity {
TextView username,email;
    public void showuserlist(){
        Intent intent=new Intent(getApplicationContext(),userlistActivity.class);
        startActivity(intent);
    }
    public void back(View view){
        Intent intent=new Intent(getApplicationContext(),userlistActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        ParseUser currentUser = ParseUser.getCurrentUser();
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);

        if (currentUser != null) {
            username.setText(currentUser.getString("name"));
            email.setText(currentUser.getEmail());

        }
        if (ParseUser.getCurrentUser() != null) {
            showuserlist();
        }
    }
    public void logout(View view){
        ProgressDialog progress=new ProgressDialog(this);
        progress.setMessage("Loading ....");
        progress.show();
        ParseUser.logOut();
        Intent intent=new Intent(HomeActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        progress.dismiss();
    }
}
