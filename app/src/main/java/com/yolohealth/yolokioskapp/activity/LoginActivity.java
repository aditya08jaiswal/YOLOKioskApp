package com.yolohealth.yolokioskapp.activity;


import android.content.Intent;
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
import com.yolohealth.yolokioskapp.helperclasses.BundleConstants;
import com.yolohealth.yolokioskapp.helperclasses.ProgressHandler;
import com.yolohealth.yolokioskapp.helperclasses.ToastTracker;
import com.yolohealth.yolokioskapp.httpclient.GetAllUsersRequest;
import com.yolohealth.yolokioskapp.httpclient.GetOTPRequest;
import com.yolohealth.yolokioskapp.httpclient.HTTPListener;
import com.yolohealth.yolokioskapp.model.UserModel;
import com.yolohealth.yolokioskapp.platform.Platform;
import com.yolohealth.yolokioskapp.user.UserAttributes;
import com.yolohealth.yolokioskapp.utils.JsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    CountryCodePicker mCountryCode;
    EditText userPhone;
    Button loginButton;
    TextView invalidPhone;
    String mPhone;
    private String otp="";
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
//                    GetOTPRequest getOTP = new GetOTPRequest(mPhone, getOTPListener);
//                    getOTP.executeRequest();
                    GetAllUsersRequest getAllUsersRequest=new GetAllUsersRequest(mPhone,"",getAllUsersListner);
                    ProgressHandler.showInfiniteProgressDialoge(LoginActivity.this, "","Please wait....", getAllUsersRequest);
                    getAllUsersRequest.executeRequest();
                }


            }
        });

    }
    HTTPListener getAllUsersListner=new HTTPListener() {
        @Override
        public void onSuccess(JSONObject response) {
            ProgressHandler.dismissDialoge();
            Log.i("Users=","List"+response.toString());
            try {
                if (response.getString("Status").equals("Success")) {
                    JSONObject jsonObject = response.getJSONObject("body");
                    JSONArray jsonArray = jsonObject.getJSONArray("userlist");
                    if(jsonArray.length()>0) {
                        ArrayList<UserModel> userModels = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            UserModel userModel = new UserModel();
                            userModel.setUsername(JsonUtil.getString(jsonObject1, UserAttributes.USERNAME));
                            userModel.setEmail(JsonUtil.getString(jsonObject1, UserAttributes.USEREMAIL));
                            userModel.setPhone(JsonUtil.getString(jsonObject1, UserAttributes.PHONE));
                            userModel.setProfilepic(JsonUtil.getString(jsonObject1, UserAttributes.PROFILEPIC));
                            userModel.setGender(JsonUtil.getString(jsonObject1, UserAttributes.GENDER));
                            userModel.setAge(JsonUtil.getInt(jsonObject1, UserAttributes.AGE));
                            userModel.setUserid(JsonUtil.getInt(jsonObject1, UserAttributes.USERID));
                            userModel.setUserWeight(""+JsonUtil.getInt(jsonObject1, UserAttributes.WEIGHT));
                            userModel.setUserHeight(""+JsonUtil.getInt(jsonObject1, UserAttributes.HEIGHT));
                            userModel.setProfilepic(""+JsonUtil.getString(jsonObject1, UserAttributes.PROFILEPIC));
                            userModels.add(userModel);
                        }
                        String str_registrationid = Platform.getInstance().getRegistrationId(LoginActivity.this);
                        if(str_registrationid != null)
                            Platform.getInstance().registerInBackground();
                        ProgressHandler.dismissDialoge();
                        Intent startMainActivity = new Intent(LoginActivity.this, UsersActivity.class);
                        startMainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startMainActivity.putExtra(BundleConstants.USERS_LIST,userModels);
                        startMainActivity.putExtra(BundleConstants.OTP,otp);
                        startMainActivity.putExtra(BundleConstants.PHONE,mPhone);
                        startActivity(startMainActivity);
                        finish();

                    }else {
                        //Go to new user registration screen
//                        Intent intent=new Intent(LoginActivity.this, NewUserActivity.class);
//                        intent.putExtra(BundleConstants.PHONE,mPhone);
//                        intent.putExtra(BundleConstants.OTP,otp);
//                        startActivity(intent);
//                        finish();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onError(String error) {
            ProgressHandler.dismissDialoge();
            Log.i("Users","Error"+error);
        }
    };
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
