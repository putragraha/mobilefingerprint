package com.klk.mobilefingerprint.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.klk.mobilefingerprint.R;

public class StaffListActivity extends AppCompatActivity {

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
                Intent intent = new Intent(this, MainActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.in_from_left, R.anim.out_to_right);
                startActivity(intent, options.toBundle());
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
