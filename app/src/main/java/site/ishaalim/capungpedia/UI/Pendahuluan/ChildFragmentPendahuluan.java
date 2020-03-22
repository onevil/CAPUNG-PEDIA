package site.ishaalim.capungpedia.UI.Pendahuluan;


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

import site.ishaalim.capungpedia.UI.Pendahuluan.adapter.PendahuluanAdapter;
import site.ishaalim.capungpedia.UI.Pendahuluan.model.isiHalamanPendahuluan;
import site.ishaalim.capungpedia.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildFragmentPendahuluan extends Fragment {

    FirebaseFirestore firestore;
    private RecyclerView rvPendahuluan;

    private ArrayList<isiHalamanPendahuluan> isiHalamanPendahuluanArrayList;
    PendahuluanAdapter pendahuluanAdapter;

    RequestOptions options;

    int halaman;
    String hal;

    public ChildFragmentPendahuluan() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child_pendahuluan, container, false);
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
        isiHalamanPendahuluanArrayList = new ArrayList<>();

        setUpFirestore();
        setUpPendahuluanRV();
        getIsiHalaman(hal);

    }

    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void getIsiHalaman(final String halaman) {
        isiHalamanPendahuluanArrayList.clear();
        CollectionReference firestoreRef = firestore.collection("pendahuluan")
                .document("AxY8NrrXRnslDNDxlsTP")
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
                        isiHalamanPendahuluan isiHalamanPendahuluan = queryIsiHalamanSnapshot.toObject(isiHalamanPendahuluan.class);

                        isiHalamanPendahuluanArrayList.add(isiHalamanPendahuluan);
                        Log.d(TAG,"Halaman sukses :" +halaman);
                    }else {
                        Log.d(TAG,"No such Document");
                    }
                }
                pendahuluanAdapter = new PendahuluanAdapter(getContext(), isiHalamanPendahuluanArrayList);
                rvPendahuluan.setAdapter(pendahuluanAdapter);
                rvPendahuluan.smoothScrollToPosition(rvPendahuluan.getAdapter().getItemCount());
                Log.d(TAG,"Array:" + isiHalamanPendahuluanArrayList);
            }
        });
    }


    private void loadisiHalaman(ArrayList mengenalCapungArrayList){


    }

    private void setUpPendahuluanRV() {

        rvPendahuluan = getView().findViewById(R.id.rv_pendahuluan);
        rvPendahuluan.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvPendahuluan.setLayoutManager(layoutManager);
    }
}
