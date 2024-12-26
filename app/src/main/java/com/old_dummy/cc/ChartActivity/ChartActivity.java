package com.old_dummy.cc.ChartActivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Extras.Utility;
import com.old_dummy.cc.Extras.YourService;
import com.old_dummy.cc.R;

import static com.old_dummy.cc.Extras.Utility.BroadCastStringForAction;
import static com.old_dummy.cc.Extras.Utility.myReceiver;

public class ChartActivity extends AppCompatActivity {
    WebView webView;
    String gameName;
    String chartUrl;
    MaterialToolbar toolbar;
    ProgressBar progressBar;
    MaterialTextView dataConText;
    IntentFilter mIntentFilter;
    Utility utility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_color));
        setContentView(R.layout.activity_chart);
        intIDs();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void intIDs() {
        webView = findViewById(R.id.webView);
        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progressBar);

        dataConText = findViewById(R.id.dataConText);
        utility = new Utility(dataConText);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BroadCastStringForAction);
        Intent serviceIntent = new Intent(this, YourService.class);
        startService(serviceIntent);

        gameName = getIntent().getStringExtra(getString(R.string.game_name));
        if (gameName!=null)
        toolbar.setTitle(gameName.toUpperCase());
        else toolbar.setTitle("CHART");
        chartUrl = getIntent().getStringExtra(getString(R.string.chart));
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClint());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Win64; x64; rv:46.0) Gecko/20100101 Firefox/68.0");
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setNeedInitialFocus(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setInitialScale(100);
        webView.loadUrl(chartUrl);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                   progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private static class WebViewClint extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
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
}