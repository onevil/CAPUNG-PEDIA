package site.ishaalim.capungpedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import site.ishaalim.capungpedia.SharedPref.SharedPref;

public class SettingActivity extends AppCompatActivity {

    private Switch swDarkmode;
    SharedPref sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);
        checkTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initUI();
        setSwitch();
        setEvents();

    }

    private void initUI() {
        swDarkmode = findViewById(R.id.myswitch);
    }

    private void setEvents() {
        swDarkmode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    sharedpref.setNightModeState(true);
                    restartApp();

                }else {
                    sharedpref.setNightModeState(false);
                    restartApp();
                }
            }
        });
    }

    private void checkTheme(){

        if(sharedpref.loadNightModeState()==true) {
            setTheme(R.style.DarkTheme);
        }
        else  {
            sharedpref.setNightModeState(false);
            setTheme(R.style.AppTheme);
        }

    }

    private void setSwitch() {
        if (sharedpref.loadNightModeState()==true) {
            swDarkmode.setChecked(true);
        }
    }

    private void restartApp(){
        recreate();
    }


}
