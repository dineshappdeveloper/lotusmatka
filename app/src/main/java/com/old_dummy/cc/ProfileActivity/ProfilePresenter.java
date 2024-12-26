package com.old_dummy.cc.ProfileActivity;

import com.old_dummy.cc.Models.LoginModel;

public class ProfilePresenter implements ProfileContract.ViewModel.OnFinishedListener, ProfileContract.Presenter{
    ProfileContract.View view;
    ProfileContract.ViewModel viewModel;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
        viewModel = new ProfileViewModel();
    }

    @Override
    public void finished(LoginModel.Data data) {
        if (view!=null){
            view.hideProgressBar();
            view.apiResponse(data);
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
    public void api(String logInToken, String email, String name) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callApi(this,logInToken,email, name);
    }
}
