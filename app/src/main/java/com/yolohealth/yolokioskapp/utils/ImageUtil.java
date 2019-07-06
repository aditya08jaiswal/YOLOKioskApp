package com.yolohealth.yolokioskapp.utils;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.yolohealth.yolokioskapp.R;


/**
 * Created by yolo on 27/3/17.
 */

public class ImageUtil {
    public static void loadFromURL(final Context context, final String url, int placeHolderResourceId, final ImageView imageView, final ProgressBar progressBar) {

        Picasso.with(context).setIndicatorsEnabled(false);
        if(url!=null && !url.isEmpty()) {
            if (placeHolderResourceId != 0) {
                Picasso.with(context).load(url).placeholder(placeHolderResourceId)
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                if (progressBar != null)
                                    progressBar.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onError() {
                                if (progressBar != null)
                                    progressBar.setVisibility(View.INVISIBLE);
                                Picasso.with(context).load(url).into(imageView);
                            }
                        });
            } else {
                Picasso.with(context).load(url).placeholder(R.drawable.newsthumbplaceholder)
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                if (progressBar != null)
                                    progressBar.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onError() {
                                if (progressBar != null)
                                    progressBar.setVisibility(View.INVISIBLE);
                                Picasso.with(context).load(url).into(imageView);
                            }
                        });
            }
        }

    }

    public static void loadFromURI(Context context, Uri uri, ImageView imageView) {
//        Glide.with(context).load(uri).into(imageView);
        Picasso.with(context).load(uri).into(imageView);
    }


}