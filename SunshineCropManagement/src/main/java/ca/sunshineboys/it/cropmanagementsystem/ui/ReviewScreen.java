package ca.sunshineboys.it.cropmanagementsystem.ui;

import static java.util.regex.Pattern.compile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.regex.Pattern;

import ca.sunshineboys.it.cropmanagementsystem.R;

public class ReviewScreen extends AppCompatActivity  implements View.OnClickListener {

    private Firebase crop;
    private EditText reviewName, reviewEmail, reviewPhone;
    private TextView reviewUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_review);

        crop = Firebase.INSTANCE;


        reviewUser = (Button) findViewById(R.id.reviewbutton);
        reviewUser.setOnClickListener(this);

        reviewName = (EditText) findViewById(R.id.reviewEditTextTextName);
        reviewPhone = (EditText) findViewById(R.id.reviewEditTextPhone);
        reviewEmail = (EditText) findViewById(R.id.reviewEditTextTextEmailAddress);
       // reviewComment =(EditText) findViewById(R.id.reviewEditTextTextComment);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // case R.id.castimage:
            // startActivity(new Intent(this, Login.class));
            // break;
            // case R.id.reviewUser:
            // registerUser();
            //   break;
        }
    }

    private void registerUser() {
        String email = reviewEmail.getText().toString().trim();
        String name = reviewName.getText().toString().trim();
        String phone = reviewPhone.getText().toString().trim();

        if (name.isEmpty()) {
            reviewName.setError(getString(R.string.reviewName));
            reviewName.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            reviewPhone.setError("Phone number is required");
            reviewName.requestFocus();
            return;

        }
        if (email.isEmpty()) {
            reviewEmail.setError(getString(R.string.reviewEmail));
            reviewEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            reviewEmail.setError(getString(R.string.reviewEmail));
            reviewEmail.requestFocus();
            return;

        }}}

/*

                crop.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(name, comment, email, phone);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(Firebase.INSTANCE.getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ReviewScreen.this, R.string.user_successfull, Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(ReviewScreen.this, R.string.try_again, Toast.LENGTH_LONG).show();

                                    }
                                }


                            });
                        } else {
                            Toast.makeText(ReviewScreen.this, R.string.failed_register, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}*/