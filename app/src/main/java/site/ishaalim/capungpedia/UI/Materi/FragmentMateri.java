package site.ishaalim.capungpedia.UI.Materi;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import site.ishaalim.capungpedia.R;


public class FragmentMateri extends Fragment {


    public FragmentMateri() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_materi, container, false);
    }

}
