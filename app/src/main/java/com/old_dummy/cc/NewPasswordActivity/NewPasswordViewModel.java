package com.old_dummy.cc.NewPasswordActivity;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPasswordViewModel implements NewPasswordContract.ViewModel{
    @Override
    public void callApi(OnFinishedListener onFinishedListener, String token, String mobile, String inputConformPass) {
        Call<LoginModel> call = ApiClient.getClient().forgotPasswordVerify(token,mobile, "firebase_mobile_token",inputConformPass);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                LoginModel modelSignin = response.body();
                if (response.isSuccessful()) {
                    if (modelSignin.getStatus().equals("success")) {
                        onFinishedListener.finished(modelSignin.getData().getToken());
                    }
                   // onFinishedListener.message(modelSignin.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                System.out.println("forgotPasswordVerify "+t);
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);

            }
        });
    }
    @Override
    public void callApiChangePin(OnFinishedListener onFinishedListener, String token, String mobile, String pin) {
        Call<LoginModel> call = ApiClient.getClient().createPin(token,"firebase_mobile_token",mobile,pin);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                LoginModel modelSignin = response.body();
                if (response.isSuccessful()) {
                    if (modelSignin.getStatus().equals("success")) {
                        onFinishedListener.finished(modelSignin.getData().getToken());
                    }
                    onFinishedListener.message(modelSignin.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                System.out.println("forgotPasswordVerify "+t);
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);

            }
        });
    }
}
