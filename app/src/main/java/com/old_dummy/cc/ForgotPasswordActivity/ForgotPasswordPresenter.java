package com.old_dummy.cc.ForgotPasswordActivity;

public class ForgotPasswordPresenter implements ForgotPasswordContract.ViewModel.OnFinishedListener, ForgotPasswordContract.Presenter{

    ForgotPasswordContract.View view;
    ForgotPasswordContract.ViewModel viewModel;

    public ForgotPasswordPresenter(ForgotPasswordContract.View view) {
        this.view = view;
        viewModel = new ForgotPasswordViewModel();
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
    public void api(String number) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callApi(this,number);
    }
}
