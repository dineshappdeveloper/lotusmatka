package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Models.WinModel;
import com.old_dummy.cc.R;

import java.util.List;

public class BidHistoryAdapter extends RecyclerView.Adapter<BidHistoryAdapter.ViewHolder> {

    Context context;
    List<WinModel.Data> dataList;

    public BidHistoryAdapter(Context context, List<WinModel.Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recy_bid_history_layout, parent, false);
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
        MaterialTextView gameID,gameName,  gameDate,points,digits, panna, gameSession;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gameName = itemView.findViewById(R.id.gameName);
            gameID = itemView.findViewById(R.id.historyID);
            gameSession = itemView.findViewById(R.id.sessionText);
            gameDate = itemView.findViewById(R.id.dateText);
            points = itemView.findViewById(R.id.points);
            digits = itemView.findViewById(R.id.digitText);
            panna = itemView.findViewById(R.id.pannaText);
        }

        public void bind(WinModel.Data data) {
            String gameNameStr = data.getGameName();
            points.setText(data.getBidPoints()+" Points");
            gameDate.setText(data.getBiddedAt());
            gameID.setText("#"+data.getGameId());
            switch (data.getGameType()){
                case "single_digit":
                    gameName.setText(gameNameStr+"(Single Digit)");
                    panna.setVisibility(View.GONE);
                    gameSession.setVisibility(View.VISIBLE);
                    if(data.getSession().equalsIgnoreCase("open")){
                        gameSession.setText("Session : Open");
                        digits.setText("Digit : "+data.getOpenDigit());
                    }
                    else{
                        gameSession.setText("Session : Close");
                        digits.setText("Digit : "+data.getCloseDigit());
                    }
                    break;
                case "jodi_digit":
                    gameName.setText(gameNameStr+" (Jodi Digit)");
                    panna.setVisibility(View.GONE);
                    gameSession.setVisibility(View.GONE);
                    String jodi = "Jodi Digit : "+data.getOpenDigit()+""+data.getCloseDigit();
                    digits.setText("Digit : "+jodi);
                    break;
                case "single_panna":
                    panna.setVisibility(View.GONE);
                    gameName.setText(gameNameStr+" (Single Panna)");
                    gameSession.setVisibility(View.VISIBLE);
                    if(data.getSession().equalsIgnoreCase("open")){
                        gameSession.setText("Session : Open");
                        digits.setText("Panna : "+data.getOpenPanna());
                    }
                    else{
                        gameSession.setText("Session : Close");
                        digits.setText("Panna : "+data.getClosePanna());
                    }
                    break;
                case "double_panna":
                    gameName.setText(gameNameStr+" (Double Panna)");
                    gameSession.setVisibility(View.VISIBLE);
                    panna.setVisibility(View.GONE);
                    if(data.getSession().equalsIgnoreCase("open")){
                        gameSession.setText("Session : Open");
                        digits.setText("Panna : "+data.getOpenPanna());
                    }
                    else{
                        gameSession.setText("Session : Close");
                        digits.setText("Panna : "+data.getClosePanna());
                    }
                    break;
                case "triple_panna":
                    gameName.setText(gameNameStr+" (Triple Panna)");
                    gameSession.setVisibility(View.VISIBLE);
                    panna.setVisibility(View.GONE);
                    if(data.getSession().equalsIgnoreCase("open")){
                        gameSession.setText("Session : Open");
                        digits.setText("Panna : "+data.getOpenPanna());
                    }
                    else{
                        gameSession.setText("Session : Close");
                        digits.setText("Panna : "+data.getClosePanna());
                    }
                    break;
                case "half_sangam":
                    gameName.setText(gameNameStr+" (Half Sangam)");
                    gameSession.setVisibility(View.VISIBLE);
                    panna.setVisibility(View.VISIBLE);
                    if(data.getSession().equalsIgnoreCase("open")){
                        gameSession.setText("Session : Open");
                        digits.setText("Open Digit : "+data.getOpenDigit());
                        panna.setText("Close Panna : "+data.getClosePanna());
                    }
                    else{
                        gameSession.setText("Session : Close");
                        digits.setText("Open Panna : "+data.getOpenPanna());
                        panna.setText("Close Digit : "+data.getCloseDigit());
                    }
                    break;
                case "full_sangam":
                    panna.setVisibility(View.VISIBLE);
                    gameSession.setVisibility(View.GONE);
                    gameName.setText(gameNameStr+"(Full Sangam)");
                    gameSession.setText("Session : Open");
                    digits.setText("Open Panna : "+data.getOpenPanna());
                    panna.setText("Close Panna : "+data.getClosePanna());
                    break;

            }
        }
    }
}
