package ca.sunshineboys.it.cropmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Switch;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;

/*
Sivajan Manikavasagar (Team Leader) N01240148
Muhammad Qamar N01344609
Noha Philips N01351336
Tanvir Pahwa N01245843
CENG 322 - RNC/D
CENG 317 - 0NF
 */

public class SettingActivity extends Fragment {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private SettingViewModel mViewModel;


    RadioButton fBtn;
    RadioButton cBtn;
    Switch portlock;
    Switch notifcationswit;
    Switch cBlind;
    Button saveButton;

    public static SettingActivity newInstance() {
        return new SettingActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.settings);
        View root = inflater.inflate(R.layout.setting_fragment, container, false);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("settingsPREF", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("fahrenheit");
        RadioGroup rgUnits;

        portlock = root.findViewById(R.id.portlock);
        notifcationswit = root.findViewById(R.id.notficswitch);
        cBlind = root.findViewById(R.id.colourblindswitch);

        fBtn = root.findViewById(R.id.fahrenheitButton);
        cBtn = root.findViewById(R.id.celsiusButton);
        saveButton = root.findViewById(R.id.savesettingsbutton);

        Snackbar tempChange = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.tempChanged, Snackbar.LENGTH_LONG);
        Snackbar tempChange2 = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.tempChanged2, Snackbar.LENGTH_LONG);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (portlock.isChecked()){
                    prefEditor.putBoolean("PortLock", true);
                    prefEditor.commit();
                }else{
                    prefEditor.putBoolean("PortLock", false);
                    prefEditor.commit();
                }
                if (notifcationswit.isChecked()){
                    prefEditor.putBoolean("NotificationsOn", true);
                    prefEditor.commit();
                }else{
                    prefEditor.putBoolean("NotificationsOn", false);
                    prefEditor.commit();
                }
                if (cBlind.isChecked()){
                    prefEditor.putBoolean("ColourBlind", true);
                    prefEditor.commit();
                }else{
                    prefEditor.putBoolean("ColourBlind", false);
                    prefEditor.commit();
                }

            }
        });



        if (fBtn.isChecked()) {
            prefEditor.putBoolean("PrefUnits", false);
            databaseReference.setValue("1", new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    tempChange.show();
                }
            });
            prefEditor.commit();
        } else if (cBtn.isChecked()) {
            prefEditor.putBoolean("PrefUnits", true);
            databaseReference.setValue("0", new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    tempChange2.show();
                }
            });
            prefEditor.commit();
        }

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        // TODO: Use the ViewModel
    }



}