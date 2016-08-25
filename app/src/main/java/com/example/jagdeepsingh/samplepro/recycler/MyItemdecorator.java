package com.example.jagdeepsingh.samplepro.recycler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jagdeepsingh.samplepro.R;

/**
 * Created by Jagdeep.Singh on 02-08-2016.
 */
public class MyItemdecorator extends RecyclerView.ItemDecoration {

    Drawable blue_divider_drawable;

    public MyItemdecorator(Context context) {
        blue_divider_drawable = context.getResources().getDrawable(R.drawable.blue_divider);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int left =parent.getPaddingLeft()+80;
        int right = parent.getWidth() - parent.getPaddingRight()-30;


        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + blue_divider_drawable.getIntrinsicHeight();

            blue_divider_drawable.setBounds(left,bottom,right,top);
            blue_divider_drawable.draw(c);
        }
    }

}
