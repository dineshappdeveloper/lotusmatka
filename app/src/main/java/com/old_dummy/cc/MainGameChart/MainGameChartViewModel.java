package com.old_dummy.cc.MainGameChart;

import androidx.annotation.NonNull;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.MainGameChartModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainGameChartViewModel implements MainGameChartContract.ViewModel{
    @Override
    public void callApi(OnFinishedListener onFinishedListener, String token, String gameId) {
        Call<MainGameChartModel> call = ApiClient.getClient().mainGameChart(token, gameId);
        call.enqueue(new Callback<MainGameChartModel>() {
            @Override
            public void onResponse(@NonNull Call<MainGameChartModel> call, @NonNull Response<MainGameChartModel> response) {
                if (response.isSuccessful()){
                    MainGameChartModel mainGameChart = response.body();
                    assert mainGameChart != null;
                    if (mainGameChart.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.finished(mainGameChart.getData());
                    }else onFinishedListener.message(mainGameChart.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<MainGameChartModel> call, Throwable t) {
                System.out.println("getAppDetails error "+t);
            }
        });
    }
}
