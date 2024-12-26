package com.old_dummy.cc.AddFundMethodsActivity;

import com.old_dummy.cc.Models.PaymentRequestModel;

public class FundMethodsPresenter implements AddFundMethodContract.ViewModel.OnFinishedListener, AddFundMethodContract.Presenter{

    AddFundMethodContract.View view;
    AddFundMethodContract.ViewModel viewModel;

    public FundMethodsPresenter(AddFundMethodContract.View view) {
        this.view = view;
        viewModel = new FundMethodsViewModel();
    }

    @Override
    public void finishedPaymentReceive(String message) {
        if (view!=null){
            view.apiResponsePaymentReceive(message);
        }
    }

    @Override
    public void finishedPaymentRequest(PaymentRequestModel.Data data) {
        if (view!=null){
            view.apiResponsePaymentRequest(data);
        }
    }

    @Override
    public void message(String msg) {
        if(view!=null){
            view.hideProgressBar();
            view.message(msg);
        }
    }

    @Override
    public void destroy(String msg) {
        if(view!=null){
            view.hideProgressBar();
        }
    }

    @Override
    public void failure(Throwable t) {
        if(view!=null){
            view.hideProgressBar();
        }
    }

    @Override
    public void apiPaymentReceive( String token,String amount, String methodName, String screenshot, String transactionId, String methodDetails) {
        if(view!=null){
            view.showProgressBar();
        }
        viewModel.callPaymentReceiveApi(this,token,amount, methodName,screenshot,transactionId,methodDetails);
    }

    @Override
    public void apiPaymentRequest(String token,String amount, String methodName) {
        if(view!=null){
            view.showProgressBar();
        }
        viewModel.callPaymentRequestApi(this,token,amount,methodName);
    }
}
