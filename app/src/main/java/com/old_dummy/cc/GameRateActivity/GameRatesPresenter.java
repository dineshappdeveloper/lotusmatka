package com.old_dummy.cc.GameRateActivity;

import com.old_dummy.cc.Models.GameRateModel;
import com.old_dummy.cc.Models.HowToPlayModel;

public class GameRatesPresenter implements GameRatesContract.ViewModel.OnFinishedListener, GameRatesContract.Presenter{

    GameRatesContract.View view;
    GameRatesContract.ViewModel viewModel;

    public GameRatesPresenter(GameRatesContract.View view) {
        this.view = view;
        viewModel = new GameRatesViewModel();
    }

    @Override
    public void gameRatesFinished(GameRateModel data) {
        if (view!=null){
            view.hideProgressBar();
            view.gameRatesApiResponse(data);
        }
    }

    @Override
    public void howToPlayFinished(HowToPlayModel.Data data) {
        if (view!=null){
            view.hideProgressBar();
            view.howToPlayApiResponse(data);
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
            view.hideProgressBar();
            view.destroy(msg);
        }
    }

    @Override
    public void failure(Throwable t) {
        if (view!=null){
            view.hideProgressBar();
        }
    }

    @Override
    public void gameRatesApi(String token) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callGameRatesApi(this, token);
    }

    @Override
    public void howToPlayApi(String token) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callHowToPlayApi(this,token);
    }
}
