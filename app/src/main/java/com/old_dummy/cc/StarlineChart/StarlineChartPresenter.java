package com.old_dummy.cc.StarlineChart;

import com.old_dummy.cc.Models.StarlineChartModel;

import java.util.List;

public class StarlineChartPresenter implements StarlineChartContract.ViewModel.OnFinishedListener, StarlineChartContract.Presenter{

    StarlineChartContract.View view;
    StarlineChartContract.ViewModel viewModel;

    public StarlineChartPresenter(StarlineChartContract.View view) {
        this.view = view;
        viewModel = new StarlineChartViewModel();
    }

    @Override
    public void finished(List<StarlineChartModel.Data> data) {
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
    public void error(String msg) {
        if (view!=null){
            view.error(msg);
        }
    }

    @Override
    public void failure(Throwable t) {

    }

    @Override
    public void api(String token) {
        viewModel.callApi(this,token);
    }
}
