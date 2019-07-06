package com.yolohealth.yolokioskapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.yolohealth.yolokioskapp.R;


/**
 * Created by yolohealth on 22/12/17.
 */

public class CustomProgressView extends Dialog {

    private ImageView loader;

    public CustomProgressView(@NonNull Context context) {
        super(context);

    }

    private void initUI() {
        AnimationDrawable animation = (AnimationDrawable) loader.getDrawable();
        animation.setOneShot(false);
        animation.start();
    }

    public CustomProgressView(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomProgressView(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), R.color.transwhite)));
        setContentView(R.layout.custom_progress_dialog);
        loader = (ImageView)findViewById(R.id.loader);
        setCancelable(false);
        initUI();
    }
    public void showDialog(){
        if(!isShowing())
            this.show();
    }
    public void dismissDialog(){
        if(isShowing())
            dismiss();
    }
}
