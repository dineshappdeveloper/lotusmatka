package com.old_dummy.cc.WalletActivity;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.WalletStatementModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletViewModel implements WalletContract.ViewModel {
    @Override
    public void callApi(OnFinishedListener onFinishedListener, String token) {
        Call<WalletStatementModel> call = ApiClient.getClient().walletStatement(token,"");
        call.enqueue(new Callback<WalletStatementModel>() {
            @Override
            public void onResponse(Call<WalletStatementModel> call, Response<WalletStatementModel> response) {
                if (response.isSuccessful()){
                    WalletStatementModel walletStatementModel = response.body();
                    if (walletStatementModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(walletStatementModel.getMessage());
                    }
                    if (walletStatementModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.finished(walletStatementModel);
                    }
//                    onFinishedListener.message(walletStatementModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<WalletStatementModel> call, Throwable t) {
                System.out.println("walletStatement error "+t);
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);
            }
        });
    }
}
