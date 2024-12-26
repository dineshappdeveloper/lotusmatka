package com.old_dummy.cc.HistoryActivity;

import com.old_dummy.cc.Models.GalidesawarWinModel;
import com.old_dummy.cc.Models.StarLineWinModel;
import com.old_dummy.cc.Models.WinModel;

public class HistoryPresenter implements HistoryContract.ViewModel.OnFinishedListener, HistoryContract.Presenter{

    HistoryContract.View view;
    HistoryContract.ViewModel viewModel;

    public HistoryPresenter(HistoryContract.View view) {
        this.view = view;
        viewModel = new HistoryViewModel();
    }


    @Override
    public void mainHistoryFinished(WinModel winModel) {
        if (view!=null){
            view.hideProgressBar();
            view.mainHistoryApiResponse(winModel);
        }
    }

    @Override
    public void starLineHistoryFinished(StarLineWinModel starLineWinModel) {
        if (view!=null){
            view.hideProgressBar();
            view.starLineHistoryApiResponse(starLineWinModel);
        }
    }

    @Override
    public void galidesawarHistoryFinished(GalidesawarWinModel starLineWinModel) {
        if (view!=null){
            view.hideProgressBar();
            view.galidesawarHistoryApiResponse(starLineWinModel);
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
    public void mainHistoryApi(String token, String fromDate, String toDate, int history) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callMainHistoryApi(this,token,fromDate,toDate, history);
    }

    @Override
    public void starLineHistoryApi(String token, String fromDate, String toDate, int history) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callStarLineHistoryApi(this,token,fromDate,toDate, history);
    }

    @Override
    public void galidesawarHistoryApi(String token, String fromDate, String toDate, int history) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callGalidesawarHistoryApi(this,token,fromDate,toDate, history);
    }

}
