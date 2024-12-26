package com.old_dummy.cc.SplashActivity;

import com.old_dummy.cc.Models.AppDetailsModel;

public class SplashPresenter implements SplashContract.ViewModel.OnFinishedListener, SplashContract.Presenter{

    SplashContract.View view;
    SplashContract.ViewModel viewModel;

    public SplashPresenter(SplashContract.View view) {
        this.view = view;
        viewModel = new SplashViewModel();
    }

    @Override
    public void finished(AppDetailsModel.Data data) {
        if (view!=null){
            view.apiResponse(data);
        }
    }

    @Override
    public void finishedPin(String token) {
        if (view!=null){
            view.apiPinResponse(token);
        }
    }

    @Override
    public void message(String msg) {
        if (view!=null){
            view.message(msg);
        }
    }

    @Override
    public void destroy() {
        if(view!=null){
            view.destroy();
        }
    }

    @Override
    public void failure(Throwable t) {

    }

    @Override
    public void api(String token) {
        viewModel.callApi(this,token);
    }

    @Override
    public void pinApi(String token, String pin) {
        viewModel.callPinApi(this,token,pin);
    }
}
