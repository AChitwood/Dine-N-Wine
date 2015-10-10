package com.dine_n_wineinc.dine_n_wine;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_homepage);

        Intent intent = getIntent();
        String message = intent.getStringExtra(LandingActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);

        textView.setTextSize(40);
        textView.setText(message);
        setContentView(textView);

    }

}
