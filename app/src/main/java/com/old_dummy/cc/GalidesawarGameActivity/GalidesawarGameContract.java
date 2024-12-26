package com.old_dummy.cc.GalidesawarGameActivity;

import android.app.Activity;

public interface GalidesawarGameContract {
    interface View{
        void message(String msg);
    }


    interface Presenter{
        void gameClick(Activity activity, String games, String gameName, int innerGame);
    }
}
