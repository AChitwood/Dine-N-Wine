package com.dine_n_wineinc.dine_n_wine;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import android.transition.Slide;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LandingActivity extends AppCompatActivity implements Animation.AnimationListener{

    public final static String EXTRA_MESSAGE = "com.dine_n_wineinc.dine_n_wine.MESSAGE";


    TextView textView;
    LinearLayout linearLayout; //Buttons on the start screen (Login, signup)
    RelativeLayout loginLayout, signupLayout;
    //--------------------------------
    Animation animationSlideLeft; //Welcome offscreen for login
    Animation animationSlideRight; //Login
    Animation animationSlideLeftS; //Signup
    Animation animationSlideExit; //Welcome offscreen for signup
    //---------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        textView = (TextView)findViewById(R.id.welcomeText);
        linearLayout = (LinearLayout)findViewById(R.id.buttons);
        loginLayout = (RelativeLayout)findViewById(R.id.loginlayout);
        loginLayout.setVisibility(View.INVISIBLE);
        signupLayout = (RelativeLayout)findViewById(R.id.signuplayout);
        signupLayout.setVisibility(View.INVISIBLE);
        //-----------------------------------
        animationSlideLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_leave);
        animationSlideLeft.setAnimationListener(this);
        animationSlideRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_enter);
        animationSlideLeftS = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_enter_right);
        animationSlideExit = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_leave_right);
        animationSlideExit.setAnimationListener(this);
        //------------------------------------
    }

    public void login(View view) {

        textView.startAnimation(animationSlideLeft);
        linearLayout.startAnimation(animationSlideLeft);
        loginLayout.setVisibility(View.VISIBLE);
        loginLayout.startAnimation(animationSlideRight);

    }

    public void signUp(View view) {

        textView.startAnimation(animationSlideExit);
        linearLayout.startAnimation(animationSlideExit);
        signupLayout.setVisibility(View.VISIBLE);
        signupLayout.startAnimation(animationSlideLeftS);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

            if((signupLayout.getVisibility() == View.VISIBLE) || (loginLayout.getVisibility() == View.VISIBLE)) {
                textView.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.INVISIBLE);
            }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

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

    @Override
    public void onBackPressed() {
        if(signupLayout.getVisibility() == View.VISIBLE) {
            signupLayout.startAnimation(animationSlideLeft);
            signupLayout.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
            textView.startAnimation(animationSlideRight);
            linearLayout.startAnimation(animationSlideRight);
        }
        else if(loginLayout.getVisibility() == View.VISIBLE) {
            loginLayout.startAnimation(animationSlideExit);
            loginLayout.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
            textView.startAnimation(animationSlideLeftS);
            linearLayout.startAnimation(animationSlideLeftS);
        }
        else {
            System.exit(1);
        }
    }

}
