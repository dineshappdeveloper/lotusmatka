package com.old_dummy.cc.Chart;

import androidx.annotation.NonNull;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.GalidesawarGameListModel;
import com.old_dummy.cc.Models.StarlineGameListModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartViewModel implements ChartContract.ViewModel{
    @Override
    public void callStarlineApi(OnFinishedListener onFinishedListener, String token) {
        Call<StarlineGameListModel> call = ApiClient.getClient().starLineGame(token, "");
        call.enqueue(new Callback<StarlineGameListModel>() {
            @Override
            public void onResponse(@NonNull Call<StarlineGameListModel> call, @NonNull Response<StarlineGameListModel> response) {
                if (response.isSuccessful()) {
                    StarlineGameListModel starlineGameListModel = response.body();
                    assert starlineGameListModel != null;
                    if (starlineGameListModel.getCode().equalsIgnoreCase("505")) {
                        onFinishedListener.destroy(starlineGameListModel.getMessage());
                    }
                    if (starlineGameListModel.getStatus().equalsIgnoreCase("success")) {
                        onFinishedListener.finishedStarline(starlineGameListModel.getData().getStarlineGame());
                    }
                  //  onFinishedListener.message(starlineGameListModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<StarlineGameListModel> call, @NonNull Throwable t) {
                System.out.println("game list Error "+t);
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);
            }
        });
    }

    @Override
    public void callGaliApi(OnFinishedListener onFinishedListener, String token) {
        Call<GalidesawarGameListModel> call = ApiClient.getClient().galidesawarGame(token, "");
        call.enqueue(new Callback<GalidesawarGameListModel>() {
            @Override
            public void onResponse(@NonNull Call<GalidesawarGameListModel> call, @NonNull Response<GalidesawarGameListModel> response) {
                if (response.isSuccessful()) {
                    GalidesawarGameListModel galidesawarGameListModel = response.body();
                    assert galidesawarGameListModel != null;
                    if (galidesawarGameListModel.getCode().equalsIgnoreCase("505")) {
                        onFinishedListener.destroy(galidesawarGameListModel.getMessage());
                    }
                    if (galidesawarGameListModel.getStatus().equalsIgnoreCase("success")) {
                        onFinishedListener.finishedGali(galidesawarGameListModel.getData().getGalidesawrGame());
                    }else
                     onFinishedListener.message(galidesawarGameListModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<GalidesawarGameListModel> call, @NonNull Throwable t) {
                System.out.println("game list Error "+t);
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);
            }
        });
    }
}
