package site.ishaalim.capungpedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import site.ishaalim.capungpedia.tentangPengembang.adapter.TPpagerAdapter;

public class tentangPengembangActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TPpagerAdapter adapter;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_pengembang);

        getSupportActionBar();
        initUI();

        setupViewPager();

        setupTabLayout();
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
