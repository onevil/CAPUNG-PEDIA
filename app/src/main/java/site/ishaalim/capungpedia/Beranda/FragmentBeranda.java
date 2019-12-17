package site.ishaalim.capungpedia.Beranda;


import android.icu.text.Transliterator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import site.ishaalim.capungpedia.Beranda.adapter.SliderAdapter;
import site.ishaalim.capungpedia.MainActivity;
import site.ishaalim.capungpedia.Materi.adapter.ListMateriAdapter;
import site.ishaalim.capungpedia.Materi.model.listMateri;
import site.ishaalim.capungpedia.R;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class FragmentBeranda extends Fragment {

    private Switch DarkModeswitch;
    private ImageButton buttonNav;

    FirebaseFirestore firestore;

    RecyclerView listMateriRV;

    ListMateriAdapter listMateriAdapter;
    SliderAdapter sliderAdapter;

    ArrayList<String> links = new ArrayList<>();
    ArrayList<listMateri> listMateriArrayList;

    String idmateri;

    public FragmentBeranda() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_beranda, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();

        listMateriArrayList = new ArrayList<>();

        setUpFirestore();

        setUpSliderView();

        setUplistMateriRV();

        loadlistMateriRV();

        buttonNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openDrawer();
            }
        });
        /*if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            DarkModeswitch.setChecked(true);
        }
        DarkModeswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                }
            }
        });*/

    }

    private void loadlistMateriRV() {
        listMateriArrayList.clear();
        final CollectionReference firestoreRef = firestore.collection("materi");
        Query queryListMateri = firestoreRef.orderBy("judul", Query.Direction.ASCENDING);
        queryListMateri.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot querySnapshotListMateri : task.getResult()){
                            if(task.getResult() != null){
                                listMateri listmateri = querySnapshotListMateri.toObject(listMateri.class);
                                listMateriArrayList.add(listmateri);
                            }else{
                                Log.d(TAG, "No such Document");
                            }
                        }

                        listMateriAdapter = new ListMateriAdapter(getContext(), listMateriArrayList);
                        listMateriRV.setAdapter(listMateriAdapter);
                        listMateriRV.smoothScrollToPosition(listMateriAdapter.getItemCount());


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    private void setUplistMateriRV() {
        listMateriRV = getView().findViewById(R.id.rv_materi);
        listMateriRV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listMateriRV.setLayoutManager(layoutManager);
    }

    private void setUpSliderView() {
        SliderView sliderView = getView().findViewById(R.id.imageSlider_Header);
        sliderAdapter = new  SliderAdapter(getContext(), links);
        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);

        sliderView.setScrollTimeInSec(5);
        sliderView.startAutoCycle();
    }

    private void initUI() {
        DarkModeswitch = getView().findViewById(R.id.myswitch);
        buttonNav = getView().findViewById(R.id.btn_drawer);
    }


}

