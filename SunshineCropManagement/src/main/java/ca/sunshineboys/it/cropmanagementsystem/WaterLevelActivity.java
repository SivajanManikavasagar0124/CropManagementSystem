package ca.sunshineboys.it.cropmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

//import com.gelitenight.waveview.library.WaveView;

/*
Sivajan Manikavasagar (Team Leader) N01240148
Muhammad Qamar N01344609
Noha Philips N01351336
Tanvir Pahwa N01245843
CENG 322 - RNC/D
CENG 317 - 0NF
 */
public class WaterLevelActivity extends Fragment {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private WaterLevelViewModel mViewModel;

    TextView waterText;
  //  WaveView waveView;
    public static WaterLevelActivity newInstance() {
        return new WaterLevelActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Water Level");
      View root = inflater.inflate(R.layout.water_level_fragment, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("waterDistance");

        waterText = root.findViewById(R.id.waterlevelPercent);

        getData();
            return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WaterLevelViewModel.class);
        // TODO: Use the ViewModel
    }

    private void getData() {
        Snackbar sensorError = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.errorUpdating,Snackbar.LENGTH_SHORT);
        Snackbar sensorUpdating = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.updatingData,Snackbar.LENGTH_SHORT);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double value = snapshot.getValue(Double.class);
                waterText.setText(value + "cm");
                sensorUpdating.show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                sensorError.show();
            }
        });
    }

}