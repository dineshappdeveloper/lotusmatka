package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Models.GameProceedModel;
import com.old_dummy.cc.R;

import java.util.List;

public class GameProceedAdapter extends RecyclerView.Adapter<GameProceedAdapter.ViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    Context context;
    List<GameProceedModel> gameProceedModelList;
    OnItemClickListener listener;

    public GameProceedAdapter(Context context, List<GameProceedModel> gameProceedModelList, OnItemClickListener listener) {
        this.context = context;
        this.gameProceedModelList = gameProceedModelList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recy_layout_game_proceed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(gameProceedModelList.get(position),position, listener);
    }

    @Override
    public int getItemCount() {
        return gameProceedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView digitsText,pannaText,pointText;
        ShapeableImageView crossBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            digitsText = itemView.findViewById(R.id.digitsText);
            pannaText = itemView.findViewById(R.id.pannaText);
            crossBtn = itemView.findViewById(R.id.crossBtn);
            pointText = itemView.findViewById(R.id.pointText);
        }

        public void bind(GameProceedModel gameProceedModel, int position, OnItemClickListener listener) {
            pointText.setText(gameProceedModel.getBid_points()+" Points");
            switch (gameProceedModel.getGame_type()){
                case "single_digit":
                    //pannaText.setVisibility(View.GONE);
                    if(gameProceedModel.getSession().equalsIgnoreCase("open")){
                        digitsText.setText("Digit "+gameProceedModel.getOpen_digit());
                        pannaText.setText("Open");
                    }
                    else{
                        digitsText.setText("Digit "+gameProceedModel.getClose_digit());
                        pannaText.setText("Close");
                    }
                    break;
                case "jodi_digit":
                    pannaText.setVisibility(View.GONE);
                    String jodi = gameProceedModel.getOpen_digit()+""+gameProceedModel.getClose_digit();
                    digitsText.setText("Digit "+jodi);
                    break;
                case "single_panna":
                case "double_panna":
                case "triple_panna":
                   // pannaText.setVisibility(View.GONE);
                    if(gameProceedModel.getSession().equalsIgnoreCase("open")){
                        digitsText.setText("Panna "+gameProceedModel.getOpen_panna());
                        pannaText.setText("Open");
                    }
                    else{
                        digitsText.setText("Panna "+gameProceedModel.getClose_panna());
                        pannaText.setText("Close");
                    }
                    break;
                case "half_sangam":
                    pannaText.setVisibility(View.VISIBLE);
                    if(gameProceedModel.getSession().equalsIgnoreCase("open")){
                        digitsText.setText("Open Digit "+gameProceedModel.getOpen_digit());
                        pannaText.setText("Close Panna "+gameProceedModel.getClose_panna());
                    }
                    else{
                        digitsText.setText("Open Panna: "+gameProceedModel.getOpen_panna());
                        pannaText.setText("Close Digit: "+gameProceedModel.getClose_digit());
                    }
                    break;
                case "full_sangam":
                    pannaText.setVisibility(View.VISIBLE);
                    digitsText.setText("Open Panna "+gameProceedModel.getOpen_panna());
                    pannaText.setText("Close Panna "+gameProceedModel.getClose_panna());
                    break;

            }

            crossBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(position);
                }
            });
        }
    }
}
