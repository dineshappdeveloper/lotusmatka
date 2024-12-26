package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Models.StarlineBidModel;
import com.old_dummy.cc.R;

import java.util.List;

public class StarLineSubmitGameAdapter extends RecyclerView.Adapter<StarLineSubmitGameAdapter.ViewHolder> {


    Context context;
    List<StarlineBidModel> starlineBidModelList;

    public StarLineSubmitGameAdapter(Context context, List<StarlineBidModel> gameProceedModelList) {
        this.context = context;
        this.starlineBidModelList = gameProceedModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recy_dialog_layout_starline_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(starlineBidModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return starlineBidModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView digitsText,pointText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            digitsText = itemView.findViewById(R.id.digitsText);
            pointText = itemView.findViewById(R.id.pointText);
        }

        public void bind(StarlineBidModel gameProceedModel) {
            pointText.setText("Amount: "+gameProceedModel.getBid_points());
            switch (gameProceedModel.getGame_type()){
                case "single_digit":
                    digitsText.setText("Digit: "+gameProceedModel.getDigit());
                    break;
                case "single_panna":
                case "double_panna":
                case "triple_panna":
                    digitsText.setText("Panna: "+gameProceedModel.getPanna());
                    break;

            }
        }
    }
}
