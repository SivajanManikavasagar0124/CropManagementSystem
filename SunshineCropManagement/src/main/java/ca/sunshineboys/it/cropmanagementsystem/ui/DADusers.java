package ca.sunshineboys.it.cropmanagementsystem.ui;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ca.sunshineboys.it.cropmanagementsystem.Users;
/*
Sivajan Manikavasagar (Team Leader) N01240148
Muhammad Qamar N01344609
Noha Philips N01351336
Tanvir Pahwa N01245843
CENG 322 - RNC/D
CENG 317 - 0NF
 */
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
