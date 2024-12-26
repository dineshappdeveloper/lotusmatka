package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.R;

import java.util.ArrayList;

public class SelectDigitAdapter extends RecyclerView.Adapter<SelectDigitAdapter.ViewHolder>{

    public interface OnItemClickListener{
        void onItemClick(String position);
    }

    Context context;
    ArrayList<String> dataList;
    OnItemClickListener onItemClickListener;
    int gameProceed;
    ArrayList<String> selectedList;

    public void filterList(ArrayList<String> filterList){
        dataList =filterList;
        notifyDataSetChanged();
    }
    public SelectDigitAdapter(Context context, ArrayList<String> dataList, int gameProceed, ArrayList<String> galidesawarBidModelList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.dataList = dataList;
        this.onItemClickListener = onItemClickListener;
        this.gameProceed = gameProceed;
        this.selectedList = galidesawarBidModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.digit_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView digits;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            digits = itemView.findViewById(R.id.digits);
        }

        public void bind(String dataList) {
            digits.setText(dataList);
            if (gameProceed!=6 && gameProceed!=7) {
                if (!selectedList.contains(dataList)) {
                    itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                    digits.setTextColor(ContextCompat.getColor(context, R.color.black));
                } else {
                    itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_orange_dark));
                    digits.setTextColor(ContextCompat.getColor(context, R.color.white));
                }
            }
            digits.setOnClickListener(view -> {
                if (gameProceed!=6 && gameProceed!=7) {
                    if (!selectedList.contains(dataList)) {
                        itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_orange_dark));
                        digits.setTextColor(ContextCompat.getColor(context, R.color.white));
                        selectedList.add(dataList);
                    } else {
                        selectedList.remove(dataList);
                        itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                        digits.setTextColor(ContextCompat.getColor(context, R.color.black));
                    }
                }else onItemClickListener.onItemClick(digits.getText().toString());
            });
        }
    }
}
