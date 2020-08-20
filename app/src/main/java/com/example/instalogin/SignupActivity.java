package com.example.instalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {
    EditText editText, editText2, name, confpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        name = findViewById(R.id.name);
        confpassword = findViewById(R.id.confpassword);

    }

    public void signup(View view) {
        if (TextUtils.isEmpty(name.getText())) {
            name.setError("name required");
        } else if (TextUtils.isEmpty(editText.getText())) {
            editText.setError("email required");
        } else if (TextUtils.isEmpty(editText2.getText())) {
            editText2.setError("password required");
        } else if (TextUtils.isEmpty(confpassword.getText())) {
            confpassword.setError("confirm password required");
        } else if (!editText2.getText().toString().equals(confpassword.getText().toString())) {
            Toast.makeText(SignupActivity.this, "password not the same", Toast.LENGTH_LONG).show();
        } else {
            final ProgressDialog progress=new ProgressDialog(this);
            progress.setMessage("Loading ....");
            progress.show();
            ParseUser user = new ParseUser();
// Set the user's username and password, which can be obtained by a forms
            user.setUsername(name.getText().toString().trim());
            user.setEmail(editText.getText().toString().trim());
            user.setPassword(editText2.getText().toString().trim());
            user.put("name",name.getText().toString().trim());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    progress.dismiss();
                    if (e == null) {
                        Toast.makeText(SignupActivity.this, "welcome", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(SignupActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                        // alertDisplayer("Sucessful Sign Up!","Welcome" + <Insert Username Here> + "!");
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }
}

