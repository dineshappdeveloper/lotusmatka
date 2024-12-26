package com.old_dummy.cc.TopUpActivity;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.CommonModel;
import com.old_dummy.cc.Models.PaymentConfigModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopUpViewModel implements TopUpContract.ViewModel{
    @Override
    public void callApi(OnFinishedListener onFinishedListener, String token, String amount) {
        Call<CommonModel> call = ApiClient.getClient().addFund(token, amount,"successful","3resdn34yw8er");
        call.enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful()){
                    CommonModel commonModel = response.body();
                    if (commonModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(commonModel.getMessage());
                    }
                    if (commonModel.getStatus().equalsIgnoreCase("success")){
                       onFinishedListener.finished(commonModel.getMessage());

                    }
                    onFinishedListener.message(commonModel.getMessage());
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
    public void callPaymentConfigApi(OnFinishedListener onFinishedListener, String token) {
        Call<PaymentConfigModel> call = ApiClient.getClient().paymentConfig(token,"");
        call.enqueue(new Callback<PaymentConfigModel>() {
            @Override
            public void onResponse(Call<PaymentConfigModel> call, Response<PaymentConfigModel> response) {
                if (response.isSuccessful()){
                    PaymentConfigModel paymentConfigModel = response.body();
                    if (paymentConfigModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(paymentConfigModel.getMessage());
                    }
                    if (paymentConfigModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.paymentConfigFinished(paymentConfigModel.getData());

                    }
                    onFinishedListener.message(paymentConfigModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<PaymentConfigModel> call, Throwable t) {
                System.out.println("addFund Error "+t);
                onFinishedListener.failure(t);
                onFinishedListener.message("Server Error");
            }
        });
    }
}
