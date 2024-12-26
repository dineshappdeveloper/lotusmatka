package com.old_dummy.cc.GameActivity;

import android.app.Activity;
import android.content.Intent;

import com.old_dummy.cc.GameProceedActivity.GameProceedActivity;
import com.old_dummy.cc.R;

public class GameActivityPresenter implements GameActivityContract.Presenter{
    GameActivityContract.View view;

    public GameActivityPresenter(GameActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void gameClick(Activity activity, boolean open, String games, String gameName,int innerGame) {
        Intent intent = new Intent(activity, GameProceedActivity.class);
        intent.putExtra("open",open);
        intent.putExtra("games",games);
        intent.putExtra("gamesName",gameName);
        intent.putExtra(activity.getString(R.string.game_name), innerGame);
        activity.startActivity(intent);
    }
}
