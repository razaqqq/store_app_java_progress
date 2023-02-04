package com.example.storeapps.Util;

import android.widget.ImageView;

public class MediaObjectVideoContainer {
    String username;
    String description;
    String subDescription;
    Integer videoSrc;
    Integer profileImageBottom;
    String shareNumber;
    String chatNumber;
    String likeNumber;
    Integer profileImage;

    public MediaObjectVideoContainer(String username, String description, String subDescription, Integer videoSrc, Integer profileImageBottom, String shareNumber, String chatNumber, String likeNumber, Integer profileImage) {
        this.username = username;
        this.description = description;
        this.subDescription = subDescription;
        this.videoSrc = videoSrc;
        this.profileImageBottom = profileImageBottom;
        this.shareNumber = shareNumber;
        this.chatNumber = chatNumber;
        this.likeNumber = likeNumber;
        this.profileImage = profileImage;
    }

    public MediaObjectVideoContainer() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubDescription() {
        return subDescription;
    }

    public void setSubDescription(String subDescription) {
        this.subDescription = subDescription;
    }

    public Integer getVideoSrc() {
        return videoSrc;
    }

    public void setVideoSrc(Integer videoSrc) {
        this.videoSrc = videoSrc;
    }

    public Integer getProfileImageBottom() {
        return profileImageBottom;
    }

    public void setProfileImageBottom(Integer profileImageBottom) {
        this.profileImageBottom = profileImageBottom;
    }

    public String getShareNumber() {
        return shareNumber;
    }

    public void setShareNumber(String shareNumber) {
        this.shareNumber = shareNumber;
    }

    public String getChatNumber() {
        return chatNumber;
    }

    public void setChatNumber(String chatNumber) {
        this.chatNumber = chatNumber;
    }

    public String getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(String likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Integer getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Integer profileImage) {
        this.profileImage = profileImage;
    }
}
