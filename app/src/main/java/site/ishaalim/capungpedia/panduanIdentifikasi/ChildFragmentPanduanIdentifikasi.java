package site.ishaalim.capungpedia.panduanIdentifikasi;


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
import site.ishaalim.capungpedia.panduanIdentifikasi.adapter.PanduanIdentifikasiAdapter;
import site.ishaalim.capungpedia.panduanIdentifikasi.model.isiHalamanPanduanIdentifikasi;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildFragmentPanduanIdentifikasi extends Fragment {

    FirebaseFirestore firestore;
    private RecyclerView rvPanduanIdentifikasi;

    private ArrayList<isiHalamanPanduanIdentifikasi> isiHalamanPanduanIdentifikasiArrayList;
    PanduanIdentifikasiAdapter panduanIdentifikasiAdapter;


    RequestOptions options;

    int halaman;
    String hal;

    public ChildFragmentPanduanIdentifikasi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_panduan_identifikasi, container, false);
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
        isiHalamanPanduanIdentifikasiArrayList = new ArrayList<>();

        setUpFirestore();
        setUpPanduanIdentifikasiRV();
        getIsiHalaman(hal);

    }

    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void getIsiHalaman(final String halaman) {
        isiHalamanPanduanIdentifikasiArrayList.clear();
        CollectionReference firestoreRef = firestore.collection("panduanIdentifikasi")
                .document("dDL5eGmceaUk0NRRgxUd")
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
                        isiHalamanPanduanIdentifikasi isiHalamanPanduanIdentifikasi = queryIsiHalamanSnapshot.toObject(isiHalamanPanduanIdentifikasi.class);

                        isiHalamanPanduanIdentifikasiArrayList.add(isiHalamanPanduanIdentifikasi);
                        Log.d(TAG,"Halaman sukses :" +halaman);
                    }else {
                        Log.d(TAG,"No such Document");
                    }
                }
                panduanIdentifikasiAdapter = new PanduanIdentifikasiAdapter(getContext(), isiHalamanPanduanIdentifikasiArrayList);
                rvPanduanIdentifikasi.setAdapter(panduanIdentifikasiAdapter);
                rvPanduanIdentifikasi.smoothScrollToPosition(rvPanduanIdentifikasi.getAdapter().getItemCount());
                Log.d(TAG,"Array:" + isiHalamanPanduanIdentifikasiArrayList);
            }
        });
    }


    private void loadisiHalaman(ArrayList mengenalCapungArrayList){


    }
    private void setUpPanduanIdentifikasiRV() {

        rvPanduanIdentifikasi = getView().findViewById(R.id.rv_panduan_identifikasi);
        rvPanduanIdentifikasi.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvPanduanIdentifikasi.setLayoutManager(layoutManager);
    }
}
