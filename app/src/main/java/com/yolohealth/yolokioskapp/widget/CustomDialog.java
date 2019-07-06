package com.yolohealth.yolokioskapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.core.view.ViewCompat;

import com.yolohealth.yolokioskapp.R;


/**
 * Created by yolo on 24/3/17.
 */

public class CustomDialog extends Dialog implements View.OnClickListener {
    private boolean success = false;
    private EditText field;
    private EditText description;
    private String mProblem;
    private String mDescription;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Button positive = (Button) findViewById(R.id.button_positive);
        Button negative = (Button) findViewById(R.id.button_negative);
        field = (EditText) findViewById(R.id.etProblem);
        description = (EditText) findViewById(R.id.etDescription);
        View photoHeader = findViewById(R.id.photoaaHeader);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            /* For devices equal or higher than lollipop set the translation above everything else */
            photoHeader.setTranslationZ(6);
            ViewCompat.setTranslationZ(photoHeader,20f);
            /* Redraw the view to show the translation */
            photoHeader.invalidate();
        }
        positive.setOnClickListener(this);
        negative.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_positive:
                onPositiveButtonClicked();
                break;
            case R.id.button_negative:
                onNegativeButtonClicked();
                break;
        }
    }

    private void onNegativeButtonClicked() {
        dismiss();
    }

    private void onPositiveButtonClicked() {
        if(verifyForm()) {
            success = true;
            mProblem=field.getText().toString();
            mDescription=description.getText().toString();
            dismiss();
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public String getmProblem() {
        return mProblem;
    }

    public String getmDescription() {
        return mDescription;
    }

    private boolean verifyForm() {
        boolean valid = true;
        /* verify each field and setError() if not valid */
        if(TextUtils.isEmpty(field.getText().toString())) { //or any other condition
            valid = false;
            field.setError("Please Enter your Problem");
        }
        return valid;
    }

}
