package com.old_dummy.cc.OtpActivity;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.CommonModel;
import com.old_dummy.cc.Models.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpViewModel implements OtpContract.ViewModel{

    @Override
    public void callVerifyOtpApi(OnFinishedListener onFinishedListener, String number, String otp) {
        Call<LoginModel> call = ApiClient.getClient().verifyOtp(number,otp);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.isSuccessful()) {
                    LoginModel loginModel = response.body();
                    if (loginModel.getStatus().equals("success")) {
                        onFinishedListener.verifyOtpApiFinished(loginModel.getData().getToken());
                    }else{
                        onFinishedListener.failure();
                    }
                } else onFinishedListener.message("Network Error");
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                System.out.println("verifyUser " + t);
                onFinishedListener.failure();
                onFinishedListener.message("Server Error");
            }
        });
    }

    @Override
    public void callVerifyUserMethodApi(OnFinishedListener onFinishedListener, String number, String otp) {
        Call<LoginModel> call = ApiClient.getClient().verifyCustomer(number, "mobile_token", otp);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.isSuccessful()) {
                    LoginModel loginModel = response.body();
                    if (loginModel.getStatus().equals("success")) {
                        onFinishedListener.verifyUserMethodApiFinished(loginModel.getData().getToken());
                    }else {
                        onFinishedListener.failure();
                    }
                 //   onFinishedListener.message(loginModel.getMessage());
                } else
                    onFinishedListener.message("Network Error");
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                System.out.println("verifyUser " + t);
                onFinishedListener.message("Server Error");
                onFinishedListener.failure();
            }
        });
    }

    @Override
    public void callResendOtpApi(OnFinishedListener onFinishedListener, String number) {
        Call<CommonModel> call = ApiClient.getClient().resendOtp(number);
        call.enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful()) {
                    CommonModel commonModel = response.body();
                    onFinishedListener.resendOtpApiFinished();
                    onFinishedListener.message(commonModel.getMessage());
                } else
                    onFinishedListener.message("Network Error");
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                System.out.println("verifyUser " + t);
                onFinishedListener.message("Server Error");
                onFinishedListener.failure();
            }
        });
    }
}
