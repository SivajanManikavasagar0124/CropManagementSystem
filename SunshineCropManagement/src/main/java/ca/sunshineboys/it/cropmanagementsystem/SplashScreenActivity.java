package ca.sunshineboys.it.cropmanagementsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashScreenActivity.this, SunshineMain.class));
        finish();


    }
}
