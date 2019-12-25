package com.example.baketodeliver.activities;

import android.os.Bundle;
import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.baketodeliver.R;
import com.example.baketodeliver.adapters.BakedItemAdapter;
import com.example.baketodeliver.helpers.BakedItem;
import com.example.baketodeliver.utils.SharedContextUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BakedItemsActivity extends AppCompatActivity {

    private String TAG = BakedItemsActivity.class.getSimpleName();

    private ArrayList<BakedItem> bakedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baked_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bakedItems = (ArrayList) SharedContextUtils.getObjectFromMap(getString(R.string.bakedGoodsList));

        if (bakedItems == null) {
            bakedItems = new ArrayList<>();
        }

        RecyclerView recyclerView = findViewById(R.id.bakedItemListRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        bakedItems.add((BakedItem) getIntent().getExtras().getParcelable(getString(R.string.bakedGoodItem)));
        bakedItems.add((BakedItem) SharedContextUtils.getObjectFromMap(getString(R.string.bakedGoodItem)));
        BakedItemAdapter bakedItemAdapter = new BakedItemAdapter(bakedItems);
        recyclerView.setAdapter(bakedItemAdapter);
//        savedInstanceState.putParcelableArrayList(getString(R.string.bakedGoodsList), bakedItems);
        /** Persist object to Map **/
        SharedContextUtils.putObjectToMap(getString(R.string.bakedGoodsList), bakedItems);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public List<BakedItem> generateBakeItemList() {
        List<BakedItem> bakedItemsList = new ArrayList<>();
        BakedItem bakedItem = new BakedItem("Baker1", "Food1", 1.23);
        BakedItem bakedItemTwo = new BakedItem("Baker2", "Food2", 44.23);
        BakedItem bakedItemThree = new BakedItem("Baker3", "Food2", 880.12);
        bakedItemsList.add(bakedItem);
        bakedItemsList.add(bakedItemTwo);
        bakedItemsList.add(bakedItemThree);
        return bakedItemsList;
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "calling onSaveInstanceState...");
        outState.putParcelableArrayList(getString(R.string.bakedGoodsList), bakedItems);
        super.onSaveInstanceState(outState);
        Log.i(TAG, "Finished onSaveInstanceState");
    }

    @Override
    public void onPause() {
        Log.i(TAG, "onPause called");
        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy called");
        super.onDestroy();
    }


}
