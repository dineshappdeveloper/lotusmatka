package com.old_dummy.cc.ServerActivity;

import static com.old_dummy.cc.Extras.Utility.BroadCastStringForAction;
import static com.old_dummy.cc.Extras.Utility.myReceiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Extras.Utility;
import com.old_dummy.cc.Extras.YourService;
import com.old_dummy.cc.R;

public class ServerActivity extends AppCompatActivity implements ServerContract.View {

    MaterialToolbar toolbar;
    MaterialTextView mtvAppNotice;
    MaterialTextView dataConText;
    IntentFilter mIntentFilter;
    Utility utility;
    ServerContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_color));
        setContentView(R.layout.activity_server);
        intIDs();
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
    private void intIDs() {
        toolbar = findViewById(R.id.toolbar);
        mtvAppNotice = findViewById(R.id.mtv_app_notice);
        dataConText = findViewById(R.id.dataConText);
        utility = new Utility(dataConText);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BroadCastStringForAction);
        Intent serviceIntent = new Intent(this, YourService.class);
        startService(serviceIntent);

        presenter = new ServerPresenter(this);

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