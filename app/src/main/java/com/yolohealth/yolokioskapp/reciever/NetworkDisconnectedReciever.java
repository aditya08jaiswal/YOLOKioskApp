package com.yolohealth.yolokioskapp.reciever;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.core.view.ViewCompat;

import com.yolohealth.yolokioskapp.R;
import com.yolohealth.yolokioskapp.helperclasses.ToastTracker;
import com.yolohealth.yolokioskapp.widget.CustomButton;
import com.yolohealth.yolokioskapp.widget.CustomEditText;
import com.yolohealth.yolokioskapp.widget.CustomTextView;



public class NetworkDisconnectedReciever extends BroadcastReceiver{
    private static final String TAG = "CallReciever";
    private boolean mIsShownAlready=false;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"Recieved broadcast of call disconnect");
        if(!mIsShownAlready)
            showNetworkError(context);
    }
    public void showNetworkError(Context context) {
        mIsShownAlready=true;
        ToastTracker.showToast("Lost internet connection");
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.custom_refer_dialog);

        final CustomEditText lComment = (CustomEditText) dialog.findViewById(R.id.etComment);
        final CustomTextView lMessage = (CustomTextView) dialog.findViewById(R.id.message);
        final CustomTextView lTitle = (CustomTextView) dialog.findViewById(R.id.title);
        final CustomButton lOkButton = (CustomButton) dialog.findViewById(R.id.button_positive);
        final CustomButton lCancelButton = (CustomButton) dialog.findViewById(R.id.button_negative);
       // lMessage.setCompoundDrawables(ContextCompat.getDrawable(context,R.drawable.nointernet),null,null,null);
        View photoHeader = dialog.findViewById(R.id.photoaaHeader);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            /* For devices equal or higher than lollipop set the translation above everything else */
//            photoHeader.setTranslationZ(6);
            ViewCompat.setTranslationZ(photoHeader, 6);
            ViewCompat.setElevation(photoHeader, 20f);

            /* Redraw the view to show the translation */
            photoHeader.invalidate();
        }
        ;
        lComment.setVisibility(View.GONE);
        lCancelButton.setVisibility(View.INVISIBLE);
        lTitle.setText("Connection Lost");
        lMessage.setText("No internet connection.Please make sure your wifi or mobile data is turn on,then try again ");
        dialog.setCancelable(false);
        dialog.show();
        lOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsShownAlready=false;
                dialog.dismiss();
            }
        });
        lCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
