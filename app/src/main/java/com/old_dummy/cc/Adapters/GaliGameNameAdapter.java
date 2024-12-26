package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.R;

import java.util.ArrayList;

public class GaliGameNameAdapter extends RecyclerView.Adapter<GaliGameNameAdapter.ViewHolder>{

    Context context;
    ArrayList<String> datalArrayList;


    public GaliGameNameAdapter(Context context, ArrayList<String> datalArrayList) {
        this.context = context;
        this.datalArrayList = datalArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recy_gali_chart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(datalArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return datalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView gameName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gameName = itemView.findViewById(R.id.result);
        }

        public void bind(String name) {
            gameName.setText(name);
        }

    }

}
