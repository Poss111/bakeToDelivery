package com.poss.baketodeliver.holders;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.poss.baketodeliver.R;
import com.poss.baketodeliver.helpers.BakedItem;

import java.text.NumberFormat;

public class BakedItemHolder extends RecyclerView.ViewHolder {
    private TextView titleFieldOne;
    private TextView valueFieldOne;
    private TextView titleFieldTwo;
    private TextView valueFieldTwo;
    private TextView titleFieldThree;
    private TextView valueFieldThree;

    public BakedItemHolder(@NonNull View itemView) {
        super(itemView);
        this.titleFieldOne = itemView.findViewById(R.id.foodDetails).findViewById(R.id.title_item);
        this.valueFieldOne = itemView.findViewById(R.id.foodDetails).findViewById(R.id.field_item);
        this.titleFieldTwo = itemView.findViewById(R.id.bakerDetails).findViewById(R.id.title_item);
        this.valueFieldTwo = itemView.findViewById(R.id.bakerDetails).findViewById(R.id.field_item);
        this.titleFieldThree = itemView.findViewById(R.id.priceDetails).findViewById(R.id.title_item);
        this.valueFieldThree = itemView.findViewById(R.id.priceDetails).findViewById(R.id.field_item);
    }

    public void bindData(final BakedItem bakedItem) {
        titleFieldOne.setText(R.string.cardFoodTitle);
        valueFieldOne.setText(bakedItem.getFoodName());
        titleFieldTwo.setText(R.string.cardBakerTitle);
        valueFieldTwo.setText(bakedItem.getBakerName());
        titleFieldThree.setText(R.string.cardPriceTitle);
        valueFieldThree.setText(NumberFormat.getCurrencyInstance().format(bakedItem.getPrice()));
    }

}
