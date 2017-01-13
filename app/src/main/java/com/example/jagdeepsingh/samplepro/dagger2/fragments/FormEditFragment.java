package com.example.jagdeepsingh.samplepro.dagger2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jagdeepsingh.samplepro.MyApplication;
import com.example.jagdeepsingh.samplepro.R;
import com.example.jagdeepsingh.samplepro.dagger2.database.DataService;
import com.example.jagdeepsingh.samplepro.dagger2.model.User;
import com.example.jagdeepsingh.samplepro.dagger2.widget.EditFormTextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Jagdeep.Singh on 11-01-2017.
 */

public class FormEditFragment extends android.support.v4.app.Fragment {

    EditFormTextInputLayout inputName, inputEmail, inputAddress, inputPhone;
    EditText txtName, txtEmail, txtAddress, txtPhone;
    Button submit;

    @BindView(R.id.submit_form_btn)
    Button submit_form_btn;

    User user;

    @Inject
    DataService dataService;

    public FormEditFragment(){

    }

    public static Fragment getInstance(Bundle bundle) {
        FormEditFragment formEditFragment = new FormEditFragment();
        formEditFragment.setArguments(bundle);
        return formEditFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.form_edit,container,false);
        initViews(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((MyApplication) getActivity().getApplication()).getDataComponent().injectTo(this);

        user = dataService.getUser();
        populateUserValue();
    }

    private void initViews(View view) {
        inputName = (EditFormTextInputLayout) view.findViewById(R.id.text_input_name);
        inputEmail = (EditFormTextInputLayout) view.findViewById(R.id.text_input_email);
        inputAddress = (EditFormTextInputLayout) view.findViewById(R.id.text_input_address);
        inputPhone = (EditFormTextInputLayout) view.findViewById(R.id.text_input_phone);

        txtName     = (EditText) view.findViewById(R.id.txt_name);
        txtEmail    = (EditText) view.findViewById(R.id.txt_email);
        txtAddress  = (EditText) view.findViewById(R.id.txt_address);
        txtPhone    = (EditText) view.findViewById(R.id.txt_phone);
        submit      = (Button) view.findViewById(R.id.submit_form_btn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User mUser = getUpdatedUser();
                saveNewUser(mUser);
            }
        });
    }

    private User getUpdatedUser() {
        User mUser = new User();
        mUser.name = txtName.getText().toString();
        mUser.email = txtEmail.getText().toString();
        mUser.phone = txtPhone.getText().toString();
        mUser.address = txtAddress.getText().toString();
        return mUser;
    }

    private void populateUserValue() {
        if (user == null) {
            user = new User();
            return;
        }
        txtName.setText(user.name);
        txtEmail.setText(user.email);
        txtAddress.setText(user.address);
        txtPhone.setText(user.phone);
    }

//    @OnClick(R.id.submit_form_btn)
//    public void submitForm(View view){
//        saveNewUser(user);
//    }

    private void saveNewUser(User user) {
        dataService.storeUser(user);
    }
}
