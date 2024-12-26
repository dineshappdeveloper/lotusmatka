package com.old_dummy.cc.TopUpActivity;

import static com.old_dummy.cc.Extras.Utility.BroadCastStringForAction;
import static com.old_dummy.cc.Extras.Utility.myReceiver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.old_dummy.cc.AddFundMethodsActivity.AddFundMethodsActivity;
import com.old_dummy.cc.Extras.SharPrefHelper;
import com.old_dummy.cc.Extras.Utility;
import com.old_dummy.cc.Extras.YourService;
import com.old_dummy.cc.LoginActivity.LoginActivity;
import com.old_dummy.cc.Models.AppDetailsModel;
import com.old_dummy.cc.Models.PaymentConfigModel;
import com.old_dummy.cc.R;
import com.old_dummy.cc.SplashActivity.SplashActivity;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import dev.shreyaspatil.easyupipayment.EasyUpiPayment;
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener;
import dev.shreyaspatil.easyupipayment.model.PaymentApp;
import dev.shreyaspatil.easyupipayment.model.TransactionDetails;

public class TopUpActivity extends AppCompatActivity implements PaymentStatusListener, TopUpContract.View {

    TextInputEditText inputPoints;
    String s,supportNumber;
    int points;
    InputMethodManager imm;
    ProgressBar progressBar;
    PaymentApp paymentApp;
    MaterialTextView dataConText,mtv_add_fund_notice, pointsTV;
    IntentFilter mIntentFilter;
    Utility utility;
    TopUpContract.Presenter presenter;
    List<String> tabList;
    PaymentConfigModel.Data configData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_color));
        setContentView(R.layout.activity_top_up);
        intIDs();
        configureToolbar();
    }

    private void intIDs() {
        pointsTV = findViewById(R.id.points);
        inputPoints = findViewById(R.id.inputPoints);
        progressBar = findViewById(R.id.progressBar);
        presenter = new TopUpPresenter(this);
        dataConText = findViewById(R.id.dataConText);
        mtv_add_fund_notice = findViewById(R.id.mtv_add_fund_notice);
        utility = new Utility(dataConText);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BroadCastStringForAction);
        Intent serviceIntent = new Intent(this, YourService.class);
        startService(serviceIntent);
        tabList = new ArrayList<>();
        AppDetailsModel.Data data = null;
        Gson gson = new Gson();
        Type type = new TypeToken<AppDetailsModel.Data>() {
        }.getType();
        try {
            data = gson.fromJson(SharPrefHelper.getPreferenceData(this,SharPrefHelper.KEY_App_Details), type);
        } catch (Exception e) {
            System.out.println("json conversion failed");
        }
        mtv_add_fund_notice.setText(data.getAdd_fund_notice());
        pointsTV.setText(SharPrefHelper.getUserPoints(this));
        presenter.paymentConfigApi(SharPrefHelper.getLogInToken(this));
    }

    private void configureToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.appbarLayout).findViewById(R.id.toolbar);
        toolbar.setTitle("Add Point");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void submitPoints(View view) {
        imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        s = inputPoints.getText().toString();
        if (!s.isEmpty()){
            points = Integer.parseInt(s);
        }
        if (TextUtils.isEmpty(s)){
            Snackbar.make(view, getString(R.string.please_enter_points), 2000).show();
            return;
        }
        if (points<Integer.parseInt(SharPrefHelper.getMinMaxData(this, SharPrefHelper.KEY_MIN_ADD_FUND_POINTS))){
            Snackbar.make(view,getString(R.string.minimum_points_must_be_greater_then_)+" "+SharPrefHelper.getMinMaxData(this, SharPrefHelper.KEY_MIN_ADD_FUND_POINTS), 2000).show();
            return;
        }
        if (points>Integer.parseInt(SharPrefHelper.getMinMaxData(this, SharPrefHelper.KEY_MAX_ADD_FUND_POINTS))){
            Snackbar.make(view, getString( R.string.maximum_points_must_be_less_then_)+" "+SharPrefHelper.getMinMaxData(this, SharPrefHelper.KEY_MAX_ADD_FUND_POINTS), 2000).show();
            return;
        }
        if (YourService.isOnline(this)) {
            if(tabList.isEmpty()){
                String url = "https://api.whatsapp.com/send?phone="+supportNumber;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }else {
                Intent intent = new Intent(this, AddFundMethodsActivity.class);
                intent.putExtra("configData", configData);
                intent.putExtra("amount", inputPoints.getText().toString());
                startActivity(intent);
            }

        }
        else Toast.makeText(this, getString(R.string.check_your_internet_connection), Toast.LENGTH_SHORT).show();
    }

    private void paymentDialog() {
        String transactionId = "TID" + System.currentTimeMillis();
        String amount = inputPoints.getText().toString()+".0";
        // START PAYMENT INITIALIZATION
        EasyUpiPayment.Builder builder = new EasyUpiPayment.Builder(this)
                .with(paymentApp)
                .setPayeeVpa(SharPrefHelper.getAddFundUpiId(this, SharPrefHelper.KEY_ADD_FUND_UPI_ID))
                .setPayeeName(SharPrefHelper.getAddFundUpiId(this, SharPrefHelper.KEY_ADD_FUND_UPI_NAME))
                .setTransactionId(transactionId)
                .setTransactionRefId(transactionId)
                .setPayeeMerchantCode("")
                .setDescription(getString(R.string.app_name))
                .setAmount(amount);

        // END INITIALIZATION
        try {
            // Build instance
            EasyUpiPayment  easyUpiPayment = builder.build();

            // Register Listener for Events
            easyUpiPayment.setPaymentStatusListener(this);

            // Start payment / transaction
            easyUpiPayment.startPayment();
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error "+exception.getMessage());

        }
    }

    @Override
    public void onTransactionCompleted(@NotNull TransactionDetails transactionDetails) {
        switch (transactionDetails.getTransactionStatus()) {
            case SUCCESS:
                onTransactionSuccess(transactionDetails.getAmount());
                break;
            case FAILURE:
                onTransactionFailed();
                break;
            case SUBMITTED:
                onTransactionSubmitted();
                break;
        }
    }

    @Override
    public void onTransactionCancelled() {
        // Payment Cancelled by User
        toast("Cancelled by user");
    }

    private void onTransactionSuccess(String amount) {
        // Payment Success
        toast("Success");
        presenter.api(SharPrefHelper.getLogInToken(this), amount);
    }

    private void onTransactionSubmitted() {
        // Payment Pending
        toast("Pending | Submitted");
    }

    private void onTransactionFailed() {
        // Payment Failed
        toast("Failed");
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
    public void apiResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        inputPoints.setText("");
    }

    @Override
    public void paymentConfigApiResponse(PaymentConfigModel.Data data) {
        configData = data;
        supportNumber = data.getSupportNumber();
        if (data.getAvailableMethods().getUpi()){
           tabList.add("upi");
        }
        if (data.getAvailableMethods().getQrCode()){
            tabList.add("qr_code");
        }
        if (data.getAvailableMethods().getBankAccount()){
            tabList.add("bank_account");
        }
        if (data.getAvailableMethods().getPaymentGateway()!=null){
            for(int i =0; i<data.getAvailableMethods().getPaymentGateway().size();i++){
                tabList.add(data.getAvailableMethods().getPaymentGateway().get(i).getType());
            }
        }
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

    public void amountClip(View view) {
        inputPoints.setText(view.getTag().toString());
    }
}