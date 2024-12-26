package com.old_dummy.cc.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StarlineChartModel {

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
        @SerializedName("results")
        private List<Results> results;


        public String getDate() {
            return date;
        }

        public List<Results> getResults() {
            return results;
        }

        public static class Results{
            @Expose
            @SerializedName("name")
            private String name;
            @Expose
            @SerializedName("panna")
            private String panna;
            @Expose
            @SerializedName("digit")
            private String digit;

            public String getName() {
                return name;
            }

            public String getPanna() {
                return panna;
            }

            public String getDigit() {
                return digit;
            }
        }
    }
}
