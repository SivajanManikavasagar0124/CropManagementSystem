package ca.sunshineboys.it.cropmanagementsystem.ui.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import ca.sunshineboys.it.cropmanagementsystem.R;
import ca.sunshineboys.it.cropmanagementsystem.ui.SoilMoisture.MoistureViewModel;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ImageView sensorPicture;
    TextView sensorText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        //Intialise OBJECTIDS
        sensorPicture = root.findViewById(R.id.connectionImage);
        sensorText = root.findViewById(R.id.connectionText);


        DatabaseReference sensorRef = database.getReference("sensorConnected");
        sensorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()){
                sensorPicture.setImageResource(R.drawable.greenconnection);
                sensorText.setText("Connected");
            }else{ //If snapshot == NULL show disconnected
                sensorPicture.setImageResource(R.drawable.redconnection);
                sensorText.setText("Disconnected");
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });*/
        return root;
    }
}
