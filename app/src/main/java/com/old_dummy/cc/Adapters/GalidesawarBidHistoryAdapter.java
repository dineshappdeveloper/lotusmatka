package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Models.GalidesawarWinModel;
import com.old_dummy.cc.R;

import java.util.List;

public class GalidesawarBidHistoryAdapter extends RecyclerView.Adapter<GalidesawarBidHistoryAdapter.ViewHolder> {

    Context context;
    List<GalidesawarWinModel.Data> dataList;

    public GalidesawarBidHistoryAdapter(Context context, List<GalidesawarWinModel.Data> dataList) {
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
        GalidesawarWinModel.Data data = dataList.get(position);
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

        public void bind(GalidesawarWinModel.Data data) {
            String gameNameStr = data.getGameName();
            points.setText(data.getBidPoints()+" Points");
            gameDate.setText(data.getBiddedAt());
            gameID.setText("#"+data.getGameId());
            switch (data.getGameType()){
                case "left_digit":
                    gameName.setText(gameNameStr+" (Left Digit)");
                    digits.setText("Digit : "+data.getLeft_digit());
                    break;
                case "right_digit":
                    gameName.setText(gameNameStr+" (Right Digit)");
                    digits.setText("Panna : "+data.getRight_digit());
                    break;
                case "jodi_digit":
                    gameName.setText(gameNameStr+" (Jodi Digit)");
                    digits.setText("Panna : "+data.getLeft_digit()+data.getRight_digit());
                    break;
            }
        }
    }
}
