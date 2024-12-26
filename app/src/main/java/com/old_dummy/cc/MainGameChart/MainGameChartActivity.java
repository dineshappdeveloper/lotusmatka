package com.old_dummy.cc.MainGameChart;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.old_dummy.cc.Adapters.MainGameChartAdapter;
import com.old_dummy.cc.Extras.SharPrefHelper;
import com.old_dummy.cc.Models.MainGameChartModel;
import com.old_dummy.cc.R;

import java.util.List;

public class MainGameChartActivity extends AppCompatActivity implements MainGameChartContract.View {

    MainGameChartContract.Presenter presenter;
    String gameId, gameName;
    RecyclerView recyclerView;
    MainGameChartAdapter mainGameChartAdapter;
    FrameLayout progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.main_color));
        setContentView(R.layout.activity_main_game_chart);
        presenter = new MainGameChartPresenter(this);
        gameId = getIntent().getStringExtra("gameId");
        gameName = getIntent().getStringExtra("gameName");
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(gameName);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        recyclerView = findViewById(R.id.chartRecycler);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        presenter.api(SharPrefHelper.getLogInToken(this), gameId);
    }

    @Override
    public void apiResponse(List<MainGameChartModel.Data> data) {
        progressBar.setVisibility(View.GONE);
        mainGameChartAdapter = new MainGameChartAdapter(this, data);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 7);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainGameChartAdapter);

    }

    @Override
    public void message(String msg) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}