package com.old_dummy.cc.MainGameListActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.core.app.ActivityCompat;

import com.old_dummy.cc.Extras.SharPrefHelper;
import com.old_dummy.cc.GalidesawarActivity.GalidesawarActivity;
import com.old_dummy.cc.Models.AppDetailsModel;
import com.old_dummy.cc.Models.GameListModel;
import com.old_dummy.cc.Models.LoginModel;
import com.old_dummy.cc.Models.UserStatusModel;
import com.old_dummy.cc.StarLineActivity.StarLineActivity;
import com.old_dummy.cc.TopUpActivity.TopUpActivity;
import com.old_dummy.cc.WithdrawActivity.WithdrawActivity;

import java.util.List;

public class MainGameListPresenter implements MainGameListContract.ViewModel.OnFinishedListener, MainGameListContract.Presenter{
    MainGameListContract.View view;
    MainGameListContract.ViewModel viewModel;

    public MainGameListPresenter(MainGameListContract.View view) {
        this.view = view;
        viewModel = new MainGameListViewModel();
    }

    @Override
    public void finished(List<GameListModel.Data> data) {
        if (view!=null){
            view.hideProgressBar();
            view.apiResponse(data);
        }
    }
    @Override
    public void finished2(UserStatusModel.Data data) {
        if (view!=null){
            view.apiResponse2(data);
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
        }
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
    public void api(String logInToken) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callApi(this,logInToken);
    }
    @Override
    public void api2(String logInToken) {
        viewModel.callApi2(this,logInToken);
    }

    @Override
    public void addFund(Context activity) {
        activity.startActivity(new Intent(activity, TopUpActivity.class));
    }

    @Override
    public void withdraw(Context activity) {
        activity.startActivity(new Intent(activity, WithdrawActivity.class));
    }

    @Override
    public void starline(Context activity) {
        activity.startActivity(new Intent(activity, StarLineActivity.class));
    }

    @Override
    public void galidesawar(Context activity) {
        activity.startActivity(new Intent(activity, GalidesawarActivity.class));
    }

    @Override
    public void whatsapp(Context activity) {
        String adminMsg = "Hello Sir, My Name is "+ SharPrefHelper.getSignUpData(activity, SharPrefHelper.KEY_PERSON_NAME)+"\nMobile Number :"+SharPrefHelper.getSignUpData(activity,SharPrefHelper.KEY_MOBILE_NUMBER);
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
}
