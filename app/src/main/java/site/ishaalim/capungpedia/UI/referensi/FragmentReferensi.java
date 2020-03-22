package site.ishaalim.capungpedia.UI.referensi;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import site.ishaalim.capungpedia.UI.MainActivity;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.UI.referensi.adapter.ReferensiAdapter;
import site.ishaalim.capungpedia.UI.referensi.model.Referensi;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class FragmentReferensi extends Fragment {

    private ImageButton buttonNavReferensi, buttonSearchReferensi;
    private EditText edtReferensi;

    FirebaseFirestore firestore;

    RecyclerView referensiRV;

    ReferensiAdapter referensiAdapter;
    ArrayList<Referensi> referensiArrayList, searchReferensiArrayList;

    String searchReferensi;


    public FragmentReferensi() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_referensi, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        referensiArrayList = new ArrayList<>();
        searchReferensiArrayList = new ArrayList<>();

        setUpFirestore();
        initUI();
        setUpReferensiRV();
        loadReferensiRV();

        edtReferensi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                searchReferensiArrayList.clear();
                referensiArrayList.clear();
                searchReferensi = s.toString().toLowerCase();

                if (searchReferensi != null) {
                    search();

                } else {
                    loadReferensiRV();
                }
            }

        });


        buttonNavReferensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });
    }

    private void search() {
        searchReferensiArrayList.clear();

        CollectionReference firestoreRef = firestore.collection("Referensi");
        Query querySearchReferensi = firestoreRef.orderBy("referensi", Query.Direction.ASCENDING);
        querySearchReferensi.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        searchReferensiArrayList.clear();
                        for (DocumentSnapshot querySearchSnapshotReferensi : task.getResult()) {
                            if (task.getResult() != null) {
                                String keyWord = querySearchSnapshotReferensi.getString("referensi").toLowerCase();

                                if (keyWord.contains(searchReferensi)) {
                                    Referensi referensi = querySearchSnapshotReferensi.toObject(Referensi.class);
                                    searchReferensiArrayList.add(referensi);
                                }
                            } else {
                                Log.d(TAG, "No such Document");
                            }
                        }

                        referensiAdapter = new ReferensiAdapter(getContext(), searchReferensiArrayList);
                        referensiRV.setAdapter(referensiAdapter);
                        referensiRV.smoothScrollToPosition(referensiAdapter.getItemCount());


                    }
                });

    }

    private void loadReferensiRV() {
        referensiArrayList.clear();

        CollectionReference firestoreRef = firestore.collection("Referensi");
        Query queryReferensi = firestoreRef.orderBy("referensi", Query.Direction.ASCENDING);
        queryReferensi.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        for (DocumentSnapshot querySnapshotReferensi : task.getResult()) {
                            if (task.getResult() != null) {
                                Referensi referensi = querySnapshotReferensi.toObject(Referensi.class);
                                referensiArrayList.add(referensi);
                            } else {
                                Log.d(TAG, "No such Document");
                            }
                        }

                        referensiAdapter = new ReferensiAdapter(getContext(), referensiArrayList);
                        referensiRV.setAdapter(referensiAdapter);
                        referensiRV.smoothScrollToPosition(referensiAdapter.getItemCount());


                    }
                });
    }

    private void setUpReferensiRV() {
        referensiRV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        referensiRV.setLayoutManager(layoutManager);
    }

    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    private void initUI() {
        buttonNavReferensi = getView().findViewById(R.id.btn_drawer_referensi);
        buttonSearchReferensi = getView().findViewById(R.id.btn_search_referensi);
        edtReferensi = getView().findViewById(R.id.edt_referensi);
        referensiRV = getView().findViewById(R.id.rv_referensi);
    }


}
