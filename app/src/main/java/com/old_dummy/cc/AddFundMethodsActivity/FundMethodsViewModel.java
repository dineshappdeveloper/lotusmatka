package com.old_dummy.cc.AddFundMethodsActivity;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.CommonModel;
import com.old_dummy.cc.Models.PaymentRequestModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FundMethodsViewModel implements AddFundMethodContract.ViewModel{
    @Override
    public void callPaymentReceiveApi(OnFinishedListener onFinishedListener, String token,String amount, String methodName, String screenshot, String transactionId, String methodDetails) {
        Call<CommonModel> call = ApiClient.getClient().paymentReceive(token, amount,methodName,screenshot,transactionId,"success ",methodDetails);
        call.enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful()){
                    CommonModel commonModel = response.body();
                    if (commonModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(commonModel.getMessage());
                    }
                    if (commonModel.getStatus().equalsIgnoreCase("success")){
                       onFinishedListener.finishedPaymentReceive(commonModel.getMessage());

                    }
                    //onFinishedListener.message(commonModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                System.out.println("addFund Error "+t);
                onFinishedListener.failure(t);
                onFinishedListener.message("Server Error");
            }
        });
    }
    @Override
    public void callPaymentRequestApi(OnFinishedListener onFinishedListener, String token, String amount, String methodName) {
        Call<PaymentRequestModel> call = ApiClient.getClient().paymentRequest(token,amount,methodName);
        call.enqueue(new Callback<PaymentRequestModel>() {
            @Override
            public void onResponse(Call<PaymentRequestModel> call, Response<PaymentRequestModel> response) {
                if (response.isSuccessful()){
                    PaymentRequestModel paymentRequestModel = response.body();
                    if (paymentRequestModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(paymentRequestModel.getMessage());
                    }
                    if (paymentRequestModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.finishedPaymentRequest(paymentRequestModel.getData());
                    }else {
                        onFinishedListener.message(paymentRequestModel.getMessage());
                    }
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<PaymentRequestModel> call, Throwable t) {
                System.out.println("addFund Error "+t);
                onFinishedListener.failure(t);
                onFinishedListener.message("Server Error");
            }
        });
    }
}
