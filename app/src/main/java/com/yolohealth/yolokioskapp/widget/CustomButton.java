package com.yolohealth.yolokioskapp.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by ajitzegde on 22/9/15.
 */
public class CustomButton extends androidx.appcompat.widget.AppCompatButton {
    public CustomButton(Context context) {
        super(context);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial.ttf");
        setTypeface(tf);
    }

}
