package ca.sunshineboys.it.cropmanagementsystem.ui.AirHumidity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private HumidityViewModel homeViewModel;

    Button humidityMeasure;
    TextView humidityText;
    ProgressBar humidBar;

    float val;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HumidityViewModel.class);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.airhumid);
        View root = inflater.inflate(R.layout.fragment_airhumidity, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("temperatureHumidity");

        humidityMeasure = root.findViewById(R.id.airHumidityButton);
        humidityText = root.findViewById(R.id.humidityLevelText);
        humidBar = root.findViewById(R.id.progressBarAirHumid);

        getData();

        humidityMeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        return root;
    }

    private void getData() {
        Snackbar sensorError = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.errorUpdating,Snackbar.LENGTH_SHORT);
        Snackbar sensorUpdating = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.updatingData,Snackbar.LENGTH_SHORT);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double value = snapshot.getValue(Double.class);
                humidityText.setText(value + "%");
                humidBar.setProgress((int)value);
                sensorUpdating.show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                sensorError.show();
            }
        });
    }



}