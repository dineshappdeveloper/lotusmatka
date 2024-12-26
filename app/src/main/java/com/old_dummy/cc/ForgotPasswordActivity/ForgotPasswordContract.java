package com.old_dummy.cc.ForgotPasswordActivity;

public interface ForgotPasswordContract {

    interface View{
        void showProgressBar();
        void hideProgressBar();
        void apiResponse();
        void message(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void finished();
            void message(String msg);
            void failure(Throwable t);
        }
        void callApi(OnFinishedListener onFinishedListener, String number);

    }

    interface Presenter{
        void api(String number);
    }
}
