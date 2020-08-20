package com.example.instalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class reset extends AppCompatActivity {
EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        editText=findViewById(R.id.editText);


    }
public void reset(View view){
        if(TextUtils.isEmpty(editText.getText())){
            editText.setError("email required");
        }else{
            ParseUser.requestPasswordResetInBackground(editText.getText().toString(), new RequestPasswordResetCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null){
                        Toast.makeText(reset.this, "an email was sent with reset instruction", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(reset.this, "something went wrong....", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
}
}
