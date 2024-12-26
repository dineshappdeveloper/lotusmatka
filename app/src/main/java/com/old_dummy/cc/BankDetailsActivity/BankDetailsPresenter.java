package com.old_dummy.cc.BankDetailsActivity;

public class BankDetailsPresenter implements BankDetailsContract.Presenter, BankDetailsContract.ViewModel.OnFinishedListener{

    BankDetailsContract.View view;
    BankDetailsContract.ViewModel viewModel;

    public BankDetailsPresenter(BankDetailsContract.View view) {
        this.view = view;
        viewModel = new BankDetailsViewModel();
    }

    @Override
    public void api(String token, String holderName, String accountNumber, String ifscCode, String bankName, String branchAddress) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callApi(this, token, holderName, accountNumber, ifscCode, bankName, branchAddress);
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


}
