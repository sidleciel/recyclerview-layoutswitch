package com.sc.demo.layoutswitch.holder;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sc.demo.layoutswitch.R;

public class ListBigViewHolder extends RecyclerView.ViewHolder {

    private final ConstraintLayout constraintLayout;

    public ListBigViewHolder(@NonNull View itemView) {
        super(itemView);
        constraintLayout = itemView.findViewById(R.id.constraintLayout);
    }
}
