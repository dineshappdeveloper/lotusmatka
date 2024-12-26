package com.old_dummy.cc.GalidesawarActivity;

import androidx.annotation.NonNull;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.GalidesawarGameListModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalidesawarViewModel implements GalidesawarContract.ViewModel{
    @Override
    public void callApi(OnFinishedListener onFinishedListener, String token) {
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
                        onFinishedListener.finished(galidesawarGameListModel.getData());
                    }
                  //  onFinishedListener.message(galidesawarGameListModel.getMessage());
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
