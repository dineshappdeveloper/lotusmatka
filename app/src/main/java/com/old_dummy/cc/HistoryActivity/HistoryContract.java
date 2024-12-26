package com.old_dummy.cc.HistoryActivity;

import com.old_dummy.cc.Models.GalidesawarWinModel;
import com.old_dummy.cc.Models.StarLineWinModel;
import com.old_dummy.cc.Models.WinModel;

public interface HistoryContract {
    interface View{
        void showProgressBar();
        void hideProgressBar();
        void mainHistoryApiResponse(WinModel winModel);
        void starLineHistoryApiResponse(StarLineWinModel starLineWinModel);
        void galidesawarHistoryApiResponse(GalidesawarWinModel starLineWinModel);
        void message(String msg);
        void destroy(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void mainHistoryFinished(WinModel winModel);
            void starLineHistoryFinished(StarLineWinModel starLineWinModel);
            void galidesawarHistoryFinished(GalidesawarWinModel starLineWinModel);
            void message(String msg);
            void destroy(String msg);
            void failure(Throwable t);
        }
        void callMainHistoryApi(OnFinishedListener onFinishedListener, String token , String fromDate, String  toDate, int history);
        void callStarLineHistoryApi(OnFinishedListener onFinishedListener, String token , String  fromDate,String  toDate, int history);
        void callGalidesawarHistoryApi(OnFinishedListener onFinishedListener, String token , String  fromDate,String  toDate, int history);

    }

    interface Presenter{
        void mainHistoryApi(String token,String  fromDate, String  toDate, int history);
        void starLineHistoryApi(String token,String fromDate, String  toDate, int history);
        void galidesawarHistoryApi(String token,String fromDate, String  toDate, int history);
    }
}
