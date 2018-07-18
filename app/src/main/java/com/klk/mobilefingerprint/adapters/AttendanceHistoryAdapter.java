package com.klk.mobilefingerprint.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.models.AttendanceHistory;

import java.util.ArrayList;

public class AttendanceHistoryAdapter extends RecyclerView.Adapter<AttendanceHistoryAdapter.AttendanceHistoryViewHolder> {

    private static final String TAG = AttendanceHistoryAdapter.class.getSimpleName();

    private Context mContext;
    private ArrayList<AttendanceHistory> mAttendanceHistoryList;

    public AttendanceHistoryAdapter(Context context, ArrayList<AttendanceHistory> attendanceHistoryList){
        this.mContext = context;
        this.mAttendanceHistoryList = attendanceHistoryList;
    }

    @Override
    public AttendanceHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_attendance_history, parent, false);
        return new AttendanceHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AttendanceHistoryViewHolder holder, int position) {
        AttendanceHistory attendanceHistory = mAttendanceHistoryList.get(position);
        holder.tvDate.setText(attendanceHistory.getDate());
        holder.tvTime.setText(attendanceHistory.getTime());
    }

    @Override
    public int getItemCount() {
        return mAttendanceHistoryList.size();
    }

    public class AttendanceHistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDate, tvTime;

        public AttendanceHistoryViewHolder(View view) {
            super(view);
            tvDate = view.findViewById(R.id.tvDate);
            tvTime = view.findViewById(R.id.tvTime);
        }
    }
}
