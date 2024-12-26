package com.old_dummy.cc.TopUpActivity;

import com.old_dummy.cc.Models.PaymentConfigModel;

public class TopUpPresenter implements TopUpContract.ViewModel.OnFinishedListener, TopUpContract.Presenter{

    TopUpContract.View view;
    TopUpContract.ViewModel viewModel;

    public TopUpPresenter(TopUpContract.View view) {
        this.view = view;
        viewModel = new TopUpViewModel();
    }

    @Override
    public void finished(String message) {
        if (view!=null){
            view.apiResponse(message);
        }
    }

    @Override
    public void paymentConfigFinished(PaymentConfigModel.Data data) {
        if (view!=null){
            view.paymentConfigApiResponse(data);
        }
    }

    @Override
    public void message(String msg) {

    }

    @Override
    public void destroy(String msg) {

    }

    @Override
    public void failure(Throwable t) {

    }

    @Override
    public void api(String token, String amount) {
        viewModel.callApi(this,token,amount);
    }

    @Override
    public void paymentConfigApi(String token) {
        viewModel.callPaymentConfigApi(this,token);
    }


}
