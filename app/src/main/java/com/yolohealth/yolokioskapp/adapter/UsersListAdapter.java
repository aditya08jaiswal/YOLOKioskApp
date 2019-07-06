package com.yolohealth.yolokioskapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.yolohealth.yolokioskapp.R;
import com.yolohealth.yolokioskapp.helperclasses.BundleConstants;
import com.yolohealth.yolokioskapp.model.UserModel;
import com.yolohealth.yolokioskapp.utils.ImageUtil;
import com.yolohealth.yolokioskapp.widget.CustomButton;
import com.yolohealth.yolokioskapp.widget.CustomTextView;

import java.util.ArrayList;


/**
 * Created by yolo on 21/3/17.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {

    private final String mPhoneNumber;
    private String mOtp = "";
    public Context mContext;
    private final ArrayList<UserModel> mUsersList;
    private Bitmap bitmap;

    public UsersListAdapter(Context context, String otp, String mPhone, ArrayList<UserModel> userModels) {
        mContext = context;
        mUsersList = userModels;
        mOtp = otp;
        mPhoneNumber = mPhone;

    }

    @Override
    public UsersListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_items, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UsersListAdapter.ViewHolder holder, int position) {

        if (position == 0) {
            holder.mUserImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.create_user));
            holder.mUserGender.setVisibility(View.GONE);
            holder.mUserAge.setVisibility(View.GONE);
            holder.mUserName.setVisibility(View.GONE);
            holder.StartCheckup.setVisibility(View.INVISIBLE);
            holder.mCreateUser.setVisibility(View.VISIBLE);
            holder.mCreateUser.setText("Create User");
            holder.mUserDetail.setVisibility(View.GONE);
            holder.mUserImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent=new Intent(mContext, NewUserActivity.class);
//                    intent.putExtra(BundleConstants.PHONE,mPhoneNumber);
//                    intent.putExtra(BundleConstants.OTP,mOtp);
//                    mContext.startActivity(intent);
                }
            });

        } else {
            final UserModel userModel = mUsersList.get(position-1);

            if (!userModel.getProfilepic().equals(""))
                ImageUtil.loadFromURL(mContext, userModel.getProfilepic(), 0, holder.mUserImage, null);
            else
                holder.mUserImage.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_default_avatar));
            holder.mUserName.setText(userModel.getUsername());
            if (userModel.getGender().equals("m") || userModel.getGender().equals("Male")) {
                holder.mUserGender.setText("Gender : Male");
            } else {
                holder.mUserGender.setText("Gender : Female");
            }
holder.mUserDetail.setVisibility(View.VISIBLE);
            holder.mUserAge.setText("Age : " + userModel.getAge());
            holder.StartCheckup.setVisibility(View.VISIBLE);
            holder.mCreateUser.setVisibility(View.GONE);

        }


    }


    @Override
    public int getItemCount() {
        return mUsersList.size();
    }

    public void addVideos(ArrayList<UserModel> pList) {
        if (mUsersList.size() > 0) {
            mUsersList.clear();
            notifyDataSetChanged();
        }
        mUsersList.addAll(pList);
        notifyDataSetChanged();

    }

    public void refreshList(ArrayList<UserModel> pList) {
        mUsersList.clear();
        mUsersList.addAll(pList);
        notifyDataSetChanged();
    }

    public UserModel getItemAtPosition(int position) {
        if (mUsersList.size() > 0)
            return mUsersList.get(position);
        else
            return null;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mUserImage;
        private final CustomTextView mUserName;
        private final CustomTextView mUserGender;
        private final CustomTextView mUserAge;
        private final Button StartCheckup;
        private final CustomTextView mCreateUser;
        private final LinearLayout mUserDetail;
//        private final CustomTextView mDate;
//        private final CustomTextView mRole;

        public ViewHolder(View v) {
            super(v);

            mUserImage = (ImageView) v.findViewById(R.id.userProfile);
            mUserName = (CustomTextView) v.findViewById(R.id.username);
            mUserGender = v.findViewById(R.id.userGender);
            mUserAge = v.findViewById(R.id.userAge);
            StartCheckup = v.findViewById(R.id.startHealthCheckup);
            mCreateUser = v.findViewById(R.id.createUser);
            mUserDetail=v.findViewById(R.id.userDetails);
//            mDate=(CustomTextView)v.findViewById(R.id.tvConsultDate);
//            mRole=(CustomTextView)v.findViewById(R.id.role);

        }


    }

}

