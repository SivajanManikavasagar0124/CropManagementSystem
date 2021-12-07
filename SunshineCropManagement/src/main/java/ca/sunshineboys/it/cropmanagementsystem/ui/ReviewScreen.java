package ca.sunshineboys.it.cropmanagementsystem.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import ca.sunshineboys.it.cropmanagementsystem.R;
import ca.sunshineboys.it.cropmanagementsystem.Users;

public class ReviewScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.fragment_review);

        EditText editName = findViewById(R.id.reviewEditTextTextName);
        EditText editPhone = findViewById(R.id.reviewEditTextPhone);
        EditText editEmail = findViewById(R.id.reviewEditTextTextEmailAddress);
        EditText editComment = findViewById(R.id.reviewEditTextTextComment);
        RatingBar ratingBar = findViewById(R.id.simpleRatingBar);
        Button reviewBtn = findViewById(R.id.reviewbutton);
        DADusers dad = new DADusers();
        reviewBtn.setOnClickListener(v->{

            Users usr = new Users(editName.getText().toString(),editPhone.getText().toString(),editEmail.getText().toString(),editComment.getText().toString(),ratingBar.getRating());
            dad.add(usr).addOnSuccessListener(suc->{

                Toast.makeText(this, "Review went through!", Toast.LENGTH_SHORT).show();

            }).addOnFailureListener(er->
            {
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
                }
                );


    }
}