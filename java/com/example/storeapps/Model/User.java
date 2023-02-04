package com.example.storeapps.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public String user_id;
    public String phone_number;
    public String email;
    public String username;
    public String alamat;
    public String password;

    public User(String user_id, String phone_number, String email, String username, String alamat, String password) {
        this.user_id = user_id;
        this.phone_number = phone_number;
        this.email = email;
        this.username = username;
        this.alamat = alamat;
        this.password = password;
    }

    public User(String email, String username, String alamat, String password) {
        this.email = email;
        this.username = username;
        this.alamat = alamat;
        this.password = password;
    }

    protected User(Parcel in) {
        user_id = in.readString();
        phone_number = in.readString();
        email = in.readString();
        username = in.readString();
        alamat = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(user_id);
        parcel.writeString(phone_number);
        parcel.writeString(email);
        parcel.writeString(username);
        parcel.writeString(alamat);
        parcel.writeString(password);
    }
}
