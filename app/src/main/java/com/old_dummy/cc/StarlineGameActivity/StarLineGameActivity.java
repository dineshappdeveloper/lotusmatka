package com.old_dummy.cc.StarlineGameActivity;

import static com.old_dummy.cc.Extras.Utility.BroadCastStringForAction;
import static com.old_dummy.cc.Extras.Utility.myReceiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Extras.Utility;
import com.old_dummy.cc.Extras.YourService;
import com.old_dummy.cc.R;

public class StarLineGameActivity extends AppCompatActivity implements StarlineGameContract.View{
    String games="";
    Intent intent;
    MaterialToolbar toolbar;
    MaterialTextView dataConText;
    IntentFilter mIntentFilter;
    Utility utility;
    String gameName="";
    StarlineGameContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_color));
        setContentView(R.layout.activity_stae_line_game);
        intIDs();
    }

    private void intIDs() {
        toolbar = findViewById(R.id.toolbar);
        dataConText = findViewById(R.id.dataConText);
        presenter = new StarlineGamePresenter(this);
        utility = new Utility(dataConText);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BroadCastStringForAction);
        Intent serviceIntent = new Intent(this, YourService.class);
        startService(serviceIntent);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        games = getIntent().getStringExtra(getString(R.string.game));
        gameName = getIntent().getStringExtra(getString(R.string.game_name));
        toolbar.setTitle(gameName);
    }

    public void singleDigit(View view) {
        presenter.gameClick(this,games,gameName,8);
    }

    public void singlePana(View view) {
        presenter.gameClick(this,games,gameName,9);
    }

    public void doublePana(View view) {
        presenter.gameClick(this,games,gameName,10);
    }

    public void triplePana(View view) {
        presenter.gameClick(this,games,gameName,11);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onRestart() {
        super.onRestart();
        registerReceiver(myReceiver, mIntentFilter, Context.RECEIVER_NOT_EXPORTED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myReceiver, mIntentFilter, Context.RECEIVER_NOT_EXPORTED);
    }

    @Override
    public void message(String msg) {

    }
}