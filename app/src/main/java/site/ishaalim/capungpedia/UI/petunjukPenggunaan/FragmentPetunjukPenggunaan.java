package site.ishaalim.capungpedia.UI.petunjukPenggunaan;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import site.ishaalim.capungpedia.UI.MainActivity;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.UI.petunjukPenggunaan.adapter.PentunjukPengunaanViewPagerAdapter;
import site.ishaalim.capungpedia.UI.petunjukPenggunaan.model.petunjukPenggunaan;


public class FragmentPetunjukPenggunaan extends Fragment {
    ArrayList<petunjukPenggunaan> petunjukPenggunaanArrayList;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private PentunjukPengunaanViewPagerAdapter viewPagerAdapter;
    private Toolbar toolbar;

    public FragmentPetunjukPenggunaan() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_petunjuk_penggunaan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        petunjukPenggunaanArrayList = new ArrayList<>();
        initUI();
        setupToolbar();
        setupViewPager();
    }

    public void initUI() {
        tabLayout = getView().findViewById(R.id.tl_petunjuk_penggunaan);
        toolbar = getView().findViewById(R.id.toolbar_petunjuk_penggunaan);
        viewPager2 = getView().findViewById(R.id.vp_petunjuk_penggunaan);
        viewPagerAdapter = new PentunjukPengunaanViewPagerAdapter(getActivity());
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
                tab.setText("Hal " + (position+1));
            }
        });
        mediator.attach();
    }
}
