package ca.sunshineboys.it.cropmanagementsystem;
/*
Sivajan Manikavasagar (Team Leader) N01240148
Muhammad Qamar N01344609
Noha Philips N01351336
Tanvir Pahwa N01245843
CENG 322 - RNC/D
CENG 317 - 0NF
 */

//The design patterns used in the code are model view
import android.Manifest;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.HashMap;
import java.util.Map;


/*
KISS PRINCIPLE, keep it simple and stupid design principle
 */
public class SunshineMain extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private int BLUETOOTHPERMISSIONCODE = 1;
    ImageView AboutusExit;
    Dialog aboutUsPop;
    Dialog reviewPop;
    ImageView ReviewExit;
    Button reviewSubmit;
    RatingBar ratingBar;
    EditText name;
    EditText email;
    EditText comment;
    EditText phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            AlertDialog.Builder DIANoBT = new AlertDialog.Builder(this);
            DIANoBT.setTitle(R.string.deviceblue);
            DIANoBT.setIcon(R.drawable.alert_icon);
            DIANoBT.setMessage(R.string.devicesupportblue);
            DIANoBT.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) { }
            });
            AlertDialog alertDialog = DIANoBT.create();
            alertDialog.show();
        }
        if (!bluetoothAdapter.isEnabled()) {
            requestBluetoothPerms();
        }

     toolbar.setTitleTextColor(getResources().getColor(R.color.Black));
    toolbar.getOverflowIcon().setTint(ContextCompat.getColor(this, R.color.Black));
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_Home, R.id.nav_airHumidity, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_waterlevel, R.id.nav_autonomous)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);


        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        reviewPop = new Dialog(this);
        aboutUsPop = new Dialog(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this, R.string.settingsfragmentopen, Toast.LENGTH_SHORT).show();
                SettingActivity settingActivity = new SettingActivity();
                SunshineMain.this.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, settingActivity).addToBackStack(null).commit();
                return true;
            case R.id.bugreport_settings:
                //Toast.makeText(this, R.string.placeholder, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SunshineMain.this, PopUp.class));
                return true;
            case R.id.sensor_settings:
                Toast.makeText(this, R.string.openingbluetooth, Toast.LENGTH_SHORT).show();
                if (ContextCompat.checkSelfPermission(SunshineMain.this, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED){
                    startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
                } else{
                    requestBluetoothPerms();
                }
                return true;
            case R.id.aboutus_settings:
                aboutUsPop.setContentView(R.layout.aboutuspopup);
                aboutUsPop.show();
                return true;
            case R.id.support_settings:
                startActivity(new Intent(SunshineMain.this, ReviewScreen.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }


    private void requestBluetoothPerms() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.BLUETOOTH)){
            new AlertDialog.Builder(this)
                .setTitle(R.string.requiredperm)
                    .setIcon(R.drawable.alert_icon)
                    .setMessage(R.string.permissionrequiredforblue)
                    .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(SunshineMain.this, new String[]{Manifest.permission.BLUETOOTH}, BLUETOOTHPERMISSIONCODE);
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          dialog.dismiss();
                        }
                    })
                .create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH}, BLUETOOTHPERMISSIONCODE);
        }


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == BLUETOOTHPERMISSIONCODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, R.string.permissiongrant, Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, R.string.permissiondeny, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void onBackPressed(){
        AlertDialog.Builder DIA1 = new AlertDialog.Builder(this);
        DIA1.setTitle(R.string.exitapp);
        DIA1.setIcon(R.drawable.alert_icon);
        DIA1.setMessage(R.string.dialogtitle);
        DIA1.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SunshineMain.this.finish();
            }
        });
        DIA1.setNegativeButton(R.string.stay, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = DIA1.create();
        alertDialog.show();

    }

    public void ReviewPopupExit(View view) { reviewPop.dismiss(); }

    public void AboutUsPopupExit(View view) {
        aboutUsPop.dismiss();
    }
}