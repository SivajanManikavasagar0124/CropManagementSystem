package ca.sunshineboys.it.cropmanagementsystem.ui.AirHumidity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ca.sunshineboys.it.cropmanagementsystem.Post;
import ca.sunshineboys.it.cropmanagementsystem.R;
/*
Sivajan Manikavasagar (Team Leader) N01240148
Muhammad Qamar N01344609
Noha Philips N01351336
Tanvir Pahwa N01245843
CENG 322 - RNC/D
CENG 317 - 0NF
 */
public class AirHumidity extends Fragment {

 //   private DatabaseReference databaseReference;

    private HumidityViewModel homeViewModel;

    Button humidityMeasure;
    TextView humidityText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HumidityViewModel.class);
        View root = inflater.inflate(R.layout.fragment_airhumidity, container, false);

     //   databaseReference = FirebaseDatabase.getInstance().getReference();

        humidityMeasure = root.findViewById(R.id.airHumidityButton);
        humidityText = root.findViewById(R.id.humidityLevelText);

        Snackbar sensorError = Snackbar.make(getActivity().findViewById(android.R.id.content), "Unable to update sensor please try again.",Snackbar.LENGTH_SHORT);
        Snackbar sensorUpdating = Snackbar.make(getActivity().findViewById(android.R.id.content), "Currently updating data from the sensor.",Snackbar.LENGTH_SHORT);

        humidityMeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                ValueEventListener postListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                      //  Post post = snapshot.getValue(Post.class);
                        int humidity = snapshot.child("temperatureHumidity").getValue(Integer.class);
                        humidityText.setText(humidity);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };
               //mPostReference.addValueEventListener(postListener);
               */

            }
        });

        return root;
    }


}