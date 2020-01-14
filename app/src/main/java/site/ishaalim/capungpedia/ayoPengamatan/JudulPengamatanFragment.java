package site.ishaalim.capungpedia.ayoPengamatan;


import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import site.ishaalim.capungpedia.MainActivity;
import site.ishaalim.capungpedia.R;


public class JudulPengamatanFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private Toolbar toolbar;
    Button btnTambahKeterangan;
    EditText edtJudulPengamatan, edtLokasi, edtTanggal;

    String Characters = "ABCDEFGHIJKMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz0123456789";
    String docID = "";
    String tanggal;

    FirebaseFirestore firestore;

    int IDLength = 20;


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
        setupFirestore();
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

        edtTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.setTargetFragment(JudulPengamatanFragment.this, 0);
                datePicker.show(getFragmentManager(), "date picker");
            }
        });

        GenerateID();
    }

    private void setupFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    private void GenerateID() {
        Random random = new Random();

        char[] chars = new char[IDLength];

        for (int i = 0; i < IDLength; i++){
            chars[i] = Characters.charAt(random.nextInt(Characters.length()));
        }

        for (int i = 0; i < chars.length; i++){
            docID += chars[i];
        }



    }

    public void SavePengamatan(){

        Map<String, Object> pengamatan = new HashMap<>();
        pengamatan.put("judulPengamatan" , edtJudulPengamatan.getText().toString());
        pengamatan.put("lokasiPengamatan" , edtLokasi.getText().toString());
        pengamatan.put("tanggalPengamatan" , edtTanggal.getText().toString());

        firestore.collection("ayoPengamatan").document(docID)
                .set(pengamatan).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "Data Berhasil Disimpan!", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Data Gagal Disimpan!", Toast.LENGTH_LONG).show();
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
        return super.onOptionsItemSelected(item);

    }

    private void initUI() {
        toolbar = getView().findViewById(R.id.toolbar_judul_pengamatan);
        btnTambahKeterangan = getView().findViewById(R.id.btn_spesies_keterangan);
        edtJudulPengamatan = getView().findViewById(R.id.edt_judul_pengamatan);
        edtLokasi = getView().findViewById(R.id.edt_lokasi_pengamatan);
        edtTanggal = getView().findViewById(R.id.edt_tanggal_pengamatan);

    }

    private void setupToolbar() {
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.inflateMenu(R.menu.menu_toolbar_ayopengamatan);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_simpan:
                        SavePengamatan();
                        return true;
                    default:
                        return true;
                }
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AyoPengamatanFragment ayoPengamatanFragment = new AyoPengamatanFragment();

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, ayoPengamatanFragment).commit();



            }
        });
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        tanggal = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        edtTanggal.setText(tanggal);
    }
}
