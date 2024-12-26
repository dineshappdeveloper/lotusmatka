package com.old_dummy.cc.GalidesawarChart;

import androidx.annotation.NonNull;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.GalidesawarChartModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalidesawarChartViewModel implements GalidesawarChartContract.ViewModel{
    @Override
    public void callApi(OnFinishedListener onFinishedListener, String token) {
        Call<GalidesawarChartModel> call = ApiClient.getClient().galiChart(token,"");
        call.enqueue(new Callback<GalidesawarChartModel>() {
            @Override
            public void onResponse(@NonNull Call<GalidesawarChartModel> call, @NonNull Response<GalidesawarChartModel> response) {
                if (response.isSuccessful()){
                    GalidesawarChartModel galidesawarChartModel = response.body();
                    assert galidesawarChartModel != null;
                    if (galidesawarChartModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.finished(galidesawarChartModel.getData());
                    }else
                        onFinishedListener.error(response.message());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<GalidesawarChartModel> call, Throwable t) {
                System.out.println("getAppDetails error "+t);
                onFinishedListener.error(t.getMessage());
            }
        });
    }
}
