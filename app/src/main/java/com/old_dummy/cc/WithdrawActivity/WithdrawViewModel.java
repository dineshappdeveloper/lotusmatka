package com.old_dummy.cc.WithdrawActivity;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.CommonModel;
import com.old_dummy.cc.Models.WalletStatementModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WithdrawViewModel implements WithdrawContract.ViewModel {
    @Override
    public void callWithdrawPointApi(OnFinishedListener onFinishedListener, String token, String points, String method) {
        String methodStr = null;
        if (method.contains("Account number")){
            methodStr = "bank_name";
        }else if (method.contains("PayTM")){
            methodStr = "paytm_mobile_no";
        }else if (method.contains("PhonePe")){
            methodStr = "phonepe_mobile_no";
        }else if (method.contains("GooglePay")){
            methodStr = "gpay_mobile_no";
        }
        Call<CommonModel> call = ApiClient.getClient().withdrawPoints(token, points, methodStr);
        call.enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful()){
                    CommonModel commonModel = response.body();
                    assert commonModel != null;
                    if (commonModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(commonModel.getMessage());
                    }
                    if (commonModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.withdrawPointApiFinished();
                    }else onFinishedListener.message(commonModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                System.out.println("withdrawPoints Error "+t);
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);
            }
        });
    }

    @Override
    public void callWithdrawStatementApi(OnFinishedListener onFinishedListener, String token) {
        Call<WalletStatementModel> call = ApiClient.getClient().withdrawStatement(token,"");
        call.enqueue(new Callback<WalletStatementModel>() {
            @Override
            public void onResponse(Call<WalletStatementModel> call, Response<WalletStatementModel> response) {
                if (response.isSuccessful()) {
                    WalletStatementModel walletStatementModel = response.body();
                    if (walletStatementModel.getCode().equalsIgnoreCase("505")) {
                        onFinishedListener.destroy(walletStatementModel.getMessage());
                    }
                    if (walletStatementModel.getStatus().equalsIgnoreCase("success")) {
                        onFinishedListener.withdrawStatementApiFinished(walletStatementModel);
                    //    onFinishedListener.message(walletStatementModel.getMessage());
                    }
                }else {
                    onFinishedListener.message("Network Error");
                }


            }

            @Override
            public void onFailure(Call<WalletStatementModel> call, Throwable t) {
                System.out.println("walletStatement error "+t);
                onFinishedListener.failure(t);
                onFinishedListener.message("Server Error");
            }
        });
    }
}
