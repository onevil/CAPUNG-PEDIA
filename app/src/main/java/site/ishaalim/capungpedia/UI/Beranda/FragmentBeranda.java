package site.ishaalim.capungpedia.UI.Beranda;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import site.ishaalim.capungpedia.UI.Beranda.adapter.SliderAdapter;
import site.ishaalim.capungpedia.UI.MainActivity;
import site.ishaalim.capungpedia.UI.Materi.adapter.ListMateriAdapter;
import site.ishaalim.capungpedia.UI.Materi.model.listMateri;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.UI.notification.NotifListFragment;
import site.ishaalim.capungpedia.UI.petunjukPenggunaan.FragmentPetunjukPenggunaan;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class FragmentBeranda extends Fragment {

    private Switch DarkModeswitch;
    private ImageButton buttonNav, buttonNotif;
    private LinearLayout llPetunjukPenggunaan;

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

        setEvents();


    }

    private void initUI() {
        DarkModeswitch = getView().findViewById(R.id.myswitch);
        buttonNav = getView().findViewById(R.id.btn_drawer);
        buttonNotif = getView().findViewById(R.id.btn_notification);
        llPetunjukPenggunaan = getView().findViewById(R.id.ll_petunjuk_penggunaan);

    }


    private void setEvents(){
        buttonNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openDrawer();
            }
        });

        llPetunjukPenggunaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentPetunjukPenggunaan fragmentPetunjukPenggunaan = new FragmentPetunjukPenggunaan();
                String TAG = "petunjuk_penggunaan";
                ((MainActivity)getActivity()).setFragment(fragmentPetunjukPenggunaan, TAG);
            }
        });

        buttonNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotifListFragment notifListFragment = new NotifListFragment();
                String TAG = "notification";
                ((MainActivity)getActivity()).setFragment(notifListFragment, TAG);
            }
        });

    }

    private void loadlistMateriRV() {
        listMateriArrayList.clear();
        CollectionReference firestoreRef = firestore.collection("materi");
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
        final SliderView sliderView = getView().findViewById(R.id.imageSlider_Header);
        sliderAdapter = new  SliderAdapter(getContext(), links);
        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);

        sliderView.setScrollTimeInSec(5);
        sliderView.startAutoCycle();

    }

}

