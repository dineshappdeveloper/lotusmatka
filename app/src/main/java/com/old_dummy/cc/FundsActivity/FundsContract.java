package com.old_dummy.cc.FundsActivity;

import android.app.Activity;

import com.old_dummy.cc.Models.WalletStatementModel;
import com.old_dummy.cc.WalletActivity.WalletContract;

public interface FundsContract {

    interface View {
        void showProgressBar();
        void hideProgressBar();
        void apiResponse(WalletStatementModel walletStatementModel);
        void message(String msg);
        void destroy(String msg);
    }
    interface ViewModel{
        interface OnFinishedListener{
            void finished(WalletStatementModel walletStatementModel);
            void message(String msg);
            void destroy(String msg);
            void failure(Throwable t);
        }
        void callApi(FundsContract.ViewModel.OnFinishedListener onFinishedListener, String token );
    }
    interface Presenter{
        void addFund(Activity activity);
        void withdrawFund(Activity activity);
        void transferFund(Activity activity);
        void paymentMethods(Activity activity);
        void upi(Activity activity,int upi);
        void wallet(Activity activity);
        void api(String token);
    }

}
