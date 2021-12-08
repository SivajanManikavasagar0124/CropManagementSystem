package ca.sunshineboys.it.cropmanagementsystem;
/*
Sivajan Manikavasagar (Team Leader) N01240148
Muhammad Qamar N01344609
Noha Philips N01351336
Tanvir Pahwa N01245843
CENG 322 - RNC/D
CENG 317 - 0NF
 */
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;


import ca.sunshineboys.it.cropmanagementsystem.R;
import ca.sunshineboys.it.cropmanagementsystem.Users;

public class ReviewScreen extends Activity {
    String myLog = "myLog";

    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;

    FrameLayout loadingBar;
    Button reviewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.fragment_review);

        EditText editName = findViewById(R.id.reviewEditTextTextName);
        EditText editPhone = findViewById(R.id.reviewEditTextPhone);
        EditText editEmail = findViewById(R.id.reviewEditTextTextEmailAddress);
        EditText editComment = findViewById(R.id.reviewEditTextTextComment);
        RatingBar ratingBar = findViewById(R.id.simpleRatingBar);
        reviewBtn = findViewById(R.id.reviewbutton);
        loadingBar = findViewById(R.id.loadingbar);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference users = database.getReference().child("reviewUsers");

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(editName.getText().toString())){
                    Toast.makeText(v.getContext(), R.string.blankname, Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(editPhone.getText().toString())){
                    Toast.makeText(v.getContext(), R.string.phoneNoBlank, Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(editEmail.getText().toString())){
                    Toast.makeText(v.getContext(), R.string.emailBlank, Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(editComment.getText().toString())){
                    Toast.makeText(v.getContext(), R.string.commentsBlank, Toast.LENGTH_LONG).show();
                }else {
                    String phone = editPhone.getText().toString().trim();

                    Users user = new Users(editName.getText().toString().trim(), editPhone.getText().toString().trim(), editEmail.getText().toString().trim(), editComment.getText().toString().trim(), ratingBar.getRating());
                    users.child(phone).setValue(user);
                    new TwoTask().execute();
                }
            }
        });

    }
    private class TwoTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            reviewBtn.setEnabled(false);
            inAnimation = new AlphaAnimation(0f, 1f);
            inAnimation.setDuration(200);
            loadingBar.setAnimation(inAnimation);
            loadingBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            outAnimation = new AlphaAnimation(1f, 0f);
            outAnimation.setDuration(200);
            loadingBar.setAnimation(outAnimation);
            loadingBar.setVisibility(View.GONE);
            reviewBtn.setEnabled(true);
            startActivity(new Intent(ReviewScreen.this, SunshineMain.class));
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 0; i < 5; i++) {
                    Log.d(myLog, "Emulating some task.. Step " + i);
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }



}


