package com.dine_n_wineinc.dine_n_wine;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilePage extends Activity implements Animation.AnimationListener{

    public int BUTTON_ACTIVE_COLOR;
    public int BUTTON_INACTIVE_COLOR;

    Animation profilePictureExitAnim;
    Animation profilePictureEnterAnim;
    ImageView profilePicture;
    ImageView housePicture;
    TextView firstName;
    TextView lastName;
    Button infoButton;
    Button recentButton;
    int swipeCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        //object initilization
        Intent intent = getIntent();
        String firstNameString = intent.getStringExtra(LandingActivity.EXTRA_FIRSTNAME);
        String lastNameString = intent.getStringExtra(LandingActivity.EXTRA_LASTNAME);


        //layout object initilizations
        profilePicture = (ImageView)findViewById(R.id.ProfilePicture);
        housePicture = (ImageView)findViewById(R.id.HousePicture);
        infoButton = (Button)findViewById(R.id.InfoButton);
        recentButton = (Button)findViewById(R.id.RecentButton);
        BUTTON_ACTIVE_COLOR = getResources().getColor(R.color.buttonActive);
        BUTTON_INACTIVE_COLOR = getResources().getColor(R.color.buttonInactive);
        firstName = (TextView)findViewById(R.id.ProfilePageFirstName);
        lastName = (TextView)findViewById(R.id.ProfilePageLastName);


        //animation initilizations
        //profile picture goes from default position to off screen
        profilePictureExitAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.profilepictureexit);
        profilePictureExitAnim.setAnimationListener(this);
        //profile picutre comes back on screen to default position
        profilePictureEnterAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.profile_picture_enter);
        profilePictureEnterAnim.setAnimationListener(this);

        //initialize listeners for swiping on home picture
        housePicture.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop() {
                Toast.makeText(getApplicationContext(), "top", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {
                if (swipeCounter > 0)
                    swipeCounter--;
                if (profilePicture.getVisibility() == View.INVISIBLE && swipeCounter == 0)
                    profilePicture.startAnimation(profilePictureEnterAnim);
            }

            public void onSwipeLeft() {
                swipeCounter++;
                if (profilePicture.getVisibility() == View.VISIBLE)
                    profilePicture.startAnimation(profilePictureExitAnim);
            }

            public void onSwipeBottom() {
                Toast.makeText(getApplicationContext(), "bottom", Toast.LENGTH_SHORT).show();
            }


        });
        if(firstNameString != null && lastNameString != null){
            firstName.setText(firstNameString);
            lastName.setText(lastNameString);
        }

    }
    public void showRecent(View view){
        recentButton.setBackgroundColor(BUTTON_ACTIVE_COLOR);
        infoButton.setBackgroundColor(BUTTON_INACTIVE_COLOR);
    }
    public void showInfo(View view){
        recentButton.setBackgroundColor(BUTTON_INACTIVE_COLOR);
        infoButton.setBackgroundColor(BUTTON_ACTIVE_COLOR);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(animation == profilePictureExitAnim)
            profilePicture.setVisibility(View.INVISIBLE);
        else profilePicture.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
