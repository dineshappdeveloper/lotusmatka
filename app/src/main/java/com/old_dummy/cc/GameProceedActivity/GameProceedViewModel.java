package com.old_dummy.cc.GameProceedActivity;

import android.view.View;

import androidx.annotation.NonNull;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.CommonModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameProceedViewModel implements GameProceedContract.ViewModel{
    @Override
    public void callApi(OnFinishedListener onFinishedListener,String token, String serverData, View view) {
        Call<CommonModel> call = ApiClient.getClient().placeBid(token, serverData);
        call.enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(@NonNull Call<CommonModel> call, @NonNull Response<CommonModel> response) {
                if (response.isSuccessful()){
                    CommonModel commonModel = response.body();
                    assert commonModel != null;
                    if (commonModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(commonModel.getMessage());
                    }
                    if (commonModel.getStatus().equals("success")){
                        onFinishedListener.finished(view);
                    }
                    onFinishedListener.message(commonModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }
            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);
                System.out.println("Placed Bid OnFailure "+t);
            }
        });
    }
}
