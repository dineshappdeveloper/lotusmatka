package com.old_dummy.cc.TransferPointsActivity;

import static com.old_dummy.cc.Extras.Utility.BroadCastStringForAction;
import static com.old_dummy.cc.Extras.Utility.myReceiver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Extras.SharPrefHelper;
import com.old_dummy.cc.Extras.Utility;
import com.old_dummy.cc.Extras.YourService;
import com.old_dummy.cc.LoginActivity.LoginActivity;
import com.old_dummy.cc.R;
import com.old_dummy.cc.SplashActivity.SplashActivity;

public class TransferPointsActivity extends AppCompatActivity implements TransferPointsContract.View{

    TextInputEditText inputPoints,userNumber;
    String s;
    int points=0,ava_points=0;
    InputMethodManager imm;
    MaterialToolbar toolbar;
    ProgressBar progressBar;
    MaterialTextView pointText,userName,walletAmount;
    MaterialButton submitButton,verifyBtn;
    MaterialTextView dataConText;
    IntentFilter mIntentFilter;
    Utility utility;
    TransferPointsContract.Presenter presenter;
    LinearLayout pointLyt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_color));
        setContentView(R.layout.activity_transfer_points);
        intIDs();
        configureToolbar();
    }

    private void intIDs() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Transfer Points");
        inputPoints = findViewById(R.id.inputPoints);
        progressBar = findViewById(R.id.progressBar);
        userNumber = findViewById(R.id.userNumber);
        verifyBtn = findViewById(R.id.verifyText);
        pointText = findViewById(R.id.pointText);
        userName = findViewById(R.id.userName);
        submitButton = findViewById(R.id.submitButton);
        pointLyt = findViewById(R.id.pointsLyt);
        walletAmount = findViewById(R.id.walletAmount);

        presenter = new TransferPointsPresenter(this);

        dataConText = findViewById(R.id.dataConText);
        utility = new Utility(dataConText);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BroadCastStringForAction);
        Intent serviceIntent = new Intent(this, YourService.class);
        startService(serviceIntent);
        ava_points = Integer.parseInt(SharPrefHelper.getUserPoints(TransferPointsActivity.this));
        walletAmount.setText(String.valueOf(ava_points));
    }

    private void configureToolbar() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        userNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()<10){
                    verifyBtn.setEnabled(false);
                    pointText.setVisibility(View.GONE);
                    pointLyt.setVisibility(View.GONE);
                    submitButton.setVisibility(View.GONE);
                    userName.setVisibility(View.GONE);

                }else {
                    verifyBtn.setEnabled(true);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void submitPoints(View view) {
        imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        s = inputPoints.getText().toString();
        if (s.length()>0){
            points = Integer.parseInt(s);
        }
        if (TextUtils.isEmpty(s) || points<1){
            Snackbar.make(view, getString(R.string.please_enter_points), 2000).show();
            return;
        }
        if (points<Integer.parseInt(SharPrefHelper.getMinMaxData(this, SharPrefHelper.KEY_MIN_TRANSFER_POINTS))){
            Snackbar.make(view,getString(R.string.minimum_points_must_be_greater_then_)+SharPrefHelper.getMinMaxData(this, SharPrefHelper.KEY_MIN_TRANSFER_POINTS), 2000).show();
            return;
        }
        if (points>Integer.parseInt(SharPrefHelper.getMinMaxData(this, SharPrefHelper.KEY_MAX_TRANSFER_POINTS))){
            Snackbar.make(view, getString( R.string.maximum_points_must_be_less_then_)+SharPrefHelper.getMinMaxData(this, SharPrefHelper.KEY_MAX_TRANSFER_POINTS), 2000).show();
            return;
        }
        if (points>ava_points){
            Snackbar.make(view, "Insufficient Points", 2000).show();
            return;
        }
        if (YourService.isOnline(this))
            presenter.transferPointsApi(SharPrefHelper.getLogInToken(TransferPointsActivity.this), inputPoints.getText().toString(), userNumber.getText().toString());
        else Toast.makeText(this, getString(R.string.check_your_internet_connection), Toast.LENGTH_SHORT).show();

    }


    public void verify(View view) {
        imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        if (YourService.isOnline(this))
        presenter.verificationApi(SharPrefHelper.getLogInToken(this), userNumber.getText().toString());
        else Toast.makeText(this, getString(R.string.check_your_internet_connection), Toast.LENGTH_SHORT).show();
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
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void transferApiResponse() {
        inputPoints.setText("");
        userNumber.setText("");
        SharPrefHelper.setUserPoints(this,String.valueOf(ava_points-points));
        walletAmount.setText(String.valueOf(ava_points-points));
        verifyBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void verificationApiResponse(String name) {
        verifyBtn.setEnabled(false);
        pointText.setVisibility(View.VISIBLE);
        pointLyt.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        userName.setText(name);
        userName.setVisibility(View.VISIBLE);
        inputPoints.requestFocus();
    }

    @Override
    public void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void destroy(String msg) {
        SharPrefHelper.setClearData(this);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finish();
    }
}