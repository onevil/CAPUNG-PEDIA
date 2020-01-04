package site.ishaalim.capungpedia.ayoPengamatan;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import site.ishaalim.capungpedia.MainActivity;
import site.ishaalim.capungpedia.R;


public class JudulPengamatanFragment extends Fragment {

    private Toolbar toolbar;
    Button btnTambahKeterangan;


    public JudulPengamatanFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_judul_pengamatan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        setHasOptionsMenu(true);
        setupToolbar();

        btnTambahKeterangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DetailPengamatanFragment detailPengamatanFragment = new DetailPengamatanFragment();

                getFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, detailPengamatanFragment).addToBackStack("detailPengamatanFragment").commit();
            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar_ayopengamatan, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_simpan){

        }

        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        toolbar = getView().findViewById(R.id.toolbar_judul_pengamatan);
        btnTambahKeterangan = getView().findViewById(R.id.btn_spesies_keterangan);

    }

    private void setupToolbar() {
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.inflateMenu(R.menu.menu_toolbar_ayopengamatan);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AyoPengamatanFragment ayoPengamatanFragment = new AyoPengamatanFragment();

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, ayoPengamatanFragment).commit();



            }
        });
    }


}
