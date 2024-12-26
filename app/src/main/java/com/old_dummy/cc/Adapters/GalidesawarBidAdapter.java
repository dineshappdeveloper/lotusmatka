package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Models.GalidesawarBidModel;
import com.old_dummy.cc.R;

import java.util.List;

public class GalidesawarBidAdapter extends RecyclerView.Adapter<GalidesawarBidAdapter.ViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    Context context;
    List<GalidesawarBidModel> galidesawarBidModelList;
    OnItemClickListener listener;

    public GalidesawarBidAdapter(Context context, List<GalidesawarBidModel> starlineBidModelList, OnItemClickListener listener) {
        this.context = context;
        this.galidesawarBidModelList = starlineBidModelList;
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
        holder.bind(galidesawarBidModelList.get(position),position, listener);
    }

    @Override
    public int getItemCount() {
        return galidesawarBidModelList.size();
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

        public void bind(GalidesawarBidModel starlineBidModel, int position, OnItemClickListener listener) {
            pointText.setText("Amount: "+starlineBidModel.getBid_points());
            switch (starlineBidModel.getGame_type()){
                case "left_digit":
                    digitsText.setText("Left Digit: "+starlineBidModel.getLeft_digit());
                    break;
                case "right_digit":
                    digitsText.setText("Right Digit: "+starlineBidModel.getRight_digit());
                    break;
                case "jodi_digit":
                    digitsText.setText("Jodi Digit: "+starlineBidModel.getLeft_digit()+starlineBidModel.getRight_digit());
                    break;


            }

            crossBtn.setOnClickListener(v -> listener.onItemClick(position));
        }
    }
}
