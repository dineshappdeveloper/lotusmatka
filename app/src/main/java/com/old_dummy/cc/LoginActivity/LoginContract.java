package com.old_dummy.cc.LoginActivity;

import android.app.Activity;

import com.old_dummy.cc.Models.LoginModel;

public interface LoginContract {
    interface View{
        void showProgressBar();
        void hideProgressBar();
        void apiResponse(String loginToken);
        void finished(LoginModel.Data gameListModel);
        void message(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void finished(String token);
            void finishedUserDetails(LoginModel.Data gameListModel);
            void message(String msg);
            void failure(Throwable t);
        }
        void callApi(OnFinishedListener onFinishedListener,String number, String password);
        void callUserDetailsApi(OnFinishedListener onFinishedListener, String token);

    }

    interface Presenter{
        void api(String number, String password);
        void forgotPassword(Activity activity,String number);
        void signUp(Activity activity);
        void userDetails(String token);
    }
}
