package com.old_dummy.cc.TransferPointsActivity;

public interface TransferPointsContract {

    interface View{
        void showProgressBar();
        void hideProgressBar();
        void transferApiResponse();
        void verificationApiResponse(String name);
        void message(String msg);
        void destroy(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void transferApiFinished();
            void verificationApiFinished(String name);
            void message(String msg);
            void destroy(String msg);
            void failure(Throwable t);
        }
        void callTransferPointsApi(OnFinishedListener onFinishedListener, String token , String points, String userNumber);
        void callVerificationApi(OnFinishedListener onFinishedListener, String token, String userNumber);
    }

    interface Presenter{
        void transferPointsApi(String token , String points, String userNumber);
        void verificationApi(String token, String userNumber);
    }
}
