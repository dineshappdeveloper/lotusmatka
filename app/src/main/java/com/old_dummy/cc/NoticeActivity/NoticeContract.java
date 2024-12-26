package com.old_dummy.cc.NoticeActivity;

import android.app.Activity;

public interface NoticeContract {
    interface View{
        void message(String msg);
        void apiResponse(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void finished(String msg);
            void message(String msg);
            void failure(Throwable t);
        }
        void callApi(OnFinishedListener onFinishedListener, String logInToken);

    }

    interface Presenter{
        void call(Activity activity);
        void whatsapp(Activity activity);
        void mail(Activity activity);
        void api(String logInToken);
    }

}
