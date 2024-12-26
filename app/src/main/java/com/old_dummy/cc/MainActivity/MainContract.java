package com.old_dummy.cc.MainActivity;

import android.app.Activity;

import com.old_dummy.cc.Models.AppDetailsModel;
import com.old_dummy.cc.Models.GameListModel;
import com.old_dummy.cc.Models.LoginModel;
import com.old_dummy.cc.Models.UserStatusModel;

public interface MainContract {
    interface View{
        void showProgressBar();
        void showSwipeProgressBar();
        void hideProgressBar();
        void hideSwipeProgressBar();
        void gameListApiResponse(GameListModel gameListModel);
        void userStatusApiResponse(UserStatusModel.Data userStatusData);
        void appDetailsApiResponse(AppDetailsModel appDetailsModel);
        void userDetailsApiResponse(LoginModel loginModel);
        void message(String msg);
        void destroy(String msg);
    }
    interface ViewModel{
        interface OnFinishedListener{
            void gameListFinished(GameListModel gameListModel);
            void userStatusFinished(UserStatusModel.Data userStatusData);
            void appDetailsFinished(AppDetailsModel appDetailsModel);
            void userDetailsFinished(LoginModel loginModel);
            void message(String msg);
            void destroy(String msg);
            void failure(Throwable t);
        }
        void callGameListApi(OnFinishedListener onFinishedListener, String token);
        void callUserStatusApi(OnFinishedListener onFinishedListener, String token);
        void callAppDetailsApi(OnFinishedListener onFinishedListener,String token);
        void callUserDetailsApi(OnFinishedListener onFinishedListener, String token);
    }
    interface Presenter{
        void gameListApi(String token);
        void userStatusApi(String token);
        void appDetailsApi(String token);
        void userDetailsApi(String token);

        void profile(Activity activity);
        void funds(Activity activity);
        void withdrawPoints(Activity activity);
        void walletStatement(Activity activity);
        void transferPoints(Activity activity);
        void addFund(Activity activity);
        void upiDetails(Activity activity, int upi);
        void history(Activity activity, int history);
        void gameRates(Activity activity, int i);
        void contactUs(Activity activity);
        void enquiry(Activity activity);
        void shareWithFriends(Activity activity);
        void rateApp(Activity activity);
        void changePassword(Activity activity);
        void logout(Activity activity);
        void privacyPolicy(Activity activity);

        void whatsApp(Activity activity);
        void call(Activity activity);
        void playStarLine(Activity activity);

        void back(Activity activity);
    }
}
