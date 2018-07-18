package com.klk.mobilefingerprint.components;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.klk.mobilefingerprint.utils.DPtoPixel;
import com.klk.mobilefingerprint.utils.GridSpacingItemDecorationHelper;

public class RecyclerViewHelper extends RecyclerView {

    private static final String TAG = RecyclerViewHelper.class.getSimpleName();

    private DPtoPixel mDPtoPixel;
    private GridSpacingItemDecorationHelper mGridSpacingItemDecorationHelper;

    private Context mContext;
    private RecyclerView mRecyclerView;
    private int mSpan;
    private int mDPValue;

    public RecyclerViewHelper(Context context, RecyclerView recyclerView, int span, int dpValue) {
        super(context);
        this.mContext = context;
        this.mRecyclerView = recyclerView;
        this.mSpan = span;
        this.mDPValue = dpValue;

        setRecyclerViewReady();
    }

    private void setItemDecorationComp(){
        mDPtoPixel = new DPtoPixel(mContext);
        int pixel = mDPtoPixel.convert(mDPValue);
        mGridSpacingItemDecorationHelper = new GridSpacingItemDecorationHelper(mSpan, pixel, true);
    }

    private void setRecyclerViewReady(){
        setItemDecorationComp();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext, mSpan);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(mGridSpacingItemDecorationHelper);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
