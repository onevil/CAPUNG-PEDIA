package site.ishaalim.capungpedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.widget.ImageButton;

import site.ishaalim.capungpedia.Beranda.FragmentBeranda;
import site.ishaalim.capungpedia.Evaluasi.IntroEvaluasiFragment;
import site.ishaalim.capungpedia.Glosarium.FragmentGlosarium;
import site.ishaalim.capungpedia.IdentifikasiCapung.IdentifikasiCapungFragment;
import site.ishaalim.capungpedia.MengenalCapung.FragmentMengenalCapung;
import site.ishaalim.capungpedia.ayoPengamatan.AyoPengamatanFragment;
import site.ishaalim.capungpedia.ayoPengamatan.DetailPengamatanFragment;
import site.ishaalim.capungpedia.ayoPengamatan.JudulPengamatanFragment;
import site.ishaalim.capungpedia.mengenalDesa.FragmentMengenalDesa;
import site.ishaalim.capungpedia.panduanIdentifikasi.FragmentPanduanIdentifikasi;
import site.ishaalim.capungpedia.panduanPengamatan.FragmentPanduanPengamatan;
import site.ishaalim.capungpedia.petunjukPenggunaan.FragmentPetunjukPenggunaan;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DetailPengamatanFragment.DetailPengamatanListener {


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    private ImageButton buttonNav;

    private DetailPengamatanFragment detailPengamatanFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        checktheme();


        detailPengamatanFragment = new DetailPengamatanFragment();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        setUpDrawer();


        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new FragmentBeranda()).commit();
            navigationView.setCheckedItem(R.id.beranda);
        }


    }



    private void setUpDrawer() {
        navigationView.setNavigationItemSelectedListener(this);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    public void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.beranda:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentBeranda()).commit();
                uncheckNavItem();
                navigationView.setCheckedItem(R.id.beranda);
                break;
            case R.id.mengenal_capung:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentMengenalCapung()).commit();
                uncheckNavItem();
                navigationView.setCheckedItem(R.id.pendahuluan);
                break;
            case R.id.mengenal_kawasan_desa:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentMengenalDesa()).commit();
                uncheckNavItem();
                navigationView.setCheckedItem(R.id.mengenal_kawasan_desa);
                break;
            case R.id.panduan_pengamatan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentPanduanPengamatan()).commit();
                uncheckNavItem();
                break;
            case R.id.panduan_Identifikasi:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentPanduanIdentifikasi()).commit();
                uncheckNavItem();
                navigationView.setCheckedItem(R.id.pendahuluan);
                break;

            case R.id.identifikasi_capung:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new IdentifikasiCapungFragment()).commit();
                uncheckNavItem();
                navigationView.setCheckedItem(R.id.identifikasi_capung);
                break;

            case R.id.ayo_pengamatan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AyoPengamatanFragment()).commit();
                uncheckNavItem();
                navigationView.setCheckedItem(R.id.ayo_pengamatan);
                break;

            case R.id.evaluasi:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new IntroEvaluasiFragment()).commit();
                uncheckNavItem();
                navigationView.setCheckedItem(R.id.evaluasi);
                break;

            case R.id.petunjuk_penggunaan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentPetunjukPenggunaan()).commit();
                uncheckNavItem();
                navigationView.setCheckedItem(R.id.petunjuk_penggunaan);
                break;

            case R.id.glosarium:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FragmentGlosarium()).commit();
                uncheckNavItem();
                navigationView.setCheckedItem(R.id.glosarium);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void uncheckNavItem() {
        int navSize = navigationView.getMenu().size();
        for (int i = 0; i < navSize; i++){
            navigationView.getMenu().getItem(i).setCheckable(false);
        }
    }

    private void initUI() {

        drawerLayout = findViewById(R.id.dl_main);
        navigationView = findViewById(R.id.navigation_view);
        buttonNav = findViewById(R.id.btn_drawer);
    }

    public void checktheme() {
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

    @Override
    public void addArraylist(String namapengamat, String habitat, String cuaca, String aktifiktas, String deskripsi, String hasil, Uri imageUri) {
        JudulPengamatanFragment judulPengamatanFragment = (JudulPengamatanFragment)getSupportFragmentManager().findFragmentByTag("judulPengamatan");
        judulPengamatanFragment.insertArray(namapengamat, habitat, cuaca, aktifiktas, deskripsi, hasil, imageUri);
    }
}
