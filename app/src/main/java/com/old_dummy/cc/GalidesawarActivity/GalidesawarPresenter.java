package com.old_dummy.cc.GalidesawarActivity;

import android.app.Activity;
import android.content.Intent;

import com.old_dummy.cc.GalidesawarChart.GalidesawarChartActivity;
import com.old_dummy.cc.HistoryActivity.WinHistoryActivity;
import com.old_dummy.cc.Models.GalidesawarGameListModel;
import com.old_dummy.cc.R;

import java.util.ArrayList;

public class GalidesawarPresenter implements GalidesawarContract.ViewModel.OnFinishedListener, GalidesawarContract.Presenter{

    GalidesawarContract.View view;
    GalidesawarContract.ViewModel viewModel;

    public GalidesawarPresenter(GalidesawarContract.View view) {
        this.view = view;
        viewModel = new GalidesawarViewModel();
    }

    @Override
    public void finished(GalidesawarGameListModel.Data data) {
        if (view!=null){
            view.hideProgressBar();
            view.apiResponse(data);
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
    public void api(String token) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callApi(this, token);
    }

    @Override
    public void chart(Activity activity, ArrayList<String> name) {
        Intent intent = new Intent(activity, GalidesawarChartActivity.class);
        intent.putExtra("gameNames",name);
        activity.startActivity(intent);
    }

    @Override
    public void History(Activity activity, int history, String title) {
        Intent bidHistory = new Intent(activity, WinHistoryActivity.class);
        bidHistory.putExtra(activity.getString(R.string.history), history);
        bidHistory.putExtra("from", title);
        activity.startActivity(bidHistory);
    }
}
