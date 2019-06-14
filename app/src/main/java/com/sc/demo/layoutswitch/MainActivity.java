package com.sc.demo.layoutswitch;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;

import java.util.ArrayList;
import java.util.List;

import static com.sc.demo.layoutswitch.ItemAdapter.SPAN_COUNT_ONE;
import static com.sc.demo.layoutswitch.ItemAdapter.SPAN_COUNT_THREE;


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

        long duration = 1200;

        gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT_ONE);
        gridLayoutManager.setAutoMeasureEnabled(true);
        itemAdapter = new ItemAdapter(items, gridLayoutManager);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new LayoutSwitchItemAnimator());

//        recyclerView.getItemAnimator().setRemoveDuration(duration);
//        recyclerView.getItemAnimator().setMoveDuration(duration);
//        recyclerView.getItemAnimator().setAddDuration(duration);
        recyclerView.getItemAnimator().setChangeDuration(duration);
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
