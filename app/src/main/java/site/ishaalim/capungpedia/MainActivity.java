package site.ishaalim.capungpedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.widget.ImageButton;

import maes.tech.intentanim.CustomIntent;
import site.ishaalim.capungpedia.Beranda.FragmentBeranda;
import site.ishaalim.capungpedia.Evaluasi.EvaluasiFragment;
import site.ishaalim.capungpedia.Evaluasi.IntroEvaluasiFragment;
import site.ishaalim.capungpedia.Glosarium.FragmentGlosarium;
import site.ishaalim.capungpedia.IdentifikasiCapung.IdentifikasiCapungFragment;
import site.ishaalim.capungpedia.MengenalCapung.FragmentMengenalCapung;
import site.ishaalim.capungpedia.Pendahuluan.FragmentPendahuluan;
import site.ishaalim.capungpedia.ayoPengamatan.AyoPengamatanFragment;
import site.ishaalim.capungpedia.ayoPengamatan.DetailPengamatanFragment;
import site.ishaalim.capungpedia.ayoPengamatan.JudulPengamatanFragment;
import site.ishaalim.capungpedia.laporBug.LaporBugFragment;
import site.ishaalim.capungpedia.mengenalDesa.FragmentMengenalDesa;
import site.ishaalim.capungpedia.panduanIdentifikasi.FragmentPanduanIdentifikasi;
import site.ishaalim.capungpedia.panduanPengamatan.FragmentPanduanPengamatan;
import site.ishaalim.capungpedia.petunjukPenggunaan.FragmentPetunjukPenggunaan;
import site.ishaalim.capungpedia.referensi.FragmentReferensi;

import com.google.android.material.navigation.NavigationView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DetailPengamatanFragment.DetailPengamatanListener {


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    private ImageButton buttonNav;
    public Fragment fragment, fragment2;
    public String TAG;

    private DetailPengamatanFragment detailPengamatanFragment;

    String share = "Yuk identifikasi Capung melalui Capung Pedia!!";
    String link = "https://play.google.com/store/apps/details?id=site.ishaalim.capungpedia";


    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        checktheme();

        detailPengamatanFragment = new DetailPengamatanFragment();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        setUpDrawer();

        if (savedInstanceState == null){
            fragment = new FragmentBeranda();
            TAG = "beranda";
            goToFragment(fragment, TAG);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.pendahuluan:
                fragment = new FragmentPendahuluan();
                TAG = "pendahuluan";
                goToFragment(fragment, TAG);
                break;

            case R.id.beranda:
                fragment = new FragmentBeranda();
                TAG = "beranda";
                goToFragment(fragment, TAG);
                break;

            case R.id.mengenal_capung:
                fragment = new FragmentMengenalCapung();
                TAG = "mengenal_capung";
                goToFragment(fragment, TAG);
                break;

            case R.id.mengenal_kawasan_desa:
                fragment = new FragmentMengenalDesa();
                TAG = "mengenal_kawasan_desa";
                goToFragment(fragment, TAG);
                break;

            case R.id.panduan_pengamatan:
                fragment = new FragmentPanduanPengamatan();
                TAG = "panduan_pengamatan";
                goToFragment(fragment, TAG);
                break;

            case R.id.panduan_Identifikasi:
                fragment = new FragmentPanduanIdentifikasi();
                TAG = "panduan_Identifikasi";
                goToFragment(fragment, TAG);
                break;

            case R.id.identifikasi_capung:
                fragment = new IdentifikasiCapungFragment();
                TAG = "identifikasi_capung";
                goToFragment(fragment, TAG);
                break;

            case R.id.ayo_pengamatan:
                fragment = new AyoPengamatanFragment();
                TAG = "ayo_pengamatan";
                goToFragment(fragment, TAG);
                break;

            case R.id.evaluasi:
                fragment = new EvaluasiFragment();
                TAG = "evaluasi";
                goToFragment(fragment, TAG);
                break;

            case R.id.petunjuk_penggunaan:
                fragment = new FragmentPetunjukPenggunaan();
                TAG = "petunjuk_penggunaan";
                goToFragment(fragment, TAG);
                break;

            case R.id.glosarium:
                fragment = new FragmentGlosarium();
                TAG = "glosarium";
                goToFragment(fragment, TAG);
                break;

            case R.id.tentang_pengembang:
                Intent intent = new Intent(getApplicationContext(), tentangPengembangActivity.class);
                startActivity(intent);
                CustomIntent.customType(this, "left-to-right");
                break;

            case R.id.referensi:
                fragment = new FragmentReferensi();
                TAG = "referensi";
                goToFragment(fragment, TAG);
                break;

            case R.id.bagikan_aplikasi:
                final Intent shareintent = new Intent(android.content.Intent.ACTION_SEND);
                shareintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareintent.putExtra(Intent.EXTRA_TEXT, share+"\n"+link );
                shareintent.setType("text/plain");
                startActivity(Intent.createChooser(shareintent, "Share Aplikasi Via :"));
                CustomIntent.customType(this, "bottom-to-top");
                break;

            case R.id.laporkan_bug:
                fragment = new LaporBugFragment();
                TAG = "laporkan_bug";
                goToFragment(fragment, TAG);
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (fragment instanceof FragmentBeranda){
            super.onBackPressed();
        }else if (fragment instanceof JudulPengamatanFragment){
            if (fragment2 instanceof DetailPengamatanFragment){
                TAG = "detail_pengamatan";
                RemoveFragment(TAG);
                fragment2 = null;
            }else {
                fragment = new AyoPengamatanFragment();
                TAG = "ayo_pengamatan";
                goToFragment(fragment, TAG);
            }

        } else {
            fragment = new FragmentBeranda();
            TAG = "beranda";
            goToFragment(fragment, TAG);
        }

    }

    @Override
    public void addArraylist(String namapengamat, String habitat, String cuaca, String aktifiktas, String deskripsi, String hasil, Uri imageUri, Date date) {
        JudulPengamatanFragment judulPengamatanFragment = (JudulPengamatanFragment)getSupportFragmentManager().findFragmentByTag("judulPengamatan");
        judulPengamatanFragment.insertArray(namapengamat, habitat, cuaca, aktifiktas, deskripsi, hasil, imageUri, date);
    }

    public void setFragment (Fragment mFragment, String tag){
        fragment = mFragment;
        TAG = tag;

        goToFragment(fragment, TAG);
    }

    public void goToFragment(Fragment mfragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mfragment, tag).commit();
    }

    public void addFragment(Fragment mfragment, String tag) {
        fragment2 = mfragment;
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mfragment, tag).commit();
    }

    public void RemoveFragment(String tag) {
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag(tag)).commit();
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

    private void setUpDrawer() {
        navigationView.setNavigationItemSelectedListener(this);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    public void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }
}
