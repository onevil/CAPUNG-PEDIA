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
import site.ishaalim.capungpedia.mengenalDesa.adapter.MengenalDesaViewPagerAdapter;
import site.ishaalim.capungpedia.mengenalDesa.model.MengenalDesa;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class FragmentMengenalDesa extends Fragment {

    FirebaseFirestore firestore;

    ArrayList<MengenalDesa> mengenalDesaArrayList;

    private TabLayout tabLayout;
    FrameLayout flmengenalDesa;
    private ViewPager viewPager;
    private MengenalDesaViewPagerAdapter mengenalDesaViewPagerAdapter;

    private Toolbar toolbar;

    int tabSize;
    int selectedTabPosition;
    int halaman;


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
                ChildFragmentMengenalDesa childFragmentMengenalDesa = new ChildFragmentMengenalDesa();
                childFragmentMengenalDesa.setArguments(bundle);
                tab.getPosition();
                getFragmentManager().beginTransaction().replace(R.id.fl_mengenal_desa, childFragmentMengenalDesa, "MengenalDesa").commit();
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
        mengenalDesaArrayList.clear();

        CollectionReference firestoreref = firestore.collection("mengenalDesa")
                .document("hXyIcy9qlbdbDfh1zbOC")
                .collection("halaman");
        firestoreref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot documentSnapshot : task.getResult()){
                    if(task.getResult() != null){
                        MengenalDesa petunjukPenggunaan = documentSnapshot.toObject(MengenalDesa.class);
                        mengenalDesaArrayList.add(petunjukPenggunaan);
                        Log.d(TAG,"size :"+tabSize);

                    }else {
                        Log.d(TAG,"No such Document");
                    }
                }
                tabSize = mengenalDesaArrayList.size();
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
        tabLayout = getView().findViewById(R.id.tl_mengenal_desa);
        flmengenalDesa = getView().findViewById(R.id.fl_mengenal_desa);
        toolbar = getView().findViewById(R.id.toolbar_mengenal_desa);


    }


    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }
}
