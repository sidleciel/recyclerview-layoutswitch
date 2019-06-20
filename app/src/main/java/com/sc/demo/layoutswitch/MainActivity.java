package com.sc.demo.layoutswitch;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.ChangeBounds;
import androidx.transition.Explode;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionListenerAdapter;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;

import java.util.ArrayList;
import java.util.List;

import static com.sc.demo.layoutswitch.ItemAdapter.SPAN_COUNT_ONE;
import static com.sc.demo.layoutswitch.ItemAdapter.SPAN_COUNT_THREE;


public class MainActivity extends AppCompatActivity {
    long duration = 12000;

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
        gridLayoutManager.setAutoMeasureEnabled(true);
        itemAdapter = new ItemAdapter(items, gridLayoutManager);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new LayoutSwitchItemAnimator(gridLayoutManager));

//        recyclerView.getItemAnimator().setRemoveDuration(duration);
        recyclerView.getItemAnimator().setMoveDuration(duration);
//        recyclerView.getItemAnimator().setAddDuration(duration);
        recyclerView.getItemAnimator().setChangeDuration(duration);

        testChangeBound();
        final Explode explode = new Explode();
        TransitionSet set = new TransitionSet()
                .addTransition(explode)
                .addTransition(new Fade());
        TransitionManager.beginDelayedTransition(recyclerView, set);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    void testChangeBound() {
        final ViewGroup container = findViewById(R.id.container);
        final View text = findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int d = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100.f, v.getResources().getDisplayMetrics());

                TransitionSet set = new TransitionSet().addTransition(new ChangeBounds()).setDuration(1000);
                TransitionManager.beginDelayedTransition(container, set);
                ViewGroup.LayoutParams params = text.getLayoutParams();
                if (params.width == d) {
                    params.width = d * 2;
                    params.height = d * 2;
                } else {
                    params.width = d;
                    params.height = d;
                }
                text.setLayoutParams(params);
            }
        });
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
