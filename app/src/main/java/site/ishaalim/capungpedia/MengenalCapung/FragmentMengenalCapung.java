package site.ishaalim.capungpedia.MengenalCapung;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.DrawableUtils;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.StringValue;

import java.util.ArrayList;

import site.ishaalim.capungpedia.IdentifikasiCapung.AnisopteraFragment;
import site.ishaalim.capungpedia.MainActivity;
import site.ishaalim.capungpedia.MengenalCapung.adapter.MengenalCapungViewPagerAdapter;
import site.ishaalim.capungpedia.MengenalCapung.model.mengenalCapung;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.SharedPref.SharedPref;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class FragmentMengenalCapung extends Fragment {

    FirebaseFirestore firestore;

    ArrayList<mengenalCapung> mengenalCapungArrayList;

    SharedPref sharedPref;

    private TabLayout tabLayout;
    FrameLayout flMengenalCapung;
    private ViewPager viewPager;
    private MengenalCapungViewPagerAdapter mengenalCapungViewPagerAdapter;

    private Toolbar toolbar;

    int tabSize;
    int selectedTabPosition;
    int halaman;


    public FragmentMengenalCapung() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mengenal_capung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mengenalCapungArrayList = new ArrayList<>();
        sharedPref = new SharedPref(getContext());
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
                ChildFragmentMengenalCapung childFragmentMengenalCapung = new ChildFragmentMengenalCapung();
                childFragmentMengenalCapung.setArguments(bundle);
                tab.getPosition();
                getFragmentManager().beginTransaction().replace(R.id.fl_mengenal_capung, childFragmentMengenalCapung, "mengenalcapung").commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void addPage(int Ids) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", Ids);
        String id = Integer.toString(Ids);
        Log.d(TAG,"id :"+id);
        ChildFragmentMengenalCapung childFragmentMengenalCapung = new ChildFragmentMengenalCapung();
        childFragmentMengenalCapung.setArguments(bundle);
        mengenalCapungViewPagerAdapter.addPage(childFragmentMengenalCapung);
        mengenalCapungViewPagerAdapter.notifyDataSetChanged();


        int count =  mengenalCapungViewPagerAdapter.getCount();
        String counts = Integer.toString(count);
        Toast.makeText(getContext(),counts,Toast.LENGTH_LONG);
        Log.d(TAG,"count :"+counts);


        selectedTabPosition = viewPager.getCurrentItem();

    }

    private void getTabSize() {
        mengenalCapungArrayList.clear();

        CollectionReference firestoreref = firestore.collection("mengenalCapung")
                .document("IkWxaKG5Loi6JhcrET2V")
                .collection("halaman");
        firestoreref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot documentSnapshot : task.getResult()){
                    if(task.getResult() != null){
                        mengenalCapung mengenalCapung = documentSnapshot.toObject(mengenalCapung.class);
                        mengenalCapungArrayList.add(mengenalCapung);
                        Log.d(TAG,"size :"+tabSize);

                    }else {
                        Log.d(TAG,"No such Document");
                    }
                }
                tabSize = mengenalCapungArrayList.size();
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
        tabLayout = getView().findViewById(R.id.tl_mengenal_capung);
        flMengenalCapung = getView().findViewById(R.id.fl_mengenal_capung);
        toolbar = getView().findViewById(R.id.toolbar_mengenal_capung);
        tabLayout.setupWithViewPager(viewPager);

    }


    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }
}
