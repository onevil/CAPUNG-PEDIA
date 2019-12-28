package site.ishaalim.capungpedia.panduanPengamatan;


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
import site.ishaalim.capungpedia.panduanPengamatan.adapter.PanduanPengamatanAdapter;
import site.ishaalim.capungpedia.panduanPengamatan.model.isiHalamanPanduanPengamatan;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildFragmentPanduanPengamatan extends Fragment {

    FirebaseFirestore firestore;
    private RecyclerView rvPanduanPengamatan;

    private ArrayList<isiHalamanPanduanPengamatan> isiHalamanPanduanPengamatanArrayList;
    PanduanPengamatanAdapter panduanPengamatanAdapter;
    

    RequestOptions options;

    int halaman;
    String hal;

    public ChildFragmentPanduanPengamatan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_panduan_pengamatan, container, false);
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
        isiHalamanPanduanPengamatanArrayList = new ArrayList<>();

        setUpFirestore();
        setUpPanduanPengamatanRV();
        getIsiHalaman(hal);

    }

    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void getIsiHalaman(final String halaman) {
        isiHalamanPanduanPengamatanArrayList.clear();
        CollectionReference firestoreRef = firestore.collection("panduanPengamatan")
                .document("5x2ebsOYqmqZJQQYggVX")
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
                        isiHalamanPanduanPengamatan isiHalamanPanduanPengamatan = queryIsiHalamanSnapshot.toObject(isiHalamanPanduanPengamatan.class);

                        isiHalamanPanduanPengamatanArrayList.add(isiHalamanPanduanPengamatan);
                        Log.d(TAG,"Halaman sukses :" +halaman);
                    }else {
                        Log.d(TAG,"No such Document");
                    }
                }
                panduanPengamatanAdapter = new PanduanPengamatanAdapter(getContext(), isiHalamanPanduanPengamatanArrayList);
                rvPanduanPengamatan.setAdapter(panduanPengamatanAdapter);
                rvPanduanPengamatan.smoothScrollToPosition(rvPanduanPengamatan.getAdapter().getItemCount());
                Log.d(TAG,"Array:" + isiHalamanPanduanPengamatanArrayList);
            }
        });
    }


    private void loadisiHalaman(ArrayList mengenalCapungArrayList){


    }
    private void setUpPanduanPengamatanRV() {

        rvPanduanPengamatan = getView().findViewById(R.id.rv_panduan_pengamatan);
        rvPanduanPengamatan.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvPanduanPengamatan.setLayoutManager(layoutManager);
    }
}
