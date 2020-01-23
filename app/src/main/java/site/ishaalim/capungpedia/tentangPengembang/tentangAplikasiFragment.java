package site.ishaalim.capungpedia.tentangPengembang;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.tentangPengembang.viewmodel.TPpageViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class tentangAplikasiFragment extends Fragment {

    private static final String TAG = "Aplikasi";
    private TPpageViewModel pageViewModel;


    public tentangAplikasiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tentang_aplikasi, container, false);
    }

}
