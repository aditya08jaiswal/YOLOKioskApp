package com.yolohealth.yolokioskapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;


/**
 * An {@link RelativeLayout} layout that maintains a consistent width to height aspect ratio.
 */
public class DynamicHeightRelativeLayout extends RelativeLayout {

    private double mHeightRatio;

    public DynamicHeightRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicHeightRelativeLayout(Context context) {
        super(context);
    }

    public void setHeightRatio(double ratio) {
        if (ratio != mHeightRatio) {
            mHeightRatio = ratio;
            requestLayout();
        }
    }

    public double getHeightRatio() {
        return mHeightRatio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mHeightRatio > 0.0) {
            // set the image views size
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) (width * mHeightRatio);
            setMeasuredDimension(width, height);
        }
        else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
