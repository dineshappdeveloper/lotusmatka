package com.old_dummy.cc.BankDetailsActivity;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.CommonModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankDetailsViewModel implements BankDetailsContract.ViewModel{

    @Override
    public void callApi(OnFinishedListener onFinishedListener, String token, String holderName, String accountNumber, String ifscCode, String bankName, String branchAddress) {
        Call<CommonModel> call = ApiClient.getClient().updateBankDetails(token, holderName,accountNumber,ifscCode,bankName,branchAddress);
        call.enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful()){
                    CommonModel commonModel = response.body();
                    if (commonModel.getCode().equalsIgnoreCase("505")){
                       onFinishedListener.destroy(commonModel.getMessage());
                    }
                    if (commonModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.finished();
                    }
                //    onFinishedListener.message(commonModel.getMessage());
                }else onFinishedListener.message("Network Error");

            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                System.out.println("updateBankDetails error "+t);
                onFinishedListener.failure(t);
                onFinishedListener.message("Server Error");
            }
        });
    }
}
