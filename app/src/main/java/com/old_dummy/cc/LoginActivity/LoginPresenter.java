package com.old_dummy.cc.LoginActivity;

import android.app.Activity;
import android.content.Intent;

import com.old_dummy.cc.Models.LoginModel;
import com.old_dummy.cc.SignUpActivity.SignUpActivity;
import com.old_dummy.cc.ForgotPasswordActivity.ForgotPasswordActivity;
import com.old_dummy.cc.R;

import java.util.Objects;

public class LoginPresenter implements LoginContract.ViewModel.OnFinishedListener, LoginContract.Presenter{

    LoginContract.View view;
    LoginContract.ViewModel viewModel;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        viewModel = new LoginViewModel();
    }

    @Override
    public void finished(String token) {
        if (view!=null){
            view.hideProgressBar();
            view.apiResponse(token);
        }
    }
    @Override
    public void finishedUserDetails(LoginModel.Data gameListModel) {
        if(view!=null){
            view.hideProgressBar();
            view.finished(gameListModel);
        }
    }
    @Override
    public void userDetails(String token) {
        viewModel.callUserDetailsApi(this, token);
    }
    @Override
    public void message(String msg) {
        if (view!=null){
            view.hideProgressBar();
            view.message(msg);
        }
    }

    @Override
    public void failure(Throwable t) {
        if (view!=null){
            view.hideProgressBar();
        }
    }

    @Override
    public void api(String number, String password) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callApi(this, number, password);
    }


    @Override
    public void forgotPassword(Activity activity, String number) {
        Intent intent = new Intent(activity, ForgotPasswordActivity.class);
        if (Objects.equals(number, "")){
            intent.putExtra(activity.getString(R.string.mobile_number), number);
        }
        activity.startActivity(intent);
    }

    @Override
    public void signUp(Activity activity) {
        activity.startActivity(new Intent(activity, SignUpActivity.class));
    }
}
