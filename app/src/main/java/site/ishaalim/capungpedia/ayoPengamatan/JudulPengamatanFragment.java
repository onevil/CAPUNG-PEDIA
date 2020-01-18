package site.ishaalim.capungpedia.ayoPengamatan;


import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.ayoPengamatan.adapter.tambahPengamatanAdapter;
import site.ishaalim.capungpedia.ayoPengamatan.model.Pengamatan;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class JudulPengamatanFragment extends Fragment implements DatePickerDialog.OnDateSetListener, tambahPengamatanAdapter.OnDeletePengamatan {

    private Toolbar toolbar;
    private Button btnTambahKeterangan;
    private EditText edtJudulPengamatan, edtLokasi, edtTanggal;
    private RecyclerView pengamatanRV;

    ArrayList<Pengamatan> pengamatanArrayList;

    tambahPengamatanAdapter adapter;


    String Characters = "ABCDEFGHIJKMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz0123456789";
    String docID = "";
    String tanggal;

    FirebaseFirestore firestore;

    int IDLength = 20;


    public JudulPengamatanFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_judul_pengamatan, container, false);

        pengamatanArrayList = new ArrayList<>();
        toolbar = v.findViewById(R.id.toolbar_judul_pengamatan);
        btnTambahKeterangan = v.findViewById(R.id.btn_spesies_keterangan);
        edtJudulPengamatan = v.findViewById(R.id.edt_judul_pengamatan);
        edtLokasi = v.findViewById(R.id.edt_lokasi_pengamatan);
        edtTanggal = v.findViewById(R.id.edt_tanggal_pengamatan);
        pengamatanRV = v.findViewById(R.id.rv_tambah_pengamatan);

        adapter = new tambahPengamatanAdapter(getContext(), pengamatanArrayList, this);



        setupFirestore();
        setHasOptionsMenu(true);
        setupToolbar();

        btnTambahKeterangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DetailPengamatanFragment detailPengamatanFragment = new DetailPengamatanFragment();

                getFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, detailPengamatanFragment).commit();
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

        setUpRecycler();

        adapter.setOnItemClickListener(new tambahPengamatanAdapter.OnDeletePengamatan() {
            @Override
            public void onDeleteClick(int position) {
                deleteArray(position);
            }
        });

        return v;
    }

    public void setUpRecycler() {
        pengamatanRV.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        pengamatanRV.setLayoutManager(linearLayoutManager);
        pengamatanRV.setAdapter(adapter);
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



    public void insertArray(String namapengamat, String habitat, String cuaca, String aktifiktas, String deskripsi, String hasil) {
        Pengamatan pengamatan = new Pengamatan();
        pengamatan.setNamaPengamat(namapengamat);
        pengamatan.setHabitat(habitat);
        pengamatan.setCuaca(cuaca);
        pengamatan.setAktifiktas(aktifiktas);
        pengamatan.setDeskripsi(deskripsi);
        pengamatan.setHasil(hasil);
        pengamatanArrayList.add(pengamatan);
        Log.d(TAG, "Document : " + pengamatan);
        Log.d(TAG, "Arraylist : " + pengamatanArrayList);
        Log.d(TAG, "Arraylist Size : " + pengamatanArrayList.size());
        adapter.notifyDataSetChanged();

    }

    public void deleteArray(int position){
        Log.d(TAG, "ARRAY : " + pengamatanArrayList.size());
        pengamatanArrayList.remove(position);
        pengamatanRV.removeViewAt(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, pengamatanArrayList.size());
        Log.d(TAG, "Delete Clicked! ");
        Toast.makeText(getContext(), "Delete!" + position, Toast.LENGTH_LONG).show();

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
                for (int i = 0; i < pengamatanArrayList.size(); i++){
                    saveSpesies(i);
                }
                Toast.makeText(getContext(), "Data Berhasil Disimpan!", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Data Gagal Disimpan!", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void saveSpesies(int position) {
        Map<String, Object> spesies = new HashMap<>();
        spesies.put("namaPengamat", pengamatanArrayList.get(position).getNamaPengamat());
        spesies.put("habitat", pengamatanArrayList.get(position).getHabitat());
        spesies.put("cuaca", pengamatanArrayList.get(position).getCuaca());
        spesies.put("aktifiktas", pengamatanArrayList.get(position).getAktifiktas());
        spesies.put("deskripsi", pengamatanArrayList.get(position).getDeskripsi());
        spesies.put("hasil", pengamatanArrayList.get(position).getHasil());

        firestore.collection("ayoPengamatan").document(docID).collection("pengamatan")
                .add(spesies).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "Pengamatan berhasil disimpan!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Pengamatan gagal disimpan!");
            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar_ayopengamatan, menu);
        super.onCreateOptionsMenu(menu, inflater);
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

    @Override
    public void onDeleteClick(int position) {
        deleteArray(position);
    }
}
