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
import android.widget.RadioButton;
import android.widget.RatingBar;

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

    public static SettingActivity newInstance() {
        return new SettingActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Settings");
        View root = inflater.inflate(R.layout.setting_fragment, container, false);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("settingsPREF", MODE_PRIVATE);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("fahrenheit");


        fBtn = root.findViewById(R.id.fahrenheitButton);
        cBtn = root.findViewById(R.id.celsiusButton);

        Snackbar tempChange = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.tempChanged, Snackbar.LENGTH_LONG);
        Snackbar tempChange2 = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.tempChanged2, Snackbar.LENGTH_LONG);

        if (fBtn.isChecked()) {
            databaseReference.setValue("1", new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    tempChange.show();
                }
            });
        } else if (cBtn.isChecked()) {
            databaseReference.setValue("0", new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    tempChange2.show();
                }
            });
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