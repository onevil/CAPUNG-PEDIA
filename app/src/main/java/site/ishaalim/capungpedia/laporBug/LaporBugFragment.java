package site.ishaalim.capungpedia.laporBug;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import site.ishaalim.capungpedia.R;

public class LaporBugFragment extends Fragment {

    private EditText edtNama, edtBug;
    private Button btnLapor;
    private ProgressDialog progressDialog;

    FirebaseFirestore firestore;

    String Characters = "ABCDEFGHIJKMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz0123456789";
    int IDLength = 20;
    String docID = "";


    public LaporBugFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lapor_bug, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setFirestore();
        initUI();
        setListener();
        setProgressDialog();
    }

    private void setProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Menyimpan Laporan Bug");
        progressDialog.setMessage("Mohon tunggu...");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private void setFirestore(){
        firestore = FirebaseFirestore.getInstance();
    }

    private void initUI() {
        edtNama = getView().findViewById(R.id.edt_nama_pelapor);
        edtBug = getView().findViewById(R.id.edt_bug);
        btnLapor = getView().findViewById(R.id.btn_lapor);
    }

    private void setListener() {
        btnLapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDocId();
                progressDialog.show();
                saveLaporan();
            }
        });
    }

    private void saveLaporan() {
        String NamaPelapor = edtNama.getText().toString();
        String Bug = edtBug.getText().toString();

        Map<String, Object> laporanBug = new HashMap<>();
        laporanBug.put("namapelapor", NamaPelapor);
        laporanBug.put("bug", Bug);

        firestore.collection("Bug").document(docID)
                .set(laporanBug)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(),"Laporan berhasil dikirim!", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        clearEditText();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"Laporan gagal dikirim!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void clearEditText(){
        edtNama.setText("");
        edtBug.setText("");
    }

    private void GetDocId(){

        Random random = new Random();

        char[] chars = new char[IDLength];

        for (int i = 0; i < IDLength; i++){
            chars[i] = Characters.charAt(random.nextInt(Characters.length()));
        }

        for (int i = 0; i < chars.length; i++){
            docID += chars[i];
        }

    }
}
