package site.ishaalim.capungpedia.UI.Evaluasi;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import site.ishaalim.capungpedia.UI.MainActivity;
import site.ishaalim.capungpedia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroEvaluasiFragment extends Fragment {

    private Toolbar toolbar;
    Button btnLanjutkan;


    public IntroEvaluasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro_evaluasi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        setupToolbar();

        btnLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EvaluasiFragment evaluasiFragment = new EvaluasiFragment();
                String TAG = "Evaluasi";
                ((MainActivity) getActivity()).setFragment(evaluasiFragment, TAG);
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, evaluasiFragment, "Evaluasi").commit();
            }
        });
    }

    private void initUI() {
        toolbar = getView().findViewById(R.id.toolbar_intro_evaluasi);
        btnLanjutkan = getView().findViewById(R.id.btn_lanjutkan);
    }

    private void setupToolbar() {
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_drawer));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });
        toolbar.setTitle("Evaluasi");
    }
}
