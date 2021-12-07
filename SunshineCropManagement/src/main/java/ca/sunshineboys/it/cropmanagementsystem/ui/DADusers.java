package ca.sunshineboys.it.cropmanagementsystem.ui;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ca.sunshineboys.it.cropmanagementsystem.Users;

public class DADusers {

    private DatabaseReference databaseReference;
    public DADusers(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Users.class.getSimpleName());
    }

    public Task<Void> add(Users usr){

       return databaseReference.push().setValue(usr);
    }
}
