package com.old_dummy.cc.NoticeActivity;

import androidx.annotation.NonNull;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.CommonModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeViewModel implements NoticeContract.ViewModel{

    @Override
    public void callApi(OnFinishedListener onFinishedListener, String logInToken) {
        Call<CommonModel> call = ApiClient.getClient().readNotification(logInToken,"onedata");
        call.enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(@NonNull Call<CommonModel> call, @NonNull Response<CommonModel> response) {
                if (response.isSuccessful()){
                    CommonModel commonModel = response.body();
                    if (commonModel.getStatus().equalsIgnoreCase("success")){
                     //   onFinishedListener.finished(commonModel.getMessage());
                    }else
                        onFinishedListener.message(response.message());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                System.out.println("readNotification error "+t);
            }
        });
    }
}
