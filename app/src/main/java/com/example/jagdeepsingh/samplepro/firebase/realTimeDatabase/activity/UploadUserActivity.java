package com.example.jagdeepsingh.samplepro.firebase.realTimeDatabase.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jagdeepsingh.samplepro.R;
import com.example.jagdeepsingh.samplepro.firebase.Config;
import com.example.jagdeepsingh.samplepro.firebase.realTimeDatabase.model.Person;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class UploadUserActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name_et;
    EditText password_et;
    Button submit_btn;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_user);

        initializeIds();
        setClickListener();
    }

    private void setClickListener() {

    }

    private void initializeIds() {
        name_et         = (EditText)findViewById(R.id.name_et);
        password_et     = (EditText)findViewById(R.id.password_et);
        submit_btn      = (Button)findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(this);

        /**
         * FireBase Initialization
         */
        Firebase.setAndroidContext(this);
        firebase        = new Firebase(Config.FIREBASE_URL);


        /**
         * Getting data from fireBase Real Time Database
         */
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Person person = postSnapshot.getValue(Person.class);
                    name_et.setText(person.getName());
                    password_et.setText(person.getPassword());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(UploadUserActivity.this, "Failed To retrieve Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(TextUtils.isEmpty(name_et.getText().toString()) &&
                TextUtils.isEmpty(name_et.getText().toString())){
            Toast.makeText(UploadUserActivity.this, "Please fill Fields", Toast.LENGTH_SHORT).show();
        }else{

            /**
             * Sending data to fireBase Real Time Database in form of object
             */
            Person person = new Person();
            person.setName(name_et.getText().toString());
            person.setPassword(password_et.getText().toString());

            firebase.child("Person").setValue(person);
        }
    }
}
