package site.ishaalim.capungpedia.ayoPengamatan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.ayoPengamatan.model.Pengamatan;
import site.ishaalim.capungpedia.ayoPengamatan.viewholder.tambahPengamatanVH;

public class tambahPengamatanAdapter extends RecyclerView.Adapter<tambahPengamatanVH> {

    private ArrayList<Pengamatan> pengamatanArrayList;
    public OnDeletePengamatan deletePengamatan;
    Context context;
    RequestOptions options;

    public interface OnDeletePengamatan {
        void onDeleteClick(int position);

    }

    public void setOnItemClickListener(OnDeletePengamatan listener) {
        deletePengamatan = listener;
    }

    public tambahPengamatanAdapter( Context context, ArrayList<Pengamatan> pengamatanArrayList, OnDeletePengamatan deletePengamatan) {
        this.pengamatanArrayList = pengamatanArrayList;
        this.context = context;
        this.deletePengamatan = deletePengamatan;
        options = new RequestOptions().centerCrop();
    }

    @NonNull
    @Override
    public tambahPengamatanVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_rv_tambah_pengamatan, parent,false);

        return new  tambahPengamatanVH(view, deletePengamatan);
    }

    @Override
    public void onBindViewHolder(@NonNull final tambahPengamatanVH holder, final int position) {
        holder.tvTambahNamaPengamat.setText(pengamatanArrayList.get(position).getNamaPengamat());
        holder.tvTambahDeskripsi.setText(pengamatanArrayList.get(position).getDeskripsi());
//        holder.ivTambahPengamtan.setImageURI(pengamatanArrayList.get(position).getImageUri());
        Glide.with(holder.itemView)
                .load(pengamatanArrayList.get(position).getImageUri())
                .apply(options)
                .into(holder.ivTambahPengamtan);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deletePengamatan != null) {
                    if (position != RecyclerView.NO_POSITION) {
                        deletePengamatan.onDeleteClick(position);
                        holder.tvTambahDeskripsi.setText("Deskripsi");
                    }

                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return pengamatanArrayList.size();
    }
}
