package com.old_dummy.cc.ContactUsActivity;

import android.app.Activity;

public interface ContactUsContract {
    interface View{
        void message(String msg);
    }

    interface Presenter{
        void call(Activity activity);
        void whatsapp(Activity activity);
        void mail(Activity activity);
        void telegram(Activity activity);
        void withdrawProof(ContactUsActivity contactUsActivity);
    }
}
