package com.example.storeapps.Home;

import android.widget.ImageView;
import android.widget.VideoView;

public class VideoItem {
    public String username, videoName, videoDesc, videoSubDesc, videoUrl, shareCount, chatCount, likeCount;
    public Integer profileImageBottom, profileImage;

    public VideoItem(String username,
                     String videoName,
                     String videoDesc,
                     String videoSubDesc,
                     String videoUrl,
                     String shareCount,
                     String chatCount,
                     String likeCount,
                     Integer profileImageBottom,
                     Integer profileImage) {
        this.username = username;
        this.videoName = videoName;
        this.videoDesc = videoDesc;
        this.videoSubDesc = videoSubDesc;
        this.videoUrl = videoUrl;
        this.shareCount = shareCount;
        this.chatCount = chatCount;
        this.likeCount = likeCount;
        this.profileImageBottom = profileImageBottom;
        this.profileImage = profileImage;
    }

    public VideoItem(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getVideoSubDesc() {
        return videoSubDesc;
    }

    public void setVideoSubDesc(String videoSubDesc) {
        this.videoSubDesc = videoSubDesc;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getShareCount() {
        return shareCount;
    }

    public void setShareCount(String shareCount) {
        this.shareCount = shareCount;
    }

    public String getChatCount() {
        return chatCount;
    }

    public void setChatCount(String chatCount) {
        this.chatCount = chatCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getProfileImageBottom() {
        return profileImageBottom;
    }

    public void setProfileImageBottom(Integer profileImageBottom) {
        this.profileImageBottom = profileImageBottom;
    }

    public Integer getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Integer profileImage) {
        this.profileImage = profileImage;
    }
}
