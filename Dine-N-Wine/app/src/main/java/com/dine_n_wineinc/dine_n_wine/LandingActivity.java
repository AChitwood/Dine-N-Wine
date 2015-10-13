package com.dine_n_wineinc.dine_n_wine;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    Animation animationSlideLeft; //Welcome offscreen for login
    Animation animationSlideRight; //Login
    Animation animationSlideLeftS; //Signup
    Animation animationSlideExit; //Welcome offscreen for signup
    String loginPassword, loginEmail; //login EditText values
    String SUPassword, SUEmail, SUFN, SULN;//sign up strings
    EditText loginPasswordEditText, loginEmailEditText;
    EditText SUPWEditText, SUEmailEditText, SUFNEditText,SULNEditText;
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
        animationSlideLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_leave);
        animationSlideLeft.setAnimationListener(this);
        animationSlideRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_enter);
        animationSlideLeftS = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_enter_right);
        animationSlideExit = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_leave_right);
        animationSlideExit.setAnimationListener(this);
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
        /*
        String message = "Aloha!";

        intent.putExtra(EXTRA_MESSAGE, message); */
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

    public void checkLogin(View view){

        loginPasswordEditText = (EditText)findViewById(R.id.password);
        loginEmailEditText = (EditText)findViewById(R.id.email);
        loginEmail = loginEmailEditText.getText().toString();
        loginPassword = loginPasswordEditText.getText().toString();
        String usps = loginEmail + " " + loginPassword;
        if(loginPassword.compareTo("") != 0 || loginEmail.compareTo("") != 0){
            Intent intent = new Intent(this, Homepage.class);
            intent.putExtra(EXTRA_MESSAGE, usps);
            startActivity(intent);
            finish();
        }
        else{

            new AlertDialog.Builder(this)
                    .setTitle("Login Failed")
                    .setMessage("Username or Password was incorrect.")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

    }
    public void checkSignUp(View view){
        SUPWEditText = (EditText)findViewById(R.id.passwordS);
        SUEmailEditText = (EditText)findViewById(R.id.emailS);
        SULNEditText = (EditText)findViewById(R.id.lastname);
        SUFNEditText = (EditText)findViewById(R.id.firstname);

        SUEmail= SUEmailEditText.getText().toString();
        SUPassword = SUPWEditText.getText().toString();
        SUFN = SUFNEditText.getText().toString();
        SULN = SULNEditText.getText().toString();

        String usps = SUEmail + " " + SUPassword + " " + SUFN + " " + SULN;
        if(SUPassword.compareTo("") != 0 || SUEmail.compareTo("") != 0 || SUFN.compareTo("") != 0 || SULN.compareTo("") != 0){
            final Intent intent = new Intent(this, Homepage.class);
            new AlertDialog.Builder(this)
                    .setTitle("Account Created")
                    .setMessage("Account Creation Successful.")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(intent);
                            finish();
                        }
                    })
                    /*.setNegativeButton(android.R.string., new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })*/
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            intent.putExtra(EXTRA_MESSAGE, usps);
            startActivity(intent);
            finish();
        }
        else{

            new AlertDialog.Builder(this)
                    .setTitle("Account Creation Failed")
                    .setMessage("Account Creation Failed")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    /*.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })*/
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }



}
