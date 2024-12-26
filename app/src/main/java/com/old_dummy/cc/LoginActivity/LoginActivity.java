package com.old_dummy.cc.LoginActivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.old_dummy.cc.Extras.SharPrefHelper;
import com.old_dummy.cc.Extras.Utility;
import com.old_dummy.cc.Extras.YourService;
import com.old_dummy.cc.MainActivity.MainActivity;
import com.old_dummy.cc.Models.AppDetailsModel;
import com.old_dummy.cc.Models.LoginModel;
import com.old_dummy.cc.R;
import com.google.android.material.textfield.TextInputEditText;
import com.old_dummy.cc.SecurityPinActivity.SecurityPinActivity;

import static com.old_dummy.cc.Extras.Utility.BroadCastStringForAction;
import static com.old_dummy.cc.Extras.Utility.myReceiver;

import java.lang.reflect.Type;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    TextInputEditText inputMobNumber,inputPassword;
    InputMethodManager imm;
    ShapeableImageView passToggleEye;
    FrameLayout progressBar;
    MaterialTextView dataConText;
    IntentFilter mIntentFilter;
    Utility utility;
    LoginContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_color));
        setContentView(R.layout.activity_login);

        intIDs();

        utility = new Utility(dataConText);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BroadCastStringForAction);
        Intent serviceIntent = new Intent(this, YourService.class);
        startService(serviceIntent);

    }

    private void intIDs() {
        inputMobNumber = findViewById(R.id.inputMobNumber);
        inputPassword = findViewById(R.id.inputPassword);
        passToggleEye = findViewById(R.id.visibleEye);
        progressBar = findViewById(R.id.progressBar);
        dataConText = findViewById(R.id.dataConText);
        presenter = new LoginPresenter(this);
        MaterialTextView topTitle = findViewById(R.id.topDesign).findViewById(R.id.topText);
        topTitle.setText("Login");
//        findViewById(R.id.backButton).setOnClickListener(v -> {
//            onBackPressed();
//        });
        findViewById(R.id.adminButton).setOnClickListener(v -> {
            AppDetailsModel.Data data = null;
            Gson gson = new Gson();
            Type type = new TypeToken<AppDetailsModel.Data>() {
            }.getType();
            try {
                data = gson.fromJson(SharPrefHelper.getPreferenceData(this,SharPrefHelper.KEY_App_Details), type);
            } catch (Exception e) {
                System.out.println("json conversion failed");
            }
            String adminMsg = data.getAdmin_message();

            String url = "https://api.whatsapp.com/send?phone="+SharPrefHelper.getContactDetails(this, SharPrefHelper.KEY_WHATSAPP_NUMBER)+"&text="+adminMsg;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });
        /*if(SharPrefHelper.getSignUpData(this, SharPrefHelper.KEY_MOBILE_NUMBER)!=null){
            inputMobNumber.setText(SharPrefHelper.getSignUpData(this, SharPrefHelper.KEY_MOBILE_NUMBER));
        }
        if(SharPrefHelper.getSignUpData(this, SharPrefHelper.KEY_USER_PASSWORD)!=null){
            inputPassword.setText(SharPrefHelper.getSignUpData(this, SharPrefHelper.KEY_USER_PASSWORD));
        }*/
    }

    public void registerClick(View view) {
        onBackPressed();
    }

    void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void LogInBtn(View view) {
        imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        String number = inputMobNumber.getText().toString();
        String password = inputPassword.getText().toString();
        if (TextUtils.isEmpty(number)){
            Snackbar.make(view, getString(R.string.please_enter_mobile_number),2000).show();
            return;
        }
        if (number.length()!=10){
            Snackbar.make(view, getString(R.string.please_enter_valid_mobile_number),2000).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Snackbar.make(view, getString(R.string.please_enter_password),2000).show();
            return;
        }
        if (password.length()<4){
            Snackbar.make(view, getString(R.string.please_enter_min_4_digits_password),2000).show();
            return;
        }
        if (YourService.isOnline(this))
        presenter.api(number,password);
        else toast(getString(R.string.check_your_internet_connection));


    }

    public void forgotPassword(View view) {
        presenter.forgotPassword(this,inputMobNumber.getText().toString());
    }

    public void passToggleEye(View view) {
        if (inputPassword.getTransformationMethod().getClass().getSimpleName() .equals("PasswordTransformationMethod")) {
            inputPassword.setTransformationMethod(new SingleLineTransformationMethod());
            passToggleEye.setImageResource(R.drawable.ic_baseline_visibility_24);
        } else {
            inputPassword.setTransformationMethod(new PasswordTransformationMethod());
            passToggleEye.setImageResource(R.drawable.ic_baseline_visibility_off_24);
        }

        inputPassword.setSelection(inputPassword.getText().length());
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
    public void apiResponse(String loginToken) {
        SharPrefHelper.setLoginToken(this, loginToken);
        SharPrefHelper.setLoginSuccess(this,true);
        SharPrefHelper.setSignUpData(this,SharPrefHelper.KEY_MOBILE_NUMBER ,inputMobNumber.getText().toString());
        SharPrefHelper.setSignUpData(this,SharPrefHelper.KEY_USER_PASSWORD, inputPassword.getText().toString());
        Intent intent = new Intent(this, SecurityPinActivity.class);
        intent.putExtra(getString(R.string.mobile_number), inputMobNumber.getText().toString());
        intent.putExtra("from", "login");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  );
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    @Override
    public void finished(LoginModel.Data gameListModel) {

    }
    @Override
    public void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}