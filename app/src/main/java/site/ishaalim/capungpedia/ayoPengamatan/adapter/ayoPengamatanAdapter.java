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
import site.ishaalim.capungpedia.ayoPengamatan.model.ayoPengamatan;
import site.ishaalim.capungpedia.ayoPengamatan.viewholder.ayoPengamatanVH;
import site.ishaalim.capungpedia.ayoPengamatan.viewholder.tambahPengamatanVH;

public class ayoPengamatanAdapter extends RecyclerView.Adapter<ayoPengamatanVH> {

    private ArrayList<ayoPengamatan> pengamatanArrayList;
    Context context;
    RequestOptions options;



    public ayoPengamatanAdapter(Context context, ArrayList<ayoPengamatan> pengamatanArrayList) {
        this.pengamatanArrayList = pengamatanArrayList;
        this.context = context;
        options = new RequestOptions().centerCrop();
    }

    @NonNull
    @Override
    public ayoPengamatanVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_rv_ayo_pengamatan, parent,false);

        return new  ayoPengamatanVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ayoPengamatanVH holder, final int position) {
        holder.tvnamaPengamatan.setText(pengamatanArrayList.get(position).getJudulPengamatan());
        holder.tvTanggalPengamatan.setText(pengamatanArrayList.get(position).getTanggalPengamatan());
        holder.tvTempatPengamatan.setText(pengamatanArrayList.get(position).getLokasiPengamatan());

        Glide.with(holder.itemView).load(pengamatanArrayList.get(position).getImageURL())
                .apply(options).into(holder.ivAyoPengamatan);

    }

    @Override
    public int getItemCount() {
        return pengamatanArrayList.size();
    }
}
