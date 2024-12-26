package com.old_dummy.cc.MyHistoryActivity;

import android.app.Activity;

public interface MyHistoryContract {

    interface View {
        void message(String msg);
    }

    interface Presenter{
        void history(Activity activit, int history,String title);
    }

}
