package com.old_dummy.cc.Chart;

import android.app.Activity;

import com.old_dummy.cc.Models.GalidesawarGameListModel;
import com.old_dummy.cc.Models.StarlineGameListModel;

import java.util.List;

public interface ChartContract {
    interface View{
        void showProgressBar();
        void hideProgressBar();
        void message(String msg);
        void destroy(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void finishedStarline(List<StarlineGameListModel.Data.StarlineGame> nameList);
            void finishedGali(List<GalidesawarGameListModel.Data.GalidesawarGame> nameList);
            void message(String msg);
            void destroy(String msg);
            void failure(Throwable t);
        }
        void callStarlineApi(OnFinishedListener onFinishedListener, String token);
        void callGaliApi(OnFinishedListener onFinishedListener, String token);

    }

    interface Presenter{
        void starlineApi(String token);
        void galiApi(String token);
        void starlineChart(Activity activity);
        void galiChart(Activity activity);
    }
}
