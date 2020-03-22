package site.ishaalim.capungpedia.UI.Evaluasi;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import site.ishaalim.capungpedia.UI.Evaluasi.adapter.listSoalAdapter;
import site.ishaalim.capungpedia.UI.Evaluasi.model.listSoal;
import site.ishaalim.capungpedia.UI.MainActivity;
import site.ishaalim.capungpedia.R;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class EvaluasiFragment extends Fragment {


    RecyclerView rvlistSoal;

    FirebaseFirestore firestore;

    listSoalAdapter listsoalAdapter;

    ArrayList<listSoal>listSoalArrayList;

    Toolbar tbEvaluasi;

    public EvaluasiFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_evaluasi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listSoalArrayList = new ArrayList<>();

        setUpFirestore();
        initUI();
        setupToolbar();
        setUplistSoalRV();
        loadlistSoalRV();
    }

    private void setupToolbar() {
        tbEvaluasi.setNavigationIcon(getResources().getDrawable(R.drawable.ic_drawer));
        tbEvaluasi.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });
    }

    private void loadlistSoalRV() {
        listSoalArrayList.clear();
        CollectionReference firestoreRef = firestore.collection("Soal");
        Query querylistSoal = firestoreRef.orderBy("namaSoal", Query.Direction.ASCENDING);

        querylistSoal.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot querylistSoalSnapshot : task.getResult()){
                    if (task.getResult() != null){
                        listSoal listSoal = querylistSoalSnapshot.toObject(listSoal.class);

                        listSoalArrayList.add(listSoal);
                    }else {
                        Log.d(TAG,"No such Document");
                    }
                }
                listsoalAdapter = new listSoalAdapter(getContext(),listSoalArrayList);
                rvlistSoal.setAdapter(listsoalAdapter);
                rvlistSoal.smoothScrollToPosition(rvlistSoal.getAdapter().getItemCount());
            }
        });

    }

    private void setUplistSoalRV() {
        rvlistSoal = getView().findViewById(R.id.rv_listSoal);
        rvlistSoal.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvlistSoal.setLayoutManager(layoutManager);

    }

    private void initUI() {
        rvlistSoal = getView().findViewById(R.id.rv_listSoal);
        tbEvaluasi = getView().findViewById(R.id.toolbar_evaluasi);
    }


    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }
}
