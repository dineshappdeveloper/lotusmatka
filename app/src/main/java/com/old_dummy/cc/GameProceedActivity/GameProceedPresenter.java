package com.old_dummy.cc.GameProceedActivity;

import android.view.View;

public class GameProceedPresenter implements GameProceedContract.ViewModel.OnFinishedListener, GameProceedContract.Presenter{

    GameProceedContract.View view;
    GameProceedContract.ViewModel viewModel;

    public GameProceedPresenter(GameProceedContract.View view) {
        this.view = view;
        viewModel = new GameProceedViewModel();
    }

    @Override
    public void finished(View view2) {
        if (view!=null){
            view.hideProgressBar();
            view.apiResponse(view2);
        }
    }

    @Override
    public void message(String msg) {
        if (view!=null){
            view.message(msg);
        }
    }

    @Override
    public void destroy(String msg) {
        if (view!=null){
            view.destroy(msg);
            view.hideProgressBar();
        }
    }

    @Override
    public void failure(Throwable t) {
        if (view!=null){
            view.hideProgressBar();
        }
    }

    @Override
    public void api(String token, String serverData, View view2) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callApi(this,token,serverData,view2);
    }
}
