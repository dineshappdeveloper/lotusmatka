package com.old_dummy.cc.MainGameListActivity;

import android.app.Activity;
import android.content.Context;

import com.old_dummy.cc.Models.AppDetailsModel;
import com.old_dummy.cc.Models.GameListModel;
import com.old_dummy.cc.Models.LoginModel;
import com.old_dummy.cc.Models.UserStatusModel;

import java.util.List;

public interface MainGameListContract {
    interface View{
        void showProgressBar();
        void hideProgressBar();
        void apiResponse(List<GameListModel.Data> data);
        void apiResponse2(UserStatusModel.Data data);
        void appDetailsApiResponse(AppDetailsModel appDetailsModel);
        void userDetailsApiResponse(LoginModel loginModel);
        void message(String msg);
        void destroy(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void finished(List<GameListModel.Data> data);
            void finished2(UserStatusModel.Data data);
            void appDetailsFinished(AppDetailsModel appDetailsModel);
            void userDetailsFinished(LoginModel loginModel);
            void message(String msg);
            void destroy(String msg);
            void failure(Throwable t);
        }
        void callApi(OnFinishedListener onFinishedListener, String logInToken);
        void callApi2(OnFinishedListener onFinishedListener, String logInToken);
        void callAppDetailsApi(OnFinishedListener onFinishedListener,String token);
        void callUserDetailsApi(OnFinishedListener onFinishedListener, String token);

    }

    interface Presenter{
        void api(String logInToken);
        void api2(String logInToken);
        void addFund(Context activity);
        void withdraw(Context activity);
        void starline(Context activity);
        void galidesawar(Context activity);
        void whatsapp(Context activity);
        void call(Activity activity);
        void appDetailsApi(String token);
        void userDetailsApi(String token);
    }
}
