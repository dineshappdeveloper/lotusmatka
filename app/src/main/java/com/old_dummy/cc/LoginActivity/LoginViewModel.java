package com.old_dummy.cc.LoginActivity;

import androidx.annotation.NonNull;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Api.ApiUrl;
import com.old_dummy.cc.Models.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel implements LoginContract.ViewModel{
    @Override
    public void callApi(OnFinishedListener onFinishedListener, String number, String password) {
        Call<LoginModel> call = ApiClient.getClient().getLogIn(number, password, ApiUrl.version);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                if (response.isSuccessful()){
                    LoginModel loginModel = response.body();
                    assert loginModel != null;
                    if (loginModel.getStatus().equals("success")){
                        onFinishedListener.finished(loginModel.getData().getToken());
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
    @Override
    public void callUserDetailsApi(OnFinishedListener onFinishedListener, String token) {
        Call<LoginModel> call = ApiClient.getClient().getUserDetails(token, "");
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                if (response.isSuccessful()){
                    LoginModel gameListModel = response.body();
                    assert gameListModel != null;

                    if(gameListModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.finishedUserDetails(gameListModel.getData());
                    }
                    //  onFinishedListener.message(gameListModel.getMessage());
                }else {
                    onFinishedListener.message("Network error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                System.out.println("game list Error "+t);
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);
            }
        });
    }
}
