package site.ishaalim.capungpedia.UI.tentangPengembang;


import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.UI.tentangPengembang.adapter.ContributorAdapter;
import site.ishaalim.capungpedia.UI.tentangPengembang.model.Contributor;

/**
 * A simple {@link Fragment} subclass.
 */
public class tentangAplikasiFragment extends Fragment {


    String[] penulis = {"Ishadiyanto Salim"};
    ArrayList<Contributor> terimakasihArrayList, fotograferArrayList, penulisArrayList;

    RecyclerView RVterimakasih, RVpenulis, RVfotografer;

    CardView cv_fotografer, cv_penulis, cv_terimaKasih;
    ArrayAdapter<String> adapter;


    public tentangAplikasiFragment() {
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();
        setTerimaKasihArray();
        setFotograferArray();
        setPenulisArray();
        setEvents();

    }

    private void setTerimaKasihArray() {
        terimakasihArrayList = new ArrayList<>();
        String c1 = "1.\tIbu, Ayah dan Adik yang selalu mendoakan dan mendukung hal-hal yang menjadi kebutuhan anak-anaknya";
        String c2 = "2.\tBapak Yuni Wibowo yang berkenan membmbing pembuatan aplikasi ini";
        String c3 = "3.\tMas Praja, Mas Hening dan Mbak Tria yang mengenalkan dunia percapungan";
        String c4 = "4.\tMariza Uthami, Noormalita, Nur Aini, dan Kharisma Diah yang membantu banyak dalam pengambilan data penelitian";
        String c5 = "5.\tAnggota Kelompok Studi Odonata yang selalu menemani dan menyemagati dalam pembuatan aplikasi ini";
        String c6 = "6.\tMuhammad Adi Febri Setiawan, Muhammad Ihsan Satyawan, Ananda Mukhammad Ikhsan dan tim IT lainnya atas saran, kerjasama dan bantuannya";
        String c7 = "7.\tMas Hening, Mas Praja, Mas Angga, Uut, Alfian, Ainun atas pinjaman foto-fotonya";
        String c8 = "8.\tIndonesia Dragonfly Society atas masukkan dan refrensinya";

        addTerimaKasihArray(c1);
        addTerimaKasihArray(c2);
        addTerimaKasihArray(c3);
        addTerimaKasihArray(c4);
        addTerimaKasihArray(c5);
        addTerimaKasihArray(c6);
        addTerimaKasihArray(c7);
        addTerimaKasihArray(c8);

    }

    private void addTerimaKasihArray(String nama){
        Contributor contributor = new Contributor();
        contributor.setNama(nama);
        terimakasihArrayList.add(contributor);
    }

    public void setFotograferArray(){
        fotograferArrayList = new ArrayList<>();
        String c1 = "Ainun Rubi F.";
        String c2 = "Alfian Surya F.";
        String c3 = "Angga Nur Cahyanto";
        String c4 = "Hening Triandika R.";
        String c5 = "Ishadiyanto Salim";
        String c6 = "Mariza Uthami";
        String c7 = "Prajawan Kusuma W.";

        addfotograferArray(c1);
        addfotograferArray(c2);
        addfotograferArray(c3);
        addfotograferArray(c4);
        addfotograferArray(c5);
        addfotograferArray(c6);
        addfotograferArray(c7);

    }

    private void addfotograferArray(String nama){
        Contributor contributor = new Contributor();
        contributor.setNama(nama);
        fotograferArrayList.add(contributor);
    }

    public void setPenulisArray(){
        penulisArrayList = new ArrayList<>();
        String c1 = "Ishadiyanto Salim";

        addPenulisArray(c1);
    }

    private void addPenulisArray(String nama){
        Contributor contributor = new Contributor();
        contributor.setNama(nama);
        penulisArrayList.add(contributor);
    }


    private void initUI() {
        cv_fotografer = getView().findViewById(R.id.cv_tv_fotografer);
        cv_penulis= getView().findViewById(R.id.cv_tv_penulis);
        cv_terimaKasih = getView().findViewById(R.id.cv_tv_terimakasih);
    }

    private void setEvents() {
        cv_fotografer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFotografer();
            }
        });

        cv_penulis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPenulis();
            }
        });

        cv_terimaKasih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTerimakasih();
            }
        });
    }


    private void showTerimakasih() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_list_terima_kasih, null);

        ContributorAdapter contributorAdapter = new ContributorAdapter(terimakasihArrayList, getContext());

        RVterimakasih = mView.findViewById(R.id.rv_contributor);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        RVterimakasih.setLayoutManager(layoutManager);
        RVterimakasih.setAdapter(contributorAdapter);

        dialog.setView(mView);
        AlertDialog dialogFotografer = dialog.create();
        dialogFotografer.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogFotografer.show();
    }

    private void showFotografer() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_list_fotografer, null);

        ContributorAdapter contributorAdapter = new ContributorAdapter(fotograferArrayList, getContext());

        RVfotografer = mView.findViewById(R.id.rv_contributor);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        RVfotografer.setLayoutManager(layoutManager);
        RVfotografer.setAdapter(contributorAdapter);

        dialog.setView(mView);
        AlertDialog dialogFotografer = dialog.create();
        dialogFotografer.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogFotografer.show();
    }

    private void showPenulis() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_list_penulis, null);

        ContributorAdapter contributorAdapter = new ContributorAdapter(penulisArrayList, getContext());

        RVpenulis = mView.findViewById(R.id.rv_contributor);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        RVpenulis.setLayoutManager(layoutManager);
        RVpenulis.setAdapter(contributorAdapter);

        dialog.setView(mView);
        AlertDialog dialogFotografer = dialog.create();
        dialogFotografer.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogFotografer.show();
    }


}
