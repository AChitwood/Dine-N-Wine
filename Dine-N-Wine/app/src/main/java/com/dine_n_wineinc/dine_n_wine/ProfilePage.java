package com.dine_n_wineinc.dine_n_wine;

import android.app.ActionBar;
import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class ProfilePage extends Activity implements Animation.AnimationListener{

    Animation profilePictureExitAnim;
    Animation profilePictureEnterAnim;
    ImageView profilePicture;
    ImageView housePicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        profilePicture = (ImageView)findViewById(R.id.ProfilePicture);
        housePicture = (ImageView)findViewById(R.id.HousePicture);
        profilePictureExitAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.profilepictureexit);
        profilePictureExitAnim.setAnimationListener(this);
        profilePictureEnterAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.profile_picture_enter);
        profilePictureEnterAnim.setAnimationListener(this);
        housePicture.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop() {
                Toast.makeText(getApplicationContext(), "top", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {
                Toast.makeText(getApplicationContext(), "right", Toast.LENGTH_SHORT).show();
                if (profilePicture.getVisibility() == View.INVISIBLE)
                    profilePicture.startAnimation(profilePictureEnterAnim);
            }

            public void onSwipeLeft() {
                Toast.makeText(getApplicationContext(), "left", Toast.LENGTH_SHORT).show();
                if (profilePicture.getVisibility() == View.VISIBLE)
                    profilePicture.startAnimation(profilePictureExitAnim);
            }

            public void onSwipeBottom() {
                Toast.makeText(getApplicationContext(), "bottom", Toast.LENGTH_SHORT).show();
            }


        });
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
