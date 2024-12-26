package com.old_dummy.cc.NewPasswordActivity;

public class NewPasswordPresenter implements NewPasswordContract.ViewModel.OnFinishedListener, NewPasswordContract.Presenter{

    NewPasswordContract.View view;
    NewPasswordContract.ViewModel viewModel;

    public NewPasswordPresenter(NewPasswordContract.View view) {
        this.view = view;
        viewModel = new NewPasswordViewModel();
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
            view.message(msg);
        }
    }

    @Override
    public void destroy(String msg) {
        if (view!=null){
            view.destroy(msg);
            view.hideProgressBar();
        }
    }

    @Override
    public void failure(Throwable t) {
        if (view!=null){
            view.hideProgressBar();
        }
    }

    @Override
    public void api(String token, String mobile, String inputConformPass) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callApi(this,token, mobile,inputConformPass);
    }

    @Override
    public void apiChangePin(String token, String mobile, String pin) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callApiChangePin(this,token, mobile,pin);
    }
}
