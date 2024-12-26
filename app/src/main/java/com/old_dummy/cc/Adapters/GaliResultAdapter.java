package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Models.GalidesawarChartModel;
import com.old_dummy.cc.R;

import java.util.List;

public class GaliResultAdapter extends RecyclerView.Adapter<GaliResultAdapter.ViewHolder>{

    Context context;
    List<GalidesawarChartModel.Data.Results> datalArrayList;


    public GaliResultAdapter(Context context, List<GalidesawarChartModel.Data.Results> datalArrayList) {
        this.context = context;
        this.datalArrayList = datalArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recy_gali_chart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(datalArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return datalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView result;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            result = itemView.findViewById(R.id.result);
        }

        public void bind(GalidesawarChartModel.Data.Results data) {
            result.setText(data.getLeftDigit()+data.getRightDigit());

        }

    }

}
