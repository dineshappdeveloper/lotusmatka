package com.old_dummy.cc.StarlineGameActivity;

import android.app.Activity;
import android.content.Intent;

import com.old_dummy.cc.R;
import com.old_dummy.cc.StarLineBidPlacedActivity.StarLineBidPlacedActivity;

public class StarlineGamePresenter implements StarlineGameContract.Presenter{
    StarlineGameContract.View view;

    public StarlineGamePresenter(StarlineGameContract.View view) {
        this.view = view;
    }

    @Override
    public void gameClick(Activity activity,String games, String gameName, int innerGame) {
        Intent intent = new Intent(activity, StarLineBidPlacedActivity.class);
        intent.putExtra("games",games);
        intent.putExtra("gamesName",gameName);
        intent.putExtra(activity.getString(R.string.game_name), innerGame);
        activity.startActivity(intent);
    }
}
