package com.example.jagdeepsingh.samplepro.firebase.LoginReg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jagdeepsingh.samplepro.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    public static final String TAG = ForgotPasswordActivity.class.getSimpleName();
    FirebaseAuth        auth;
    TextView            login_again_tv;
    EditText            email_et;
    Button              reset_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initializeIds();
        setOnclickListeners();

    }

    private void initializeIds() {
        login_again_tv      =       (TextView) findViewById(R.id.login_again_tv);
        email_et            =       (EditText) findViewById(R.id.email_et);
        reset_btn           =       (Button) findViewById(R.id.reset_btn);
        auth                =       FirebaseAuth.getInstance();
    }

    private void setOnclickListeners() {
        login_again_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
            }
        });

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email_et.getText().toString().isEmpty()){
                    Toast.makeText(ForgotPasswordActivity.this, "Please fill Email Id", Toast.LENGTH_SHORT).show();
                }
                auth.sendPasswordResetEmail(email_et.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                    Toast.makeText(ForgotPasswordActivity.this, "Mail Has Been sent to Your Email Id", Toast.LENGTH_SHORT).show();
                                Log.i(TAG, "onComplete:"+ task.getResult().toString());
                            }
                        });
            }
        });
    }
}
