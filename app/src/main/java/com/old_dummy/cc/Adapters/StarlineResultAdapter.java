package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Models.StarlineChartModel;
import com.old_dummy.cc.R;

import java.util.List;

public class StarlineResultAdapter extends RecyclerView.Adapter<StarlineResultAdapter.ViewHolder>{

    Context context;
    List<StarlineChartModel.Data.Results> datalArrayList;


    public StarlineResultAdapter(Context context, List<StarlineChartModel.Data.Results> datalArrayList) {
        this.context = context;
        this.datalArrayList = datalArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recy_chart_result_item, parent, false);
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
        MaterialTextView panna;
        MaterialTextView digit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            panna = itemView.findViewById(R.id.panna);
            digit = itemView.findViewById(R.id.digit);
        }

        public void bind(StarlineChartModel.Data.Results data) {
            panna.setText(data.getPanna());
            digit.setText(data.getDigit());

        }

    }

}
