package com.example.storeapps.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Store implements Serializable {
    public String storeName;
    public String jenisPengiriman;
    public String alamat;
    public String email;
    public String noHP;
    public Boolean hasCreatedStore;

    public Store(String storeName, String jenisPengiriman, String alamat, String email, String noHP, Boolean hasCreatedStore) {
        this.storeName = storeName;
        this.jenisPengiriman = jenisPengiriman;
        this.alamat = alamat;
        this.email = email;
        this.noHP = noHP;
        this.hasCreatedStore = hasCreatedStore;
    }

    public Store()
    {

    }

    protected Store(Parcel in) {
        storeName = in.readString();
        jenisPengiriman = in.readString();
        alamat = in.readString();
        email = in.readString();
        noHP = in.readString();
        byte tmpHasCreatedStore = in.readByte();
        hasCreatedStore = tmpHasCreatedStore == 0 ? null : tmpHasCreatedStore == 1;
    }



    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getJenisPengiriman() {
        return jenisPengiriman;
    }

    public void setJenisPengiriman(String jenisPengiriman) {
        this.jenisPengiriman = jenisPengiriman;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public Boolean getHasCreatedStore() {
        return hasCreatedStore;
    }

    public void setHasCreatedStore(Boolean hasCreatedStore) {
        this.hasCreatedStore = hasCreatedStore;
    }






}
