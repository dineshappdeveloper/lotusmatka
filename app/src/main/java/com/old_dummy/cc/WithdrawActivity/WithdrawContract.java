package com.old_dummy.cc.WithdrawActivity;

import android.app.Activity;

import com.old_dummy.cc.Models.WalletStatementModel;

public interface WithdrawContract {
    interface View{
        void showProgressBar();
        void showSwipeProgressBar();
        void hideProgressBar();
        void hideSwipeProgressBar();
        void withdrawPointApiResponse();
        void withdrawStatementApiResponse(WalletStatementModel walletStatementModel);
        void message(String msg);
        void destroy(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void withdrawPointApiFinished();
            void withdrawStatementApiFinished(WalletStatementModel walletStatementModel);
            void message(String msg);
            void destroy(String msg);
            void failure(Throwable t);
        }
        void callWithdrawPointApi(OnFinishedListener onFinishedListener, String token, String points, String method);
        void callWithdrawStatementApi(OnFinishedListener onFinishedListener, String token);

    }

    interface Presenter{
        void withdrawPointApi(String token,String points, String method);
        void withdrawStatementApi(String token);
        void method(Activity activity, int upi);
        void bank(Activity activity);
    }
}
