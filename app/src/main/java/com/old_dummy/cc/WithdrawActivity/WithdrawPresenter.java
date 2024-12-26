package com.old_dummy.cc.WithdrawActivity;

import android.app.Activity;
import android.content.Intent;

import com.old_dummy.cc.BankDetailsActivity.BankDetailsActivity;
import com.old_dummy.cc.Models.WalletStatementModel;
import com.old_dummy.cc.R;
import com.old_dummy.cc.UPIDetailsActivity.UPIDetailsActivity;

public class WithdrawPresenter implements WithdrawContract.ViewModel.OnFinishedListener, WithdrawContract.Presenter{

    WithdrawContract.View view;
    WithdrawContract.ViewModel viewModel;

    public WithdrawPresenter(WithdrawContract.View view) {
        this.view = view;
        viewModel = new WithdrawViewModel();
    }

    @Override
    public void withdrawPointApiFinished() {
        if (view!=null){
            view.hideProgressBar();
            view.withdrawPointApiResponse();
        }
    }

    @Override
    public void withdrawStatementApiFinished(WalletStatementModel walletStatementModel) {
        if (view!=null){
            view.hideProgressBar();
            view.hideSwipeProgressBar();
            view.withdrawStatementApiResponse(walletStatementModel);
        }
    }
    @Override
    public void method(Activity activity, int upi) {
        Intent intent = new Intent(activity, UPIDetailsActivity.class);
        intent.putExtra(activity.getString(R.string.upi), upi);
        activity.startActivity(intent);
    }
    @Override
    public void bank(Activity activity){
        Intent intent = new Intent(activity, BankDetailsActivity.class);
        activity.startActivity(intent);
    }
    @Override
    public void message(String msg) {
        if (view!=null){
            view.hideProgressBar();
            view.hideSwipeProgressBar();
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
            view.hideSwipeProgressBar();
        }
    }

    @Override
    public void withdrawPointApi(String token, String points, String method) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callWithdrawPointApi(this, token, points,method);
    }

    @Override
    public void withdrawStatementApi(String token) {
        if (view!=null){
            view.showSwipeProgressBar();
        }
        viewModel.callWithdrawStatementApi(this, token);
    }
}
