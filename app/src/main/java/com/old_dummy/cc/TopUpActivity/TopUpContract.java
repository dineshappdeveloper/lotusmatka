package com.old_dummy.cc.TopUpActivity;

import com.old_dummy.cc.Models.PaymentConfigModel;

public interface TopUpContract {
    interface View{
        void showProgressBar();
        void hideProgressBar();
        void apiResponse(String message);
        void paymentConfigApiResponse(PaymentConfigModel.Data data);
        void message(String msg);
        void destroy(String msg);
    }

    interface ViewModel{
        interface OnFinishedListener{
            void finished(String message);
            void paymentConfigFinished(PaymentConfigModel.Data data);
            void message(String msg);
            void destroy(String msg);
            void failure(Throwable t);
        }
        void callApi(OnFinishedListener onFinishedListener, String token, String amount);
        void callPaymentConfigApi(OnFinishedListener onFinishedListener, String token);

    }

    interface Presenter{
        void api(String token,String amount);
        void paymentConfigApi(String token);
    }
}
