package com.old_dummy.cc.StarlineChart;

import androidx.annotation.NonNull;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.StarlineChartModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarlineChartViewModel implements StarlineChartContract.ViewModel{
    @Override
    public void callApi(OnFinishedListener onFinishedListener, String token) {
        Call<StarlineChartModel> call = ApiClient.getClient().starlineChart(token,"");
        call.enqueue(new Callback<StarlineChartModel>() {
            @Override
            public void onResponse(@NonNull Call<StarlineChartModel> call, @NonNull Response<StarlineChartModel> response) {
                if (response.isSuccessful()){
                    StarlineChartModel starlineChartModel = response.body();
                    assert starlineChartModel != null;
                    if (starlineChartModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.finished(starlineChartModel.getData());
                    }else
                        onFinishedListener.error(response.message());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<StarlineChartModel> call, Throwable t) {
                System.out.println("getAppDetails error "+t);
                onFinishedListener.error(t.getMessage());
            }
        });
    }
}
