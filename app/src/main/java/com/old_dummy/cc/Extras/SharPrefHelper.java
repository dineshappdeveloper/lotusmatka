package com.old_dummy.cc.Extras;

import android.content.Context;
import android.content.SharedPreferences;

public class SharPrefHelper {

    public static String SHARE_PRE_NAME = "sattaMatka";
    public static String KEY_PERSON_NAME = "personName";
    public static String KEY_PENDING_NOTICE = "pendingNotice";
    public static String KEY_MOBILE_NUMBER = "mobileNumber";
    public static String KEY_USER_EMAIL = "UserEmail";
    public static String KEY_USER_PASSWORD = "UserPassword";
    public static String KEY_BANK_HOLDER_NAME = "bankHolderName";
    public static String KEY_BANK_AC_NUMBER = "bankAccountNumber";
    public static String KEY_BANK_IFSC_CODE = "bankIfscCode";
    public static String KEY_BANK_NAME = "bankName";
    public static String KEY_BANK_ADDRESS = "bankAddress";
    public static String KEY_PAYTM_UPI = "paytmUPI";
    public static String KEY_PHONEPE_UPI = "phonePeUPI";
    public static String KEY_GOOGLEPAY_UPI = "googlePayUPI";
    public static String KEY_MARQUEE_TEXT = "marqueeText";
    public static String KEY_CONTACT_NUMBER1 = "contactNumber1";
    public static String KEY_CONTACT_NUMBER2 = "contactNumber2";
    public static String KEY_WHATSAPP_NUMBER = "whatsappNumber";
    public static String KEY_CONTACT_EMAIL = "contactEmail";
    public static String KEY_BANNER_IMAGES1 = "bannerImages1";
    public static String KEY_BANNER_IMAGES2 = "bannerImages2";
    public static String KEY_BANNER_IMAGES3 = "bannerImages3";
    static String KEY_LOGIN_SUCCESS = "loginSuccess";
    static String KEY_ACTIVE_USER = "activeUser";
    static String KEY_LOGIN_TOKEN = "logInToken";
    public static String KEY_FIREBASE_TOKEN = "fbToken";
    static String KEY_USER_POINTS = "userPoints";
    static String KEY_TRANSFER_POINTS = "transferPoints";
    public static String KEY_ADD_FUND_UPI_ID = "addFundUpiID";
    public static String KEY_ADD_FUND_UPI_NAME = "addFundUpiName";
    public static String KEY_MIN_WITHDRAW_POINTS = "minWithdrawPoints";
    public static String KEY_MAX_WITHDRAW_POINTS = "maxWithdrawPoints";
    public static String KEY_MIN_TRANSFER_POINTS = "minTransferPoints";
    public static String KEY_MAX_TRANSFER_POINTS = "maxTransferPoints";
    public static String KEY_MIN_BID_AMOUNT = "minBidAmount";
    public static String KEY_MAX_BID_AMOUNT = "maxBidAmount";
    public static String KEY_MIN_ADD_FUND_POINTS = "minAddFundPoints";
    public static String KEY_MAX_ADD_FUND_POINTS = "maxAddFundPoints";
    public static String KEY_FIREBASE_ENABLE = "firebaseEnable";

    public static String KEY_WITHDRAW_PROOF = "withdrawProof";
    public static String KEY_MAIN_MARKET_STATUS = "mainMarketStatus";
    public static String KEY_STARLINE_MARKET_STATUS = "starlineMarketStatus";
    public static String KEY_GALIDESAWAR_MARKET_STATUS = "galidesawarMarketStatus";
    public static String KEY_BANNER_STATUS = "bannerStatus";
    public static String KEY_MARQUEE_STATUS = "marqueeStatus";
    public static String KEY_BANNER_LIST = "bannerList";
    public static String KEY_App_Details = "appDetails";
    public static String KEY_VIP_STATUS = "vipStatus";
    public static String KEY_TELEGRAM = "telegram";
    public static String KEY_WELCOME_MSG = "welcomeMessage";
    public static String KEY_APP_LINK = "appLink";
    public static String KEY_PLAY_LINK = "playLink";
    public static String KEY_ADMIN_MESSAGE = "adminMessage";

    public static SharedPreferences getPreference(Context context){
        return context.getSharedPreferences(SHARE_PRE_NAME, Context.MODE_PRIVATE);
    }

    //setter
    public static void setPreferenceData(Context context, String KEY, String data){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.putString(KEY, data);
        editor.apply();
    }
    public static void setBooleanData(Context context, String KEY, boolean data){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.putBoolean(KEY, data);
        editor.apply();
    }

    public static void setMinMaxData(Context context, String KEY, String amount ){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.putString(KEY, amount);
        editor.apply();
    }

    public static void setActiveUser(Context context, String status){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.putString(KEY_ACTIVE_USER, status);
        editor.apply();
    }
    public static void setVipStatus(Context context, Boolean status){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.putBoolean(KEY_VIP_STATUS, status);
        editor.apply();
    }
    public static void setTransferPoints(Context context , boolean status){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.putBoolean(KEY_TRANSFER_POINTS, status);
        editor.apply();
    }
    public static void setSignUpData(Context context, String KEY, String data ){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.putString(KEY, data);
        editor.apply();
    }
    public static void setBankDetails(Context context, String KEY, String details){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.putString(KEY, details);
        editor.apply();
    }

    public static void setBannerImages(Context context, String  KEY,String imageUrl){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.putString(KEY, imageUrl);
        editor.apply();
    }

    public static void setContactDetails(Context context,String KEY ,String details){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.putString(KEY, details);
        editor.apply();
    }
    public static void setAddFundUPI(Context context,String KEY,String upi){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.putString(KEY, upi);
        editor.apply();
    }
    public static void setUserPoints(Context context, String points){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.putString(KEY_USER_POINTS, points);
        editor.apply();
    }
    public static Boolean getVipStatus(Context context){
        SharedPreferences sharedPreferences = getPreference(context);
        return sharedPreferences.getBoolean(KEY_VIP_STATUS, false);
    }
    public static void setLoginSuccess(Context context, boolean status){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.putBoolean(KEY_LOGIN_SUCCESS, status);
        editor.apply();
    }
    public static void setLoginToken(Context context, String token){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.putString(KEY_LOGIN_TOKEN, token);
        editor.apply();
    }


    public static void setClearData(Context context){
        SharedPreferences.Editor editor = getPreference(context).edit();
        editor.clear();
        editor.apply();
    }




    //getter
    public static String getPreferenceData(Context context, String KEY){
        SharedPreferences sharedPreferences = getPreference(context);
        return sharedPreferences.getString(KEY, null);
    }
    public static boolean getBooleanData(Context context, String KEY, boolean defaultValue){
        SharedPreferences sharedPreferences = getPreference(context);
        return sharedPreferences.getBoolean(KEY, defaultValue);
    }
    public static String getMinMaxData(Context context, String KEY){
        SharedPreferences sharedPreferences = getPreference(context);
        return sharedPreferences.getString(KEY, null);
    }
    public static String getSignUpData(Context context, String KEY){
        SharedPreferences sharedPreferences = getPreference(context);
        return sharedPreferences.getString(KEY, null);
    }
    public static String getBankDetails(Context context, String KEY){
        SharedPreferences sharedPreferences = getPreference(context);
        return sharedPreferences.getString(KEY, null);
    }
    public static String getContactDetails(Context context, String KEY){
        SharedPreferences sharedPreferences = getPreference(context);
        return sharedPreferences.getString(KEY, null);
    }

    public static String getAddFundUpiId(Context context, String KEY){
        SharedPreferences sharedPreferences = getPreference(context);
        return sharedPreferences.getString(KEY, "");
    }
    public static String getActiveUser(Context context){
        SharedPreferences sharedPreferences = getPreference(context);
        return sharedPreferences.getString(KEY_ACTIVE_USER, "2");
    }
    public static boolean getLoginSuccess(Context context){
        SharedPreferences sharedPreferences = getPreference(context);
        return sharedPreferences.getBoolean(KEY_LOGIN_SUCCESS,false);
    }
    public static String getLogInToken(Context context){
        SharedPreferences sharedPreferences = getPreference(context);
        return sharedPreferences.getString(KEY_LOGIN_TOKEN, null);
    }
    public static String getUserPoints(Context context){
        SharedPreferences sharedPreferences = getPreference(context);
        return sharedPreferences.getString(KEY_USER_POINTS, "0");
    }

    public static boolean getTransferPoint(Context context){
        SharedPreferences sharedPreferences = getPreference(context);
        return sharedPreferences.getBoolean(KEY_TRANSFER_POINTS, false);
    }

}
