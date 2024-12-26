package com.old_dummy.cc.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.old_dummy.cc.Models.MenuItemModel;
import com.old_dummy.cc.R;

import java.util.List;
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<MenuItemModel> menuItems;
    private OnMenuItemClickListener listener;

    public MenuAdapter(List<MenuItemModel> menuItems, OnMenuItemClickListener listener) {
        this.menuItems = menuItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_menu_item, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItemModel menuItem = menuItems.get(position);
        holder.title.setText(menuItem.getTitle());
        holder.icon.setImageResource(menuItem.getIcon());

        holder.linearLayout.setOnClickListener(v -> {
            v.setAlpha(0.5f);  // Click animation: Change alpha
            v.animate().alpha(1.0f).start();
            listener.onMenuItemClick(menuItem.getTitle());
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView title;
        ShapeableImageView icon;
        LinearLayout linearLayout;


        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.drawerText);
            icon = itemView.findViewById(R.id.drawerIcon);
            linearLayout = itemView.findViewById(R.id.menuItem);
        }
    }

    public interface OnMenuItemClickListener {
        void onMenuItemClick(String menuItem);
    }
}