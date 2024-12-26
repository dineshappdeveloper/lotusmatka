package com.old_dummy.cc.BankDetailsActivity;

import static com.old_dummy.cc.Extras.Utility.BroadCastStringForAction;
import static com.old_dummy.cc.Extras.Utility.myReceiver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Extras.SharPrefHelper;
import com.old_dummy.cc.Extras.Utility;
import com.old_dummy.cc.Extras.YourService;
import com.old_dummy.cc.LoginActivity.LoginActivity;
import com.old_dummy.cc.R;
import com.old_dummy.cc.SplashActivity.SplashActivity;

public class BankDetailsActivity extends AppCompatActivity implements BankDetailsContract.View {

    TextInputEditText holderName, accountNumber,conformAcNumber,ifscCode,bankName,branchAddress;
    InputMethodManager imm;
    ProgressBar progressBar;
    MaterialTextView dataConText;
    IntentFilter mIntentFilter;
    Utility utility;
    BankDetailsContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_color));
        setContentView(R.layout.activity_bank_details);
        intIDs();
        configureToolbar();
        if (SharPrefHelper.getBankDetails(this, SharPrefHelper.KEY_BANK_HOLDER_NAME)!=null)holderName.setText(SharPrefHelper.getBankDetails(this, SharPrefHelper.KEY_BANK_HOLDER_NAME));else holderName.setText("");
        if (SharPrefHelper.getBankDetails(this, SharPrefHelper.KEY_BANK_AC_NUMBER)!=null)accountNumber.setText(SharPrefHelper.getBankDetails(this, SharPrefHelper.KEY_BANK_AC_NUMBER));else accountNumber.setText("");
        if (SharPrefHelper.getBankDetails(this, SharPrefHelper.KEY_BANK_AC_NUMBER)!=null)conformAcNumber.setText(SharPrefHelper.getBankDetails(this, SharPrefHelper.KEY_BANK_AC_NUMBER));else conformAcNumber.setText("");
        if (SharPrefHelper.getBankDetails(this, SharPrefHelper.KEY_BANK_IFSC_CODE)!=null)ifscCode.setText(SharPrefHelper.getBankDetails(this, SharPrefHelper.KEY_BANK_IFSC_CODE));else ifscCode.setText("");
        if (SharPrefHelper.getBankDetails(this, SharPrefHelper.KEY_BANK_NAME)!=null)bankName.setText(SharPrefHelper.getBankDetails(this, SharPrefHelper.KEY_BANK_NAME));else bankName.setText("");
        if (SharPrefHelper.getBankDetails(this, SharPrefHelper.KEY_BANK_ADDRESS)!=null)branchAddress.setText(SharPrefHelper.getBankDetails(this, SharPrefHelper.KEY_BANK_ADDRESS));else branchAddress.setText("");

        utility = new Utility(dataConText);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BroadCastStringForAction);
        Intent serviceIntent = new Intent(this, YourService.class);
        startService(serviceIntent);

    }


    private void intIDs() {
        holderName = findViewById(R.id.holderName);
        accountNumber = findViewById(R.id.accountNumber);
        conformAcNumber = findViewById(R.id.conformAcNumber);
        ifscCode = findViewById(R.id.ifscCode);
        bankName = findViewById(R.id.bankName);
        branchAddress = findViewById(R.id.branchAddress);
        progressBar = findViewById(R.id.progressBar);
        dataConText = findViewById(R.id.dataConText);

        presenter = new BankDetailsPresenter(this);

    }
    private void configureToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.appbarLayout).findViewById(R.id.toolbar);
        toolbar.setTitle("Bank Details");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void submitBankDetails(View view) {
        imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        if (TextUtils.isEmpty(holderName.getText().toString())){
            Snackbar.make(view, getString(R.string.please_enter_your_bank_account_holder_name), 2000).show();
            return;
        }
        if (TextUtils.isEmpty(accountNumber.getText().toString())){
            Snackbar.make(view, getString(R.string.please_enter_your_bank_account_number), 2000).show();
            return;
        }
        if (accountNumber.getText().toString().length()<5){
            Snackbar.make(view, getString(R.string.enter_valid_bank_account_number), 2000).show();
            return;
        }
        if (TextUtils.isEmpty(conformAcNumber.getText().toString())){
            Snackbar.make(view, getString(R.string.please_enter_your_conform_bank_account_number), 2000).show();
            return;
        }
        if (!accountNumber.getText().toString().equals(conformAcNumber.getText().toString())){
            Snackbar.make(view, getString(R.string.account_number_not_matching), 2000).show();
            return;
        }
        if (TextUtils.isEmpty(ifscCode.getText().toString())){
            Snackbar.make(view, getString(R.string.please_enter_your_bank_ifsc_code), 2000).show();
            return;
        }
        if (ifscCode.getText().toString().length()<11){
            Snackbar.make(view, getString(R.string.please_enter_valid_ifsc_code_of_your_bank), 2000).show();
            return;
        }
        if (TextUtils.isEmpty(bankName.getText().toString())){
            Snackbar.make(view, getString(R.string.please_enter_your_bank_name), 2000).show();
            return;
        }
        if (TextUtils.isEmpty(branchAddress.getText().toString())){
            Snackbar.make(view, getString(R.string.please_enter_your_branch_address), 2000).show();
            return;
        }

        if (YourService.isOnline(this))
        presenter.api(SharPrefHelper.getLogInToken(this),holderName.getText().toString(),accountNumber.getText().toString(), ifscCode.getText().toString(),bankName.getText().toString(),branchAddress.getText().toString());
        else Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
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
    public void apiResponse() {
        SharPrefHelper.setBankDetails(this, SharPrefHelper.KEY_BANK_HOLDER_NAME, holderName.getText().toString());
        SharPrefHelper.setBankDetails(this, SharPrefHelper.KEY_BANK_AC_NUMBER, accountNumber.getText().toString());
        SharPrefHelper.setBankDetails(this, SharPrefHelper.KEY_BANK_IFSC_CODE, ifscCode.getText().toString());
        SharPrefHelper.setBankDetails(this, SharPrefHelper.KEY_BANK_NAME, bankName.getText().toString());
        SharPrefHelper.setBankDetails(this, SharPrefHelper.KEY_BANK_ADDRESS,branchAddress.getText().toString());
        onBackPressed();
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