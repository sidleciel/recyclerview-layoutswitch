package com.sc.demo.layoutswitch.holder;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sc.demo.layoutswitch.R;

public class GridViewHolder extends RecyclerView.ViewHolder {
    public  ConstraintLayout constraintLayout;

    public GridViewHolder(@NonNull View itemView) {
        super(itemView);
        constraintLayout = itemView.findViewById(R.id.constraintLayout);
    }
}
