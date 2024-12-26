package com.old_dummy.cc.SplashActivity;

import androidx.annotation.NonNull;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.AppDetailsModel;
import com.old_dummy.cc.Models.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashViewModel implements SplashContract.ViewModel{
    @Override
    public void callApi(OnFinishedListener onFinishedListener,String token) {
        Call<AppDetailsModel> call = ApiClient.getClient().getAppDetails(token, "");
        call.enqueue(new Callback<AppDetailsModel>() {
            @Override
            public void onResponse(@NonNull Call<AppDetailsModel> call, @NonNull Response<AppDetailsModel> response) {
                if (response.isSuccessful()){
                    AppDetailsModel appDetailsModel = response.body();
                    if (appDetailsModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy();
                    }
                    if (appDetailsModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.finished(appDetailsModel.getData());
                    }else
                        onFinishedListener.message(response.message());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<AppDetailsModel> call, Throwable t) {
                System.out.println("getAppDetails error "+t);
            }
        });
    }


    @Override
    public void callPinApi(OnFinishedListener onFinishedListener, String token, String pin) {
        Call<LoginModel> call = ApiClient.getClient().logInPin(token,pin);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                if (response.isSuccessful()){
                    LoginModel loginModel = response.body();
                    assert loginModel != null;
                    if (loginModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy();
                    }
                    if (loginModel.getStatus().equals("success")){
                        onFinishedListener.finishedPin(loginModel.getData().getToken());
                    }else
                        onFinishedListener.message(loginModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                System.out.println("login OnFailure "+t);
                onFinishedListener.failure(t);
                onFinishedListener.message("Server Error");
            }
        });
    }
}
