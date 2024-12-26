package com.old_dummy.cc.NewPasswordActivity;

public interface NewPasswordContract {
    interface View{
        void showProgressBar();
        void hideProgressBar();
        void apiResponse(String token);
        void message(String msg);
        void destroy(String msg);
    }
    interface ViewModel{
        interface OnFinishedListener{
            void finished(String token);
            void message(String msg);
            void destroy(String msg);
            void failure(Throwable t);
        }
        void callApi(OnFinishedListener onFinishedListener, String token , String mobile, String inputConformPass);
        void callApiChangePin(OnFinishedListener onFinishedListener, String token , String mobile, String pin);
    }
    interface Presenter{
        void api(String token,String mobile, String inputConformPass);
        void apiChangePin(String token,String mobile, String pin);
    }
}
