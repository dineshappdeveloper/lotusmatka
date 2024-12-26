package com.old_dummy.cc.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HowToPlayModel {

    @Expose
    @SerializedName("message")
    String message;

    @Expose
    @SerializedName("code")
    String code;

    @Expose
    @SerializedName("status")
    String status;

    @Expose
    @SerializedName("data")
    Data data;

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }

    public static class Data{
        @Expose
        @SerializedName("how_to_play")
        String howToPlay;

        @Expose
        @SerializedName("video_link")
        String videoLink;

        public String getHowToPlay() {
            return howToPlay;
        }

        public String getVideoLink() {
            return videoLink;
        }
    }
}
