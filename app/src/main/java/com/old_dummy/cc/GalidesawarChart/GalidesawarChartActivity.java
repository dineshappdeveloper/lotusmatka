package com.old_dummy.cc.GalidesawarChart;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.old_dummy.cc.Adapters.GaliChartDataAdapter;
import com.old_dummy.cc.Adapters.GaliGameNameAdapter;
import com.old_dummy.cc.Extras.SharPrefHelper;
import com.old_dummy.cc.Models.GalidesawarChartModel;
import com.old_dummy.cc.R;

import java.util.ArrayList;
import java.util.List;

public class GalidesawarChartActivity extends AppCompatActivity implements GalidesawarChartContract.View {

    GalidesawarChartContract.Presenter presenter;
    ArrayList<String> gameNames;
    RecyclerView nameRecyclerView;
    RecyclerView dataRecyclerView;
    GaliGameNameAdapter starlineChartAdapter;
    GaliChartDataAdapter starlineChartDataAdapter;
    FrameLayout progressBar;
    LinearLayout retryLayout;
    NestedScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galidesawar_chart);
        presenter = new GalidesawarChartPresenter(this);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        gameNames = getIntent().getStringArrayListExtra("gameNames");
        toolbar.setTitle("Galidesawar Chart");
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        nameRecyclerView = findViewById(R.id.nameRecycler);
        dataRecyclerView = findViewById(R.id.dataRecycler);
        progressBar = findViewById(R.id.progressBar);
        scrollView = findViewById(R.id.scrollView);
        retryLayout = findViewById(R.id.retryLL);
        presenter.api(SharPrefHelper.getLogInToken(this));
        retryLayout.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            retryLayout.setVisibility(View.GONE);
            scrollView.setVisibility(View.GONE);
            presenter.api(SharPrefHelper.getLogInToken(this));
        });
    }

    @Override
    public void apiResponse(List<GalidesawarChartModel.Data> data) {
        progressBar.setVisibility(View.GONE);
        retryLayout.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
        starlineChartAdapter = new GaliGameNameAdapter(this, gameNames);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        nameRecyclerView.setLayoutManager(layoutManager);
        nameRecyclerView.setAdapter(starlineChartAdapter);

        starlineChartDataAdapter = new GaliChartDataAdapter(this, data);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        dataRecyclerView.setLayoutManager(layoutManager2);
        dataRecyclerView.setAdapter(starlineChartDataAdapter);

    }

    @Override
    public void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void error(String msg) {
        progressBar.setVisibility(View.GONE);
        retryLayout.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}