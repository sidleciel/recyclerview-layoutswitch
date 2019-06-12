package com.gjiazhe.layoutswitch;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    public static final int SPAN_COUNT_ONE   = 1;
    public static final int SPAN_COUNT_THREE = 3;

    private static final int VIEW_TYPE_LIST     = 1;
    private static final int VIEW_TYPE_GRID     = 2;
    private static final int VIEW_TYPE_LIST_BIG = 3;

    private List<Item>        mItems;
    private GridLayoutManager mLayoutManager;

    private int listType = VIEW_TYPE_LIST;

    public void setListType(int listType) {
        this.listType = listType;
    }

    public ItemAdapter(List<Item> items, GridLayoutManager layoutManager) {
        mItems = items;
        mLayoutManager = layoutManager;
    }

    @Override
    public int getItemViewType(int position) {
        int spanCount = mLayoutManager.getSpanCount();
        if (spanCount == SPAN_COUNT_THREE) {
            return VIEW_TYPE_GRID;
        } else {
            return listType;
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == VIEW_TYPE_LIST) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        } else if (viewType == VIEW_TYPE_GRID) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        } else if (viewType == VIEW_TYPE_LIST_BIG) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_big, parent, false);
        }
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
