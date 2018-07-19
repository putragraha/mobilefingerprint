package com.klk.mobilefingerprint.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.utils.BackTransitioner;
import com.klk.mobilefingerprint.utils.NextTransitioner;

public class LoginHistoryAcitivity extends AppCompatActivity {

    private Button mLogin;

    private BackTransitioner mBackTransitioner = new BackTransitioner();
    private NextTransitioner mNextTransitioner = new NextTransitioner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_history);

        mLogin = findViewById(R.id.btnLogin);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNextTransitioner.animate(LoginHistoryAcitivity.this, AttendanceHistoryActivity.class);
                finish();
            }
        });
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
        super.onBackPressed();
        backAction();
    }

    private void backAction(){
        mBackTransitioner.animate(this, MainActivity.class);
        finish();
    }
}
