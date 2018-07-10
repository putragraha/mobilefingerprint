package com.klk.mobilefingerprint.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                startActivity(intent);
            }
        });
    }
}
