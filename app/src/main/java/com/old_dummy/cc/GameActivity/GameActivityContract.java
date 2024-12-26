package com.old_dummy.cc.GameActivity;

import android.app.Activity;

public interface GameActivityContract {
    interface View{
        void message(String msg);
    }


    interface Presenter{
        void gameClick(Activity activity, boolean open, String games, String gameName,int innerGame);
    }
}
