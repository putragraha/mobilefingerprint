package com.klk.mobilefingerprint.utils;

/**
 *  TO SET GRID MARGIN EQUAL EACH OTHER
 */

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridSpacingItemDecorationHelper extends RecyclerView.ItemDecoration {

    private int mSpanCount;
    private int mSpacing;
    private boolean isEdgeIncluded;

    public GridSpacingItemDecorationHelper(int spanCount, int spacing, boolean isEdgeIncluded){
        this.mSpanCount = spanCount;
        this.mSpacing = spacing;
        this.isEdgeIncluded = isEdgeIncluded;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % mSpanCount; // item column

        if (isEdgeIncluded) {
            outRect.left = mSpacing - column * mSpacing / mSpanCount;
            outRect.right = (column + 1) * mSpacing / mSpanCount;

            if (position < mSpanCount) { // top edge
                outRect.top = mSpacing;
            }
            outRect.bottom = mSpacing; // item bottom
        } else {
            outRect.left = column * mSpacing / mSpanCount;
            outRect.right = mSpacing - (column + 1) * mSpacing / mSpanCount;
            if (position >= mSpanCount) {
                outRect.top = mSpacing; // item top
            }
        }
    }
}
