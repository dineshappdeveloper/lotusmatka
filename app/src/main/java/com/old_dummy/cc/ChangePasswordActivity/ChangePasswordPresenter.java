package com.old_dummy.cc.ChangePasswordActivity;

public class ChangePasswordPresenter implements ChangePasswordContract.ViewModel.OnFinishedListener, ChangePasswordContract.Presenter{

    ChangePasswordContract.View view;
    ChangePasswordContract.ViewModel viewModel;

    public ChangePasswordPresenter(ChangePasswordContract.View view) {
        this.view = view;
        viewModel = new ChangePasswordViewModel();
    }

    @Override
    public void finished(String token) {
        if (view!=null){
            view.hideProgressBar();
            view.apiResponse(token);
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
    public void destroy(String msg) {
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
    public void api(String token, String mobile, String mobileToken, String inputConformPass) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callApi(this, token,mobile,mobileToken, inputConformPass);
    }
}
