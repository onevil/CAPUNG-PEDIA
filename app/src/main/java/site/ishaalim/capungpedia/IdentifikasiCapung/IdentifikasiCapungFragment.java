package site.ishaalim.capungpedia.IdentifikasiCapung;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import site.ishaalim.capungpedia.R;


public class IdentifikasiCapungFragment extends Fragment {

    private Toolbar toolbar;

    private TabLayout tabLayout;


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
        setupTabLayout();

        if (savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.fl_identifikasi_capung,
                    new  AnisopteraFragment()).commit();
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    getFragmentManager().beginTransaction().replace(R.id.fl_identifikasi_capung,
                            new  AnisopteraFragment()).commit();
                }else if (tab.getPosition() == 1){
                    getFragmentManager().beginTransaction().replace(R.id.fl_identifikasi_capung,
                            new ZygopteraFragment()).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupTabLayout() {
    }

    private void initUI() {
        toolbar = getView().findViewById(R.id.toolbar_identifikasi_capung);
        tabLayout = getView().findViewById(R.id.tl_materi_identifikasi_capung);
        
        
    }


}
