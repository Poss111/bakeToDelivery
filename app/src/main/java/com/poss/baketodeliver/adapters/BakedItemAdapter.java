package com.poss.baketodeliver.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.poss.baketodeliver.R;
import com.poss.baketodeliver.helpers.BakedItem;
import com.poss.baketodeliver.holders.BakedItemHolder;

import java.util.List;

public class BakedItemAdapter extends RecyclerView.Adapter {

    private List<BakedItem> bakedItemList;

    public BakedItemAdapter(List<BakedItem> bakedItemList) {
        this.bakedItemList = bakedItemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new BakedItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((BakedItemHolder) holder).bindData(bakedItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return bakedItemList.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.baked_item_card_layout;
    }

}
