package com.old_dummy.cc.AddFundMethodsActivity;

import com.old_dummy.cc.Models.PaymentRequestModel;

public interface AddFundMethodContract {
    interface View{
        void showProgressBar();
        void hideProgressBar();
        void apiResponsePaymentReceive(String message);
        void apiResponsePaymentRequest (PaymentRequestModel.Data data);
        void message(String msg);
        void destroy(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void finishedPaymentReceive(String message);
            void finishedPaymentRequest (PaymentRequestModel.Data data);
            void message(String msg);
            void destroy(String msg);
            void failure(Throwable t);
        }
        void callPaymentReceiveApi(OnFinishedListener onFinishedListener,String token,String amount, String methodName, String screenshot, String transactionId, String methodDetails);
        void callPaymentRequestApi(OnFinishedListener onFinishedListener,String token,String amount, String methodName);

    }

    interface Presenter{
        void apiPaymentReceive(String token,String amount, String methodName, String screenshot, String transactionId, String methodDetails);
        void apiPaymentRequest(String token,String amount, String methodName);
    }
}
