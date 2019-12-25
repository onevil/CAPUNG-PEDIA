package site.ishaalim.capungpedia.IdentifikasiCapung;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import site.ishaalim.capungpedia.IdentifikasiCapung.adapter.CapungAdapter;
import site.ishaalim.capungpedia.IdentifikasiCapung.model.Capung;
import site.ishaalim.capungpedia.R;

public class ZygopteraFragment extends Fragment {

    FirebaseFirestore firestore;

    RecyclerView zygopteraRV;

    CapungAdapter capungAdapter;
    ArrayList<Capung> capungArrayList;
    public ZygopteraFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_zygoptera, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        capungArrayList = new ArrayList<>();

        setUpFirestore();
        initUI();
        setUpcapungRV();
        loadcapungRV();
    }

    private void initUI() {
        zygopteraRV = getView().findViewById(R.id.rv_zygoptera);
    }

    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    private void setUpcapungRV() {
        anisopteraRV.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        anisopteraRV.setLayoutManager(layoutManager);
    }

}
