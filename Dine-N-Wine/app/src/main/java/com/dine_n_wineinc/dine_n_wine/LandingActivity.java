package com.dine_n_wineinc.dine_n_wine;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;


public class LandingActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.dine_n_wineinc.dine_n_wine.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    /** Called when the user clicks the Send button */

    public void homepage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Homepage.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();

        String message = "Aloha!";

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}
