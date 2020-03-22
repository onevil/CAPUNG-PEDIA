package site.ishaalim.capungpedia.UI.ayoPengamatan;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.UI.MainActivity;
import site.ishaalim.capungpedia.UI.ayoPengamatan.adapter.PengamatanViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParentPengamatanFragment extends Fragment {
    Toolbar toolbar;
    ViewPager2 viewPager2;
    TabLayout tabLayout;

    PengamatanViewPagerAdapter viewPagerAdapter;

    public ParentPengamatanFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parent_pengamatan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        setupToolbar();
        setupViewPager();
        setEvents();
    }

    private void initUI(){
        toolbar = getView().findViewById(R.id.toolbar_ayo_pengamatan);
        viewPager2 = getView().findViewById(R.id.vp_listPengamatan);
        tabLayout = getView().findViewById(R.id.tl_pengamatan);
        viewPagerAdapter = new PengamatanViewPagerAdapter(getActivity());
    }

    private void setupToolbar(){
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_drawer));
    }

    private void setupViewPager(){
        viewPager2.setAdapter(viewPagerAdapter);
        setupTabLayout();
    }

    private void setupTabLayout(){
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.OnConfigureTabCallback() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Pengamatan");
                        break;
                    case 1:
                        tab.setText("Profile");
                        break;
                }
            }
        });
        mediator.attach();
    }

    private void setEvents(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });
    }
}
