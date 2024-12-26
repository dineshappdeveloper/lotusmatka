package com.old_dummy.cc.WalletActivity;

import com.old_dummy.cc.Models.WalletStatementModel;

public class WalletPresenter implements WalletContract.ViewModel.OnFinishedListener, WalletContract.Presenter{

    WalletContract.View view;
    WalletContract.ViewModel viewModel;

    public WalletPresenter(WalletContract.View view) {
        this.view = view;
        viewModel = new WalletViewModel();
    }

    @Override
    public void finished(WalletStatementModel walletStatementModel) {
        if (view!=null){
            view.hideProgressBar();
            view.apiResponse(walletStatementModel);
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
    public void api(String token) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callApi(this,token);
    }
}
