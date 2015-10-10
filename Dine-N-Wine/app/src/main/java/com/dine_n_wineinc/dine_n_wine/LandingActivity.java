package com.dine_n_wineinc.dine_n_wine;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void login(View view) {

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        this.overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);

    }

    public void signUp(View view) {

    }

}
