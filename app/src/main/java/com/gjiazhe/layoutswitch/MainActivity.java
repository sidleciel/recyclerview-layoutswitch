package com.gjiazhe.layoutswitch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static com.gjiazhe.layoutswitch.ItemAdapter.SPAN_COUNT_ONE;
import static com.gjiazhe.layoutswitch.ItemAdapter.SPAN_COUNT_THREE;


public class MainActivity extends AppCompatActivity {

    private RecyclerView      recyclerView;
    private ItemAdapter       itemAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<Item>        items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        initItemsData();

        gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT_ONE);
        itemAdapter = new ItemAdapter(items, gridLayoutManager);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void initItemsData() {
        items = new ArrayList<>();
        items.add(new Item());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meun_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.list) {
            itemAdapter.setListType(1);
            gridLayoutManager.setSpanCount(SPAN_COUNT_ONE);
            itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
            return true;
        } else if (item.getItemId() == R.id.grid) {
            gridLayoutManager.setSpanCount(SPAN_COUNT_THREE);
            itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
            return true;
        } else if (item.getItemId() == R.id.listBig) {
            itemAdapter.setListType(3);
            gridLayoutManager.setSpanCount(SPAN_COUNT_ONE);
            itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
