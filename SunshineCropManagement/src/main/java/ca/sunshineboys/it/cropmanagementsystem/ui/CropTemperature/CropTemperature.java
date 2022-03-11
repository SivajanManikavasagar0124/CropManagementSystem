package ca.sunshineboys.it.cropmanagementsystem.ui.CropTemperature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import ca.sunshineboys.it.cropmanagementsystem.R;
/*
Sivajan Manikavasagar (Team Leader) N01240148
Muhammad Qamar N01344609
Noha Philips N01351336
Tanvir Pahwa N01245843
CENG 322 - RNC/D
CENG 317 - 0NF
 */
public class CropTemperature extends Fragment {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference unit;

    private TemperatureViewModel galleryViewModel;

    TextView tempText;
    Button tempButt;

    float val;
    float fahr;
    float fahren;

    Long num = Long.valueOf(1);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(TemperatureViewModel.class);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.croptemp);
        View root = inflater.inflate(R.layout.fragment_croptemperature, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("absoluteTemperature");
        unit = firebaseDatabase.getReference("fahrenheit");

        tempButt = root.findViewById(R.id.NohaButton);
        tempText = root.findViewById(R.id.tempLevelText);


        getData();

        tempButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
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
                tempText.setText(value + "c");
                sensorUpdating.show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                sensorError.show();
            }
        });
    }

}