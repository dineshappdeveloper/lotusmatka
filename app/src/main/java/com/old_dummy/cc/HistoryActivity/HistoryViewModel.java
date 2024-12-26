package com.old_dummy.cc.HistoryActivity;

import androidx.annotation.NonNull;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.GalidesawarWinModel;
import com.old_dummy.cc.Models.StarLineWinModel;
import com.old_dummy.cc.Models.WinModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryViewModel implements HistoryContract.ViewModel{
    Call<WinModel> call;
    Call<StarLineWinModel> starLineCall;
    Call<GalidesawarWinModel>galidesawarCall;
    @Override
    public void callMainHistoryApi(OnFinishedListener onFinishedListener, String token, String fromDate, String toDate, int history) {
        switch (history){
            case 100:
                call = ApiClient.getClient().winHistory(token,fromDate, toDate);
                break;
            case 200:
                call = ApiClient.getClient().bidHistory(token,fromDate, toDate);
                break;
        }

        call.enqueue(new Callback<WinModel>() {
            @Override
            public void onResponse(@NonNull Call<WinModel> call, @NonNull Response<WinModel> response) {
                if (response.isSuccessful()){
                    WinModel winModel = response.body();
                    if (winModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(winModel.getMessage());
                    }
                    onFinishedListener.mainHistoryFinished(winModel);
//                    onFinishedListener.message(winModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<WinModel> call, Throwable t) {
                onFinishedListener.message("Server Error");
                System.out.println("bidHistory error "+t);
                onFinishedListener.failure(t);

            }
        });
    }

    @Override
    public void callStarLineHistoryApi(OnFinishedListener onFinishedListener, String token, String  fromDate, String toDate, int history) {
        switch (history){
            case 300:
                starLineCall = ApiClient.getClient().starLineWinHistory(token,fromDate, toDate);
                break;
            case 400:
                starLineCall = ApiClient.getClient().starLineBidHistory(token,fromDate, toDate);
                break;
        }

        starLineCall.enqueue(new Callback<StarLineWinModel>() {
            @Override
            public void onResponse(@NonNull Call<StarLineWinModel> call, @NonNull Response<StarLineWinModel> response) {
                if (response.isSuccessful()){
                    StarLineWinModel starLineWinModel = response.body();
                    if (starLineWinModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(starLineWinModel.getMessage());
                    }
                    onFinishedListener.starLineHistoryFinished(starLineWinModel);
//                    onFinishedListener.message(starLineWinModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<StarLineWinModel> call, Throwable t) {
                onFinishedListener.message("Server Error");
                System.out.println("bidHistory error "+t);
                onFinishedListener.failure(t);

            }
        });
    }
    @Override
    public void callGalidesawarHistoryApi(OnFinishedListener onFinishedListener, String token, String  fromDate, String toDate, int history) {
        switch (history){
            case 500:
                galidesawarCall = ApiClient.getClient().galidesawrWinHistory(token,fromDate, toDate);
                break;
            case 600:
                galidesawarCall = ApiClient.getClient().galidesawrBidHistory(token,fromDate, toDate);
                break;
        }

        galidesawarCall.enqueue(new Callback<GalidesawarWinModel>() {
            @Override
            public void onResponse(@NonNull Call<GalidesawarWinModel> call, @NonNull Response<GalidesawarWinModel> response) {
                if (response.isSuccessful()){
                    GalidesawarWinModel starLineWinModel = response.body();
                    if (starLineWinModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(starLineWinModel.getMessage());
                    }
                    onFinishedListener.galidesawarHistoryFinished(starLineWinModel);
                    onFinishedListener.message(starLineWinModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<GalidesawarWinModel> call, Throwable t) {
                onFinishedListener.message("Server Error");
                System.out.println("bidHistory error "+t);
                onFinishedListener.failure(t);

            }
        });
    }
}
