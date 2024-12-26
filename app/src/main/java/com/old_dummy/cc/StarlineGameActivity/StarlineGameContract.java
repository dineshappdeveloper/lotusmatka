package com.old_dummy.cc.StarlineGameActivity;

import android.app.Activity;

public interface StarlineGameContract {
    interface View{
        void message(String msg);
    }


    interface Presenter{
        void gameClick(Activity activity, String games, String gameName, int innerGame);
    }
}
