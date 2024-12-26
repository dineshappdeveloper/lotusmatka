package com.old_dummy.cc.WalletActivity;

import com.old_dummy.cc.Models.WalletStatementModel;

public interface WalletContract {
    interface View{
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
        void callApi(OnFinishedListener onFinishedListener, String token );

    }

    interface Presenter{
        void api(String token);
    }
}
