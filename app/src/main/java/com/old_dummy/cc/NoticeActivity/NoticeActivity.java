package com.old_dummy.cc.NoticeActivity;

import static com.old_dummy.cc.Extras.Utility.BroadCastStringForAction;
import static com.old_dummy.cc.Extras.Utility.myReceiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.old_dummy.cc.Extras.SharPrefHelper;
import com.old_dummy.cc.Extras.Utility;
import com.old_dummy.cc.Extras.YourService;
import com.old_dummy.cc.Models.AppDetailsModel;
import com.old_dummy.cc.R;

import java.lang.reflect.Type;

public class NoticeActivity extends AppCompatActivity implements NoticeContract.View {

    MaterialTextView mtvAppNotice, mtvAddFund,mtvWithdraw;
    MaterialTextView dataConText;
    IntentFilter mIntentFilter;
    Utility utility;
    NoticeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_color));
        setContentView(R.layout.activity_notice);
        intIDs();
    }
    private void intIDs() {
        mtvAppNotice = findViewById(R.id.mtv_app_notice);
        mtvAddFund = findViewById(R.id.mtv_add_fund_notice);
        mtvWithdraw = findViewById(R.id.mtv_withdraw_notice);
        dataConText = findViewById(R.id.dataConText);
        utility = new Utility(dataConText);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BroadCastStringForAction);
        Intent serviceIntent = new Intent(this, YourService.class);
        startService(serviceIntent);
        MaterialToolbar toolbar = findViewById(R.id.appbarLayout).findViewById(R.id.toolbar);
        toolbar.setTitle("Notice");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        presenter = new NoticePresenter(this);
        AppDetailsModel.Data data = null;
        Gson gson = new Gson();
        Type type = new TypeToken<AppDetailsModel.Data>() {
        }.getType();
        try {
            data = gson.fromJson(SharPrefHelper.getPreferenceData(this,SharPrefHelper.KEY_App_Details), type);
        } catch (Exception e) {
            System.out.println("json conversion failed");
        }
        mtvAppNotice.setText(data.getApp_notice());
        mtvWithdraw.setText(data.getWithdraw_notice());
        mtvAddFund.setText(data.getAdd_fund_notice());


        presenter = new NoticePresenter(this);
        presenter.api(SharPrefHelper.getLogInToken(this));
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

    @Override
    public void apiResponse(String msg) {
        SharPrefHelper.setPreferenceData(this,SharPrefHelper.KEY_PENDING_NOTICE,"0");
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}