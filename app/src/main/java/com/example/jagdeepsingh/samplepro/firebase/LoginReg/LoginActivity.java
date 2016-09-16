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

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;
    private EditText            email_et;
    private EditText            password_et;
    private Button              login_btn;
    private TextView            forgotpassword_tv;
    private TextView            new_user_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
        setOnClickListener();
    }

    private void initialize() {
        auth = FirebaseAuth.getInstance();
        email_et            = (EditText) findViewById(R.id.email_et);
        password_et         = (EditText) findViewById(R.id.password_et);
        login_btn           = (Button) findViewById(R.id.login_btn);
        forgotpassword_tv   = (TextView) findViewById(R.id.forgotpassword_tv);
        new_user_tv         = (TextView) findViewById(R.id.new_user_tv);
    }

    private void setOnClickListener() {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(email_et.getText().toString())
                        || TextUtils.isEmpty(password_et.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    auth.signInWithEmailAndPassword(email_et.getText().toString(),password_et.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
                                    }else{
                                        Toast.makeText(LoginActivity.this, "UnAuthorized Access", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            });
                }
            }
        });

        forgotpassword_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
            }
        });

        new_user_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterationActivity.class));
            }
        });
    }
}
