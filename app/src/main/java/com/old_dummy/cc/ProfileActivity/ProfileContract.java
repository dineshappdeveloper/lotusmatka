package com.old_dummy.cc.ProfileActivity;

import com.old_dummy.cc.Models.LoginModel;

public interface ProfileContract {
    interface View{
        void showProgressBar();
        void hideProgressBar();
        void apiResponse(LoginModel.Data data);
        void message(String msg);
        void destroy(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void finished(LoginModel.Data data);
            void message(String msg);
            void destroy(String msg);
            void failure(Throwable t);
        }
        void callApi(OnFinishedListener onFinishedListener, String logInToken, String email, String name);

    }

    interface Presenter{
        void api(String logInToken, String email, String name);
    }
}
