package com.old_dummy.cc.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonModel {

    @Expose
    @SerializedName("message")
    String message;

    @Expose
    @SerializedName("code")
    String code;

    @Expose
    @SerializedName("status")
    String status;

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}
