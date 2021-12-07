package ca.sunshineboys.it.cropmanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/*
Sivajan Manikavasagar (Team Leader) N01240148
Muhammad Qamar N01344609
Noha Philips N01351336
Tanvir Pahwa N01245843
CENG 322 - RNC/D
CENG 317 - 0NF
 */
public class TaskSchedulerActivity extends Fragment {

    FloatingActionButton fab;
    long dateSelected;
    private TaskSchedulerViewModel mViewModel;


    public static TaskSchedulerActivity newInstance() {
        return new TaskSchedulerActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_scheduler_fragment, container, false);
        CalendarView CV = view.findViewById(R.id.calendarView);
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CV.isSelected()){
                    AlertDialog.Builder DIACVError = new AlertDialog.Builder(getActivity());
                    DIACVError.setTitle("No Date Selected");
                    DIACVError.setIcon(R.drawable.tasksche);
                    DIACVError.setMessage("Select a date first!");
                    DIACVError.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) { }
                    });
                    DIACVError.create();
                    DIACVError.show();


                }else {

                    AlertDialog.Builder DIACVChecker = new AlertDialog.Builder(getActivity());
                    DIACVChecker.setTitle("Add Calendar Date?");
                    DIACVChecker.setIcon(R.drawable.tasksche);
                    DIACVChecker.setMessage("Add the selected Calendar date?");
                    DIACVChecker.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dateSelected = CV.getDate();
                            Snackbar dateSnack = Snackbar.make(getActivity().findViewById(android.R.id.content),"Added date", Snackbar.LENGTH_SHORT);
                            dateSnack.show();

                        }
                    })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    DIACVChecker.create();
                    DIACVChecker.show();

                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TaskSchedulerViewModel.class);
        // TODO: Use the ViewModel
    }

}