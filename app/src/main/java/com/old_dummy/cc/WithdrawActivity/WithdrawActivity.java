package com.old_dummy.cc.WithdrawActivity;

import static com.old_dummy.cc.Extras.Utility.BroadCastStringForAction;
import static com.old_dummy.cc.Extras.Utility.myReceiver;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.old_dummy.cc.Adapters.WalletAdapter;
import com.old_dummy.cc.Api.ApiClient;
import com.old_dummy.cc.BankDetailsActivity.BankDetailsActivity;
import com.old_dummy.cc.Extras.SharPrefHelper;
import com.old_dummy.cc.Extras.Utility;
import com.old_dummy.cc.Extras.YourService;
import com.old_dummy.cc.LoginActivity.LoginActivity;
import com.old_dummy.cc.Models.AppDetailsModel;
import com.old_dummy.cc.Models.CommonModel;
import com.old_dummy.cc.Models.WalletStatementModel;
import com.old_dummy.cc.R;
import com.old_dummy.cc.SplashActivity.SplashActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WithdrawActivity extends AppCompatActivity implements WithdrawContract.View{

    TextInputEditText inputWithdrawPoints;
    String s;
    int points;
    InputMethodManager imm;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    WalletAdapter walletAdapter;
    List<WalletStatementModel.Data.Statement> modelWalletArrayList = new ArrayList<>();
    ShapeableImageView emptyIcon;
    int currentPoints = 0;
    MaterialTextView dataConText,walletAmount;
    IntentFilter mIntentFilter;
    Utility utility;
    boolean checked = false;
    String paymentMethod = "";
   // RadioButton bankRadio, paytmRadio,phoneRadio, googlePayRadio;
    MaterialTextView /*bankNumber,paytmNumber,phonepeNumber,googlePayNumber,*/selectPayMethod,openTime,closeTime,mtv_withdraw_notice/*,title1,title2,title3,title4*/;
  //  RelativeLayout cardBank,cardPaytm,cardPhonepe,cardGooglePay;

    WithdrawContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_color));
        setContentView(R.layout.activity_withdraw);
        intIDs();
        configureToolbar();
        presenter.withdrawStatementApi(SharPrefHelper.getLogInToken(this));
    }
    private void intIDs() {
        inputWithdrawPoints = findViewById(R.id.inputWithdrawPoints);
        progressBar = findViewById(R.id.progressBar);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
        emptyIcon = findViewById(R.id.emptyIcon);
        /*bankRadio = findViewById(R.id.bankRadio);
        paytmRadio = findViewById(R.id.radioPaytm);
        phoneRadio = findViewById(R.id.radioPhonepe);
        googlePayRadio = findViewById(R.id.radioGooglePay);*/
        walletAmount = findViewById(R.id.walletAmount);
        selectPayMethod = findViewById(R.id.selectPaymentMethod);
        /*bankNumber = findViewById(R.id.bankNumber);
        paytmNumber = findViewById(R.id.paytmNumber);
        phonepeNumber = findViewById(R.id.phonepeNumber);
        googlePayNumber = findViewById(R.id.googlePayNumber);*/
        openTime = findViewById(R.id.openTime);
        closeTime = findViewById(R.id.closeTime);
        /*cardPhonepe = findViewById(R.id.cardPhonepe);
        cardGooglePay = findViewById(R.id.cardGooglePay);
        cardBank = findViewById(R.id.cardBank);
        cardPaytm = findViewById(R.id.cardPaytm);
        title1 = findViewById(R.id.title1);
        title2 = findViewById(R.id.title2);
        title3 = findViewById(R.id.title3);
        title4 = findViewById(R.id.title4);*/
        mtv_withdraw_notice = findViewById(R.id.mtv_withdraw_notice);

        presenter = new WithdrawPresenter(this);

        dataConText = findViewById(R.id.dataConText);
        utility = new Utility(dataConText);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BroadCastStringForAction);
        Intent serviceIntent = new Intent(this, YourService.class);
        startService(serviceIntent);

        walletAmount.setText(SharPrefHelper.getUserPoints(WithdrawActivity.this));
        AppDetailsModel.Data data = null;
        Gson gson = new Gson();
        Type type = new TypeToken<AppDetailsModel.Data>() {
        }.getType();
        try {
            data = gson.fromJson(SharPrefHelper.getPreferenceData(this,SharPrefHelper.KEY_App_Details), type);
        } catch (Exception e) {
            System.out.println("json conversion failed");
        }
        assert data != null;
        mtv_withdraw_notice.setText(data.getWithdraw_notice());
/*
        if (Objects.equals(SharPrefHelper.getPreferenceData(this, SharPrefHelper.KEY_PAYTM_UPI), ""))
        {
            paytmRadio.setEnabled(false);
            cardPaytm.setEnabled(false);
            paytmNumber.setEnabled(false);
            title3.setEnabled(false);
            paytmNumber.setText("Please add your paytm number");
        }
        else {
            paytmRadio.setEnabled(true);
            cardPaytm.setEnabled(true);
            paytmNumber.setEnabled(true);
            title3.setEnabled(true);
            paytmNumber.setText(SharPrefHelper.getPreferenceData(this, SharPrefHelper.KEY_PAYTM_UPI));
            cardPaytm.setOnClickListener(this::paytmClick);
        }
        if (Objects.equals(SharPrefHelper.getPreferenceData(this, SharPrefHelper.KEY_BANK_AC_NUMBER), ""))
        {
            cardBank.setEnabled(false);
            bankRadio.setEnabled(false);
            bankNumber.setEnabled(false);
            title4.setEnabled(false);
            bankNumber.setText("Please add your bank details");
        }
        else {
            bankRadio.setEnabled(true);
            cardBank.setEnabled(true);
            bankNumber.setEnabled(true);
            title4.setEnabled(true);
            bankNumber.setText(SharPrefHelper.getPreferenceData(this, SharPrefHelper.KEY_BANK_AC_NUMBER));
            cardBank.setOnClickListener(this::bankClick);
        }
        if (Objects.equals(SharPrefHelper.getPreferenceData(this, SharPrefHelper.KEY_PHONEPE_UPI), ""))
        {
            phoneRadio.setEnabled(false);
            cardPhonepe.setEnabled(false);
            phonepeNumber.setEnabled(false);
            title2.setEnabled(false);
            phonepeNumber.setText("Please add your phonePe number");
        }
        else {
            phoneRadio.setEnabled(true);
            cardPhonepe.setEnabled(true);
            phonepeNumber.setEnabled(true);
            title2.setEnabled(true);
            phonepeNumber.setText(SharPrefHelper.getPreferenceData(this, SharPrefHelper.KEY_PHONEPE_UPI));
            cardPhonepe.setOnClickListener(this::phonePeClick);
        }
        if (Objects.equals(SharPrefHelper.getPreferenceData(this, SharPrefHelper.KEY_GOOGLEPAY_UPI), "")) {
            googlePayRadio.setEnabled(false);
            cardGooglePay.setEnabled(false);
            googlePayNumber.setEnabled(false);
            title1.setEnabled(false);
            googlePayNumber.setText("Please add your google pay number");
        }else{
            googlePayRadio.setEnabled(true);
            cardGooglePay.setEnabled(true);
            googlePayNumber.setEnabled(true);
            title1.setEnabled(true);
            googlePayNumber.setText(SharPrefHelper.getPreferenceData(this, SharPrefHelper.KEY_GOOGLEPAY_UPI));
            cardGooglePay.setOnClickListener(this::googlePayClick);
        }*/
    }

    private void withdrawStatementMethod(WithdrawActivity activity) {
        swipeRefreshLayout.setRefreshing(true);
        Call<WalletStatementModel> call = ApiClient.getClient().withdrawStatement(SharPrefHelper.getLogInToken(this),"");
        call.enqueue(new Callback<WalletStatementModel>() {
            @Override
            public void onResponse(Call<WalletStatementModel> call, Response<WalletStatementModel> response) {
                if (response.isSuccessful()) {
                    WalletStatementModel walletStatementModel = response.body();
                    if (walletStatementModel.getCode().equalsIgnoreCase("505")) {
                        SharPrefHelper.setClearData(activity);
                        Toast.makeText(activity, walletStatementModel.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    if (walletStatementModel.getStatus().equalsIgnoreCase(getString(R.string.success))) {
                        if (walletStatementModel.getMessage().equalsIgnoreCase("No Data")){
                            emptyIcon.setVisibility(View.VISIBLE);
                        }else {
                            modelWalletArrayList = walletStatementModel.getData().getStatement();
                            LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
                            recyclerView.setLayoutManager(layoutManager);
                            walletAdapter = new WalletAdapter(activity, modelWalletArrayList);
                            recyclerView.setAdapter(walletAdapter);
                        }
                        if (walletStatementModel.getData().getWithdrawOpenTime()!=null)
                            openTime.setText("Withdraw Open time: "+walletStatementModel.getData().getWithdrawOpenTime());
                        if (walletStatementModel.getData().getWithdrawCloseTime()!=null)
                            closeTime.setText("Withdraw Close time: "+walletStatementModel.getData().getWithdrawCloseTime());
                        Toast.makeText(activity, walletStatementModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(activity, getString(R.string.response_error), Toast.LENGTH_SHORT).show();
                }
                swipeRefreshLayout.setRefreshing(false);


            }

            @Override
            public void onFailure(Call<WalletStatementModel> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                System.out.println("walletStatement error "+t);
                Toast.makeText(activity, getString(R.string.on_api_failure), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configureToolbar() {

        MaterialToolbar toolbar = findViewById(R.id.appbarLayout).findViewById(R.id.toolbar);
        toolbar.setTitle("Withdraw Points");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.withdrawStatementApi(SharPrefHelper.getLogInToken(WithdrawActivity.this));
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

    public void submitWithdrawPoints(View view) {
        imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        s = inputWithdrawPoints.getText().toString();
        if (s.length()>0){
            points = Integer.parseInt(s);
        }
        if (TextUtils.isEmpty(inputWithdrawPoints.getText().toString())){
            Snackbar.make(view, "Enter Points", 2000).show();
            return;
        }
        if (points<Integer.parseInt(SharPrefHelper.getMinMaxData(this, SharPrefHelper.KEY_MIN_WITHDRAW_POINTS))){
            Snackbar.make(view, getString(R.string.minimum_points_must_be_greater_then_)+" "+SharPrefHelper.getMinMaxData(this, SharPrefHelper.KEY_MIN_WITHDRAW_POINTS),2000).show();
            return;
        }
        if (points>Integer.parseInt(SharPrefHelper.getMinMaxData(this, SharPrefHelper.KEY_MAX_WITHDRAW_POINTS))){
            Snackbar.make(view, getString(R.string.maximum_points_must_be_less_then_)+" "+SharPrefHelper.getMinMaxData(this, SharPrefHelper.KEY_MAX_WITHDRAW_POINTS), 2000).show();
            return;
        }
        if (Objects.equals(paymentMethod, "")){
            Snackbar.make(view, getString(R.string.please_select_payment_method), 2000).show();
            return;
        }
        if(SharPrefHelper.getBankDetails(this, SharPrefHelper.KEY_BANK_AC_NUMBER).equals("")){


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert");
            builder.setMessage("We are facing the technical issue in UPI withdraw so please add your bank details.");

            AlertDialog alertDialog = builder.create();
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;


            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", (dialogInterface, i) -> {

            });
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Add", (dialogInterface, i) -> {
                startActivity(new Intent(this, BankDetailsActivity.class));
            });
            alertDialog.show();
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this,R.color.resend_text_color));
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this,R.color.resend_text_color));
            alertDialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.rounded_corner_white));
            alertDialog.getWindow().setLayout(800, LinearLayout.LayoutParams.WRAP_CONTENT);
            return;
        }
        if (YourService.isOnline(this))
            presenter.withdrawPointApi(SharPrefHelper.getLogInToken(this), inputWithdrawPoints.getText().toString(), paymentMethod);
        else Toast.makeText(this, getString(R.string.check_your_internet_connection), Toast.LENGTH_SHORT).show();
    }

    private void withdrawPointMethod(WithdrawActivity activity, String points, String method) {
        progressBar.setVisibility(View.VISIBLE);
        String methodStr = null;
        if (method.contains("Account number")){
            methodStr = "bank_name";
        }else if (method.contains("PayTM")){
            methodStr = "paytm_mobile_no";
        }else if (method.contains("PhonePe")){
            methodStr = "phonepe_mobile_no";
        }else if (method.contains("GooglePay")){
            methodStr = "gpay_mobile_no";
        }
        Call<CommonModel> call = ApiClient.getClient().withdrawPoints(SharPrefHelper.getLogInToken(activity), points, methodStr);
        call.enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                if (response.isSuccessful()){
                    CommonModel commonModel = response.body();
                    assert commonModel != null;
                    if (commonModel.getCode().equalsIgnoreCase("505")){
                        SharPrefHelper.setClearData(activity);
                        Toast.makeText(activity, commonModel.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    if (commonModel.getStatus().equalsIgnoreCase(getString(R.string.success))){
                        currentPoints = Integer.parseInt(SharPrefHelper.getUserPoints(activity))-Integer.parseInt(points);
                        walletAmount.setText(String.valueOf(currentPoints));
                        SharPrefHelper.setUserPoints(WithdrawActivity.this, String.valueOf(currentPoints));
                        withdrawStatementMethod(activity);
                    }
                    Toast.makeText(WithdrawActivity.this, commonModel.getMessage(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(WithdrawActivity.this, getString(R.string.response_error), Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                System.out.println("withdrawPoints Error "+t);
                Toast.makeText(activity, getString(R.string.on_api_failure), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    PopupWindow popupWindow;
    MaterialTextView bankDetails, paytmUpi, phonePeUpi,googlePayUpi;
    public void selectPayMethod(View view) {
        View popupView = LayoutInflater.from(this).inflate(R.layout.select_pay_method_popup, null);
        bankDetails = popupView.findViewById(R.id.bankDetails);
        paytmUpi = popupView.findViewById(R.id.paytmUpi);
        phonePeUpi = popupView.findViewById(R.id.phonePeUpi);
        googlePayUpi = popupView.findViewById(R.id.googlePayUpi);
        popupWindow = new PopupWindow(popupView, 900, LinearLayout.LayoutParams.WRAP_CONTENT, true );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(20);
        }
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(view,0,-135);


        if (SharPrefHelper.getBankDetails(this, SharPrefHelper.KEY_BANK_AC_NUMBER)!=null && !SharPrefHelper.getBankDetails(this,SharPrefHelper.KEY_BANK_AC_NUMBER).equals("")){
            bankDetails.setText("Account number ( "+SharPrefHelper.getBankDetails(this, SharPrefHelper.KEY_BANK_AC_NUMBER)+" )");
            bankDetails.setVisibility(View.VISIBLE);
        }
        if (SharPrefHelper.getPreferenceData(this, SharPrefHelper.KEY_PAYTM_UPI)!=null && !SharPrefHelper.getPreferenceData(this, SharPrefHelper.KEY_PAYTM_UPI).equals("")){
            paytmUpi.setVisibility(View.VISIBLE);
            paytmUpi.setText("PayTM ( "+SharPrefHelper.getPreferenceData(this, SharPrefHelper.KEY_PAYTM_UPI)+" )");
        }
        if (SharPrefHelper.getPreferenceData(this, SharPrefHelper.KEY_PHONEPE_UPI)!=null && !SharPrefHelper.getPreferenceData(this, SharPrefHelper.KEY_PHONEPE_UPI).equals("")){
            phonePeUpi.setVisibility(View.VISIBLE);
            phonePeUpi.setText("PhonePe ( "+SharPrefHelper.getPreferenceData(this, SharPrefHelper.KEY_PHONEPE_UPI)+" )");
        }
        if (SharPrefHelper.getPreferenceData(this, SharPrefHelper.KEY_GOOGLEPAY_UPI)!=null && !SharPrefHelper.getPreferenceData(this, SharPrefHelper.KEY_GOOGLEPAY_UPI).equals("")){
            googlePayUpi.setVisibility(View.VISIBLE);
            googlePayUpi.setText("GooglePay ( "+SharPrefHelper.getPreferenceData(this, SharPrefHelper.KEY_GOOGLEPAY_UPI)+" )");
        }
    }

    public void googlepayUpi(View view) {
        paymentMethod = "GooglePay";
        selectPayMethod.setText(googlePayUpi.getText().toString());
        popupWindow.dismiss();
    }

    public void phonePeUpi(View view) {
        paymentMethod = "PhonePe";
        selectPayMethod.setText(phonePeUpi.getText().toString());
        popupWindow.dismiss();
    }

    public void payTmUpi(View view) {
        paymentMethod = "PayTM";
        selectPayMethod.setText(paytmUpi.getText().toString());
        popupWindow.dismiss();
    }

    public void bankDeails(View view) {
        paymentMethod = "Account number";
        selectPayMethod.setText(bankDetails.getText().toString());
        popupWindow.dismiss();
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
        selectPayMethod.setText("");
    }
    /*public void bankClick(View view) {
        checked= true;
        paymentMethod = "Account number";
        googlePayRadio.setChecked(false);
        bankRadio.setChecked(true);
        paytmRadio.setChecked(false);
        phoneRadio.setChecked(false);

    }

    public void paytmClick(View view) {
        paymentMethod = "PayTM";
        checked= true;
        googlePayRadio.setChecked(false);
        bankRadio.setChecked(false);
        paytmRadio.setChecked(true);
        phoneRadio.setChecked(false);
    }

    public void phonePeClick(View view) {
        paymentMethod = "PhonePe";
        checked= true;
        googlePayRadio.setChecked(false);
        bankRadio.setChecked(false);
        paytmRadio.setChecked(false);
        phoneRadio.setChecked(true);
    }

    public void googlePayClick(View view) {
        paymentMethod = "GooglePay";
        checked= true;
        googlePayRadio.setChecked(true);
        bankRadio.setChecked(false);
        paytmRadio.setChecked(false);
        phoneRadio.setChecked(false);
    }
*/
    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSwipeProgressBar() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideSwipeProgressBar() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void withdrawPointApiResponse() {
        currentPoints = Integer.parseInt(SharPrefHelper.getUserPoints(this))-Integer.parseInt(inputWithdrawPoints.getText().toString());
        walletAmount.setText(String.valueOf(currentPoints));
        SharPrefHelper.setUserPoints(WithdrawActivity.this, String.valueOf(currentPoints));
        presenter.withdrawStatementApi(SharPrefHelper.getLogInToken(this));
    }

    @Override
    public void withdrawStatementApiResponse(WalletStatementModel walletStatementModel) {
        if (walletStatementModel.getMessage().equalsIgnoreCase("No Data")){
            emptyIcon.setVisibility(View.VISIBLE);
        }else {
            modelWalletArrayList = walletStatementModel.getData().getStatement();
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            walletAdapter = new WalletAdapter(this, modelWalletArrayList);
            recyclerView.setAdapter(walletAdapter);
        }
        if (walletStatementModel.getData().getWithdrawOpenTime()!=null)
            openTime.setText("Withdraw Open time: "+walletStatementModel.getData().getWithdrawOpenTime());
        if (walletStatementModel.getData().getWithdrawCloseTime()!=null)
            closeTime.setText("Withdraw Close time: "+walletStatementModel.getData().getWithdrawCloseTime());
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