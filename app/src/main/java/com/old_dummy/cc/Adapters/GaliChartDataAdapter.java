package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Models.GalidesawarChartModel;
import com.old_dummy.cc.R;

import java.util.List;

public class GaliChartDataAdapter extends RecyclerView.Adapter<GaliChartDataAdapter.ViewHolder>{

    Context context;
    List<GalidesawarChartModel.Data> data;


    public GaliChartDataAdapter(Context context, List<GalidesawarChartModel.Data> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recy_gali_data_chart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView gameDate;
        RecyclerView resultRecycler;
        GaliResultAdapter resultAdapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gameDate = itemView.findViewById(R.id.gameDate);
            resultRecycler = itemView.findViewById(R.id.resultRecycler);
        }

        public void bind(GalidesawarChartModel.Data data) {
            gameDate.setText(data.getDate());

            resultAdapter = new GaliResultAdapter(context, data.getResults());
            RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
            resultRecycler.setLayoutManager(layoutManager2);
            resultRecycler.setAdapter(resultAdapter);
        }

    }

}
