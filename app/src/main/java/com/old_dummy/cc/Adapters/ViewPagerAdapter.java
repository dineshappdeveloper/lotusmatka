package com.old_dummy.cc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.old_dummy.cc.Models.AppDetailsModel;
import com.old_dummy.cc.R;
//import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

//public class ViewPagerAdapter extends SliderViewAdapter<ViewPagerAdapter.SliderAdapterViewHolder> {

//    List<AppDetailsModel.Data.Banner> bannerList;
//
//    public ViewPagerAdapter(Context context, List<AppDetailsModel.Data.Banner> bannerList) {
//        this.bannerList = bannerList;
//    }
//    @Override
//    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
//        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pager_layout, null);
//        return new SliderAdapterViewHolder(inflate);
//    }

    // Inside on bind view holder we will
    // set data to item of Slider View.
//    @Override
//    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {
//        Glide.with(viewHolder.itemView)
//                .load(bannerList.get(position).getImage())
//                .fitCenter()
//                .into(viewHolder.imageViewBackground);
//    }

    // this method will return
    // the count of our list.
//    @Override
//    public int getCount() {
//        return bannerList.size();
//    }

//    static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        // Adapter class for initializing
        // the views of our slider view.
//        View itemView;
//        ShapeableImageView imageViewBackground;
//
//        public SliderAdapterViewHolder(View itemView) {
//            super(itemView);
//            imageViewBackground = itemView.findViewById(R.id.viewPagerImage);
//            this.itemView = itemView;
//        }
//    }
//}