package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Models.WinModel;
import com.old_dummy.cc.R;

import java.util.List;

public class WinHistoryAdapter extends RecyclerView.Adapter<WinHistoryAdapter.ViewHolder> {

    Context context;
    List<WinModel.Data> dataList;

    public WinHistoryAdapter(Context context, List<WinModel.Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recy_win_history_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WinModel.Data data = dataList.get(position);
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView gameName,  gameDate, winPoints,gameId,result1,result2, gameSession;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gameName = itemView.findViewById(R.id.gameName);
            gameSession = itemView.findViewById(R.id.session);
            gameId = itemView.findViewById(R.id.gameId);
            gameDate = itemView.findViewById(R.id.gameDate);
            winPoints = itemView.findViewById(R.id.winPoints);
            result1 = itemView.findViewById(R.id.result1);
            result2 = itemView.findViewById(R.id.result2);
            winPoints.setVisibility(View.GONE);
            gameId.setVisibility(View.VISIBLE);
            gameSession.setVisibility(View.GONE);
        }

        public void bind(WinModel.Data data) {
            String gameNameStr = data.getGameName();
            gameDate.setText(data.getBiddedAt());
            if(!TextUtils.isEmpty(data.getWinPoints())){
                winPoints.setText("Won Points: "+data.getWinPoints());
                winPoints.setVisibility(View.VISIBLE);
                result1.setText("Bid Points : "+data.getBidPoints());
                gameDate.setText(data.getWonAt());
            }
            switch (data.getGameType()){
                case "single_digit":
                    gameName.setText(gameNameStr+" (Single Digit)");
                    if(data.getSession().equalsIgnoreCase("open")){
                        result2.setText("Session : Open");
                    }
                    else{
                        result2.setText("Session : Close");
                    }
                    break;
                case "jodi_digit":
                    gameName.setText(gameNameStr+" (Jodi Digit)");
                    result2.setVisibility(View.GONE);
                    gameSession.setVisibility(View.GONE);
                    break;
                case "single_panna":
                    gameName.setText(gameNameStr+"(Single Panna )");
                    if(data.getSession().equalsIgnoreCase("open")){
                        result2.setText("Session : Open");
                    }
                    else{
                        result2.setText("Session : Close");
                    }
                    break;
                case "double_panna":
                    gameName.setText(gameNameStr+" (Double Panna)");
                    if(data.getSession().equalsIgnoreCase("open")){
                        result2.setText("Session : Open");
                    }
                    else{
                        result2.setText("Session : Close");
                    }
                    break;
                case "triple_panna":
                    gameName.setText(gameNameStr+" (Triple Panna)");
                    if(data.getSession().equalsIgnoreCase("open")){
                        result2.setText("Session : Open");
                    }
                    else{
                        result2.setText("Session : Close");
                    }
                    break;
                case "half_sangam":

                    gameName.setText(gameNameStr+" (Half Sangam)");
                    if(data.getSession().equalsIgnoreCase("open")){
                        result2.setText("Session : Open");
                    }
                    else{
                        result2.setText("Session : Close");

                    }
                    break;
                case "full_sangam":
                    gameName.setText(gameNameStr+" (Full Sangam)");
                    result2.setVisibility(View.GONE);
                    break;

            }
        }
    }
}
