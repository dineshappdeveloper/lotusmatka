package com.old_dummy.cc.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainGameChartModel {

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
    private List<Data> data;

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public List<Data> getData() {
        return data;
    }

    public  static  class Data{
        @Expose
        @SerializedName("date")
        private String date;
        @Expose
        @SerializedName("day")
        private String day;
        @Expose
        @SerializedName("open_digit")
        private String openDigit;
        @Expose
        @SerializedName("close_digit")
        private String closeDigit;
        @Expose
        @SerializedName("open_panna")
        private String openPanna;
        @Expose
        @SerializedName("close_panna")
        private String closePanna;
        @Expose
        @SerializedName("chart_color")
        private String chartColor;
        @Expose
        @SerializedName("id")
        private String id;
        @Expose
        @SerializedName("game_id")
        private String gameId;
        @Expose
        @SerializedName("decleared_at")
        private String declearedAt;

        public String getChartColor() {
            return chartColor;
        }

        public String getId() {
            return id;
        }

        public String getGameId() {
            return gameId;
        }

        public String getDeclearedAt() {
            return declearedAt;
        }

        public String getDate() {
            return date;
        }

        public String getDay() {
            return day;
        }

        public String getOpenDigit() {
            return openDigit;
        }

        public String getCloseDigit() {
            return closeDigit;
        }

        public String getOpenPanna() {
            return openPanna;
        }

        public String getClosePanna() {
            return closePanna;
        }
    }
}
