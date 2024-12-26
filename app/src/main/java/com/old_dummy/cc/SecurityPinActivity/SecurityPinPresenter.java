package com.old_dummy.cc.SecurityPinActivity;

import com.old_dummy.cc.Models.AppDetailsModel;
import com.old_dummy.cc.Models.LoginModel;

public class SecurityPinPresenter implements SecurityPinContract.ViewModel.OnFinishedListener, SecurityPinContract.Presenter{

    SecurityPinContract.View view;
    SecurityPinContract.ViewModel viewModel;

    public SecurityPinPresenter(SecurityPinContract.View view) {
        this.view = view;
        viewModel = new SecurityPinViewModel();
    }

    @Override
    public void apiFinished(LoginModel loginModel) {
        if (view!=null){
            view.hideProgressBar();
            view.ApiResponse(loginModel);
        }
    }

    @Override
    public void onForgotPinFinished(String number) {
        if (view!=null){
            view.hideProgressBar();
            view.onForgotPinApiResponse(number);
        }
    }

    @Override
    public void onAppDetailsApiFinished(AppDetailsModel.Data data) {
        if (view!=null){
            view.hideProgressBar();
            view.onAppDetailsApiResponse(data);
        }
    }

    @Override
    public void onUserDetailsApiFinished(LoginModel.Data data) {
        if (view!=null){
            view.hideProgressBar();
            view.onUserDetailsApiResponse(data);
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
    public void api(String token, String pin) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callApi(this,token,pin);
    }

    @Override
    public void resetPinBtn(String number) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callApiForgotPinResponse(this, number);
    }

    @Override
    public void doAppDetailsApi(String token) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callApiAppDetailsResponse(this,token);
    }
    @Override
    public void doUserDetailsApi(String token) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callApiUserDetailsResponse(this,token);
    }
}
