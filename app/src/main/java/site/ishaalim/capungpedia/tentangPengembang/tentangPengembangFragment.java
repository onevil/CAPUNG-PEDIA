package site.ishaalim.capungpedia.tentangPengembang;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import site.ishaalim.capungpedia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class tentangPengembangFragment extends Fragment {


    public tentangPengembangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tentang_pengembang, container, false);
    }

}
