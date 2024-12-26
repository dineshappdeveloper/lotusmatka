package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Models.StarLineWinModel;
import com.old_dummy.cc.R;

import java.util.List;

public class StarlineWinHistoryAdapter extends RecyclerView.Adapter<StarlineWinHistoryAdapter.ViewHolder> {

    Context context;
    List<StarLineWinModel.Data> dataList;

    public StarlineWinHistoryAdapter(Context context, List<StarLineWinModel.Data> dataList) {
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
        StarLineWinModel.Data data = dataList.get(position);
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView gameName, gameDate, winPoints;
        LinearLayout ll_bid_history;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gameName = itemView.findViewById(R.id.gameName);
            gameDate = itemView.findViewById(R.id.gameDate);
//            bidPoints = itemView.findViewById(R.id.bidPoints);
            winPoints = itemView.findViewById(R.id.winPoints);
//            session = itemView.findViewById(R.id.gameSession);
//            gameNumberOpen = itemView.findViewById(R.id.gameNumberOpen);
//            gameNumberClose = itemView.findViewById(R.id.gameNumberClose);
            ll_bid_history = itemView.findViewById(R.id.ll_bid_history);
            winPoints.setVisibility(View.GONE);
//            gameNumberClose.setVisibility(View.GONE);
//            session.setVisibility(View.GONE);
        }

        public void bind(StarLineWinModel.Data data) {
            String gameNameStr = data.getGameName();
           // bidPoints.setText(data.getBidPoints()+" Points");
            gameDate.setText(data.getBiddedAt());
           // ll_bid_history.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.teal_200)));
            if(!TextUtils.isEmpty(data.getWinPoints())){
               // ll_bid_history.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.green)));
                winPoints.setText("+"+data.getWinPoints()+" Points");
                winPoints.setVisibility(View.VISIBLE);
                gameDate.setText(data.getWonAt());
            }
            gameName.setText(gameNameStr+"in Single Digit for bid amount-"+data.getBidPoints()+" Won");
            switch (data.getGameType()){
                case "single_digit":
                    gameName.setText(gameNameStr+"in Single Digit for bid amount-"+data.getBidPoints()+" Won");
                    /*gameName.setText(gameNameStr+"( Single Digit )");
                    gameNumberOpen.setText("Game Number : "+data.getDigit());*/
                    break;
                case "single_panna":
                    gameName.setText(gameNameStr+"in Single Panna for bid amount-"+data.getBidPoints()+" Won");
                   /* gameName.setText(gameNameStr+"( Single Panna )");
                    gameNumberOpen.setText("Game Number : "+data.getPanna());*/
                    break;
                case "double_panna":
                    gameName.setText(gameNameStr+"in Double Panna for bid amount-"+data.getBidPoints()+" Won");
                    /*gameName.setText(gameNameStr+"( Double Panna )");
                    gameNumberOpen.setText("Game Number : "+data.getPanna());*/
                    break;
                case "triple_panna":
                    gameName.setText(gameNameStr+"in Triple Panna for bid amount-"+data.getBidPoints()+" Won");
                    /*gameName.setText(gameNameStr+"( Triple Panna )");
                    gameNumberOpen.setText("Game Number : "+data.getPanna());*/
                    break;
            }
        }
    }
}
