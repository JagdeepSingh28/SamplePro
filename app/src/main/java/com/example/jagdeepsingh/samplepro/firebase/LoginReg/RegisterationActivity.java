package com.example.jagdeepsingh.samplepro.firebase.LoginReg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jagdeepsingh.samplepro.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterationActivity extends AppCompatActivity {

    private FirebaseAuth        auth;
    private EditText            email_et;
    private EditText            password_et;
    private Button              register_btn;
    private TextView            already_user_tv;
    // Need a progressBar to be added

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        initialize();
        setClickListeners();

    }

    private void initialize() {
        auth                = FirebaseAuth.getInstance();
        email_et            = (EditText) findViewById(R.id.email_et);
        password_et         = (EditText) findViewById(R.id.password_et);
        register_btn        = (Button) findViewById(R.id.register_btn);
        already_user_tv     = (TextView) findViewById(R.id.already_user_tv);
    }

    private void setClickListeners() {

        already_user_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterationActivity.this,LoginActivity.class));
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(email_et.getText().toString())
                        || TextUtils.isEmpty(password_et.getText().toString())){
                    Toast.makeText(RegisterationActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    auth.createUserWithEmailAndPassword(email_et.getText().toString(),password_et.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        startActivity(new Intent(RegisterationActivity.this,LoginActivity.class));
                                    }else{
                                        Toast.makeText(RegisterationActivity.this, "Authentication Failure", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
            }
        });
    }
}
