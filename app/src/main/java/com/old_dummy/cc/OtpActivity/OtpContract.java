package com.old_dummy.cc.OtpActivity;

import com.google.android.material.textview.MaterialTextView;

public interface OtpContract {
    interface View{
        void showProgressBar();
        void hideProgressBar();
        void verifyOtpApiResponse(String token);
        void verifyUserMethodApiResponse(String token);
        void resendOtpApiResponse();
        void message(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void verifyOtpApiFinished(String token);
            void verifyUserMethodApiFinished(String token);
            void resendOtpApiFinished();
            void message(String msg);
            void failure();
        }
        void callVerifyOtpApi(OnFinishedListener onFinishedListener, String number , String otp);
        void callVerifyUserMethodApi(OnFinishedListener onFinishedListener, String number , String otp);
        void callResendOtpApi(OnFinishedListener onFinishedListener, String number);

    }

    interface Presenter{
        void verifyOtpApi(String number,String otp);
        void verifyUserMethodApi(String number,String otp);
        void resendOtpApi(String number);
        void countdown(MaterialTextView buttonText);
    }
}
