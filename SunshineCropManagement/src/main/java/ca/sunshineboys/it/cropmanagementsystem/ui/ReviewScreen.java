package ca.sunshineboys.it.cropmanagementsystem.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import ca.sunshineboys.it.cropmanagementsystem.R;

public class ReviewScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.fragment_review);

        EditText editName = findViewById(R.id.reviewEditTextTextName);
        EditText editPhone = findViewById(R.id.reviewEditTextPhone);
        EditText editEmail = findViewById(R.id.reviewEditTextTextEmailAddress);
        EditText editComment = findViewById(R.id.reviewEditTextTextComment);
        Button reviewBtn = findViewById(R.id.reviewbutton);

        reviewBtn.setOnClickListener(v->{



                }
                );


    }
}