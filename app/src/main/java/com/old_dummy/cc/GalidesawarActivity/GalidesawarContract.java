package com.old_dummy.cc.GalidesawarActivity;

import android.app.Activity;

import com.old_dummy.cc.Models.GalidesawarGameListModel;

import java.util.ArrayList;

public interface GalidesawarContract {
    interface View{
        void showProgressBar();
        void hideProgressBar();
        void apiResponse(GalidesawarGameListModel.Data data);
        void message(String msg);
        void destroy(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void finished(GalidesawarGameListModel.Data data);
            void message(String msg);
            void destroy(String msg);
            void failure(Throwable t);
        }
        void callApi(OnFinishedListener onFinishedListener, String token);

    }

    interface Presenter{
        void api(String token);
        void chart(Activity activity, ArrayList<String> namel);
        void History(Activity activity, int history, String title);
    }
}
