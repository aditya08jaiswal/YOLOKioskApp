package com.yolohealth.yolokioskapp.model;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Created by yolo on 31/3/17.
 */

public class UserModel implements Serializable {
    private String username="";
    private String gender="";
    private int age=0;
    private int userid=0;
    private String phone="";
    private String email="";
    private String profilepic="";
    private String role;
    private String userWeight;
    private String userHeight;
    private String dob;

    public UserModel(){

    }
    private UserModel(Parcel in) {
        username=in.readString();
        gender=in.readString();
        phone=in.readString();
        email=in.readString();
        profilepic=in.readString();
        age=in.readInt();
        userid=in.readInt();
    }

//    {"servicetag":"Ajit","gender":"","age":1,"userid":3119,"phone":"8007755527","profilepic":null,"email":null}


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }


    public String getRole() {
        return role;
    }

    public void setUserWeight(String userWeight) {
        this.userWeight = userWeight;
    }

    public void setUserHeight(String userHeight) {
        this.userHeight = userHeight;
    }

    public String getUserWeight() {
        return userWeight;
    }

    public String getUserHeight() {
        return userHeight;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDob() {
        return dob;
    }
}
