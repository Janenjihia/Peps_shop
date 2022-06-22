package com.moringaschool.peps_shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SliderAdapter  extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    private final List<SliderItem> sliderItems;

    public SliderAdapter(List<SliderItem> sliderItems) {
        this.sliderItems = sliderItems;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_banner_slider_item, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  SliderViewHolder holder, int position) {
        SliderItem sliderItem = sliderItems.get(position);
        holder.sliderImg.setImageResource(sliderItem.getImageResource());
    }

    @Override
    public int getItemCount() {
        if (sliderItems == null || sliderItems.isEmpty()) return 0;
        return sliderItems.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {

        private final ImageView sliderImg;

        public SliderViewHolder(@NonNull  View itemView) {
            super(itemView);
            sliderImg = itemView.findViewById(R.id.slider_item_img);
        }
    }
}
