package com.old_dummy.cc.SecurityPinActivity;

import androidx.annotation.NonNull;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.AppDetailsModel;
import com.old_dummy.cc.Models.CommonModel;
import com.old_dummy.cc.Models.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecurityPinViewModel implements SecurityPinContract.ViewModel{
    @Override
    public void callApi(OnFinishedListener onFinishedListener, String token, String pin) {
        Call<LoginModel> call = ApiClient.getClient().logInPin(token, pin);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                if (response.isSuccessful()){
                    LoginModel loginModel = response.body();
                    if (loginModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(loginModel.getMessage());
                    }
                    onFinishedListener.apiFinished(loginModel);
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                System.out.println("security pin Error "+t);
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);
            }
        });

    }

    @Override
    public void callApiForgotPinResponse(OnFinishedListener onFinishedListener, String number) {
        Call<CommonModel> call = ApiClient.getClient().forgotPinApi(number);
        call.enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful()){
                    CommonModel commonModel = response.body();
                    if (commonModel.getStatus().equals("success")) {
                        onFinishedListener.onForgotPinFinished(number);
                    }
                    onFinishedListener.message(commonModel.getMessage());
                }else onFinishedListener.message("Server Error");
            }
            @Override
            public void onFailure(@NonNull Call<CommonModel> call, @NonNull Throwable t) {
                System.out.println("forgotPin "+t);
                onFinishedListener.failure(t);
                onFinishedListener.message("Api Failure");
            }
        });
    }

    @Override
    public void callApiAppDetailsResponse(OnFinishedListener onFinishedListener, String token) {
        Call<AppDetailsModel> call = ApiClient.getClient().getAppDetails( token,"");
        call.enqueue(new Callback<AppDetailsModel>() {
            @Override
            public void onResponse(@NonNull Call<AppDetailsModel> call, @NonNull Response<AppDetailsModel> response) {
                if (response.isSuccessful()){
                    AppDetailsModel appDetailsApiModel = response.body();
                    if (appDetailsApiModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.onAppDetailsApiFinished(appDetailsApiModel.getData());
                    }else
                        onFinishedListener.message(appDetailsApiModel.getMessage());
                }else {
                    onFinishedListener.message("Server Error");
                }
            }

            @Override
            public void onFailure(Call<AppDetailsModel> call, Throwable t) {
                System.out.println("getAppDetails error "+t);
                onFinishedListener.failure(t);
                onFinishedListener.message("Api Failure");

            }
        });
    }
    @Override
    public void callApiUserDetailsResponse(OnFinishedListener onFinishedListener, String token) {
        Call<LoginModel> call = ApiClient.getClient().getUserDetails( token,"");
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                if (response.isSuccessful()){
                    LoginModel appDetailsApiModel = response.body();
                    if (appDetailsApiModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.onUserDetailsApiFinished(appDetailsApiModel.getData());
                    }else
                        onFinishedListener.message(appDetailsApiModel.getMessage());
                }else {
                    onFinishedListener.message("Server Error");
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                System.out.println("getAppDetails error "+t);
                onFinishedListener.failure(t);
                onFinishedListener.message("Api Failure");

            }
        });
    }
}
