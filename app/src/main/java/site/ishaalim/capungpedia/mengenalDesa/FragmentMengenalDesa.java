package site.ishaalim.capungpedia.mengenalDesa;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import site.ishaalim.capungpedia.MainActivity;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.mengenalDesa.adapter.MengenalDesaViewPagerAdapter;
import site.ishaalim.capungpedia.mengenalDesa.model.MengenalDesa;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class FragmentMengenalDesa extends Fragment {
    FirebaseFirestore firestore;
    ArrayList<MengenalDesa> mengenalDesaArrayList;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private MengenalDesaViewPagerAdapter viewPagerAdapter;
    private Toolbar toolbar;

    public FragmentMengenalDesa() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mengenal_desa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mengenalDesaArrayList = new ArrayList<>();
        setUpFirestore();
        initUI();
        setupToolbar();
        setupViewPager();
    }

    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void initUI() {
        tabLayout = getView().findViewById(R.id.tl_mengenal_desa);
        toolbar = getView().findViewById(R.id.toolbar_mengenal_desa);
        viewPager2 = getView().findViewById(R.id.vp_mengenal_desa);
        viewPagerAdapter = new MengenalDesaViewPagerAdapter(getActivity());
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
