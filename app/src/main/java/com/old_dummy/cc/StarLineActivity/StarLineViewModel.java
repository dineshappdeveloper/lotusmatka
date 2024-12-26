package com.old_dummy.cc.StarLineActivity;

import androidx.annotation.NonNull;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.StarlineGameListModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarLineViewModel implements StarLineContract.ViewModel{
    @Override
    public void callApi(OnFinishedListener onFinishedListener, String token) {
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
                        onFinishedListener.finished(starlineGameListModel.getData());
                    }
//                    onFinishedListener.message(starlineGameListModel.getMessage());
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
}
