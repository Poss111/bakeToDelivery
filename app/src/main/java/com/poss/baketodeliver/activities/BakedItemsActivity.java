package com.poss.baketodeliver.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.*;
import com.poss.baketodeliver.R;
import com.poss.baketodeliver.adapters.BakedItemAdapter;
import com.poss.baketodeliver.helpers.BakedItem;
import com.poss.baketodeliver.utils.SharedContextUtils;

import java.util.ArrayList;
import java.util.List;

public class BakedItemsActivity extends AppCompatActivity {

    private String TAG = BakedItemsActivity.class.getSimpleName();

    private ArrayList<BakedItem> bakedItems;

    @BindView(R.id.bakedItemListRecyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baked_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        bakedItems = new ArrayList<>();
//        bakedItems = (ArrayList) SharedContextUtils.getObjectFromMap(getString(R.string.bakedGoodsList));

        if (recyclerView == null) {
            recyclerView = findViewById(R.id.bakedItemListRecyclerView);
        }
        BakedItem bakedItemFromDB = null;
        firebaseFirestore.collection(getString(R.string.bakedGoodsFBCollectionKey))
                .addSnapshotListener(new EventListener<QuerySnapshot>() {

                    @Override
                    public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                    if (e != null) {
                        Log.e(TAG, "Error has occurred while retrieving from Firestore", e);
                        return;
                    }

                    BakedItem bakedItem = new BakedItem();
                    QueryDocumentSnapshot queryDocumentSnapshot;
                    for (DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()) {
                        switch (documentChange.getType()) {
                            case ADDED:
                                Log.d(TAG, "This document has been added.");
                                queryDocumentSnapshot = documentChange.getDocument();
                                bakedItem = new BakedItem(queryDocumentSnapshot.getString("bakerName"),
                                        queryDocumentSnapshot.getString("goodsName"),
                                        queryDocumentSnapshot.getDouble("price"));
                                bakedItem.setFirestoreId(queryDocumentSnapshot.getId());
                                bakedItems.add(bakedItem);
                                break;
                            case MODIFIED:
                                Log.d(TAG, "This document has been modified.");
                                queryDocumentSnapshot = documentChange.getDocument();
                                bakedItem = new BakedItem(queryDocumentSnapshot.getString("bakerName"),
                                    queryDocumentSnapshot.getString("goodsName"),
                                    queryDocumentSnapshot.getDouble("price"));
                                bakedItem.setFirestoreId(queryDocumentSnapshot.getId());
                                break;
                            case REMOVED:
                                Log.d(TAG, "This document has been removed.");
                        }
                    }
                    BakedItemAdapter bakedItemAdapter = new BakedItemAdapter(bakedItems);
                    recyclerView.setAdapter(bakedItemAdapter);
                }});

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        bakedItems.add((BakedItem) getIntent().getExtras().getParcelable(getString(R.string.bakedGoodItem)));
//        bakedItems.add((BakedItem) SharedContextUtils.getObjectFromMap(getString(R.string.bakedGoodItem)));
//        BakedItemAdapter bakedItemAdapter = new BakedItemAdapter(bakedItems);
//        recyclerView.setAdapter(bakedItemAdapter);
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
