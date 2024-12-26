package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Models.GameProceedModel;
import com.old_dummy.cc.R;

import java.util.List;

public class SubmitGameAdapter extends RecyclerView.Adapter<SubmitGameAdapter.ViewHolder> {


    Context context;
    List<GameProceedModel> gameProceedModelList;

    public SubmitGameAdapter(Context context, List<GameProceedModel> gameProceedModelList) {
        this.context = context;
        this.gameProceedModelList = gameProceedModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recy_dialog_layout_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(gameProceedModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return gameProceedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView digitsText,pannaText,pointText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            digitsText = itemView.findViewById(R.id.digitsText);
            pannaText = itemView.findViewById(R.id.pannaText);
            pointText = itemView.findViewById(R.id.pointText);
        }

        public void bind(GameProceedModel gameProceedModel) {
            pointText.setText(gameProceedModel.getBid_points()+" Points");
            switch (gameProceedModel.getGame_type()){
                case "single_digit":
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
                    digitsText.setText("Open Panna "+gameProceedModel.getOpen_panna());
                    pannaText.setText("Close Panna "+gameProceedModel.getClose_panna());
                    break;

            }
        }
    }
}
