package site.ishaalim.capungpedia.ayoPengamatan;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import site.ishaalim.capungpedia.MainActivity;
import site.ishaalim.capungpedia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AyoPengamatanFragment extends Fragment {

    private Toolbar toolbar;
    FloatingActionButton btnTambahPengamatan;


    public AyoPengamatanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ayo_pengamatan, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();
        setupToolbar();
        showFloatButton();

        btnTambahPengamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JudulPengamatanFragment judulPengamatanFragment = new JudulPengamatanFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, judulPengamatanFragment, "judulPengamatan").commit();
            }
        });


    }


    private void initUI() {
        toolbar = getView().findViewById(R.id.toolbar_ayo_pengamatan);
        btnTambahPengamatan = getView().findViewById(R.id.btn_add_pengamatan);
    }

    public void showFloatButton(){
        btnTambahPengamatan.show();
    }

    private void setupToolbar() {
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_drawer));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });
    }

}
