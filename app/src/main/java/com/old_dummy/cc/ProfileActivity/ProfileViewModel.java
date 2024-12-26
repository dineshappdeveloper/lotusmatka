package com.old_dummy.cc.ProfileActivity;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel implements ProfileContract.ViewModel{
    @Override
    public void callApi(OnFinishedListener onFinishedListener, String logInToken, String email, String name) {
        Call<LoginModel> call = ApiClient.getClient().updateProfile(logInToken,email,name);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.isSuccessful()){
                    LoginModel loginModel = response.body();
                    if (loginModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(loginModel.getMessage());
                    }
                    if (loginModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.finished(loginModel.getData());
                    }
                  //  onFinishedListener.message(loginModel.getMessage());
                }else
                    onFinishedListener.message("Network Error");
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                System.out.println("updateProfile error "+t);
                onFinishedListener.failure(t);
                onFinishedListener.message("Server Error");
            }
        });
    }
}
