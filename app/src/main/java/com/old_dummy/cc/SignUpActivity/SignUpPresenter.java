package com.old_dummy.cc.SignUpActivity;

import android.app.Activity;
import android.content.Intent;

import com.old_dummy.cc.LoginActivity.LoginActivity;

public class SignUpPresenter implements SignUpContract.ViewModel.OnFinishedListener, SignUpContract.Presenter{

    SignUpContract.View view;
    SignUpContract.ViewModel viewModel;

    public SignUpPresenter(SignUpContract.View view) {
        this.view = view;
        viewModel = new SignUpViewModel();
    }

    @Override
    public void finished() {
        if (view!=null){
            view.hideProgressBar();
            view.apiResponse();
        }
    }

    @Override
    public void message(String msg) {
        if (view!=null){
            view.message(msg);
        }
    }

    @Override
    public void failure(Throwable t) {

    }

    @Override
    public void api(String personName, String mobileNumber, String password, String pinCode) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callApi(this, personName, mobileNumber, password, pinCode);
    }

    @Override
    public void login(Activity activity) {
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }
}
