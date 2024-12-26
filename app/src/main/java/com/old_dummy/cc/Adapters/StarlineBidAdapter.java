package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Models.StarlineBidModel;
import com.old_dummy.cc.R;

import java.util.List;

public class StarlineBidAdapter extends RecyclerView.Adapter<StarlineBidAdapter.ViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    Context context;
    List<StarlineBidModel> starlineBidModelList;
    OnItemClickListener listener;

    public StarlineBidAdapter(Context context, List<StarlineBidModel> starlineBidModelList, OnItemClickListener listener) {
        this.context = context;
        this.starlineBidModelList = starlineBidModelList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recy_layout_starline_bid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(starlineBidModelList.get(position),position, listener);
    }

    @Override
    public int getItemCount() {
        return starlineBidModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView digitsText,pointText;
        ShapeableImageView crossBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            digitsText = itemView.findViewById(R.id.digitsText);
            pointText = itemView.findViewById(R.id.pointText);
            crossBtn = itemView.findViewById(R.id.crossBtn);
        }

        public void bind(StarlineBidModel starlineBidModel, int position, OnItemClickListener listener) {
            pointText.setText("Amount: "+starlineBidModel.getBid_points());
            switch (starlineBidModel.getGame_type()){
                case "single_digit":
                    digitsText.setText("Digit: "+starlineBidModel.getDigit());

                    break;
                case "single_panna":
                case "triple_panna":
                case "double_panna":
                    digitsText.setText("Panna: "+starlineBidModel.getPanna());
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
