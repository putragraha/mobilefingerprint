package com.klk.mobilefingerprint.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.adapters.StaffAdapter;
import com.klk.mobilefingerprint.constantvalues.CardConfig;
import com.klk.mobilefingerprint.helpers.GridSpacingItemDecoration;
import com.klk.mobilefingerprint.models.Staff;
import com.klk.mobilefingerprint.utils.BackTransitioner;
import com.klk.mobilefingerprint.utils.DPtoPixel;

import java.util.ArrayList;

public class StaffListActivity extends AppCompatActivity {

    private BackTransitioner mBackTransitioner = new BackTransitioner();
    private DPtoPixel mDPtoPixel;
    private GridSpacingItemDecoration mGridSpacingItemDecoration;

    private StaffAdapter mStaffAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<Staff> mStaffList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_list);

        init();
        setRecycler();
        setStaffData();
    }

    private void init(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewStaff);
        mStaffAdapter = new StaffAdapter(this, mStaffList);

        mDPtoPixel = new DPtoPixel(this);
        int pixel = mDPtoPixel.convert(CardConfig.STAFF_CARD_DP_VALUE);
        mGridSpacingItemDecoration = new GridSpacingItemDecoration(CardConfig.STAFF_CARD_SPAN, pixel, true);
    }

    private void setRecycler(){
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, CardConfig.STAFF_CARD_SPAN);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mGridSpacingItemDecoration);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mStaffAdapter);
    }

    private void setStaffData(){
        for (int i = 0; i < 5; i++) {
            Staff staff = new Staff();
            staff.set_id(1081012380);
            staff.setName("Seseorang dengan Nama");
            mStaffList.add(staff);
        }
        mStaffAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                mBackTransitioner.animate(this, MainActivity.class);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        mBackTransitioner.animate(this, MainActivity.class);
        finish();
        super.onBackPressed();
    }
}
