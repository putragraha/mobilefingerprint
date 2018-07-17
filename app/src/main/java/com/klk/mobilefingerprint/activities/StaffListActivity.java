package com.klk.mobilefingerprint.activities;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.adapters.StaffAdapter;
import com.klk.mobilefingerprint.constantvalues.CardConfig;
import com.klk.mobilefingerprint.utils.GridSpacingItemDecorationHelper;
import com.klk.mobilefingerprint.models.Staff;
import com.klk.mobilefingerprint.utils.BackTransitioner;
import com.klk.mobilefingerprint.utils.DPtoPixel;

import java.util.ArrayList;

public class StaffListActivity extends AppCompatActivity {

    private BackTransitioner mBackTransitioner = new BackTransitioner();
    private DPtoPixel mDPtoPixel;
    private GridSpacingItemDecorationHelper mGridSpacingItemDecorationHelper;

    private StaffAdapter mStaffAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<Staff> mStaffList = new ArrayList<>();

    private SearchView searchView;

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
        mGridSpacingItemDecorationHelper = new GridSpacingItemDecorationHelper(CardConfig.STAFF_CARD_SPAN, pixel, true);
    }

    private void setRecycler(){
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, CardConfig.STAFF_CARD_SPAN);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mGridSpacingItemDecorationHelper);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mStaffAdapter);
    }

    private void setStaffData(){
        for (int i = 0; i < 5; i++) {
            Staff staff = new Staff();
            staff.set_id(1081012380);
            staff.setName("Seseorang dengan Nama " + i);
            mStaffList.add(staff);
        }
        mStaffAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                backAction();
                return true;
            case R.id.action_search:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        backAction();
        super.onBackPressed();
    }

    void backAction(){
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
        } else {
            mBackTransitioner.animate(this, MainActivity.class);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.staff_search, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mStaffAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mStaffAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }
}
