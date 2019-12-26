package site.ishaalim.capungpedia.IdentifikasiCapung;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import site.ishaalim.capungpedia.IdentifikasiCapung.adapter.CapungAdapter;
import site.ishaalim.capungpedia.IdentifikasiCapung.model.Capung;
import site.ishaalim.capungpedia.Materi.adapter.ListMateriAdapter;
import site.ishaalim.capungpedia.Materi.model.listMateri;
import site.ishaalim.capungpedia.R;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class AnisopteraFragment extends Fragment {
    FirebaseFirestore firestore;

    RecyclerView anisopteraRV;
    EditText edtSearch;

    CapungAdapter capungAdapter;
    ArrayList<Capung> capungArrayList;
    ArrayList<Capung> searchArrayList;


    public AnisopteraFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_anisoptera, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        capungArrayList = new ArrayList<>();
        searchArrayList = new ArrayList<>();


        setUpFirestore();
        initUI();
        setUpcapungRV();
        loadcapungRV();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().toLowerCase().trim();
                if (query != null) {
                    searchCapung(query);
                } else {
                    loadcapungRV();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    public void searchCapung(final String query) {
        searchArrayList.clear();
        CollectionReference firestoreRef = firestore.collection("capung");
        Query querySearchCapung = firestoreRef.orderBy("namaSpesies", Query.Direction.ASCENDING);
        querySearchCapung.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        searchArrayList.clear();
                        for (DocumentSnapshot querySnapshotSearchMateri : task.getResult()) {
                            if (task.getResult() != null) {
                                if (task.getResult() != null) {
                                    String subOrdo = querySnapshotSearchMateri.getString("subOrdo");
                                    String namaSpesies = querySnapshotSearchMateri.getString("namaSpesies").toLowerCase();
                                    String querySearch = query.toLowerCase();

                                    if (subOrdo.contains("Anisoptera")) {
                                        if (namaSpesies.contains(querySearch)) {
                                            Capung capung = querySnapshotSearchMateri.toObject(Capung.class);
                                            searchArrayList.add(capung);
                                        }

                                    }
                                }

                            } else {
                                Log.d(TAG, "No such Document");
                            }
                        }

                        capungAdapter = new CapungAdapter(getContext(), searchArrayList);
                        anisopteraRV.setAdapter(capungAdapter);
                        anisopteraRV.smoothScrollToPosition(capungAdapter.getItemCount());


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void loadcapungRV() {
        capungArrayList.clear();
        CollectionReference firestoreRef = firestore.collection("capung");
        Query queryListCapung = firestoreRef.orderBy("namaSpesies", Query.Direction.ASCENDING);
        queryListCapung.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot querySnapshotListMateri : task.getResult()) {
                            if (task.getResult() != null) {
                                if (task.getResult() != null) {
                                    String subOrdo = querySnapshotListMateri.getString("subOrdo");

                                    if (subOrdo.contains("Anisoptera")) {
                                        Capung capung = querySnapshotListMateri.toObject(Capung.class);
                                        capungArrayList.add(capung);
                                    }
                                }

                            } else {
                                Log.d(TAG, "No such Document");
                            }
                        }

                        capungAdapter = new CapungAdapter(getContext(), capungArrayList);
                        anisopteraRV.setAdapter(capungAdapter);
                        anisopteraRV.smoothScrollToPosition(capungAdapter.getItemCount());


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void setUpcapungRV() {
        anisopteraRV.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        anisopteraRV.setLayoutManager(layoutManager);
    }

    private void initUI() {
        anisopteraRV = getView().findViewById(R.id.rv_anisoptera);
        edtSearch = getView().findViewById(R.id.edt_anisoptera);
    }

    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }
}
