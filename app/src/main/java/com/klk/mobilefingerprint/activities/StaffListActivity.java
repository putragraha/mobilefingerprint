package com.klk.mobilefingerprint.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.utils.BackTransitioner;

public class StaffListActivity extends AppCompatActivity {

    private BackTransitioner mBackTransitioner = new BackTransitioner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_list);
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
