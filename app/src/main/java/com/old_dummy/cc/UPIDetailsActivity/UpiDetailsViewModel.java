package com.old_dummy.cc.UPIDetailsActivity;

import androidx.annotation.NonNull;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.CommonModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpiDetailsViewModel implements UpiDetailsContract.ViewModel{
    @Override
    public void callApi(OnFinishedListener onFinishedListener, String token, String upiID, int upiActivity) {
        Call<CommonModel> call = null;
        switch (upiActivity){
            case 1:
                call = ApiClient.getClient().updatePayTm(token,upiID);
                break;
            case 2:
                call = ApiClient.getClient().updateGooglePay(token,upiID);
                break;
            case 3:
                call = ApiClient.getClient().updatePhonePe(token,upiID);
                break;
        }

        call.enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(@NonNull Call<CommonModel> call, @NonNull Response<CommonModel> response) {
                if (response.isSuccessful()){
                    CommonModel commonModel = response.body();

                    if (commonModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(commonModel.getMessage());
                    }
                    if (commonModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.finished();
                    }
//                    onFinishedListener.message(commonModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<CommonModel> call, @NonNull Throwable t) {
                System.out.println("UPI Update Error "+t);
                onFinishedListener.failure(t);
                onFinishedListener.message("Server Error");
            }
        });
    }
}
