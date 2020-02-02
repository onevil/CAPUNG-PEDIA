package site.ishaalim.capungpedia.tentangPengembang;


import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.tentangPengembang.viewmodel.TPpageViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class tentangAplikasiFragment extends Fragment {

    String[] fotografer = {"Ainun Rubi F.","Hening Triandika R.", "Ishadiyanto Salim", "Mariza Uthami","Prajawan Kusuma W."};
    String[] penulis = {"Ishadiyanto Salim"};

    CardView cv_fotografer, cv_penulis, cv_terimaKasih;
    ArrayAdapter<String> adapter;


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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();
        setEvents();

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

        ListView listFotografer = mView.findViewById(R.id.lv_fotografer);

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, fotografer);

        listFotografer.setAdapter(adapter);
        adapter.notifyDataSetInvalidated();

        dialog.setView(mView);
        AlertDialog dialogFotografer = dialog.create();
        dialogFotografer.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogFotografer.show();
    }

    private void showFotografer() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_list_fotografer, null);

        ListView listFotografer = mView.findViewById(R.id.lv_fotografer);

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, fotografer);

        listFotografer.setAdapter(adapter);
        adapter.notifyDataSetInvalidated();

        dialog.setView(mView);
        AlertDialog dialogFotografer = dialog.create();
        dialogFotografer.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogFotografer.show();
    }

    private void showPenulis() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_list_penulis, null);

        ListView listFotografer = mView.findViewById(R.id.lv_fotografer);

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, penulis);

        listFotografer.setAdapter(adapter);
        adapter.notifyDataSetInvalidated();

        dialog.setView(mView);
        AlertDialog dialogFotografer = dialog.create();
        dialogFotografer.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogFotografer.show();
    }


}
