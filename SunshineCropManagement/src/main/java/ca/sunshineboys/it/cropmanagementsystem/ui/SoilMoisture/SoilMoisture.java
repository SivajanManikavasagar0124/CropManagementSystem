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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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

    private MoistureViewModel slideshowViewModel;
    private Button soilBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


/*
        soilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Sensor is measuring the moisture",Toast.LENGTH_LONG).show();
            }
        });
*/
        slideshowViewModel =
                new ViewModelProvider(this).get(MoistureViewModel.class);
        View root = inflater.inflate(R.layout.fragment_soilmoisture, container, false);
       // soilBtn = root.findViewById(R.id.soilbutton);
        //final TextView textView = root.findViewById(R.id.soildescription);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s)
            {
               // textView.setText(s);
            }
        });
        return root;


    }
}