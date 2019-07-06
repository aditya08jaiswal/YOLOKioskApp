package com.yolohealth.yolokioskapp.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**class for showing image in rounded view*/
public class RoundedImageView extends ImageView {

    public RoundedImageView(Context context) {
        super(context);

    }

    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        if(b!=null) {
            Bitmap bitmap = b.copy(Config.ARGB_8888, true);

            int w = getWidth(), h = getHeight();
            int radius = w < h ? w : h;

            Bitmap roundBitmap = getCroppedBitmap(bitmap, radius, w, h);
            // roundBitmap= ImageUtils.setCircularInnerGlow(roundBitmap, 0xFFBAB399,
            // 4, 1);
            canvas.drawBitmap(roundBitmap, 0, 0, null);
        }
    }

    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius, int w, int h) {
        Bitmap sbmp;
        if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
            float _w_rate = 1.0f * radius / bmp.getWidth();
            float _h_rate = 1.0f * radius / bmp.getHeight();
            float _rate = _w_rate < _h_rate ? _h_rate : _w_rate;
            sbmp = Bitmap.createScaledBitmap(bmp, (int)(bmp.getWidth() * _rate), (int)(bmp.getHeight() * _rate), false);
        }
        else
            sbmp = bmp;

        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(),
                Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(w / 2, h / 2, (w < h ? w : h) / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);

        return output;
    }

}