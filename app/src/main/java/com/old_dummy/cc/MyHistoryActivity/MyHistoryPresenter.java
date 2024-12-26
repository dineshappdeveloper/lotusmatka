package com.old_dummy.cc.MyHistoryActivity;

import android.app.Activity;
import android.content.Intent;

import com.old_dummy.cc.HistoryActivity.WinHistoryActivity;
import com.old_dummy.cc.R;

public class MyHistoryPresenter implements MyHistoryContract.Presenter{
    MyHistoryContract.View view;

    public MyHistoryPresenter(MyHistoryContract.View view) {
        this.view = view;
    }


    @Override
    public void history(Activity activity, int history,String title) {
        Intent winHistory = new Intent(activity, WinHistoryActivity.class);
        winHistory.putExtra(activity.getString(R.string.history), history);
        winHistory.putExtra("from", title);
        activity.startActivity(winHistory);
    }
}
