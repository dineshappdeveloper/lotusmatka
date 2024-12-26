package com.old_dummy.cc.OtpActivity;

import android.os.CountDownTimer;

import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.R;

public class OtpPresenter implements OtpContract.ViewModel.OnFinishedListener,OtpContract.Presenter{
    OtpContract.View view;
    OtpContract.ViewModel viewModel;

    public OtpPresenter(OtpContract.View view) {
        this.view = view;
        viewModel = new OtpViewModel();
    }

    @Override
    public void verifyOtpApiFinished(String token) {
        if (view!=null){
            view.hideProgressBar();
            view.verifyOtpApiResponse(token);
        }
    }

    @Override
    public void verifyUserMethodApiFinished(String token) {
        if (view!=null){
            view.hideProgressBar();
            view.verifyUserMethodApiResponse(token);
        }
    }

    @Override
    public void resendOtpApiFinished() {
        if (view!=null){
            view.hideProgressBar();
            view.resendOtpApiResponse();
        }
    }

    @Override
    public void message(String msg) {
        if (view!=null){
            view.hideProgressBar();
            view.message(msg);
        }
    }

    @Override
    public void failure() {
        if (view!=null){
            view.hideProgressBar();
        }
    }

    @Override
    public void verifyOtpApi(String number, String otp) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callVerifyOtpApi(this,number,otp);
    }
    @Override
    public void countdown(MaterialTextView buttonText) {
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                buttonText.setEnabled(false);
                buttonText.setText("Resend OTP in " + millisUntilFinished / 1000+" seconds ");
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                buttonText.setText("Don't receive code? Resend OTP");
                buttonText.setEnabled(true);
            }

        }.start();
    }

    @Override
    public void verifyUserMethodApi(String number, String otp) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callVerifyUserMethodApi(this,number,otp);
    }

    @Override
    public void resendOtpApi(String number) {
        if (view!=null){
            view.showProgressBar();
        }
        viewModel.callResendOtpApi(this,number);
    }
}
