package ca.sunshineboys.it.cropmanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
/*
Sivajan Manikavasagar (Team Leader) N01240148
Muhammad Qamar N01344609
Noha Philips N01351336
Tanvir Pahwa N01245843
CENG 322 - RNC/D
CENG 317 - 0NF
 */
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences =
                       getSharedPreferences("onBoardingAct", MODE_PRIVATE);
                // Check if we need to display our OnboardingSupportFragment
                if (!sharedPreferences.getBoolean("COMPLETED_ONBOARDING", false)) {
                    // The user hasn't seen the OnboardingSupportFragment yet, so show it
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("COMPLETED_ONBOARDING", true);
                    editor.commit();

                    startActivity(new Intent(SplashScreenActivity.this, OnboardingActivity.class));
                    finish();
                } else{
                    Intent i = new Intent(SplashScreenActivity.this, SunshineMain.class);
                    startActivity(i);
                    //close this activity
                    finish();
                }
            }
        }, 3000);
    }
}
