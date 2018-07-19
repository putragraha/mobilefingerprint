package com.klk.mobilefingerprint.helpers;

import android.app.Activity;
import android.content.Context;

import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;
import com.klk.mobilefingerprint.dialogs.OpenSettingDialog;

import java.util.List;

public class PermissionChecker extends BaseMultiplePermissionsListener {

    private Activity mActivity;
    private Context mContext;

    public PermissionChecker(Activity activity, Context context){
        this.mActivity = activity;
        this.mContext = context;
    }

    @Override
    public void onPermissionsChecked(MultiplePermissionsReport report) {
        if(report.isAnyPermissionPermanentlyDenied()){
            OpenSettingDialog openSettingDialog = new OpenSettingDialog(mActivity, mContext);
            openSettingDialog.show();
        }
    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
        token.continuePermissionRequest();
    }
}
