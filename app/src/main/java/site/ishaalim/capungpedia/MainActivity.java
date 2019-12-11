package site.ishaalim.capungpedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import androidx.fragment.app.Fragment;
import site.ishaalim.capungpedia.Beranda.FragmentBeranda;
import site.ishaalim.capungpedia.Pendahuluan.FragmentPendahuluan;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    private ImageButton buttonNav;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        checktheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        navigationView.setNavigationItemSelectedListener(this);


        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);

        drawerLayout.addDrawerListener(drawerToggle);

        drawerToggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new FragmentBeranda()).commit();
            navigationView.setCheckedItem(R.id.beranda);
        }


        /*buttonNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });*/
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.beranda:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentBeranda()).commit();
                break;
            case R.id.pendahuluan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentPendahuluan()).commit();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initUI() {

        drawerLayout = findViewById(R.id.dl_main);
        navigationView = findViewById(R.id.navigation_view);
        buttonNav = findViewById(R.id.btn_drawer);
    }

    private void checktheme() {
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }else {
            setTheme(R.style.AppTheme);
        }
    }

    public void restartApp() {
        Intent i = new Intent(getApplicationContext(), Settings.class);
        startActivity(i);
        finish();
    }
}
