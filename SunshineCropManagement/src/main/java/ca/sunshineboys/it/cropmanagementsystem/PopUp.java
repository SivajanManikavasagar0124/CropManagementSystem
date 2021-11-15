package ca.sunshineboys.it.cropmanagementsystem;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class PopUp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pop_up);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int w = displayMetrics.widthPixels;
        int h = displayMetrics.heightPixels;

        getWindow().setLayout((int)(w*.8),(int)(h*.8));
    }
}
