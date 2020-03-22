package site.ishaalim.capungpedia.UI.panduanPengamatan;


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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import site.ishaalim.capungpedia.UI.MainActivity;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.UI.panduanPengamatan.adapter.PanduanPengamatanViewPagerAdapter;
import site.ishaalim.capungpedia.UI.panduanPengamatan.model.panduanPengamatan;


public class FragmentPanduanPengamatan extends Fragment {

    FirebaseFirestore firestore;

    ArrayList<panduanPengamatan> panduanPengamatanArrayList;

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    PanduanPengamatanViewPagerAdapter viewPagerAdapter;
    private Toolbar toolbar;

    public FragmentPanduanPengamatan() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_panduan_pengamatan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        panduanPengamatanArrayList = new ArrayList<>();
        setUpFirestore();
        initUI();
        setupToolbar();
        setupViewPager();
    }

    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void initUI() {
        viewPager2 = getView().findViewById(R.id.vp_panduan_pengamatan);
        toolbar = getView().findViewById(R.id.toolbar_panduan_pengamatan);
        tabLayout = getView().findViewById(R.id.tl_panduan_pengamatan);
        viewPagerAdapter = new PanduanPengamatanViewPagerAdapter(getActivity());
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
