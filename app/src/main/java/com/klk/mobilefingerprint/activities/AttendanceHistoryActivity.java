package com.klk.mobilefingerprint.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.adapters.AttendanceHistoryAdapter;
import com.klk.mobilefingerprint.components.RecyclerViewHelper;
import com.klk.mobilefingerprint.constantvalues.CardConfig;
import com.klk.mobilefingerprint.models.AttendanceHistory;
import com.klk.mobilefingerprint.utils.BackTransitioner;

import java.util.ArrayList;

public class AttendanceHistoryActivity extends AppCompatActivity {

    private BackTransitioner mBackTransitioner = new BackTransitioner();

    private AttendanceHistoryAdapter mAttendanceHistoryAdapter;
    private RecyclerView mRecyclerView;

    private ArrayList<AttendanceHistory> mAttendanceHistoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_history);

        init();
        loadAttendanceHistoryData();
    }

    private void init(){
        mRecyclerView = findViewById(R.id.recyclerViewAttendanceHistory);
        mAttendanceHistoryAdapter = new AttendanceHistoryAdapter(this, mAttendanceHistoryList);

        new RecyclerViewHelper(this, mRecyclerView, CardConfig.ATTENDANCE_HISTORY_CARD_SPAN, CardConfig.ATTENDANCE_HISTORY_CARD_DP_VALUE);
        mRecyclerView.setAdapter(mAttendanceHistoryAdapter);
    }

    private void loadAttendanceHistoryData(){
            for (int i = 0; i < 5; i++) {
                AttendanceHistory attendanceHistory = new AttendanceHistory();
                attendanceHistory.setDate("10 Jul 18");
                attendanceHistory.setTime("13:36:32");
                mAttendanceHistoryList.add(attendanceHistory);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                backAction();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        backAction();
        super.onBackPressed();
    }

    private void backAction() {
        mBackTransitioner.animate(this, MainActivity.class);
        finish();
    }
}
