package site.ishaalim.capungpedia.UI.Materi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import site.ishaalim.capungpedia.UI.IdentifikasiCapung.IdentifikasiCapungFragment;
import site.ishaalim.capungpedia.UI.MainActivity;
import site.ishaalim.capungpedia.UI.Materi.model.listMateri;
import site.ishaalim.capungpedia.UI.Materi.viewholder.ListMateriViewHolder;
import site.ishaalim.capungpedia.UI.MengenalCapung.FragmentMengenalCapung;
import site.ishaalim.capungpedia.UI.Pendahuluan.FragmentPendahuluan;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.UI.authentication.LoginFragment;
import site.ishaalim.capungpedia.UI.ayoPengamatan.ListPengamatanFragment;
import site.ishaalim.capungpedia.UI.ayoPengamatan.ParentPengamatanFragment;
import site.ishaalim.capungpedia.UI.mengenalDesa.FragmentMengenalDesa;
import site.ishaalim.capungpedia.UI.panduanIdentifikasi.FragmentPanduanIdentifikasi;
import site.ishaalim.capungpedia.UI.panduanPengamatan.FragmentPanduanPengamatan;

public class ListMateriAdapter extends RecyclerView.Adapter<ListMateriViewHolder> {

    private Context context;
    ArrayList<listMateri> listMateriArrayList;
    String idmateri;
    RequestOptions options;
    Fragment fragment;
    String TAG;

    FirebaseAuth firebaseAuth;

    public ListMateriAdapter(Context context, ArrayList<listMateri> listMateriArrayList) {
        this.context = context;
        this.listMateriArrayList = listMateriArrayList;
        options = new RequestOptions().centerCrop();
        firebaseAuth = FirebaseAuth.getInstance();
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
                        if (firebaseAuth.getCurrentUser() != null){
                            fragment = new ParentPengamatanFragment();
                            TAG = "parent_pengamatan";
                            ((MainActivity) context).setFragment(fragment, TAG);
                        }else {
                            fragment = new LoginFragment();
                            TAG = "login";
                            ((MainActivity) context).setFragment(fragment, TAG);
                        }
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
