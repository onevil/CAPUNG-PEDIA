package site.ishaalim.capungpedia.ayoPengamatan;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import site.ishaalim.capungpedia.MainActivity;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.ayoPengamatan.adapter.ayoPengamatanAdapter;
import site.ishaalim.capungpedia.ayoPengamatan.model.ayoPengamatan;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class AyoPengamatanFragment extends Fragment {

    private Toolbar toolbar;
    FloatingActionButton btnTambahPengamatan;
    private RecyclerView pengamatanRV;

    FirebaseFirestore firestore;

    ayoPengamatanAdapter ayoPengamatanAdapter;
    private ArrayList<ayoPengamatan> ayoPengamatanArrayList;


    public AyoPengamatanFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ayo_pengamatan, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ayoPengamatanArrayList = new ArrayList<>();
        setUpFirestore();
        initUI();
        setupToolbar();
        showFloatButton();

        btnTambahPengamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JudulPengamatanFragment judulPengamatanFragment = new JudulPengamatanFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, judulPengamatanFragment, "judulPengamatan").commit();
            }
        });

        setUpRV();
        loadRV();


    }

    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    private void setUpRV() {
        pengamatanRV = getView().findViewById(R.id.rv_ayo_pengamatan);
        pengamatanRV.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        pengamatanRV.setLayoutManager(layoutManager);
    }

    public void loadRV() {
        ayoPengamatanArrayList.clear();
        CollectionReference firestoreRef = firestore.collection("ayoPengamatan");
        Query queryListPengamatan = firestoreRef.orderBy("tanggalPengamatan", Query.Direction.ASCENDING);
        queryListPengamatan.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot querySnapshotListMateri : task.getResult()) {
                            if (task.getResult() != null) {

                                        ayoPengamatan pengamatan = querySnapshotListMateri.toObject(ayoPengamatan.class);
                                        ayoPengamatanArrayList.add(pengamatan);

                            } else {
                                Log.d(TAG, "No such Document");
                            }
                        }

                        ayoPengamatanAdapter = new ayoPengamatanAdapter(getContext(), ayoPengamatanArrayList);
                        pengamatanRV.setAdapter(ayoPengamatanAdapter);
                        pengamatanRV.smoothScrollToPosition(ayoPengamatanAdapter.getItemCount());


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }


    private void initUI() {
        toolbar = getView().findViewById(R.id.toolbar_ayo_pengamatan);
        btnTambahPengamatan = getView().findViewById(R.id.btn_add_pengamatan);
    }

    public void showFloatButton(){
        btnTambahPengamatan.show();
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

}
