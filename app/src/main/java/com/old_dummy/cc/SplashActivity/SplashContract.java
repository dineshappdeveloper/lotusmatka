package com.old_dummy.cc.SplashActivity;

import com.old_dummy.cc.Models.AppDetailsModel;

public interface SplashContract {

    interface View{
        void apiResponse(AppDetailsModel.Data data);
        void apiPinResponse(String token);
        void message(String msg);
        void destroy();
    }

    interface ViewModel{
        interface OnFinishedListener{
            void finished(AppDetailsModel.Data data);
            void finishedPin(String token);
            void message(String msg);
            void destroy();
            void failure(Throwable t);
        }
        void callApi(OnFinishedListener onFinishedListener,String token);
        void callPinApi(OnFinishedListener onFinishedListener,String token,String pin);

    }

    interface Presenter{
        void api(String token);
        void pinApi(String token, String pin);
    }
}
