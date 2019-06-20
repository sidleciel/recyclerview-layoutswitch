package com.sc.demo.layoutswitch.holder;

import android.support.constraint.ConstraintLayout;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.sc.demo.layoutswitch.R;

public class ListViewHolder extends RecyclerView.ViewHolder {

    public ConstraintLayout constraintLayout;

    public ListViewHolder(View itemView) {
        super(itemView);
        constraintLayout = itemView.findViewById(R.id.constraintLayout);
    }
}
