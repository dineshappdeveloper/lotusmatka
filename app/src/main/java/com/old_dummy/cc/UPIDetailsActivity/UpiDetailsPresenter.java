package com.old_dummy.cc.UPIDetailsActivity;

public class UpiDetailsPresenter implements UpiDetailsContract.ViewModel.OnFinishedListener, UpiDetailsContract.Presenter{

    UpiDetailsContract.View view;
    UpiDetailsContract.ViewModel viewModel;

    public UpiDetailsPresenter(UpiDetailsContract.View view) {
        this.view = view;
        viewModel = new UpiDetailsViewModel();
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
    public void api(String token, String upiID, int upiActivity) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callApi(this, token,upiID, upiActivity);
    }
}
