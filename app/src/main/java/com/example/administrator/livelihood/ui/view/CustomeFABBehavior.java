package com.example.administrator.livelihood.ui.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.example.administrator.livelihood.R;

/**
 * 设置FAB的显示和隐藏
 */
public class CustomeFABBehavior extends FloatingActionButton.Behavior {
    public CustomeFABBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        animationOut(child);

        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);

    }


    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        animationIn(child);
    }

    private void animationIn(FloatingActionButton child) {
        child.setAnimation(AnimationUtils.loadAnimation(child.getContext(), R.anim.anim_fab_in));
        child.setVisibility(View.VISIBLE);
    }

    private void animationOut(FloatingActionButton child) {
        child.setAnimation(AnimationUtils.loadAnimation(child.getContext(), R.anim.anim_fab_out));
        child.setVisibility(View.GONE);
    }
}
