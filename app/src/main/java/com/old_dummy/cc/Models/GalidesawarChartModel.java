package com.old_dummy.cc.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GalidesawarChartModel {

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
            @SerializedName("left_digit")
            private String leftDigit;
            @Expose
            @SerializedName("right_digit")
            private String rightDigit;

            public String getName() {
                return name;
            }

            public String getLeftDigit() {
                return leftDigit;
            }

            public String getRightDigit() {
                return rightDigit;
            }
        }
    }
}
