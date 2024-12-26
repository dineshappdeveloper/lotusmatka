package com.old_dummy.cc.GameRateActivity;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.GameRateModel;
import com.old_dummy.cc.Models.HowToPlayModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameRatesViewModel implements GameRatesContract.ViewModel{

    @Override
    public void callGameRatesApi(OnFinishedListener onFinishedListener, String token) {
        Call<GameRateModel> call = ApiClient.getClient().gameRateList(token,"");
        call.enqueue(new Callback<GameRateModel>() {
            @Override
            public void onResponse(Call<GameRateModel> call, Response<GameRateModel> response) {
                if (response.isSuccessful()){
                    GameRateModel gameRateModel = response.body();
                    if (gameRateModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(gameRateModel.getMessage());
                    }
                    if(gameRateModel.getStatus().equalsIgnoreCase("success")) {
                        onFinishedListener.gameRatesFinished(gameRateModel);
                    }
//                    onFinishedListener.message(gameRateModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<GameRateModel> call, Throwable t) {
                System.out.println("gameRateList Error "+t);
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);
            }
        });
    }

    @Override
    public void callHowToPlayApi(OnFinishedListener onFinishedListener, String token) {
        Call<HowToPlayModel> call = ApiClient.getClient().howToPlay(token,"");
        call.enqueue(new Callback<HowToPlayModel>() {
            @Override
            public void onResponse(Call<HowToPlayModel> call, Response<HowToPlayModel> response) {
                if (response.isSuccessful()){
                    HowToPlayModel howToPlayModel = response.body();
                    if (howToPlayModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(howToPlayModel.getMessage());
                    }
                    if (howToPlayModel.getStatus().equals("success"))
                        onFinishedListener.howToPlayFinished(howToPlayModel.getData());

                    onFinishedListener.message(howToPlayModel.getMessage());

                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<HowToPlayModel> call, Throwable t) {
                System.out.println("howToPlay Error "+t);
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);
            }
        });
    }
}
