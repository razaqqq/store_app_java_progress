package com.example.storeapps.Model;

public class Videos {
    String videoTitle;
    String videooDescription;
    String videoSubDescription;
    String videoImageIcon;
    String videoLike;
    String videoComment;
    String videoUrl;

    public Videos(String videoTitle, String videooDescription, String videoSubDescription, String videoImageIcon, String videoLike, String videoComment, String videoUrl) {
        this.videoTitle = videoTitle;
        this.videooDescription = videooDescription;
        this.videoSubDescription = videoSubDescription;
        this.videoImageIcon = videoImageIcon;
        this.videoLike = videoLike;
        this.videoComment = videoComment;
        this.videoUrl = videoUrl;
    }

    public Videos()
    {

    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideooDescription() {
        return videooDescription;
    }

    public void setVideooDescription(String videooDescription) {
        this.videooDescription = videooDescription;
    }

    public String getVideoSubDescription() {
        return videoSubDescription;
    }

    public void setVideoSubDescription(String videoSubDescription) {
        this.videoSubDescription = videoSubDescription;
    }

    public String getVideoImageIcon() {
        return videoImageIcon;
    }

    public void setVideoImageIcon(String videoImageIcon) {
        this.videoImageIcon = videoImageIcon;
    }

    public String getVideoLike() {
        return videoLike;
    }

    public void setVideoLike(String videoLike) {
        this.videoLike = videoLike;
    }

    public String getVideoComment() {
        return videoComment;
    }

    public void setVideoComment(String videoComment) {
        this.videoComment = videoComment;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
