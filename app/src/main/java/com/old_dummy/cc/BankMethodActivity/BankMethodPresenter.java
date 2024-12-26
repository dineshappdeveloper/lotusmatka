package com.old_dummy.cc.BankMethodActivity;

import android.app.Activity;
import android.content.Intent;

import com.old_dummy.cc.BankDetailsActivity.BankDetailsActivity;
import com.old_dummy.cc.R;
import com.old_dummy.cc.UPIDetailsActivity.UPIDetailsActivity;

public class BankMethodPresenter implements BankMethodContract.Presenter{

    BankMethodContract.View view;

    public BankMethodPresenter(BankMethodContract.View view) {
        this.view = view;
    }

    @Override
    public void method(Activity activity, int upi) {
        Intent intent = new Intent(activity, UPIDetailsActivity.class);
        intent.putExtra(activity.getString(R.string.upi), upi);
        activity.startActivity(intent);
    }
    @Override
    public void bank(Activity activity){
        Intent intent = new Intent(activity, BankDetailsActivity.class);
        activity.startActivity(intent);
    }
}
