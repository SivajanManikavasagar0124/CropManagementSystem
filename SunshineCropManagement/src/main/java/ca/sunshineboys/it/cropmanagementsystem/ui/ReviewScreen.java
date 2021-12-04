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
    private EditText registerName, registerEmail, registerAge, registerPass, registercPass,registerPhone;
    private ImageView castle;
    private TextView registerUser;
    private static final Pattern PASSWORD_PATTERN =
            compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&=])" +    //at least 1 special character
                    ".{8,}" +               //at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_review);

        crop = Firebase.INSTANCE;



        registerUser = (Button) findViewById(R.id.reviewbutton);
        registerUser.setOnClickListener(this);

        registerName = (EditText) findViewById(R.id.register_name);
        registerAge = (EditText) findViewById(R.id.register_Age);
        registerPhone = (EditText) findViewById(R.id.register_phone);
        registerEmail = (EditText) findViewById(R.id.register_email);
        registerPass = (EditText) findViewById(R.id.register_password);
        registercPass = (EditText) findViewById(R.id.register_confirm_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.castimage:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.registerUser:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = registerEmail.getText().toString().trim();
        String password = registerPass.getText().toString().trim();
        String name = registerName.getText().toString().trim();
        String age = registerAge.getText().toString().trim();
        String phone = registerPhone.getText().toString().trim();
        String confirmpass = registercPass.getText().toString().trim();

        if (name.isEmpty()) {
            registerName.setError(getString(R.string.reviewName));
            registerName.requestFocus();
            return;
        }
        if (age.isEmpty()) {
            registerName.setError(getString(R.string.age_req));
            registerName.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            registerPhone.setError("Phone number is required");
            registerName.requestFocus();
            return;


        }
        if (email.isEmpty()) {
            registerEmail.setError(getString(R.string.email_req));
            registerEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            registerEmail.setError(getString(R.string.try_email));
            registerEmail.requestFocus();
            return;

        }
        if (password.isEmpty()) {
            registerPass.setError(getString(R.string.pass_req));
            registerPass.requestFocus();
            return;
        }
        else if (!PASSWORD_PATTERN.matcher(password).matches()){
            registerPass.setError("Password must contain:" +
                    " One digit, One Capital Letter, One special character, A minimum of four characters");
            registerPass.requestFocus();
            return;

        } else {
            registerPass.setError(null);


        }
        if (confirmpass.isEmpty()) {
            registercPass.setError("Password Confirmation Required");
            registercPass.requestFocus();
            return;
        }
        if (!password.equals(confirmpass)) {
            registercPass.setError("Confirmation Password Must Match");
            registercPass.requestFocus();
            return;
        }

       /* crop.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(name, age, email, phone);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(Firebase.INSTANCE.getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ReviewScreen.this,R.string.user_successfull, Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(ReviewScreen.this, R.string.try_again, Toast.LENGTH_LONG).show();

                                    }
                                }


                            });
                        }else{
                            Toast.makeText(ReviewScreen.this,R.string.failed_register, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }*/
}