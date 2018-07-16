package com.klk.mobilefingerprint.activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.klk.mobilefingerprint.R;

public class LoginAdminActivity extends AppCompatActivity {

    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        mLogin = (Button) findViewById(R.id.btnLogin);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginAdminActivity.this, StaffListActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(LoginAdminActivity.this, R.anim.in_from_right, R.anim.out_to_left);
                startActivity(intent, options.toBundle());
                finish();
            }
        });
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
