package site.ishaalim.capungpedia.UI.petunjukPenggunaan;


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
import site.ishaalim.capungpedia.UI.petunjukPenggunaan.adapter.PetunjukPenggunaanAdapter;
import site.ishaalim.capungpedia.UI.petunjukPenggunaan.model.isiHalamanPetunjukPenggunaan;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildFragmentPetunjukPenggunaan extends Fragment {

    FirebaseFirestore firestore;
    private RecyclerView rvPetunjukPenggunaan;

    private ArrayList<isiHalamanPetunjukPenggunaan> isiHalamanPetunjukPenggunaanArrayList;
    PetunjukPenggunaanAdapter petunjukPenggunaanAdapter;


    RequestOptions options;

    int halaman;
    String hal;

    public ChildFragmentPetunjukPenggunaan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_petunjuk_penggunaan, container, false);
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
        isiHalamanPetunjukPenggunaanArrayList = new ArrayList<>();

        setUpFirestore();
        setUpPetunjukPenggunaanRV();
        getIsiHalaman(hal);

    }

    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void getIsiHalaman(final String halaman) {
        isiHalamanPetunjukPenggunaanArrayList.clear();
        CollectionReference firestoreRef = firestore.collection("petunjukPenggunaan")
                .document("XDphCDEudVtj5PiUr90p")
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
                        isiHalamanPetunjukPenggunaan isiHalamanPetunjukPenggunaan = queryIsiHalamanSnapshot.toObject(isiHalamanPetunjukPenggunaan.class);

                        isiHalamanPetunjukPenggunaanArrayList.add(isiHalamanPetunjukPenggunaan);
                        Log.d(TAG,"Halaman sukses :" +halaman);
                    }else {
                        Log.d(TAG,"No such Document");
                    }
                }
                petunjukPenggunaanAdapter = new PetunjukPenggunaanAdapter(getContext(), isiHalamanPetunjukPenggunaanArrayList);
                rvPetunjukPenggunaan.setAdapter(petunjukPenggunaanAdapter);
                rvPetunjukPenggunaan.smoothScrollToPosition(rvPetunjukPenggunaan.getAdapter().getItemCount());
                Log.d(TAG,"Array:" + isiHalamanPetunjukPenggunaanArrayList);
            }
        });
    }


    private void loadisiHalaman(ArrayList mengenalCapungArrayList){


    }
    private void setUpPetunjukPenggunaanRV() {

        rvPetunjukPenggunaan = getView().findViewById(R.id.rv_petunjuk_penggunaan);
        rvPetunjukPenggunaan.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvPetunjukPenggunaan.setLayoutManager(layoutManager);
    }
}
