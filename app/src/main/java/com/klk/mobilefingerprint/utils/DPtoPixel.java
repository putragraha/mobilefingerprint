package com.klk.mobilefingerprint.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import com.klk.mobilefingerprint.services.PixelConvertService;

public class DPtoPixel implements PixelConvertService {

    private Context mContext;

    public DPtoPixel(Context context){
        this.mContext = context;
    }

    @Override
    public int convert(int value) {
        Resources resources = mContext.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.getDisplayMetrics()));
    }
}
