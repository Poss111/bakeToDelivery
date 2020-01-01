package com.poss.baketodeliver.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.poss.baketodeliver.R;
import com.poss.baketodeliver.helpers.BakedItem;
import com.poss.baketodeliver.utils.SharedContextUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();

    @BindView(R.id.bakerInput)
    TextInputEditText bakerInput;

    @BindView(R.id.foodInput)
    TextInputEditText foodInput;

    @BindView(R.id.priceInput)
    TextInputEditText priceInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Creating MainActivity...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);

        if (sharedPreferences.getBoolean("userLoggedIn", false)) {
            Intent intent = new Intent(this, BakeToDeliveryLoginActivity.class);
            startActivity(intent);
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Log.d(TAG, "Created MainActivity...");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public void submitBakedGood(View view) {
        Log.i(TAG, "Submitting text field");

        BakedItem bakedItem = new BakedItem(bakerInput.getText().toString(),
                foodInput.getText().toString(),
                Double.valueOf(priceInput.getText().toString()));

        Log.i(TAG, "Created Baker Item :: " + bakedItem);
        Intent intent = new Intent(this, BakedItemsActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(getString(R.string.bakedGoodItem), bakedItem);
//        intent.putExtras(bundle);
        SharedContextUtils.putObjectToMap(getString(R.string.bakedGoodItem), bakedItem);
        Log.i(TAG, "Navigating to Baked Items Activity with Baked Item ('" + bakedItem + "')...");
        startActivity(intent);
    }

}
