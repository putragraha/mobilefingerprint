package com.klk.mobilefingerprint.helpers;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.widget.RelativeLayout;

import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.klk.mobilefingerprint.R;

public class PermissionErrorChecker implements PermissionRequestErrorListener {

    private RelativeLayout mRelativeLayout;
    private Context mContext;

    public PermissionErrorChecker(Context context, RelativeLayout relativeLayout){
        this.mRelativeLayout = relativeLayout;
        this.mContext = context;
    }

    @Override
    public void onError(DexterError error) {
        String errorMsg = mContext.getResources().getString(R.string.label_error_permission);
        Snackbar snackbar = Snackbar.make(mRelativeLayout, errorMsg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
