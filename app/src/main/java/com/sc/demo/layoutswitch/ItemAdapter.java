package com.sc.demo.layoutswitch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter {
    public static final int SPAN_COUNT_ONE = 1;
    public static final int SPAN_COUNT_TWO = 2;

    private static final int VIEW_TYPE_LIST = 1;
    private static final int VIEW_TYPE_GRID = 2;
    private static final int VIEW_TYPE_LIST_BIG = 3;

    private List<Item> mItems;
    private GridLayoutManager mLayoutManager;

    private int listType = VIEW_TYPE_LIST;

    public void setListType(int listType) {
        this.listType = listType;
    }

    public ItemAdapter(List<Item> items, GridLayoutManager layoutManager) {
        mItems = items;
        mLayoutManager = layoutManager;
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = getItemViewType(position);
                if (viewType == 2) return 1;
                return 2;
            }
        });
    }

//    @Override
//    public int getItemViewType(int position) {
//        int spanCount = mLayoutManager.getSpanCount();
//        if (spanCount == SPAN_COUNT_TWO) {
//            return VIEW_TYPE_GRID;
//        } else {
//            return listType;
//        }
//    }

    @Override
    public int getItemViewType(int position) {
        return listType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        ItemViewHolder holder = null;
        if (viewType == VIEW_TYPE_LIST) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            holder = new ItemViewHolder(view);
        } else if (viewType == VIEW_TYPE_GRID) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
            holder = new ItemViewHolder(view);
        } else if (viewType == VIEW_TYPE_LIST_BIG) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_big, parent, false);
            holder = new ItemViewHolder(view);
        }
        holder.type = viewType;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ItemViewHolder) viewHolder).pos = i;
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

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public int pos;
        public int type;

        public View main;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            main = itemView.findViewById(R.id.main);
        }
    }
}
