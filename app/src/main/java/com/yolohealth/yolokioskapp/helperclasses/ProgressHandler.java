package com.yolohealth.yolokioskapp.helperclasses;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.core.view.ViewCompat;

import com.yolohealth.yolokioskapp.R;
import com.yolohealth.yolokioskapp.httpclient.HttpRequest;
import com.yolohealth.yolokioskapp.platform.Platform;
import com.yolohealth.yolokioskapp.widget.CustomButton;
import com.yolohealth.yolokioskapp.widget.CustomEditText;
import com.yolohealth.yolokioskapp.widget.CustomTextView;

import java.util.concurrent.atomic.AtomicBoolean;




/**
 *
 * @author arpit87
 *This class is thread safe,call from anywhere to show and dismiss dialog
 */


public class ProgressHandler {
	ProgressBar progressBar = null;
	private static ProgressDialog progressDialog = null;
	private static AtomicBoolean isshowing = new AtomicBoolean(false);
	private static Activity underlying_activity = null;
	private static String title = "";
	private static String message = "";
	private static HttpRequest mRequest = null;
	private static Runnable cancelableRunnable = new Runnable() {

		@Override
		public void run() {
			if(progressDialog!=null && isshowing.get())
			{
				progressDialog.setTitle("Slow connection?");
				progressDialog.setMessage("Hold on for some time or press back to cancel");
				progressDialog.setCancelable(true);
//				progressDialog.dismiss();
			}
		}
	};
	private static void showNetworkError() {
		ToastTracker.showToast("Lost internet connection");
		if(underlying_activity!=null) {
			final Dialog dialog = new Dialog(underlying_activity);
			dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
			dialog.setContentView(R.layout.custom_refer_dialog);
			final CustomEditText lComment = (CustomEditText) dialog.findViewById(R.id.etComment);
			final CustomTextView lMessage = (CustomTextView) dialog.findViewById(R.id.message);
			final CustomTextView lTitle = (CustomTextView) dialog.findViewById(R.id.title);
			final CustomButton lOkButton = (CustomButton) dialog.findViewById(R.id.button_positive);
			final CustomButton lCancelButton = (CustomButton) dialog.findViewById(R.id.button_negative);
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
					if(progressDialog!=null)
						progressDialog.dismiss();
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

	private static Runnable startRunnable = new Runnable() {

		@Override
		public void run() {
			if(underlying_activity==null)
				return;
			if(underlying_activity!=null && underlying_activity.isFinishing()) {
				isshowing.set(false);
				return;
			}
			progressDialog = ProgressDialog.show(underlying_activity, title, message, true);
			LayoutInflater inflater = (LayoutInflater) underlying_activity.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
			View view = inflater.inflate(R.layout.loader_layout, null, false);
			progressDialog.setContentView(view);
			ImageView imageView=(ImageView)view.findViewById(R.id.progress);
			AnimationDrawable frameAnimation = (AnimationDrawable)imageView.getDrawable();
			frameAnimation.setOneShot(false);
			progressDialog.setView(view);
			progressDialog.getWindow().setBackgroundDrawableResource(R.drawable.flag_transparent);
			frameAnimation.start();
			Window window = progressDialog.getWindow();
			window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			progressDialog.getWindow().setLayout(200,200);
			progressDialog.setOnCancelListener(new OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					if(mRequest!=null)
						mRequest.cancelRequest();
					isshowing.set(false);
				}
			});
		}};


	public static void showInfiniteProgressDialoge(final Activity underlying_activity,final String title,final String message,HttpRequest request)
	{
		if(mRequest!=null)
			mRequest.cancelRequest();
		mRequest = request;
		if(!isshowing.getAndSet(true))
		{
			ProgressHandler.underlying_activity = underlying_activity;
			ProgressHandler.title = title;
			ProgressHandler.message = message;
			Platform.getInstance().getHandler().post(startRunnable);
			//set cancelable after 10 sec of delay
			Platform.getInstance().getHandler().postDelayed(cancelableRunnable,10000);

		}
		else
		{
			if(progressDialog!=null)
			{
				Platform.getInstance().getHandler().post((new Runnable(){
					public void run() {
						progressDialog.setTitle(title);
						progressDialog.setMessage(message);
					}}));
			}
		}
	}

	public static void dismissDialoge()
	{
		if(isshowing.getAndSet(false))
		{
			Platform.getInstance().getHandler().removeCallbacks(cancelableRunnable);
			Platform.getInstance().getHandler().post((new Runnable(){
				public void run() {
					if(progressDialog.isShowing())
						progressDialog.dismiss();
				}}));
		}
	}


}
