package site.ishaalim.capungpedia.UI.mengenalDesa;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.UI.mengenalDesa.adapter.MengenalDesaAdapter;
import site.ishaalim.capungpedia.UI.mengenalDesa.model.isiHalamanMengenalDesa;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildFragmentMengenalDesa extends Fragment {

    FirebaseFirestore firestore;
    private RecyclerView rvMengenalDesa;

    private ArrayList<isiHalamanMengenalDesa> isiHalamanMengenalDesaArrayList;
    MengenalDesaAdapter mengenalDesaAdapter;


    RequestOptions options;

    int halaman;
    String hal;

    public ChildFragmentMengenalDesa() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_mengenal_desa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            halaman = bundle.getInt("halaman");
            hal = Integer.toString(halaman);

        }

        options = new RequestOptions().centerCrop();
        isiHalamanMengenalDesaArrayList = new ArrayList<>();

        setUpFirestore();
        setUpMengenalDesaRV();
        getIsiHalaman(hal);

    }

    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void getIsiHalaman(final String halaman) {
        isiHalamanMengenalDesaArrayList.clear();
        CollectionReference firestoreRef = firestore.collection("mengenalDesa")
                .document("hXyIcy9qlbdbDfh1zbOC")
                .collection("halaman")
                .document(halaman)
                .collection("isiHalaman");
        Log.d(TAG,"Halaman sukses :" +halaman);

        Query queryIsiHalaman = firestoreRef.orderBy("id", Query.Direction.ASCENDING);
        queryIsiHalaman.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot queryIsiHalamanSnapshot : task.getResult()){
                    if (task.getResult() != null){
                        isiHalamanMengenalDesa isiHalamanPetunjukPenggunaan = queryIsiHalamanSnapshot.toObject(isiHalamanMengenalDesa.class);

                        isiHalamanMengenalDesaArrayList.add(isiHalamanPetunjukPenggunaan);
                        Log.d(TAG,"Halaman sukses :" +halaman);
                    }else {
                        Log.d(TAG,"No such Document");
                    }
                }
                mengenalDesaAdapter = new MengenalDesaAdapter(getContext(), isiHalamanMengenalDesaArrayList);
                rvMengenalDesa.setAdapter(mengenalDesaAdapter);
                rvMengenalDesa.smoothScrollToPosition(rvMengenalDesa.getAdapter().getItemCount());
                Log.d(TAG,"Array:" + isiHalamanMengenalDesaArrayList);
            }
        });
    }


    private void loadisiHalaman(ArrayList mengenalCapungArrayList){


    }
    private void setUpMengenalDesaRV() {

        rvMengenalDesa = getView().findViewById(R.id.rv_mengenal_desa);
        rvMengenalDesa.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvMengenalDesa.setLayoutManager(layoutManager);
    }
}
