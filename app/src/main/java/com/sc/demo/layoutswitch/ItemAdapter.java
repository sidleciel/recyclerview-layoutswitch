package com.sc.demo.layoutswitch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sc.demo.layoutswitch.holder.GridViewHolder;
import com.sc.demo.layoutswitch.holder.ListBigViewHolder;
import com.sc.demo.layoutswitch.holder.ListViewHolder;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter {
    public static final int SPAN_COUNT_ONE   = 1;
    public static final int SPAN_COUNT_THREE = 2;

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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == VIEW_TYPE_LIST) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            return new ListViewHolder(view);
        } else if (viewType == VIEW_TYPE_GRID) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
            return new GridViewHolder(view);
        } else if (viewType == VIEW_TYPE_LIST_BIG) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_big, parent, false);
            return new ListBigViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int viewType = getItemViewType(i);
        if (viewType == VIEW_TYPE_LIST) {
        } else if (viewType == VIEW_TYPE_GRID) {
        } else if (viewType == VIEW_TYPE_LIST_BIG) {
        }
    }

    @Override
    public int getItemCount() {
        return 30;
    }
}
