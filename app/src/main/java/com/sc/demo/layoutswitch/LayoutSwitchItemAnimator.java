package com.sc.demo.layoutswitch;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class LayoutSwitchItemAnimator extends BaseItemAnimator {

    private RecyclerView.LayoutManager layoutManager;

    public LayoutSwitchItemAnimator(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    protected void animateChangeImpl(ChangeInfo changeInfo) {
        super.animateChangeImpl(changeInfo);
        animateChangeBound(changeInfo);
    }

    RecyclerView recyclerView;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    void animateChangeBound(final BaseItemAnimator.ChangeInfo changeInfo) {
        final RecyclerView.ViewHolder holder = changeInfo.oldHolder;
        final View view = holder == null ? null : holder.itemView;
        final RecyclerView.ViewHolder newHolder = changeInfo.newHolder;
        final View newView = newHolder != null ? newHolder.itemView : null;

        if (view == null || newView == null) return;

        final int[] param = new int[2];
        final int[] paramNew = new int[2];
        final ViewGroup.LayoutParams params = view.getLayoutParams();
        final ViewGroup.LayoutParams newParams = newView.getLayoutParams();
        param[0] = view.getMeasuredWidth();
        param[1] = view.getMeasuredHeight();
        paramNew[0] = newView.getMeasuredWidth();
        paramNew[1] = newView.getMeasuredHeight();

        params.width = paramNew[0];
        params.height = paramNew[1];
        view.setLayoutParams(params);
//        newParams.width = param[0];
//        newParams.height = param[1];
//        newView.setLayoutParams(newParams);
        layoutManager.requestLayout();

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1.f).setDuration(getChangeDuration());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                if (value == 1) {
                    if (view != null) {
                        params.width = param[0];
                        params.height = param[1];
                    }
                    view.setLayoutParams(params);
//                    if (view != null) {
//                        newParams.width = paramNew[0];
//                        newParams.height = paramNew[1];
//                    }
//                    newView.setLayoutParams(newParams);
                }
            }
        });
        animator.start();
    }

    @Override
    public boolean animateDisappearance(RecyclerView.ViewHolder viewHolder, ItemHolderInfo preLayoutInfo, ItemHolderInfo postLayoutInfo) {

        View view = viewHolder.itemView;
        if (view != null) {

            if (view.getParent() != null)
                recyclerView = (RecyclerView) view.getParent();

            if (recyclerView != null) {
                TransitionSet set = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    set = new TransitionSet()
                            .addTransition(new ChangeBounds().addTarget(view));
                    TransitionManager.beginDelayedTransition(recyclerView, set);
                }
            }
        }
        return super.animateDisappearance(viewHolder, preLayoutInfo, postLayoutInfo);
    }
}
