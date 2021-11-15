package ca.sunshineboys.it.cropmanagementsystem;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
/*
Sivajan Manikavasagar (Team Leader) N01240148
Muhammad Qamar N01344609
Noha Philips N01351336
Tanvir Pahwa N01245843
CENG 322 - RNC/D
CENG 317 - 0NF
 */
public class PopUp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pop_up);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int w = displayMetrics.widthPixels;
        int h = displayMetrics.heightPixels;

        getWindow().setLayout((int)(w*.75),(int)(h*.98));
    }
}
