package com.old_dummy.cc.BankMethodActivity;

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

public class BankMethodActivity extends AppCompatActivity implements BankMethodContract.View{

    MaterialTextView dataConText;
    IntentFilter mIntentFilter;
    Utility utility;
    BankMethodContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_color));
        setContentView(R.layout.activity_bank_method);
        intIDs();
        configureToolbar();

        utility = new Utility(dataConText);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BroadCastStringForAction);
        Intent serviceIntent = new Intent(this, YourService.class);
        startService(serviceIntent);
    }

    private void intIDs() {
        dataConText = findViewById(R.id.dataConText);
        presenter = new BankMethodPresenter(this);
    }
    private void configureToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.appbarLayout).findViewById(R.id.toolbar);
        toolbar.setTitle("Payment Method");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void Bank(View view) {
       presenter.bank(this);
    }

    public void paytm(View view) {
        presenter.method(this, 1);
    }

    public void phonePe(View view) {
        presenter.method(this, 3);
    }

    public void googlePay(View view) {
        presenter.method(this, 2);

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