package com.klk.mobilefingerprint.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.klk.mobilefingerprint.R;

public class SuccessAttendanceDialog extends AlertDialog.Builder {

    private static final String TAG = SuccessAttendanceDialog.class.getSimpleName();

    private String currTime = "";
    private String staffId = "";
    private String staffName = "";

    private Context mContext;

    public SuccessAttendanceDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public AlertDialog.Builder setView(View view) {
        TextView tvTime = (TextView) view.findViewById(R.id.tvTime);
        tvTime.setText(currTime);

        return super.setView(view);
    }

    @Override
    public AlertDialog create() {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.staff_info, null);
        setView(view);

        return super.create();
    }

    public void setCurrTime(String currTime) {
        this.currTime = currTime;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

}
