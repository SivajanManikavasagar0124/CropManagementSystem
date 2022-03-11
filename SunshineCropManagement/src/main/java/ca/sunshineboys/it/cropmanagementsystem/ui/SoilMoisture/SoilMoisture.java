package ca.sunshineboys.it.cropmanagementsystem.ui.SoilMoisture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

import ca.sunshineboys.it.cropmanagementsystem.R;

/*
Sivajan Manikavasagar (Team Leader) N01240148
Muhammad Qamar N01344609
Noha Philips N01351336
Tanvir Pahwa N01245843
CENG 322 - RNC/D
CENG 317 - 0NF
 */
public class SoilMoisture extends Fragment {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private MoistureViewModel slideshowViewModel;
    private Button soilBtn;

    TextView soilText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(MoistureViewModel.class);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.soilmoisture);
        View root = inflater.inflate(R.layout.fragment_soilmoisture, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("soilMoisture");

        soilBtn = root.findViewById(R.id.soilbutton);
        soilText = root.findViewById(R.id.soilunits);

        getData();

        soilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s)
            {
               // textView.setText(s);
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
                if (value == 1) {
                    soilText.setText("Y");

                } else if(value == 0) {
                    soilText.setText("N");

                }else{
                    soilText.setText("ERROR");
                }

                sensorUpdating.show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                sensorError.show();
            }
        });
    }
}