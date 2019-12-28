package site.ishaalim.capungpedia.panduanIdentifikasi;


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
import site.ishaalim.capungpedia.panduanIdentifikasi.adapter.PanduanIdentifikasiViewPagerAdapter;
import site.ishaalim.capungpedia.panduanIdentifikasi.model.panduanIdentifikasi;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class FragmentPanduanIdentifikasi extends Fragment {

    FirebaseFirestore firestore;

    ArrayList<panduanIdentifikasi> panduanIdentifikasiArrayList;

    private TabLayout tabLayout;
    FrameLayout flPanduanIdentifikasi;
    private ViewPager viewPager;
    private PanduanIdentifikasiViewPagerAdapter mengenalCapungViewPagerAdapter;

    private Toolbar toolbar;

    int tabSize;
    int selectedTabPosition;
    int halaman;


    public FragmentPanduanIdentifikasi() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_panduan_identifikasi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        panduanIdentifikasiArrayList = new ArrayList<>();
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
                ChildFragmentPanduanIdentifikasi childFragmentPanduanIdentifikasi = new ChildFragmentPanduanIdentifikasi();
                childFragmentPanduanIdentifikasi.setArguments(bundle);
                tab.getPosition();
                getFragmentManager().beginTransaction().replace(R.id.fl_panduan_identifikasi, childFragmentPanduanIdentifikasi, "panduanIdentifikasi").commit();
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
        panduanIdentifikasiArrayList.clear();

        CollectionReference firestoreref = firestore.collection("panduanIdentifikasi")
                .document("dDL5eGmceaUk0NRRgxUd")
                .collection("halaman");
        firestoreref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot documentSnapshot : task.getResult()){
                    if(task.getResult() != null){
                        panduanIdentifikasi panduanIdentifikasi = documentSnapshot.toObject(panduanIdentifikasi.class);
                        panduanIdentifikasiArrayList.add(panduanIdentifikasi);
                        Log.d(TAG,"size :"+tabSize);

                    }else {
                        Log.d(TAG,"No such Document");
                    }
                }
                tabSize = panduanIdentifikasiArrayList.size();
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
        tabLayout = getView().findViewById(R.id.tl_panduan_identifikasi);
        flPanduanIdentifikasi = getView().findViewById(R.id.fl_panduan_identifikasi);
        toolbar = getView().findViewById(R.id.toolbar_panduan_identifikasi);


    }


    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }
}
