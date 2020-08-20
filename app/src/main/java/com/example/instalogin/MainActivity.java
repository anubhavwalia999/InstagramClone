package com.example.instalogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.*;
import com.parse.ParseException;
import com.parse.ParseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener,View.OnClickListener {
    EditText editText,editText2;

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
     if(i==KeyEvent.KEYCODE_ENTER){
         login(view);
     }

        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText= findViewById(R.id.editText);
        editText2= findViewById(R.id.editText2);
        editText2.setOnKeyListener(this);
        ConstraintLayout background1=(ConstraintLayout) findViewById(R.id.background1);
        background1.setOnClickListener((View.OnClickListener) this);

        CircleImageView circleImageView2=findViewById(R.id.circleImageView2);
        circleImageView2.setOnClickListener((View.OnClickListener) this);


        LinearLayout background2= findViewById(R.id.background2);
        background2.setOnClickListener((View.OnClickListener) this);

        TextView textView=findViewById(R.id.textView);
        textView.setOnClickListener((View.OnClickListener) this);
        if(ParseUser.getCurrentUser()!=null){
    Intent intent=new Intent(MainActivity.this,HomeActivity.class);
    startActivity(intent);
    finish();
}


    }
    public void login(View view){
if(TextUtils.isEmpty(editText.getText())){
    editText.setError("email is Incorrect");
}else if(TextUtils.isEmpty(editText2.getText())){
    editText2.setError("password");
}else{
    final ProgressDialog progress=new ProgressDialog(this);
    progress.setMessage("Loading ....");
    progress.show();

    ParseUser.logInInBackground(editText.getText().toString(),editText2.getText().toString(), new LogInCallback() {
        @Override
        public void done(ParseUser parseUser, ParseException e) {
            progress.dismiss();
            if (parseUser != null) {
                Toast.makeText(MainActivity.this,"welcome back", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
                showuserlist();
                // alertDisplayer("Sucessful Login","Welcome back" + <Insert Username Here> + "!");
            } else {
                ParseUser.logOut();
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    });
}
    }

    public void signup(View view){
        Intent intent=new Intent(MainActivity.this,SignupActivity.class);
        startActivity(intent);
    }
    public void forgot(View view){
        Intent intent=new Intent(MainActivity.this,reset.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.background1 || view.getId()==R.id.background2 || view.getId()==R.id.circleImageView2 || view.getId()==R.id.textView){
            InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
    }
    public void showuserlist(){
        Intent intent=new Intent(getApplicationContext(),userlistActivity.class);
        startActivity(intent);
    }
}
