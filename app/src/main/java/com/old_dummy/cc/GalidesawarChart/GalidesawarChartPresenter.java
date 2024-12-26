package com.old_dummy.cc.GalidesawarChart;


import com.old_dummy.cc.Models.GalidesawarChartModel;

import java.util.List;

public class GalidesawarChartPresenter implements GalidesawarChartContract.ViewModel.OnFinishedListener, GalidesawarChartContract.Presenter{

    GalidesawarChartContract.View view;
    GalidesawarChartContract.ViewModel viewModel;

    public GalidesawarChartPresenter(GalidesawarChartContract.View view) {
        this.view = view;
        viewModel = new GalidesawarChartViewModel();
    }

    @Override
    public void finished(List<GalidesawarChartModel.Data> data) {
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
