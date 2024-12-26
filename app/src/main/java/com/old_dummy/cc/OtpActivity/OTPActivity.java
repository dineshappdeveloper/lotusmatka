package com.old_dummy.cc.OtpActivity;

import static com.old_dummy.cc.Extras.Utility.BroadCastStringForAction;
import static com.old_dummy.cc.Extras.Utility.myReceiver;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.MainActivity.MainActivity;
import com.old_dummy.cc.Extras.SharPrefHelper;
import com.old_dummy.cc.Extras.Utility;
import com.old_dummy.cc.Extras.YourService;
import com.old_dummy.cc.NewPasswordActivity.NewPasswordActivity;
import com.old_dummy.cc.R;

import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;

public class OTPActivity extends AppCompatActivity implements OtpContract.View {

    private OtpTextView mInPC;
    private FrameLayout progressBar;
    private MaterialTextView dataConText;
    private MaterialTextView resendOtp;
    private IntentFilter mIntentFilter;
    int code = 200;
    String mobileNumber = "";
    Utility utility;
    OtpContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_color));
        getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.background_bg));
        setContentView(R.layout.activity_otpactivity);

        intVariables();
        loadData();
        presenter.countdown(resendOtp);
    }

    private void loadData() {
        utility = new Utility(dataConText);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BroadCastStringForAction);
        Intent serviceIntent = new Intent(this, YourService.class);
        startService(serviceIntent);
    }

    private void intVariables() {
        mInPC = findViewById(R.id.in_pc);
        progressBar = findViewById(R.id.progressBar);
        dataConText = findViewById(R.id.dataConText);
        resendOtp = findViewById(R.id.resendOtp);
        presenter = new OtpPresenter(this);
        code = getIntent().getIntExtra(getString(R.string.verification),200);
        mobileNumber = getIntent().getStringExtra(getString(R.string.mobile_number));

        MaterialTextView topTitle = findViewById(R.id.topDesign).findViewById(R.id.topText);
        topTitle.setText("Otp\nVerification");
//        findViewById(R.id.backButton).setOnClickListener(v -> {
//            onBackPressed();
//        });
        mInPC.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {

            }

            @Override
            public void onOTPComplete(String otp) {
                verifyOtp(mInPC.getRootView());
            }
        });
    }

    public void verifyOtp(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        if (TextUtils.isEmpty(mInPC.getOTP())){
            Snackbar.make(view, "Please Enter OTP",2000).show();
            return;
        }
        if (mInPC.getOTP().length()<4){
            Snackbar.make(view, "Please Enter a valid OTP",2000).show();
            return;
        }
        if (YourService.isOnline(this)){
            switch (code){
                case 200:
                    presenter.verifyUserMethodApi(mobileNumber, mInPC.getOTP());
                    break;
                case 300:
                case 400:
                    presenter.verifyOtpApi(mobileNumber,mInPC.getOTP());
                    break;
            }
        }
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

    public void resendOtp(View view) {
        presenter.resendOtpApi(mobileNumber);
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
    public void verifyOtpApiResponse(String token) {
        SharPrefHelper.setLoginToken(this, token);
        Intent intent = new Intent(this, NewPasswordActivity.class);
        intent.putExtra(getString(R.string.verification), code);
        intent.putExtra(getString(R.string.mobile_number),mobileNumber);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void verifyUserMethodApiResponse(String token) {
        SharPrefHelper.setLoginSuccess(this, true);
        SharPrefHelper.setLoginToken(this, token);
        Intent intentMain = new Intent(this, MainActivity.class);
        intentMain.putExtra("from","signup");
        intentMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentMain);
        finish();
    }

    @Override
    public void resendOtpApiResponse() {
        presenter.countdown(resendOtp);
    }

    @Override
    public void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}