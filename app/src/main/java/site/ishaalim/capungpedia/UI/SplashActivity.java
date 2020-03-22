package site.ishaalim.capungpedia.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.SharedPref.themesPref;

public class SplashActivity extends AppCompatActivity {
    private Runnable runnable;
    private Handler handler;
    ProgressBar progressBar;
    themesPref themesPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        themesPref = new themesPref(this);
        setThemes();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        changestatusbarColor();
        initUI();
        runProgressbar();
        runSplash();
    }

    private void setThemes(){
        if(themesPref.loadNightModeState()) {
            setTheme(R.style.DarkTheme);
        }
        else  {
            themesPref.setNightModeState(false);
            setTheme(R.style.AppTheme);
        }
    }

    private void changestatusbarColor(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

    }

    private void initUI(){
        progressBar = findViewById(R.id.progress_splash);
    }

    private void runProgressbar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void runSplash(){
        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        };

        handler = new Handler();
        handler.postDelayed(runnable, 500);
    }
}
