package com.old_dummy.cc.SignUpActivity;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Api.ApiUrl;
import com.old_dummy.cc.Models.CommonModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel implements SignUpContract.ViewModel{

    @Override
    public void callApi(OnFinishedListener onFinishedListener, String personName, String mobileNumber, String password, String pinCode) {
        Call<CommonModel> call = ApiClient.getClient().getSignUp(personName,mobileNumber,pinCode,password,  ApiUrl.version);
        call.enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {

                if (response.isSuccessful()){
                    CommonModel commonModel = response.body();
                    assert commonModel != null;
                    if (commonModel.getStatus().equals("success")){
                        onFinishedListener.finished();
                    }else
                        onFinishedListener.message(commonModel.getMessage());
                }
                else onFinishedListener.message("Network Error");
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                System.out.println("getSignUp OnFailure "+t);
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);
            }
        });
    }
}
