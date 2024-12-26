package com.old_dummy.cc.UPIDetailsActivity;

public interface UpiDetailsContract {

    interface View{
        void showProgressBar();
        void hideProgressBar();
        void apiResponse();
        void message(String msg);
        void destroy(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void finished();
            void message(String msg);
            void destroy(String msg);
            void failure(Throwable t);
        }
        void callApi(OnFinishedListener onFinishedListener, String token, String upiID, int upiActivity);

    }

    interface Presenter{
        void api(String token, String upiID, int upiActivity);
    }
}
