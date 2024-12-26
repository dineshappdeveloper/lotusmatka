package com.old_dummy.cc.BankMethodActivity;

import android.app.Activity;

public interface BankMethodContract {
    interface View{
        void message(String msg);
    }

    interface Presenter{
        void method(Activity activity, int upi);
        void bank(Activity activity);
    }
}
