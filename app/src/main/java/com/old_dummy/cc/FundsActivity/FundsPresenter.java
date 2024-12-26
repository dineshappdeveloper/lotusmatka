package com.old_dummy.cc.FundsActivity;

import android.app.Activity;
import android.content.Intent;

import com.old_dummy.cc.BankDetailsActivity.BankDetailsActivity;
import com.old_dummy.cc.BankMethodActivity.BankMethodActivity;
import com.old_dummy.cc.Models.WalletStatementModel;
import com.old_dummy.cc.R;
import com.old_dummy.cc.TopUpActivity.TopUpActivity;
import com.old_dummy.cc.TransferPointsActivity.TransferPointsActivity;
import com.old_dummy.cc.UPIDetailsActivity.UPIDetailsActivity;
import com.old_dummy.cc.WalletActivity.WalletActivity;
import com.old_dummy.cc.WalletActivity.WalletContract;
import com.old_dummy.cc.WalletActivity.WalletViewModel;
import com.old_dummy.cc.WithdrawActivity.WithdrawActivity;

public class FundsPresenter implements FundsContract.Presenter, FundsContract.ViewModel.OnFinishedListener{
    FundsContract.View view;
    FundsContract.ViewModel viewModel;
    public FundsPresenter(FundsContract.View view) {
        this.view = view;
        viewModel = new FundsViewModel();
    }


    @Override
    public void addFund(Activity activity) {
        activity.startActivity(new Intent(activity, TopUpActivity.class));
    }

    @Override
    public void withdrawFund(Activity activity) {
        activity.startActivity(new Intent(activity, WithdrawActivity.class));
    }

    @Override
    public void transferFund(Activity activity) {
        activity.startActivity(new Intent(activity, TransferPointsActivity.class));
    }

    @Override
    public void paymentMethods(Activity activity) {
        Intent intent = new Intent(activity, BankMethodActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void upi(Activity activity, int upi) {
        Intent intent = new Intent(activity, UPIDetailsActivity.class);
        intent.putExtra(activity.getString(R.string.upi), upi);
        activity.startActivity(intent);
    }


    @Override
    public void wallet(Activity activity) {
        activity.startActivity(new Intent(activity, WalletActivity.class));
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
