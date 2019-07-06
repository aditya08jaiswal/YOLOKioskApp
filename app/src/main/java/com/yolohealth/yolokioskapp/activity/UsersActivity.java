package com.yolohealth.yolokioskapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.yolohealth.yolokioskapp.R;
import com.yolohealth.yolokioskapp.adapter.UsersListAdapter;
import com.yolohealth.yolokioskapp.helperclasses.BundleConstants;
import com.yolohealth.yolokioskapp.helperclasses.ProgressHandler;
import com.yolohealth.yolokioskapp.helperclasses.ThisUserConfig;
import com.yolohealth.yolokioskapp.helperclasses.ToastTracker;
import com.yolohealth.yolokioskapp.httpclient.HTTPListener;
import com.yolohealth.yolokioskapp.listeners.RecyclerItemClickListener;
import com.yolohealth.yolokioskapp.model.UserModel;
import com.yolohealth.yolokioskapp.platform.Platform;
import com.yolohealth.yolokioskapp.quickblox.ThisMoverConfig;
import com.yolohealth.yolokioskapp.user.UserAttributes;
import com.yolohealth.yolokioskapp.widget.CustomButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;




public class UsersActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "UsersActivity";
    ArrayList<UserModel> mUsersModel=new ArrayList<>();
    private RecyclerView mUsersRecyclerView;
    private StaggeredGridLayoutManager gridLayoutManager;
    private UsersListAdapter mAdapter;
    private static String mOtp="";
    private String mPhone;
    private int mPosition=0;
//    private OtpReader otpreader;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);



        if (getIntent().getExtras() != null) {
            mUsersModel= (ArrayList<UserModel>) getIntent().getSerializableExtra(BundleConstants.USERS_LIST);
            mOtp=getIntent().getStringExtra(BundleConstants.OTP);
            mPhone=getIntent().getStringExtra(BundleConstants.PHONE);
        }
        initUI();
    }

    private void initUI() {
        ProgressHandler.showInfiniteProgressDialoge(this,"","",null);
        mUsersRecyclerView=(RecyclerView)findViewById(R.id.users);
        gridLayoutManager = new StaggeredGridLayoutManager(3, 1);
        mUsersRecyclerView.setLayoutManager(gridLayoutManager);


//        otpreader = new OtpReader(mOTPListener,"YOLOHL");

        UserModel userModel=new UserModel();
        mUsersModel.add(userModel);
        mAdapter=new UsersListAdapter(this,"123321",mPhone,mUsersModel);
        mUsersRecyclerView.setAdapter(mAdapter);

        mUsersRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mUsersRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //loginUser(position);
                mPosition = position;
                if(position!=mUsersModel.size()-1) {
                    UserModel userModel1 = mUsersModel.get(position);
                    mPhone = userModel1.getPhone();

                    ThisUserConfig.getInstance().putString(ThisUserConfig.USER_ID, String.valueOf(userModel1.getUserid()));
                    ThisUserConfig.getInstance().putString(ThisUserConfig.USERNAME, userModel1.getUsername());
//                ThisUserConfig.getInstance().putString(ThisUserConfig.USER_ROLE,body.getString(UserAttributes.USERNAME));
                    ThisUserConfig.getInstance().putString(ThisUserConfig.PHONE, userModel1.getPhone());
                    ThisUserConfig.getInstance().putString(ThisUserConfig.EMAIL, userModel1.getEmail());
                    ThisUserConfig.getInstance().putString(ThisUserConfig.GENDER, userModel1.getGender());
                    ThisUserConfig.getInstance().putString(ThisUserConfig.USER_ROLE, userModel1.getRole());
                    ThisUserConfig.getInstance().putInt(ThisUserConfig.AGE, userModel1.getAge());
                    ThisUserConfig.getInstance().putString(ThisUserConfig.WEIGHT, userModel1.getUserWeight());
                    ThisUserConfig.getInstance().putString(ThisUserConfig.HEIGHT, userModel1.getUserHeight());
                    ThisUserConfig.getInstance().putString(ThisUserConfig.USER_PROFILE, userModel1.getProfilepic());
                    Intent startMainActivity = new Intent(UsersActivity.this, MainActivity.class);
                    startMainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startMainActivity);
                    finish();
                    //loginUser(mPosition);
//                GetOTPRequest getOTPRequest=new GetOTPRequest(mPhone,getOtpListner);
//                ProgressHandler.showInfiniteProgressDialoge(UsersActivity.this, "", "Please wait...", null);
//                getOTPRequest.executeRequest();
//                showPopup();
                }else {
//                    Intent intent=new Intent(UsersActivity.this, NewUserActivity.class);
//                    intent.putExtra(BundleConstants.PHONE,mPhone);
//                    intent.putExtra(BundleConstants.OTP,mOtp);
//                    startActivity(intent);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }
    HTTPListener getOtpListner=new HTTPListener() {
        @Override
        public void onSuccess(JSONObject response) {
            ProgressHandler.dismissDialoge();
            ToastTracker.showToast("Otp sent to your mobile");
        }

        @Override
        public void onError(String error) {
            ProgressHandler.dismissDialoge();

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        ProgressHandler.dismissDialoge();
    }
//
//    private void showPopup() {
//        final android.app.AlertDialog.Builder alertDialog;
//        alertDialog = new android.app.AlertDialog.Builder(this);
//
//        LayoutInflater inflater = this.getLayoutInflater();
//        View view = inflater.inflate(R.layout.custom_otp_dialog, null);
//        editText =(EditText)view.findViewById(R.id.etOtp);
//        TextView textView=(TextView)view.findViewById(R.id.tvPhoneOtp);
//        textView.setText("Please Enter otp which is sent on : "+mPhone);
//        Button positiveBtn=(Button)view.findViewById(R.id.button_positive);
//        Button negativeBtn=(Button)view.findViewById(R.id.button_negative);
//
//        alertDialog.setView(view);
//        final android.app.AlertDialog dialog= alertDialog.create();
//        dialog.show();
//        positiveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!TextUtils.isEmpty(editText.getText().toString())) {
//                    mOtp = editText.getText().toString();
//                    loginUser(mPosition);
//                    dialog.dismiss();
//                }else{
//                    ToastTracker.showToast("Please enter otp");
//                }
//            }
//        });
//        negativeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//
//    }
    HTTPListener listener=new HTTPListener() {
        @Override
        public void onSuccess(JSONObject response) {
            JSONObject body = null;
            ProgressHandler.dismissDialoge();

            try {
                body = response.getJSONObject("body");

                ThisUserConfig.getInstance().putString(ThisUserConfig.QBUSERNAME,body.getString(UserAttributes.QBUSERNAME));
                ThisUserConfig.getInstance().putString(ThisUserConfig.QBPASSWORD,body.getString(UserAttributes.QBPASSWORD));
                ThisUserConfig.getInstance().putInt(ThisUserConfig.QBID,body.getInt(UserAttributes.QBID));

                ThisMoverConfig.getInstance().putString(ThisMoverConfig.QBUSERNAME,body.getString(UserAttributes.QBUSERNAME));
                ThisMoverConfig.getInstance().putString(ThisMoverConfig.QBPASSWORD,body.getString(UserAttributes.QBPASSWORD));
                ThisMoverConfig.getInstance().putInt(ThisMoverConfig.QBID,body.getInt(UserAttributes.QBID));

                Platform.getInstance().registerInBackground();
                Intent startMainActivity = new Intent(UsersActivity.this, MainActivity.class);
                startMainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMainActivity);
                finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(String error) {
            ProgressHandler.dismissDialoge();
            Log.i(TAG,"Error "+error);
        }
    };
//    private void loginUser(int position) {
//        UserModel userModel = mUsersModel.get(position);
//        LoginUserRequest loginUserRequest = new LoginUserRequest(userModel.getPhone(), ThisAppConfig.DEFAULT_OTP, listener);
//        ProgressHandler.showInfiniteProgressDialoge(this, "", "Please wait...", null);
//        loginUserRequest.executeRequest();
//    }
//    OTPListener mOTPListener =
//            new OTPListener() {
//                @Override
//                public void otpReceived(String messageText) {
////                    editText.setText(messageText.replace("Your verification code is:", ""));
////                    String otp = editText.getText().toString();
////                    if (StringUtils.isBlank(otp))
////                        return;
////                    ToastTracker.showToast("Verifying OTP...");
////                    String uuid = ThisAppConfig.getInstance().getString(ThisAppConfig.APPUUID);
////                    VerifyOTPRequest verifyOTPRequest=new VerifyOTPRequest(mPhone,otp,verifyOtpListner);
////                    ProgressHandler.showInfiniteProgressDialoge(RegistrationActivity.this, "", "Please wait...", verifyOTPRequest);
////                    verifyOTPRequest.executeRequest();
//
//                }
//            } ;

    @Override
    public void onClick(View v) {
//        if(v.getId()==R.id.addUser){
//            Intent intent=new Intent(UsersActivity.this, NewUserActivity.class);
//            intent.putExtra(BundleConstants.PHONE,mPhone);
//            intent.putExtra(BundleConstants.OTP,mOtp);
//            startActivity(intent);
//        }
    }
}
