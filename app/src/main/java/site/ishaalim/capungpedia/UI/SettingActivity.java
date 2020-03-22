package site.ishaalim.capungpedia.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.SharedPref.themesPref;

public class SettingActivity extends AppCompatActivity {

    private Switch swDarkmode;
    themesPref sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new themesPref(this);
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
