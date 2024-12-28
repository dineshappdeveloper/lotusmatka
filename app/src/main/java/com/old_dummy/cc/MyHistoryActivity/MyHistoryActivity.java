package com.old_dummy.cc.MyHistoryActivity;

import static com.old_dummy.cc.Extras.Utility.BroadCastStringForAction;
import static com.old_dummy.cc.Extras.Utility.myReceiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.BaseActivity;
import com.old_dummy.cc.Extras.SharPrefHelper;
import com.old_dummy.cc.Extras.Utility;
import com.old_dummy.cc.Extras.YourService;
import com.old_dummy.cc.R;

public class MyHistoryActivity extends BaseActivity implements MyHistoryContract.View {

    MaterialTextView dataConText;
    IntentFilter mIntentFilter;
    Utility utility;
    MyHistoryContract.Presenter presenter;
    LinearLayout galiHistory,starlineHistory;
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_my_history;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_color));
//        setContentView(R.layout.activity_my_history);
        intIDs();
        MaterialToolbar toolbar = findViewById(R.id.appbarLayout).findViewById(R.id.toolbar);
        toolbar.setTitle("My History");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void intIDs() {
        dataConText = findViewById(R.id.dataConText);
        starlineHistory = findViewById(R.id.starlineHistory);
        galiHistory = findViewById(R.id.galiHistory);
        dataConText = findViewById(R.id.dataConText);
        dataConText = findViewById(R.id.dataConText);
        utility = new Utility(dataConText);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BroadCastStringForAction);
        Intent serviceIntent = new Intent(this, YourService.class);
        startService(serviceIntent);
        presenter = new MyHistoryPresenter(this);

        if(SharPrefHelper.getBooleanData(this,SharPrefHelper.KEY_STARLINE_MARKET_STATUS,false)){
            starlineHistory.setVisibility(View.VISIBLE);
        }else {
            starlineHistory.setVisibility(View.GONE);
        }
        if(SharPrefHelper.getBooleanData(this,SharPrefHelper.KEY_GALIDESAWAR_MARKET_STATUS,false)){
            galiHistory.setVisibility(View.VISIBLE);
        }else {
            galiHistory.setVisibility(View.GONE);
        }
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

    public void fundItemClick(View view) {
        switch (view.getTag().toString()){
            case "mainBid":
                presenter.history(this, 200,"Main Bid History");
                break;
            case "mainWin":
                presenter.history(this,100,"Main Win History");
                break;
            case "starBid":
                presenter.history(this, 400,"Starline Bid History");
                break;
            case "starWin":
                presenter.history(this, 300,"Starline Win History");
                break;
            case "galiBid":
                presenter.history(this, 600,"Galidesawar Bid History");
                break;
            case "galiWin":
                presenter.history(this,500,"Galidesawar Win History");
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}