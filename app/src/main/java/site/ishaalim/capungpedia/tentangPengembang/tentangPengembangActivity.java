package site.ishaalim.capungpedia.tentangPengembang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.SharedPref.SharedPref;
import site.ishaalim.capungpedia.tentangPengembang.adapter.TPpagerAdapter;

public class tentangPengembangActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TPpagerAdapter adapter;
    private TabLayout tabLayout;
    private SharedPref sharedpref;

    Drawable TeamIcon, FotograferIcon, PenulisIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);
        checkTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_pengembang);

        getSupportActionBar();
        initUI();
        setupViewPager();
        setupTabLayout();
    }

    private void checkTheme(){

        if(sharedpref.loadNightModeState()==true) {
            setTheme(R.style.DarkTheme);
        }
        else  {
            sharedpref.setNightModeState(false);
            setTheme(R.style.AppTheme);
        }

        setUpIcons();

    }

    private void setUpIcons(){

        TeamIcon = getDrawable(R.drawable.ic_team);
        FotograferIcon  = getDrawable(R.drawable.ic_fotografer);
        PenulisIcon  = getDrawable(R.drawable.ic_pen);

        if (sharedpref.loadNightModeState() == true){
            setTintWhite(TeamIcon);
            setTintWhite(FotograferIcon);
            setTintWhite(PenulisIcon);

        }else {
            setTintBlack(TeamIcon);
            setTintBlack(FotograferIcon);
            setTintBlack(PenulisIcon);
        }
    }

    private void setTintBlack(Drawable drawable){
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, getResources().getColor(R.color.black));
    }

    private void setTintWhite(Drawable drawable){
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, getResources().getColor(R.color.white));
    }

    private void initUI() {
        tabLayout = findViewById(R.id.tl_tentang_pengembang);
        viewPager = findViewById(R.id.vp_tentang_pengembang);
        adapter = new TPpagerAdapter(this);
    }

    private void setupTabLayout() {

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.OnConfigureTabCallback() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Aplikasi");
                        break;
                    case 1:
                        tab.setText("Pengembang");
                        break;
                    case 2:
                        tab.setText("Pembimbing");
                        break;
                }
            }
        });
        tabLayoutMediator.attach();

    }

    private void setupViewPager() {
        viewPager.setAdapter(adapter);
    }
}
