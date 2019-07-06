package com.yolohealth.yolokioskapp.widget;


import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.view.Gravity;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.yolohealth.yolokioskapp.interfaces.DialogClickInterface;



public class CustomAlertDialog implements DialogClickInterface, DialogInterface.OnClickListener {

    public DialogClickInterface mDialogClickInterface;

    public static CustomAlertDialog mDialog;
    private int mDialogIdentifier;
    private Context mContext;

    public static CustomAlertDialog getInstance() {
        if (mDialog == null)
            mDialog = new CustomAlertDialog();

        return mDialog;

    }

    /**
     * Show confirmation dialog with two buttons
     *
     * @param pMessage
     * @param pPositiveButton
     * @param pNegativeButton
     * @param pContext
     * @param pDialogIdentifier
     */
    public void showConfirmDialog(String pMessage,
                                  String pPositiveButton, String pNegativeButton,
                                  Context pContext, int pDialogIdentifier) {


        mDialogClickInterface = (DialogClickInterface) pContext;
        mDialogIdentifier = pDialogIdentifier;
        mContext=pContext;
        AlertDialog.Builder lBuilder = new AlertDialog.Builder(pContext)

                .setMessage(pMessage)
                .setPositiveButton(pPositiveButton, this)
                .setNegativeButton(pNegativeButton, this);

        lBuilder.setCancelable(false);
        AlertDialog lDialog = lBuilder.show();
        TextView lMessageText = (TextView) lDialog.findViewById(android.R.id.message);
        lMessageText.setGravity(Gravity.CENTER);

    }




    /**
     * Show confirmation dialog with two buttons
     *@param pTitle
     * @param pMessage
     * @param pPositiveButton
     * @param pNegativeButton
     * @param pContext
     * @param pDialogIdentifier
     */
    public void showConfirmDialog(String pTitle,String pMessage,
                                  String pPositiveButton, String pNegativeButton,
                                  Context pContext, int pDialogIdentifier) {


        mDialogClickInterface = (DialogClickInterface) pContext;
        mDialogIdentifier = pDialogIdentifier;
        AlertDialog.Builder lBuilder = new AlertDialog.Builder(pContext)
.setTitle(pTitle)
                .setMessage(pMessage)
                .setPositiveButton(pPositiveButton, this)
                .setNegativeButton(pNegativeButton, this);

        lBuilder.setCancelable(false);
        AlertDialog lDialog = lBuilder.show();
        TextView lMessageText = (TextView) lDialog.findViewById(android.R.id.message);
        lMessageText.setGravity(Gravity.CENTER);


    }


    /**
     * Show alert dialog
     *
     * @param pMessage
     * @param pPositiveButton
     * @param pContext
     * @param pDialogIdentifier
     */
    public void showAlertDialogWithTitle(String pTitle,String pMessage,
                                String pPositiveButton,
                                Context pContext, int pDialogIdentifier) {


        mDialogClickInterface = (DialogClickInterface) pContext;
        mDialogIdentifier = pDialogIdentifier;
        AlertDialog.Builder lBuilder = new AlertDialog.Builder(pContext)
                .setMessage(Html.fromHtml("<b>" + pMessage + "</b>"))
                .setTitle(pTitle)
                .setPositiveButton(pPositiveButton, this);
        lBuilder.setCancelable(false);
        AlertDialog lDialog = lBuilder.show();
        TextView lMessageText = (TextView) lDialog.findViewById(android.R.id.message);
        lMessageText.setGravity(Gravity.CENTER);

    }


    /**
     * Show alert dialog
     *
     * @param pMessage
     * @param pPositiveButton
     * @param pContext
     * @param pDialogIdentifier
     */
    public void showAlertDialog(String pMessage,
                                String pPositiveButton,
                                Context pContext, int pDialogIdentifier) {


        mDialogClickInterface = (DialogClickInterface) pContext;
        mDialogIdentifier = pDialogIdentifier;
        AlertDialog.Builder lBuilder = new AlertDialog.Builder(pContext)
                .setMessage(Html.fromHtml("<b>" + pMessage + "</b>"))
                        .setPositiveButton(pPositiveButton, this);
        lBuilder.setCancelable(false);
        AlertDialog lDialog = lBuilder.show();
        TextView lMessageText = (TextView) lDialog.findViewById(android.R.id.message);
        lMessageText.setGravity(Gravity.CENTER);

    }


    @Override
    public void onClick(DialogInterface pDialog, int pWhich) {

        switch (pWhich) {
            case DialogInterface.BUTTON_POSITIVE:
                mDialogClickInterface.onClickPositiveButton(pDialog, mDialogIdentifier);

                break;
            case DialogInterface.BUTTON_NEGATIVE:
                mDialogClickInterface.onClickNegativeButton(pDialog, mDialogIdentifier);
                break;

        }

    }

    @Override
    public void onClickPositiveButton(DialogInterface pDialog, int pDialogIntefier) {


    }

    @Override
    public void onClickNegativeButton(DialogInterface pDialog, int pDialogIntefier) {

    }

}
