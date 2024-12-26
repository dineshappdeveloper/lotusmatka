package com.old_dummy.cc.GalidesawarGameActivity;

import android.app.Activity;
import android.content.Intent;

import com.old_dummy.cc.GalidesawarProceedActivity.GalidesawarBidPlacedActivity;
import com.old_dummy.cc.R;

public class GalidesawarGamePresenter implements GalidesawarGameContract.Presenter{
    GalidesawarGameContract.View view;

    public GalidesawarGamePresenter(GalidesawarGameContract.View view) {
        this.view = view;
    }

    @Override
    public void gameClick(Activity activity,String games, String gameName, int innerGame) {
        Intent intent = new Intent(activity, GalidesawarBidPlacedActivity.class);
        intent.putExtra("games",games);
        intent.putExtra("gamesName",gameName);
        intent.putExtra(activity.getString(R.string.game_name), innerGame);
        activity.startActivity(intent);
    }
}
