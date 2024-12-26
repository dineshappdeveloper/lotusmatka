package com.old_dummy.cc.StarLineBidPlacedActivity;

import android.view.View;

public class StarLineBidPlacedPresenter implements StarLineBidPlacedContract.ViewModel.OnFinishedListener, StarLineBidPlacedContract.Presenter{

    StarLineBidPlacedContract.View view;
    StarLineBidPlacedContract.ViewModel viewModel;

    public StarLineBidPlacedPresenter(StarLineBidPlacedContract.View view) {
        this.view = view;
        viewModel = new StarLineBidPlacedViewModel();
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
