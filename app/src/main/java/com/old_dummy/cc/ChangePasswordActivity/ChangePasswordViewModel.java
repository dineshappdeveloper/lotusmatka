package com.old_dummy.cc.ChangePasswordActivity;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordViewModel implements ChangePasswordContract.ViewModel{

    @Override
    public void callApi(OnFinishedListener onFinishedListener, String token, String mobile, String mobileToken, String inputConformPass) {
        Call<LoginModel> call = ApiClient.getClient().forgotPasswordVerify(token,mobile,mobileToken,inputConformPass);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                LoginModel loginModel = response.body();
                if (response.isSuccessful()) {
                    if (loginModel.getCode().equalsIgnoreCase("505")) {
                        onFinishedListener.destroy(loginModel.getMessage());
                    }
                    if (loginModel.getStatus().equals("success")) {
                        onFinishedListener.finished(loginModel.getData().getToken());
                    }
                  //  onFinishedListener.message(loginModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                System.out.println("forgotPasswordVerify "+t);
                onFinishedListener.failure(t);
                onFinishedListener.message("Server Error");

            }
        });
    }
}
