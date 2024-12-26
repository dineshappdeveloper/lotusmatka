package com.old_dummy.cc.SignUpActivity;

import android.app.Activity;

public interface SignUpContract {
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
        void callApi(OnFinishedListener onFinishedListener, String personName, String mobileNumber, String password, String pinCode);
    }
    interface Presenter{
        void api(String personName, String mobileNumber, String password, String pinCode);
        void login(Activity activity);
    }
}
