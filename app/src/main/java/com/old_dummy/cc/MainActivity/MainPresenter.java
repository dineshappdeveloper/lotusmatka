package com.old_dummy.cc.MainActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.old_dummy.cc.Api.ApiUrl;
import com.old_dummy.cc.ChangePasswordActivity.ChangePasswordActivity;
import com.old_dummy.cc.ContactUsActivity.ContactUsActivity;
import com.old_dummy.cc.Extras.SharPrefHelper;
import com.old_dummy.cc.FundsActivity.FundsActivity;
import com.old_dummy.cc.GameRateActivity.GameRatesActivity;
import com.old_dummy.cc.Models.AppDetailsModel;
import com.old_dummy.cc.Models.GameListModel;
import com.old_dummy.cc.Models.LoginModel;
import com.old_dummy.cc.Models.UserStatusModel;
import com.old_dummy.cc.MyHistoryActivity.MyHistoryActivity;
import com.old_dummy.cc.ProfileActivity.ProfileActivity;
import com.old_dummy.cc.R;
import com.old_dummy.cc.SplashActivity.SplashActivity;
import com.old_dummy.cc.StarLineActivity.StarLineActivity;
import com.old_dummy.cc.TopUpActivity.TopUpActivity;
import com.old_dummy.cc.TransferPointsActivity.TransferPointsActivity;
import com.old_dummy.cc.UPIDetailsActivity.UPIDetailsActivity;
import com.old_dummy.cc.WalletActivity.WalletActivity;
import com.old_dummy.cc.WithdrawActivity.WithdrawActivity;

import java.lang.reflect.Type;

public class MainPresenter implements MainContract.ViewModel.OnFinishedListener, MainContract.Presenter{

    MainContract.View view;
    MainContract.ViewModel viewModel;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        viewModel = new MainViewModel();
    }

    @Override
    public void gameListFinished(GameListModel gameListModel) {
        if (view!=null){
            view.hideProgressBar();
            view.gameListApiResponse(gameListModel);
        }
    }

    @Override
    public void userStatusFinished(UserStatusModel.Data userStatusData) {
        if (view!=null){
            view.hideSwipeProgressBar();
            view.userStatusApiResponse(userStatusData);
        }
    }

    @Override
    public void appDetailsFinished(AppDetailsModel appDetailsModel) {
        if (view!=null){
            view.hideProgressBar();
            view.appDetailsApiResponse(appDetailsModel);
        }
    }

    @Override
    public void userDetailsFinished(LoginModel loginModel) {
        if (view!=null){
            view.hideProgressBar();
            view.userDetailsApiResponse(loginModel);
        }
    }

    @Override
    public void message(String msg) {
        if (view!=null){
            view.message(msg);
        }
    }

    @Override
    public void destroy(String msg) {
        if (view!=null){
            view.hideProgressBar();
            view.destroy(msg);
        }
    }

    @Override
    public void failure(Throwable t) {
        if (view!=null){
            view.hideProgressBar();
            view.hideSwipeProgressBar();
        }
    }

    @Override
    public void gameListApi(String token) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callGameListApi(this, token);
    }

    @Override
    public void userStatusApi(String token) {
        if (view!=null){
            view.showSwipeProgressBar();
        }
        viewModel.callUserStatusApi(this, token);
    }

    @Override
    public void appDetailsApi(String token) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callAppDetailsApi(this, token);
    }

    @Override
    public void userDetailsApi(String token) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callUserDetailsApi(this, token);
    }

    @Override
    public void profile(Activity activity) {
        activity.startActivity(new Intent(activity, ProfileActivity.class));
    }

    @Override
    public void funds(Activity activity) {
        activity.startActivity(new Intent(activity, FundsActivity.class));
    }

    @Override
    public void withdrawPoints(Activity activity) {
        activity.startActivity(new Intent(activity, WithdrawActivity.class));
    }

    @Override
    public void walletStatement(Activity activity) {
        activity.startActivity(new Intent(activity, WalletActivity.class));
    }

    @Override
    public void transferPoints(Activity activity) {
        activity.startActivity(new Intent(activity, TransferPointsActivity.class));
    }

    @Override
    public void addFund(Activity activity) {
        activity.startActivity(new Intent(activity, TopUpActivity.class));
    }

    @Override
    public void upiDetails(Activity activity, int upi) {
        Intent intent = new Intent(activity, UPIDetailsActivity.class);
        intent.putExtra(activity.getString(R.string.upi), upi);
        activity.startActivity(intent);
    }

    @Override
    public void history(Activity activity, int history) {
        activity.startActivity(new Intent(activity, MyHistoryActivity.class));
    }

    @Override
    public void gameRates(Activity activity, int i) {
        Intent gameRates = new Intent(activity, GameRatesActivity.class);
        gameRates.putExtra(activity.getString(R.string.main_activity), i);
        activity.startActivity(gameRates);
    }

    @Override
    public void contactUs(Activity activity) {
        activity.startActivity(new Intent(activity, ContactUsActivity.class));
    }

    public void enquiry(Activity activity) {

    }

    @Override
    public void shareWithFriends(Activity activity) {
        AppDetailsModel.Data data = null;
        Gson gson = new Gson();
        Type type = new TypeToken<AppDetailsModel.Data>() {
        }.getType();
        try {
            data = gson.fromJson(SharPrefHelper.getPreferenceData(activity,SharPrefHelper.KEY_App_Details), type);
        } catch (Exception e) {
            System.out.println("json conversion failed");
        }
        String url = data.getApp_link();
        String msg = data.getShare_message();
        String shareMsg = msg +"\n\n"+url;
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,shareMsg);
        sendIntent.setType("text/plain");
        activity.startActivity(sendIntent);
    }

    @Override
    public void rateApp(Activity activity) {
        AppDetailsModel.Data data = null;
        Gson gson = new Gson();
        Type type = new TypeToken<AppDetailsModel.Data>() {
        }.getType();
        try {
            data = gson.fromJson(SharPrefHelper.getPreferenceData(activity,SharPrefHelper.KEY_App_Details), type);
        } catch (Exception e) {
            System.out.println("json conversion failed");
        }
        String url = data.getApp_link();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        activity.startActivity(i);
    }

    @Override
    public void changePassword(Activity activity) {
        String[] arrayStrings = new String[]{SharPrefHelper.getSignUpData(activity, SharPrefHelper.KEY_MOBILE_NUMBER), "firebase_mobile_token"};
        Intent changePassword = new Intent(activity, ChangePasswordActivity.class);
        changePassword.putExtra(activity.getString(R.string.mobile_number), arrayStrings);
        activity.startActivity(changePassword);
    }

    @Override
    public void logout(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.dialog_logout, null);
        builder.setView(view);

        MaterialTextView yesBtn = view.findViewById(R.id.yesBtn);
        MaterialTextView noBtn = view.findViewById(R.id.noBtn);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.show();

        yesBtn.setOnClickListener(v -> {
            SharPrefHelper.setLoginSuccess(activity, false);
            SharPrefHelper.setClearData(activity);
            Intent logOut= new Intent(activity, SplashActivity.class);
            logOut.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            activity.startActivity(logOut);
        });
        noBtn.setOnClickListener(v -> {
            alertDialog.dismiss();
        });

        alertDialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(activity,R.drawable.rounded_corner_white));
        alertDialog.getWindow().setLayout(850, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void privacyPolicy(Activity activity) {
        String url = ApiUrl.BASE_URL+"privacy_policy";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        activity.startActivity(i);
    }

    @Override
    public void whatsApp(Activity activity) {
        AppDetailsModel.Data data = null;
        Gson gson = new Gson();
        Type type = new TypeToken<AppDetailsModel.Data>() {
        }.getType();
        try {
            data = gson.fromJson(SharPrefHelper.getPreferenceData(activity,SharPrefHelper.KEY_App_Details), type);
        } catch (Exception e) {
            System.out.println("json conversion failed");
        }
        String adminMsg = data.getAdmin_message();

        if(adminMsg.equals("")){
            adminMsg = "Hello Sir, My Name is "+SharPrefHelper.getSignUpData(activity, SharPrefHelper.KEY_PERSON_NAME)+"\nMobile Number :"+SharPrefHelper.getSignUpData(activity,SharPrefHelper.KEY_MOBILE_NUMBER);
        }
        String url = "https://api.whatsapp.com/send?phone="+SharPrefHelper.getContactDetails(activity, SharPrefHelper.KEY_WHATSAPP_NUMBER)+"&text="+adminMsg;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        activity.startActivity(i);
    }

    @Override
    public void call(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CALL_PHONE}, 100);
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + SharPrefHelper.getContactDetails(activity, SharPrefHelper.KEY_CONTACT_NUMBER1)));
            activity.startActivity(callIntent);
        }
    }

    @Override
    public void playStarLine(Activity activity) {
        activity.startActivity(new Intent(activity, StarLineActivity.class));
    }

    @Override
    public void back(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.dialog_logout, null);
        builder.setView(view);

        MaterialTextView yesBtn = view.findViewById(R.id.yesBtn);
        MaterialTextView noBtn = view.findViewById(R.id.noBtn);
        MaterialTextView textView = view.findViewById(R.id.logoutText);
        MaterialTextView title = view.findViewById(R.id.title);
        textView.setText("Are you sure you want to exit?");

        yesBtn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(activity,R.color.blue_color)));
        noBtn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(activity,R.color.blue_color)));
        yesBtn.setTextColor(ContextCompat.getColor(activity,R.color.white));
        noBtn.setTextColor(ContextCompat.getColor(activity,R.color.white));

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.show();


        yesBtn.setOnClickListener(v -> {
            activity.finishAffinity();
        });
        noBtn.setOnClickListener(v -> {
            alertDialog.dismiss();
        });

        alertDialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(activity,R.drawable.rounded_corner_white));
        alertDialog.getWindow().setLayout(900, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
