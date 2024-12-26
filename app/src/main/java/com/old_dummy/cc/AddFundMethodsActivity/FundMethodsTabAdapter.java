package com.old_dummy.cc.AddFundMethodsActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.old_dummy.cc.Models.FundMethodsModel;
import com.old_dummy.cc.R;

import java.util.List;
import java.util.Objects;

public class FundMethodsTabAdapter extends RecyclerView.Adapter<FundMethodsTabAdapter.ViewHolder>{
    public interface OnItemClickListener{
        void onItemClick(String method, String notice, String noticeTitle,String videoLink);
    }
    Context context;
    List<FundMethodsModel> tabList;
    OnItemClickListener listener;
    private int selectedPosition = -1;
    String defaultMethod;

    public FundMethodsTabAdapter(Context context,String defaultMethod, List<FundMethodsModel> tabList, OnItemClickListener listener) {
        this.context = context;
        this.tabList = tabList;
        this.listener = listener;
        this.defaultMethod = defaultMethod;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recy_fund_methods_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(tabList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return tabList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView image;
        ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            layout = itemView.findViewById(R.id.layout);
        }

        public void bind(FundMethodsModel data, int position) {
            image.setImageDrawable(data.getDrawable());
            if(Objects.equals(data.getMethod(), defaultMethod)){
                defaultMethod="0";
                layout.setBackground(ContextCompat.getDrawable(context, R.drawable.tab_active_bg));
            }else {
                if (selectedPosition == position) {
                    layout.setBackground(ContextCompat.getDrawable(context, R.drawable.tab_active_bg)); // Selected background color
                } else {
                    layout.setBackground(ContextCompat.getDrawable(context, R.drawable.tab_inactive_bg)); // Default background color
                }
            }
            itemView.setOnClickListener(view -> {
                if(selectedPosition != position){
                    notifyDataSetChanged();
                    listener.onItemClick(data.getMethod(),data.getNotice(), data.getNoticeTitle(), data.getVideoLink());
                }
                selectedPosition = position;

            });
        }

    }

}
