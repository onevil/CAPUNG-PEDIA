package site.ishaalim.capungpedia.Materi.adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import maes.tech.intentanim.CustomIntent;
import site.ishaalim.capungpedia.Beranda.FragmentBeranda;
import site.ishaalim.capungpedia.DetailMateriActivity;
import site.ishaalim.capungpedia.IdentifikasiCapung.IdentifikasiCapungFragment;
import site.ishaalim.capungpedia.MainActivity;
import site.ishaalim.capungpedia.Materi.model.listMateri;
import site.ishaalim.capungpedia.Materi.viewholder.ListMateriViewHolder;
import site.ishaalim.capungpedia.MengenalCapung.FragmentMengenalCapung;
import site.ishaalim.capungpedia.Pendahuluan.FragmentPendahuluan;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.ayoPengamatan.AyoPengamatanFragment;
import site.ishaalim.capungpedia.mengenalDesa.FragmentMengenalDesa;
import site.ishaalim.capungpedia.panduanIdentifikasi.FragmentPanduanIdentifikasi;
import site.ishaalim.capungpedia.panduanPengamatan.FragmentPanduanPengamatan;

public class ListMateriAdapter extends RecyclerView.Adapter<ListMateriViewHolder> {

    private Context context;
    ArrayList<listMateri> listMateriArrayList;
    String idmateri;
    RequestOptions options;
    Fragment fragment;
    String TAG;

    public ListMateriAdapter(Context context, ArrayList<listMateri> listMateriArrayList) {
        this.context = context;
        this.listMateriArrayList = listMateriArrayList;
        options = new RequestOptions().centerCrop();
    }

    @NonNull
    @Override
    public ListMateriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_rv_materi, parent, false);

        return new ListMateriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMateriViewHolder holder, final int position) {

        idmateri = listMateriArrayList.get(position).getId();
        holder.tvJudul.setText(listMateriArrayList.get(position).getJudul());
        holder.tvDeskripsi.setText(listMateriArrayList.get(position).getDeskripsi());

        Glide.with(holder.itemView)
                .load(listMateriArrayList.get(position).getImageURL())
                .apply(options)
                .into(holder.ivMateri);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                switch (position){
                    case 0:
                        fragment = new FragmentPendahuluan();
                        TAG = "pendahuluan";
                        ((MainActivity) context).setFragment(fragment, TAG);
                        break;
                    case 1:
                        fragment = new FragmentMengenalCapung();
                        TAG = "mengenal_capung";
                        ((MainActivity) context).setFragment(fragment, TAG);
                        break;
                    case 2:
                        fragment = new FragmentMengenalDesa();
                        TAG = "mengenal_kawasan_desa";
                        ((MainActivity) context).setFragment(fragment, TAG);
                        break;
                    case 3:
                        fragment = new FragmentPanduanPengamatan();
                        TAG = "panduan_pengamatan";
                        ((MainActivity) context).setFragment(fragment, TAG);
                        break;
                    case 4:
                        fragment = new FragmentPanduanIdentifikasi();
                        TAG = "panduan_Identifikasi";
                        ((MainActivity) context).setFragment(fragment, TAG);
                        break;
                    case 5:
                        fragment = new IdentifikasiCapungFragment();
                        TAG = "identifikasi_capung";
                        ((MainActivity) context).setFragment(fragment, TAG);
                        break;

                    case 6:
                        fragment = new AyoPengamatanFragment();
                        TAG = "ayo_pengamatan";
                        ((MainActivity) context).setFragment(fragment, TAG);
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listMateriArrayList.size();
    }
}
