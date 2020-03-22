package site.ishaalim.capungpedia.UI.MengenalCapung;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import site.ishaalim.capungpedia.UI.MengenalCapung.adapter.MengenalCapungAdapter;
import site.ishaalim.capungpedia.UI.MengenalCapung.model.isiHalamanMengenalCapung;
import site.ishaalim.capungpedia.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildFragmentMengenalCapung extends Fragment {

    FirebaseFirestore firestore;
    private RecyclerView rvMengenalCapung;

    private ArrayList<isiHalamanMengenalCapung> isiHalamanMengenalCapungArrayList;
    MengenalCapungAdapter mengenalCapungAdapter;

    RequestOptions options;

    int halaman;
    String hal;

    public ChildFragmentMengenalCapung() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_mengenal_capung, container, false);
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
        isiHalamanMengenalCapungArrayList = new ArrayList<>();

        setUpFirestore();
        setUpMengenalCapungRV();
        getIsiHalaman(hal);

    }

    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void getIsiHalaman(final String halaman) {
        isiHalamanMengenalCapungArrayList.clear();
        CollectionReference firestoreRef = firestore.collection("mengenalCapung")
                .document("IkWxaKG5Loi6JhcrET2V")
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
                        isiHalamanMengenalCapung isiHalamanMengenalCapung = queryIsiHalamanSnapshot.toObject(isiHalamanMengenalCapung.class);

                        isiHalamanMengenalCapungArrayList.add(isiHalamanMengenalCapung);
                        Log.d(TAG,"Halaman sukses :" +halaman);
                    }else {
                        Log.d(TAG,"No such Document");
                    }
                }
                mengenalCapungAdapter = new MengenalCapungAdapter(getContext(), isiHalamanMengenalCapungArrayList);
                rvMengenalCapung.setAdapter(mengenalCapungAdapter);
                rvMengenalCapung.smoothScrollToPosition(rvMengenalCapung.getAdapter().getItemCount());
                Log.d(TAG,"Array:" + isiHalamanMengenalCapungArrayList);
            }
        });
    }


    private void loadisiHalaman(ArrayList mengenalCapungArrayList){


    }
    private void setUpMengenalCapungRV() {

        rvMengenalCapung = getView().findViewById(R.id.rv_mengenal_capung);
        rvMengenalCapung.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvMengenalCapung.setLayoutManager(layoutManager);
    }
}
