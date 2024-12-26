package com.old_dummy.cc.ServerActivity;

import android.app.Activity;

public interface ServerContract {
    interface View{
        void message(String msg);
    }

    interface Presenter{
        void call(Activity activity);
        void whatsapp(Activity activity);
        void mail(Activity activity);
    }
}
