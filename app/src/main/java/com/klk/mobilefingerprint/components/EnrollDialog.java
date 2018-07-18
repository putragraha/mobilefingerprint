package com.klk.mobilefingerprint.components;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.klk.mobilefingerprint.R;
import com.library.NavigationBar;
import com.library.NvTab;

public class EnrollDialog extends AlertDialog.Builder implements NavigationBar.OnTabSelected, NavigationBar.OnTabClick{

    private Context mContext;
    private NavigationBar mBar;

    private int count = 10;
    private int animateDuration = 3000;
    private int pos = 0;

    public EnrollDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public AlertDialog.Builder setView(View view) {
        mBar = view.findViewById(R.id.navbarEnrollFinger);
        mBar.setOnTabClick(this);
        mBar.setOnTabSelected(this);
        mBar.setTabCount(count);
        mBar.animateView(animateDuration);
        mBar.setCurrentPosition(pos <= 0 ? 0 : pos);
        return super.setView(view);
    }

    @Override
    public AlertDialog create() {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.enroll_fingerprint, null);
        setView(view);

        return super.create();
    }

    @Override
    public AlertDialog.Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {

        return super.setOnDismissListener(onDismissListener);
    }

    @Override
    public void onTabClick(int touchPosition, NvTab prev, NvTab nvTab) {
        Toast.makeText(mContext, "You clicked on: " + touchPosition, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTabSelected(int touchPosition, NvTab prev, NvTab nvTab) {
        Toast.makeText(mContext, "Selected position: " + touchPosition, Toast.LENGTH_LONG).show();
    }
}
