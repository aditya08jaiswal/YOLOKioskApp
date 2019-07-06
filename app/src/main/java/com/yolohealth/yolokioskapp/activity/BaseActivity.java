package com.yolohealth.yolokioskapp.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.yolohealth.yolokioskapp.R;
import com.yolohealth.yolokioskapp.helperclasses.ThisUserConfig;
import com.yolohealth.yolokioskapp.httpclient.HTTPListener;
import com.yolohealth.yolokioskapp.httpclient.SetUserOnlineStatusRequest;
import com.yolohealth.yolokioskapp.reciever.NetworkDisconnectedReciever;
import com.yolohealth.yolokioskapp.utils.StringUtils;
import com.yolohealth.yolokioskapp.widget.CustomButton;
import com.yolohealth.yolokioskapp.widget.CustomEditText;
import com.yolohealth.yolokioskapp.widget.CustomTextView;

import org.json.JSONObject;


/**
 * Created by yolo on 20/3/17.
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    public static Bitmap bitmap=null;
    private ProgressDialog mProgressDialog;
    public static boolean mIsViewed = false;
    NetworkDisconnectedReciever callDisconnectedReciever=new NetworkDisconnectedReciever();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
      //  Platform.getInstance().initialize(this);
        setContentView(R.layout.base_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction("networklost");
        registerReceiver(callDisconnectedReciever, intentFilter1);
        final String userId = ThisUserConfig.getInstance().getString(ThisUserConfig.USER_ID);
        if(!StringUtils.isBlank(userId))
            setUserOnlineApi();

    }

    private void setUserOnlineApi(){
        SetUserOnlineStatusRequest setUserOnlineStatusRequest=new SetUserOnlineStatusRequest(1,listner);
        setUserOnlineStatusRequest.executeRequest();
    }
    HTTPListener listner=new HTTPListener() {
        @Override
        public void onSuccess(JSONObject response) {
            Log.i(TAG,"Online true " + response.toString());
        }

        @Override
        public void onError(String error) {
            Log.i(TAG,"Online fail");
            if(error.contains("401"))
                doLogout();

        }
    };
    private void doLogout() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.custom_refer_dialog);
        final CustomEditText lComment=(CustomEditText)dialog.findViewById(R.id.etComment);
        final CustomTextView lMessage=(CustomTextView) dialog.findViewById(R.id.message);
        final CustomTextView lTitle=(CustomTextView) dialog.findViewById(R.id.title);
        final CustomButton lOkButton=(CustomButton)dialog.findViewById(R.id.button_positive);
        final CustomButton lCancelButton=(CustomButton)dialog.findViewById(R.id.button_negative);
        lCancelButton.setVisibility(View.INVISIBLE);
        lComment.setVisibility(View.INVISIBLE);
        lComment.getLayoutParams().height=5;
        View photoHeader = dialog.findViewById(R.id.photoaaHeader);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            /* For devices equal or higher than lollipop set the translation above everything else */
//            photoHeader.setTranslationZ(6);
            ViewCompat.setTranslationZ(photoHeader,6);
            /* Redraw the view to show the translation */
            photoHeader.invalidate();
        }
        lTitle.setText("Session Expired!!");
        dialog.setCancelable(false);
        lMessage.setText("Your session has been expired. Please login again");
        lOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ThisUserConfig.getInstance().clear();
                Intent registerActivityIntent=new Intent(BaseActivity.this,LoginActivity.class);
                registerActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(registerActivityIntent);
                finish();
                dialog.cancel();
            }

            public void onClick(DialogInterface dialog, int id) {

            }
        });
        dialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(callDisconnectedReciever);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public void showToast(Context pcontext,String pMessage){
        Toast.makeText(pcontext,pMessage,Toast.LENGTH_LONG).show();
    }
    public void hideKeyboard() {
        InputMethodManager lImm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View lView = this.getCurrentFocus();
        if (lView != null) {
            lImm.hideSoftInputFromWindow(this.getCurrentFocus()
                    .getWindowToken(), 0);
        }
    }

    public void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void showProgressDialog(String message){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }
    public void dismissProgressDialog(){
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIsViewed = false;
    }
}
