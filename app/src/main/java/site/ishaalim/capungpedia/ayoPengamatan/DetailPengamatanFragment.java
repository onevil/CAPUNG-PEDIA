package site.ishaalim.capungpedia.ayoPengamatan;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import site.ishaalim.capungpedia.MainActivity;
import site.ishaalim.capungpedia.R;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN;
import static android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailPengamatanFragment extends Fragment {
    Toolbar toolbar;
    String namaPengamat, Habitat, Cuaca, Aktifiktas, Deskripsi, Hasil;
    Button btnSimpan;

    EditText edtNamaPengamat, edtHabitat, edtPukul, edtCuaca, edtAktifitas, edtDeskripsi, edtHasil;

    private DetailPengamatanListener listener;

    public interface DetailPengamatanListener{
        void addArraylist(String namapengamat, String habitat, String cuaca, String aktifiktas, String deskripsi, String hasil);
    }


    public DetailPengamatanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_pengamatan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        setHasOptionsMenu(true);
        setupToolbar();

    }

    private void setupToolbar() {
    }

    private void initUI() {
        toolbar = getView().findViewById(R.id.toolbar_detail_pengamatan);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        edtNamaPengamat = getView().findViewById(R.id.edt_nama_pengamat);
        edtAktifitas = getView().findViewById(R.id.edt_aktifitas);
        edtHabitat = getView().findViewById(R.id.edt_habitat);
        edtCuaca = getView().findViewById(R.id.edt_cuaca);
        edtDeskripsi = getView().findViewById(R.id.edt_deskripsi);
        edtHasil = getView().findViewById(R.id.edt_hasil_identifikasi);
        btnSimpan = getView().findViewById(R.id.btn_simpan);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namaPengamat = edtNamaPengamat.getText().toString();
                Aktifiktas = edtAktifitas.getText().toString();
                Habitat = edtHabitat.getText().toString();
                Cuaca = edtCuaca.getText().toString();
                Deskripsi = edtDeskripsi.getText().toString();
                Hasil = edtHasil.getText().toString();

                listener.addArraylist(namaPengamat, Habitat, Cuaca, Aktifiktas, Deskripsi, Hasil);

                removeFragment();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof DetailPengamatanListener) {
            listener = (DetailPengamatanListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private void removeFragment(){
        getFragmentManager().beginTransaction().remove(this).commit();
    }

    private void back() {
        removeFragment();
    }
}
