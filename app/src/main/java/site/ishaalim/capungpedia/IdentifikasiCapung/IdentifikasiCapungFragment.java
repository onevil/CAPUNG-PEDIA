package site.ishaalim.capungpedia.IdentifikasiCapung;


import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import site.ishaalim.capungpedia.IdentifikasiCapung.adapter.IdentifikasiCapungVPadapter;
import site.ishaalim.capungpedia.MainActivity;
import site.ishaalim.capungpedia.R;


public class IdentifikasiCapungFragment extends Fragment {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    IdentifikasiCapungVPadapter viewPagerAdapter;

    public IdentifikasiCapungFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_identifikasi_capung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        setupToolbar();
        setupViewPager();
        setHasOptionsMenu(true);
    }

    private void initUI() {
        toolbar = getView().findViewById(R.id.toolbar_identifikasi_capung);
        tabLayout = getView().findViewById(R.id.tl_materi_identifikasi_capung);
        viewPager2 = getView().findViewById(R.id.vp_identifikasi_capung);
        viewPagerAdapter = new IdentifikasiCapungVPadapter(getActivity());
    }

    private void setupToolbar() {
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_drawer));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });
    }

    private void setupViewPager(){
        viewPager2.setAdapter(viewPagerAdapter);
        setupTabLayout();
    }

    private void setupTabLayout() {
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.OnConfigureTabCallback() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Anisoptera");
                        break;
                    case 1:
                        tab.setText("Zygoptera");
                        break;
                }
            }
        });
        mediator.attach();
    }
}
