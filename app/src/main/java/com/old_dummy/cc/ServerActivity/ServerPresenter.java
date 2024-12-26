package com.old_dummy.cc.ServerActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.core.app.ActivityCompat;

import com.old_dummy.cc.Extras.SharPrefHelper;
import com.old_dummy.cc.R;

public class ServerPresenter implements ServerContract.Presenter{
    ServerContract.View view;

    public ServerPresenter(ServerContract.View view) {
        this.view = view;
    }

    @Override
    public void call(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CALL_PHONE}, 100);
        }else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+ SharPrefHelper.getContactDetails(activity, SharPrefHelper.KEY_CONTACT_NUMBER1)));
            activity.startActivity(callIntent);
        }
    }

    @Override
    public void whatsapp(Activity activity) {
        String url = "https://api.whatsapp.com/send?phone="+SharPrefHelper.getContactDetails(activity, SharPrefHelper.KEY_WHATSAPP_NUMBER);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        activity.startActivity(i);
    }

    @Override
    public void mail(Activity activity) {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{SharPrefHelper.getContactDetails(activity, SharPrefHelper.KEY_CONTACT_EMAIL)});
        email.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.mail_subject));
        email.putExtra(Intent.EXTRA_TEXT, activity.getString(R.string.mail_message));
        email.setType("message/rfc822");
        activity.startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }
}
