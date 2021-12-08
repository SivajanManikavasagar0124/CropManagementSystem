package ca.sunshineboys.it.cropmanagementsystem.ui.Home;
/*
Sivajan Manikavasagar (Team Leader) N01240148
Muhammad Qamar N01344609
Noha Philips N01351336
Tanvir Pahwa N01245843
CENG 322 - RNC/D
CENG 317 - 0NF
 */
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Duration;
import java.util.HashMap;

import ca.sunshineboys.it.cropmanagementsystem.R;
import ca.sunshineboys.it.cropmanagementsystem.SettingActivity;
import ca.sunshineboys.it.cropmanagementsystem.SunshineMain;
import ca.sunshineboys.it.cropmanagementsystem.TaskSchedulerActivity;
import ca.sunshineboys.it.cropmanagementsystem.WaterLevelActivity;
import ca.sunshineboys.it.cropmanagementsystem.ui.AirHumidity.AirHumidity;
import ca.sunshineboys.it.cropmanagementsystem.ui.CropTemperature.CropTemperature;
import ca.sunshineboys.it.cropmanagementsystem.ui.SoilMoisture.MoistureViewModel;
import ca.sunshineboys.it.cropmanagementsystem.ui.SoilMoisture.SoilMoisture;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ImageView sensorPicture;
    TextView sensorText;
    Button waterNow;

    ImageView soilMoisture;
    ImageView taskSched;
    ImageView cropTemp;
    ImageView airHumidity;
    ImageView waterLevel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Intialise OBJECTIDS
        sensorPicture = root.findViewById(R.id.connectionImage);
        sensorText = root.findViewById(R.id.connectionText);
        waterNow = root.findViewById(R.id.waternowButton);


        //Quick Menu Items
        soilMoisture = root.findViewById(R.id.soilMoistImage);
        taskSched = root.findViewById(R.id.taskSchedImage);
        cropTemp = root.findViewById(R.id.cropTempImage);
        airHumidity = root.findViewById(R.id.airHumidityImage);
        waterLevel = root.findViewById(R.id.waterLevelImage);

        DatabaseReference waterNowRef = database.getReference("Watering");
        DatabaseReference sensorRef = database.getReference("sensorConnected");

        sensorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()){
                sensorPicture.setImageResource(R.drawable.greenconnection);
                sensorText.setText(R.string.connected);
            }else{ //If snapshot == NULL show disconnected
                sensorPicture.setImageResource(R.drawable.redconnection);
                sensorText.setText(R.string.disconnected);
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Snackbar nowWatering = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.nowWatering, Snackbar.LENGTH_LONG);
        Snackbar WaterERROR = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.WaterError, Snackbar.LENGTH_LONG);
        Snackbar completeWater = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.completeWater,Snackbar.LENGTH_SHORT);
        Snackbar WATERERROR2 = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.watererror2,Snackbar.LENGTH_SHORT);
        waterNow.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            waterNowRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    if (snapshot.exists()){
                                                        WaterERROR.show();
                                                    }else{

                                                        sensorRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                if (snapshot.exists()){
                                                                    waterNowRef.setValue("1", new DatabaseReference.CompletionListener() {
                                                                        @Override
                                                                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                                                            nowWatering.show();
                                                                        }
                                                                    });
                                                                    new Handler().postDelayed(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            waterNowRef.setValue(null, new DatabaseReference.CompletionListener() {
                                                                                @Override
                                                                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                                                                    completeWater.show();
                                                                                }
                                                                            });
                                                                        }
                                                                    },5000 );
                                                                }else{
                                                                    AlertDialog.Builder DIAWATERRROR = new AlertDialog.Builder(getActivity());
                                                                    DIAWATERRROR.setTitle(R.string.sensorNotConnected);
                                                                    DIAWATERRROR.setIcon(R.drawable.alert_icon);
                                                                    DIAWATERRROR.setMessage(R.string.sensorNotConnected2);
                                                                    DIAWATERRROR.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                            WATERERROR2.show();
                                                                        }
                                                                    });
                                                                    AlertDialog alertDialog = DIAWATERRROR.create();
                                                                    alertDialog.show();
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });



                                                    }


                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });


                                        }
                                    });

        //Quick Menu Listeners
        soilMoisture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new SoilMoisture());
                fragmentTransaction.commit();
            }
        });

        taskSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new TaskSchedulerActivity());
                fragmentTransaction.commit();
            }
        });


        cropTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new CropTemperature());
                fragmentTransaction.commit();
            }
        });

        airHumidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new AirHumidity());
                fragmentTransaction.commit();
            }
        });


        waterLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new WaterLevelActivity());
                fragmentTransaction.commit();
            }
        });



        return root;
    }
}
