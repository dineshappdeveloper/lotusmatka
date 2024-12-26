package com.old_dummy.cc.BankDetailsActivity;

public interface BankDetailsContract {

    interface View{
        void showProgressBar();
        void hideProgressBar();
        void apiResponse();
        void message(String msg);
        void destroy(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void finished();
            void message(String msg);
            void destroy(String msg);
            void failure(Throwable t);
        }
        void callApi(OnFinishedListener onFinishedListener, String token , String holderName, String accountNumber, String ifscCode, String bankName, String branchAddress);

    }

    interface Presenter{
        void api(String token,String holderName, String accountNumber, String ifscCode,String bankName, String branchAddress);
    }
}
