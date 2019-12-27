package site.ishaalim.capungpedia.MengenalCapung;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import site.ishaalim.capungpedia.MengenalCapung.adapter.MengenalCapungViewPagerAdapter;
import site.ishaalim.capungpedia.MengenalCapung.model.mengenalCapung;
import site.ishaalim.capungpedia.R;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class FragmentMengenalCapung extends Fragment {

    FirebaseFirestore firestore;

    ArrayList<mengenalCapung> mengenalCapungArrayList;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MengenalCapungViewPagerAdapter mengenalCapungViewPagerAdapter;

    int tabSize = 0;


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
        setUpFirestore();
        initUI();
        getTabSize();
        getViewPagerSize();
    }

    private void addPage(int Ids) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", Ids);
        ChildFragmentMengenalCapung childFragmentMengenalCapung = new ChildFragmentMengenalCapung();
        childFragmentMengenalCapung.setArguments(bundle);
        mengenalCapungViewPagerAdapter.addPage(childFragmentMengenalCapung);

    }

    private void getTabSize() {
        mengenalCapungArrayList.clear();

        CollectionReference firestoreref = firestore.collection("mengenalCapung");
        firestoreref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot documentSnapshot : task.getResult()){
                    if(task.getResult() != null){
                        tabSize = documentSnapshot.getLong("jumlahHalaman").intValue();
                        setupTabLayout(tabSize);
                        Log.d(TAG,"size :"+tabSize);

                    }else {
                        Log.d(TAG,"No such Document");
                    }
                }
            }
        });
    }

    private void setupTabLayout(int size) {
        tabSize = size;
        for (int i = 1; i<=tabSize; i++ ){
            tabLayout.addTab(tabLayout.newTab().setText("Hal " + i));
            addPage(i);
        }
    }

    private void initUI() {
        viewPager = getView().findViewById(R.id.vp_mengenal_capung);
        tabLayout = getView().findViewById(R.id.tl_mengenal_capung);
        mengenalCapungViewPagerAdapter = new MengenalCapungViewPagerAdapter(getFragmentManager(),getActivity(),viewPager,tabLayout);
//        viewPager.setAdapter(mengenalCapungViewPagerAdapter);
    }

    private void getViewPagerSize() {

    }

    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }
}
