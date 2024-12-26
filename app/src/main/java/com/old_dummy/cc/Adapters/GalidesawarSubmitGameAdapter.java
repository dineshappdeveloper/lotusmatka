package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Models.GalidesawarBidModel;
import com.old_dummy.cc.R;

import java.util.List;

public class GalidesawarSubmitGameAdapter extends RecyclerView.Adapter<GalidesawarSubmitGameAdapter.ViewHolder> {


    Context context;
    List<GalidesawarBidModel> galidesawarBidModelList;

    public GalidesawarSubmitGameAdapter(Context context, List<GalidesawarBidModel> gameProceedModelList) {
        this.context = context;
        this.galidesawarBidModelList = gameProceedModelList;
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
        holder.bind(galidesawarBidModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return galidesawarBidModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView digitsText,pointText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            digitsText = itemView.findViewById(R.id.digitsText);
            pointText = itemView.findViewById(R.id.pointText);
        }

        public void bind(GalidesawarBidModel gameProceedModel) {
            pointText.setText("Amount: "+gameProceedModel.getBid_points());
            switch (gameProceedModel.getGame_type()){
                case "left_digit":
                    digitsText.setText("Left Digit: "+gameProceedModel.getLeft_digit());
                    break;
                case "right_digit":
                    digitsText.setText("Right Digit: "+gameProceedModel.getRight_digit());
                    break;
                case "jodi_digit":
                    digitsText.setText("Jodi Digit: "+gameProceedModel.getLeft_digit()+gameProceedModel.getRight_digit());
                    break;
            }
        }
    }
}
