package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Models.StarLineWinModel;
import com.old_dummy.cc.R;

import java.util.List;

public class StarlineBidHistoryAdapter extends RecyclerView.Adapter<StarlineBidHistoryAdapter.ViewHolder> {

    Context context;
    List<StarLineWinModel.Data> dataList;

    public StarlineBidHistoryAdapter(Context context, List<StarLineWinModel.Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recy_starline_bid_history_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StarLineWinModel.Data data = dataList.get(position);
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView gameID,gameName,  gameDate,points,digits;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gameID = itemView.findViewById(R.id.historyID);
            digits = itemView.findViewById(R.id.digitText);
            gameName = itemView.findViewById(R.id.gameName);
            gameDate = itemView.findViewById(R.id.dateText);
            points = itemView.findViewById(R.id.points);
        }

        public void bind(StarLineWinModel.Data data) {
            String gameNameStr = data.getGameName();
            points.setText("Points: "+data.getBidPoints());
            gameDate.setText(data.getBiddedAt());
            gameID.setText("#"+data.getGameId());
            switch (data.getGameType()){
                case "single_digit":
                    gameName.setText(gameNameStr+" (Single Digit)");
                    digits.setText("Digit : "+data.getDigit());
                    break;
                case "single_panna":
                    gameName.setText(gameNameStr+" (Single Panna)");
                    digits.setText("Panna : "+data.getPanna());
                    break;
                case "double_panna":
                    gameName.setText(gameNameStr+" (Double Panna)");
                    digits.setText("Panna : "+data.getPanna());
                    break;
                case "triple_panna":
                    gameName.setText(gameNameStr+" (Triple Panna)");
                    digits.setText("Panna : "+data.getPanna());
                    break;
            }
        }
    }
}
