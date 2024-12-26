package com.old_dummy.cc.Chart;

import android.app.Activity;
import android.content.Intent;

import com.old_dummy.cc.GalidesawarChart.GalidesawarChartActivity;
import com.old_dummy.cc.Models.GalidesawarGameListModel;
import com.old_dummy.cc.Models.StarlineGameListModel;
import com.old_dummy.cc.StarlineChart.StarlineChartActivity;

import java.util.ArrayList;
import java.util.List;

public class ChartPresenter implements ChartContract.ViewModel.OnFinishedListener, ChartContract.Presenter{

    ChartContract.View view;
    ChartContract.ViewModel viewModel;
    ArrayList<String>  starlineName ;
    ArrayList<String>  galiName ;

    public ChartPresenter(ChartContract.View view) {
        this.view = view;
        viewModel = new ChartViewModel();
        starlineName = new ArrayList<>();
        galiName = new ArrayList<>();
    }

    @Override
    public void finishedStarline(List<StarlineGameListModel.Data.StarlineGame> nameList) {
        for (int i=0; i <nameList.size(); i++){
            starlineName.add(nameList.get(i).getName());
        }
    }
    @Override
    public void finishedGali(List<GalidesawarGameListModel.Data.GalidesawarGame> nameList) {
        if (view!=null){
            view.hideProgressBar();
        }
        for (int i=0; i <nameList.size(); i++){
            galiName.add(nameList.get(i).getName());
        }
    }


    @Override
    public void message(String msg) {
        if (view!=null){
            view.hideProgressBar();
            view.message(msg);
        }
    }

    @Override
    public void destroy(String msg) {
        if (view!=null){
            view.hideProgressBar();
            view.message(msg);
        }
    }

    @Override
    public void failure(Throwable t) {
        if (view!=null){
            view.hideProgressBar();
        }
    }
    @Override
    public void starlineApi(String token) {
        viewModel.callStarlineApi(this, token);
    }

    @Override
    public void galiApi(String token) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callGaliApi(this, token);
    }

    @Override
    public void starlineChart(Activity activity) {
        Intent intent = new Intent(activity, StarlineChartActivity.class);
        intent.putExtra("gameNames",starlineName);
        activity.startActivity(intent);
    }
    @Override
    public void galiChart(Activity activity) {
        Intent intent = new Intent(activity, GalidesawarChartActivity.class);
        intent.putExtra("gameNames",galiName);
        activity.startActivity(intent);
    }

}
