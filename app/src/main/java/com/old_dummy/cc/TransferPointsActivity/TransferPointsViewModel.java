package com.old_dummy.cc.TransferPointsActivity;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.CommonModel;
import com.old_dummy.cc.Models.TransferVerifyModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferPointsViewModel implements TransferPointsContract.ViewModel {

    @Override
    public void callTransferPointsApi(OnFinishedListener onFinishedListener, String token, String points, String userNumber) {
        Call<CommonModel> call = ApiClient.getClient().transferPoints(token, points,userNumber);
        call.enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful()){
                    CommonModel commonModel = response.body();
                    if (commonModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(commonModel.getMessage());
                    }
                    if (commonModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.transferApiFinished();
                    }
                 //   onFinishedListener.message(commonModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                System.out.println("transferPoints Error "+t);
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);
            }
        });
    }

    @Override
    public void callVerificationApi(OnFinishedListener onFinishedListener, String token, String userNumber) {
        Call<TransferVerifyModel> call = ApiClient.getClient().transferVerify(token, userNumber);
        call.enqueue(new Callback<TransferVerifyModel>() {
            @Override
            public void onResponse(Call<TransferVerifyModel> call, Response<TransferVerifyModel> response) {
                if (response.isSuccessful()){
                    TransferVerifyModel verifyModel = response.body();
                    if (verifyModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(verifyModel.getMessage());
                    }
                    if (verifyModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.verificationApiFinished(verifyModel.getData().getName());
                    }
                  //  onFinishedListener.message(verifyModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<TransferVerifyModel> call, Throwable t) {
                System.out.println("transferVerify Error "+t);
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);
            }
        });
    }
}
