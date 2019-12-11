package site.ishaalim.capungpedia.Pendahuluan;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import site.ishaalim.capungpedia.R;


public class FragmentPendahuluan extends Fragment {


    public FragmentPendahuluan() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pendahuluan, container, false);
    }

}
