package com.yolohealth.yolokioskapp.activity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.hbb20.CountryCodePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.yolohealth.yolokioskapp.R;
import com.yolohealth.yolokioskapp.helperclasses.ToastTracker;
import com.yolohealth.yolokioskapp.httpclient.GetOTPRequest;
import com.yolohealth.yolokioskapp.httpclient.HTTPListener;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    CountryCodePicker mCountryCode;
    EditText userPhone;
    Button loginButton;
    TextView invalidPhone;
    String mPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userPhone = findViewById(R.id.activity_login_phone);
        loginButton = findViewById(R.id.activity_login_button);
        mCountryCode = findViewById(R.id.ccp);
        invalidPhone=findViewById(R.id.invalidPhoneNumberTextView);
        userPhone.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=10){
                    userPhone.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_invalid_phone));
                    invalidPhone.setVisibility(View.VISIBLE);
                }
                else{
                    userPhone.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.border_valid_phone));
                    invalidPhone.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userPhone.getText().toString().length()==10){
                    mPhone=mCountryCode.getSelectedCountryCode()+userPhone.getText().toString();
                    GetOTPRequest getOTP = new GetOTPRequest(mPhone, getOTPListener);
                    getOTP.executeRequest();
                }


            }
        });

    }
    HTTPListener getOTPListener = new HTTPListener() {
        @Override
        public void onSuccess(JSONObject response) {
            Log.i("OTPSUCCESS","OTP"+response);
            ToastTracker.showToast("OTP sent to your mobile");


        }

        @Override
        public void onError(String error) {
            ToastTracker.showToast("Problem sending OTP");
        }
    };
}
