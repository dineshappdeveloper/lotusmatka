package com.old_dummy.cc.TransferPointsActivity;

public class TransferPointsPresenter implements TransferPointsContract.ViewModel.OnFinishedListener,TransferPointsContract.Presenter{

    TransferPointsContract.View view;
    TransferPointsContract.ViewModel viewModel;

    public TransferPointsPresenter(TransferPointsContract.View view) {
        this.view = view;
        viewModel = new TransferPointsViewModel();
    }

    @Override
    public void transferApiFinished() {
        if (view!=null){
            view.hideProgressBar();
            view.transferApiResponse();
        }
    }

    @Override
    public void verificationApiFinished(String name) {
        if (view!=null){
            view.hideProgressBar();
            view.verificationApiResponse(name);
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
    public void transferPointsApi(String token, String points, String userNumber) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callTransferPointsApi(this,token,points, userNumber);
    }

    @Override
    public void verificationApi(String token, String userNumber) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callVerificationApi(this, token, userNumber);
    }
}
