package site.ishaalim.capungpedia.IdentifikasiCapung;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import site.ishaalim.capungpedia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IdentifikasiCapungFragment extends Fragment {


    public IdentifikasiCapungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_identifikasi_capung, container, false);
    }

}
