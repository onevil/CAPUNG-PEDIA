package site.ishaalim.capungpedia.petunjukPenggunaan;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import site.ishaalim.capungpedia.MainActivity;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.petunjukPenggunaan.adapter.PentunjukPengunaanViewPagerAdapter;
import site.ishaalim.capungpedia.petunjukPenggunaan.model.petunjukPenggunaan;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class FragmentPetunjukPenggunaan extends Fragment {

    FirebaseFirestore firestore;

    ArrayList<petunjukPenggunaan> petunjukPenggunaanArrayList;

    private TabLayout tabLayout;
    FrameLayout flPetunjukPenggunaan;
    private ViewPager viewPager;
    private PentunjukPengunaanViewPagerAdapter pentunjukPengunaanViewPagerAdapter;

    private Toolbar toolbar;

    int tabSize;
    int selectedTabPosition;
    int halaman;


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
        setUpFirestore();
        initUI();
        setupToolbar();
        getTabSize();
        setEvents();


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


    private void setEvents() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Bundle bundle = new Bundle();
                halaman = 1 + tab.getPosition();
                bundle.putInt("halaman", halaman);
                Log.d(TAG,"halaman :"+halaman);
                ChildFragmentPetunjukPenggunaan childFragmentPetunjukPenggunaan = new ChildFragmentPetunjukPenggunaan();
                childFragmentPetunjukPenggunaan.setArguments(bundle);
                tab.getPosition();
                getFragmentManager().beginTransaction().replace(R.id.fl_petunjuk_penggunaan, childFragmentPetunjukPenggunaan, "petunjukPenggunaan").commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    private void getTabSize() {
        petunjukPenggunaanArrayList.clear();

        CollectionReference firestoreref = firestore.collection("petunjukPenggunaan")
                .document("XDphCDEudVtj5PiUr90p")
                .collection("halaman");
        firestoreref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot documentSnapshot : task.getResult()){
                    if(task.getResult() != null){
                        petunjukPenggunaan petunjukPenggunaan = documentSnapshot.toObject(petunjukPenggunaan.class);
                        petunjukPenggunaanArrayList.add(petunjukPenggunaan);
                        Log.d(TAG,"size :"+tabSize);

                    }else {
                        Log.d(TAG,"No such Document");
                    }
                }
                tabSize = petunjukPenggunaanArrayList.size();
                setupTabLayout(tabSize);
            }
        });
    }

    private void setupTabLayout(int size) {
        tabSize = size;
        for (int i = 1; i<=tabSize; i++ ){
            tabLayout.addTab(tabLayout.newTab().setText("Hal " + i));

        }
    }

    public void initUI() {
        tabLayout = getView().findViewById(R.id.tl_petunjuk_penggunaan);
        flPetunjukPenggunaan = getView().findViewById(R.id.fl_petunjuk_penggunaan);
        toolbar = getView().findViewById(R.id.toolbar_petunjuk_penggunaan);


    }


    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }
}
