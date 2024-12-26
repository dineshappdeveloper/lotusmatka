package com.old_dummy.cc.MainActivity;

import androidx.annotation.NonNull;

import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.Models.AppDetailsModel;
import com.old_dummy.cc.Models.GameListModel;
import com.old_dummy.cc.Models.LoginModel;
import com.old_dummy.cc.Models.UserStatusModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel implements MainContract.ViewModel{

    @Override
    public void callGameListApi(OnFinishedListener onFinishedListener, String token) {
        Call<GameListModel> call = ApiClient.getClient().mainGameList(token, "");
        call.enqueue(new Callback<GameListModel>() {
            @Override
            public void onResponse(@NonNull Call<GameListModel> call, @NonNull Response<GameListModel> response) {
                if (response.isSuccessful()){
                    GameListModel gameListModel = response.body();
                    assert gameListModel != null;
                    if (gameListModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(gameListModel.getMessage());
                    }
                    if(gameListModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.gameListFinished(gameListModel);
                    }
//                    onFinishedListener.message(gameListModel.getMessage());
                }else {
                    onFinishedListener.message("Network error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<GameListModel> call, @NonNull Throwable t) {
                System.out.println("game list Error "+t);
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);
            }
        });
    }

    @Override
    public void callUserStatusApi(OnFinishedListener onFinishedListener, String token) {
        Call<UserStatusModel> call = ApiClient.getClient().user_status(token,"");
        call.enqueue(new Callback<UserStatusModel>() {
            @Override
            public void onResponse(@NonNull Call<UserStatusModel> call, @NonNull Response<UserStatusModel> response) {
                if (response.isSuccessful()){
                    UserStatusModel userStatusModel = response.body();
                    if (userStatusModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(userStatusModel.getMessage());
                    }
                    if (userStatusModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.userStatusFinished(userStatusModel.getData());
                    }

                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<UserStatusModel> call, Throwable t) {
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);
                System.out.println("user_status error "+ t);
            }
        });
    }

    @Override
    public void callAppDetailsApi(OnFinishedListener onFinishedListener, String token) {
        Call<AppDetailsModel> call = ApiClient.getClient().getAppDetails(token,"");
        call.enqueue(new Callback<AppDetailsModel>() {
            @Override
            public void onResponse(@NonNull Call<AppDetailsModel> call, @NonNull Response<AppDetailsModel> response) {
                if (response.isSuccessful()){
                    AppDetailsModel appDetailsModel = response.body();
                    if (appDetailsModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(appDetailsModel.getMessage());
                    }
                    if (appDetailsModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.appDetailsFinished(appDetailsModel);
                    }else
                        onFinishedListener.message(appDetailsModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<AppDetailsModel> call, Throwable t) {
                System.out.println("getAppDetails error "+t);
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);

            }
        });
    }

    @Override
    public void callUserDetailsApi(OnFinishedListener onFinishedListener, String token) {
        Call<LoginModel> call = ApiClient.getClient().getUserDetails(token,"");
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.isSuccessful()){
                    LoginModel loginModel = response.body();
                    if (loginModel.getCode().equalsIgnoreCase("505")){
                        onFinishedListener.destroy(loginModel.getMessage());
                    }
                    if (loginModel.getStatus().equalsIgnoreCase("success")){
                        onFinishedListener.userDetailsFinished(loginModel);
                    }else
                        onFinishedListener.message(loginModel.getMessage());
                }else {
                    onFinishedListener.message("Network Error");
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                onFinishedListener.message("Server Error");
                onFinishedListener.failure(t);
                System.out.println("getUserDetails error "+t);

            }
        });
    }
}
