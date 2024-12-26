package com.old_dummy.cc.MainGameChart;

import com.old_dummy.cc.Models.MainGameChartModel;

import java.util.List;

public class MainGameChartPresenter implements MainGameChartContract.ViewModel.OnFinishedListener, MainGameChartContract.Presenter{

    MainGameChartContract.View view;
    MainGameChartContract.ViewModel viewModel;

    public MainGameChartPresenter(MainGameChartContract.View view) {
        this.view = view;
        viewModel = new MainGameChartViewModel();
    }

    @Override
    public void finished(List<MainGameChartModel.Data> data) {
        if (view!=null){
            view.apiResponse(data);
        }
    }

    @Override
    public void message(String msg) {
        if (view!=null){
            view.message(msg);
        }
    }

    @Override
    public void failure(Throwable t) {

    }

    @Override
    public void api(String token, String gameID) {
        viewModel.callApi(this,token, gameID);
    }
}
