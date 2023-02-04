package com.example.storeapps.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserAccountSetting implements Parcelable {

    public String username;
    public String display_name;
    public String profile_photo;
    public String website;
    public String descriptions;
    public String phone_number;
    public String address;
    public String user_id;

    public UserAccountSetting(String username, String display_name, String profile_photo, String website, String descriptions, String phone_number, String address, String user_id) {
        this.username = username;
        this.display_name = display_name;
        this.profile_photo = profile_photo;
        this.website = website;
        this.descriptions = descriptions;
        this.phone_number = phone_number;
        this.address = address;
        this.user_id = user_id;
    }

    protected UserAccountSetting(Parcel in) {
        username = in.readString();
        display_name = in.readString();
        profile_photo = in.readString();
        website = in.readString();
        descriptions = in.readString();
        phone_number = in.readString();
        address = in.readString();
        user_id = in.readString();
    }

    public static final Creator<UserAccountSetting> CREATOR = new Creator<UserAccountSetting>() {
        @Override
        public UserAccountSetting createFromParcel(Parcel in) {
            return new UserAccountSetting(in);
        }

        @Override
        public UserAccountSetting[] newArray(int size) {
            return new UserAccountSetting[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(display_name);
        parcel.writeString(profile_photo);
        parcel.writeString(website);
        parcel.writeString(descriptions);
        parcel.writeString(phone_number);
        parcel.writeString(address);
        parcel.writeString(user_id);
    }
}
