package com.old_dummy.cc.SecurityPinActivity;

import com.old_dummy.cc.Models.AppDetailsModel;
import com.old_dummy.cc.Models.LoginModel;

public interface SecurityPinContract {

    interface View{
        void showProgressBar();
        void hideProgressBar();
        void ApiResponse(LoginModel loginModel);
        void onForgotPinApiResponse(String number);
        void onAppDetailsApiResponse(AppDetailsModel.Data data);
        void onUserDetailsApiResponse(LoginModel.Data data);
        void message(String msg);
        void destroy(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void apiFinished(LoginModel loginModel);
            void onForgotPinFinished(String number);
            void onAppDetailsApiFinished(AppDetailsModel.Data data);
            void onUserDetailsApiFinished(LoginModel.Data data);
            void message(String msg);
            void destroy(String msg);
            void failure(Throwable t);
        }
        void callApi(OnFinishedListener onFinishedListener, String token , String pin);
        void callApiForgotPinResponse(OnFinishedListener onFinishedListener, String number);
        void callApiAppDetailsResponse(OnFinishedListener onFinishedListener, String token);
        void callApiUserDetailsResponse(OnFinishedListener onFinishedListener, String token);
    }

    interface Presenter{
        void api(String token,String pin);
        void resetPinBtn(String number);
        void doAppDetailsApi(String token);
        void doUserDetailsApi(String token);
    }
}
