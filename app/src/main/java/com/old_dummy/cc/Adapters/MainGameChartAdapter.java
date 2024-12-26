package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Models.MainGameChartModel;
import com.old_dummy.cc.R;

import java.util.List;

public class MainGameChartAdapter extends RecyclerView.Adapter<MainGameChartAdapter.ViewHolder>{

    Context context;
    List<MainGameChartModel.Data> dataList;

    public MainGameChartAdapter(Context context, List<MainGameChartModel.Data> datalArrayList) {
        this.context = context;
        this.dataList = datalArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.main_game_chart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView day,date,openPanna,digit,closePanna;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.day);
            date = itemView.findViewById(R.id.date);
            openPanna = itemView.findViewById(R.id.openPanna);
            digit = itemView.findViewById(R.id.digit);
            closePanna = itemView.findViewById(R.id.closePanna);
        }

        public void bind(MainGameChartModel.Data data) {

            day.setText(data.getDay());
            date.setText(data.getDate());
            String[] open = data.getOpenPanna().split("");
            String[] close = data.getClosePanna().split("");
            openPanna.setText(open[0]+"\n"+open[1]+"\n"+open[2]);
            closePanna.setText(close[0]+"\n"+close[1]+"\n"+close[2]);
            digit.setText(data.getOpenDigit()+data.getCloseDigit());

        }
    }

}
