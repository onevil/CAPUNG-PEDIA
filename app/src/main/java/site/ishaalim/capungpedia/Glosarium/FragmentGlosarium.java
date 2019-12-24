package site.ishaalim.capungpedia.Glosarium;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;

import site.ishaalim.capungpedia.Glosarium.adapter.GlosariumAdapter;
import site.ishaalim.capungpedia.Glosarium.model.Glosarium;
import site.ishaalim.capungpedia.MainActivity;
import site.ishaalim.capungpedia.Materi.adapter.ListMateriAdapter;
import site.ishaalim.capungpedia.Materi.model.listMateri;
import site.ishaalim.capungpedia.R;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class FragmentGlosarium extends Fragment {

    private ImageButton buttonNavGlosarium, buttonSearchGlosarium;
    private EditText edtGlosarium;

    FirebaseFirestore firestore;

    RecyclerView glosariumRV;

    GlosariumAdapter glosariumAdapter;
    ArrayList<Glosarium> glosariumArrayList, searchGlosariumArrayList;

    String searchGlosarium;


    public FragmentGlosarium() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_glosarium, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        glosariumArrayList = new ArrayList<>();
        searchGlosariumArrayList = new ArrayList<>();

        setUpFirestore();
        initUI();
        setUpglosariumRV();
        loadGlosariumRV();

        edtGlosarium.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                searchGlosariumArrayList.clear();
                glosariumArrayList.clear();
                searchGlosarium = s.toString().toLowerCase();

                if (searchGlosarium != null) {
                    search();

                } else {
                    loadGlosariumRV();
                }
            }

        });


        buttonNavGlosarium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });
    }

    private void search() {
        searchGlosariumArrayList.clear();

        CollectionReference firestoreRef = firestore.collection("glosarium");
        Query querySearchGlosarium = firestoreRef.orderBy("kataKunci", Query.Direction.ASCENDING);
        querySearchGlosarium.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        searchGlosariumArrayList.clear();
                        for (DocumentSnapshot querySearchSnapshotGlosarium : task.getResult()) {
                            if (task.getResult() != null) {
                                String keyWord = querySearchSnapshotGlosarium.getString("kataKunci").toLowerCase();

                                if (keyWord.contains(searchGlosarium)) {
                                    Glosarium glosarium = querySearchSnapshotGlosarium.toObject(Glosarium.class);
                                    searchGlosariumArrayList.add(glosarium);
                                }
                            } else {
                                Log.d(TAG, "No such Document");
                            }
                        }

                        glosariumAdapter = new GlosariumAdapter(getContext(), searchGlosariumArrayList);
                        glosariumRV.setAdapter(glosariumAdapter);
                        glosariumRV.smoothScrollToPosition(glosariumAdapter.getItemCount());


                    }
                });

    }

    private void loadGlosariumRV() {
        glosariumArrayList.clear();

        CollectionReference firestoreRef = firestore.collection("glosarium");
        Query queryGlosarium = firestoreRef.orderBy("kataKunci", Query.Direction.ASCENDING);
        queryGlosarium.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        for (DocumentSnapshot querySnapshotGlosarium : task.getResult()) {
                            if (task.getResult() != null) {
                                Glosarium glosarium = querySnapshotGlosarium.toObject(Glosarium.class);
                                glosariumArrayList.add(glosarium);
                            } else {
                                Log.d(TAG, "No such Document");
                            }
                        }

                        glosariumAdapter = new GlosariumAdapter(getContext(), glosariumArrayList);
                        glosariumRV.setAdapter(glosariumAdapter);
                        glosariumRV.smoothScrollToPosition(glosariumAdapter.getItemCount());


                    }
                });
    }

    private void setUpglosariumRV() {
        glosariumRV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        glosariumRV.setLayoutManager(layoutManager);
    }

    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    private void initUI() {
        buttonNavGlosarium = getView().findViewById(R.id.btn_drawer_glosarium);
        buttonSearchGlosarium = getView().findViewById(R.id.btn_search_glosarium);
        edtGlosarium = getView().findViewById(R.id.edt_glosarium);
        glosariumRV = getView().findViewById(R.id.rv_glosarium);
    }


}
