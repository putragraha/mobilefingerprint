package com.klk.mobilefingerprint.components;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.klk.mobilefingerprint.R;

public class EnrollDialog extends AlertDialog.Builder {

    private Context mContext;

    public EnrollDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public AlertDialog.Builder setView(View view) {
        return super.setView(view);
    }

    @Override
    public AlertDialog create() {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.enroll_fingerprint, null);
        setView(view);

        return super.create();
    }
}
