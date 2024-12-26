package com.old_dummy.cc.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentRequestModel {
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

    public static class Data{
        @Expose
        @SerializedName("status")
        String status;

        @Expose
        @SerializedName("txnid")
        String txnid;

        @Expose
        @SerializedName("amount")
        String amount;

        @Expose
        @SerializedName("upi_url")
        String upi_url;

        public String getStatus() {
            return status;
        }

        public String getTxnid() {
            return txnid;
        }

        public String getAmount() {
            return amount;
        }

        public String getUpi_url() {
            return upi_url;
        }
    }

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
}
