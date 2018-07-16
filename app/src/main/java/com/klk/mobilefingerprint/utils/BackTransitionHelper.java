package com.klk.mobilefingerprint.utils;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;

import com.klk.mobilefingerprint.R;
import com.klk.mobilefingerprint.services.TransitionService;

public class BackTransitionHelper implements TransitionService {

    public BackTransitionHelper(){}

    @Override
    public void animate(Context context, Class javaClass) {
        Intent intent = new Intent(context, javaClass);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(context, R.anim.in_from_left, R.anim.out_to_right);
        context.startActivity(intent, options.toBundle());
    }
}
