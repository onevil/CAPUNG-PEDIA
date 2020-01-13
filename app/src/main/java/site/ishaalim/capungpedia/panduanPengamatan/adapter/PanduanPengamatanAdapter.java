package site.ishaalim.capungpedia.panduanPengamatan.adapter;

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
import site.ishaalim.capungpedia.panduanPengamatan.model.isiHalamanPanduanPengamatan;
import site.ishaalim.capungpedia.panduanPengamatan.viewholder.PanduanPengamatanViewHoder;

public class PanduanPengamatanAdapter extends RecyclerView.Adapter<PanduanPengamatanViewHoder> {
    private Context context;
    ArrayList<isiHalamanPanduanPengamatan> isiHalamanPanduanPengamatanArrayList;
    RequestOptions options;

    public PanduanPengamatanAdapter(Context context, ArrayList<isiHalamanPanduanPengamatan> isiHalamanPanduanPengamatanArrayList) {
        this.context = context;
        this.isiHalamanPanduanPengamatanArrayList = isiHalamanPanduanPengamatanArrayList;
        options = new RequestOptions().centerCrop();
    }

    @NonNull
    @Override
    public PanduanPengamatanViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_rv_panduan_pengamatan, parent, false);
        return new PanduanPengamatanViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PanduanPengamatanViewHoder holder, int position) {
        if(isiHalamanPanduanPengamatanArrayList.get(position).getImageURL() == null){
            holder.cvHeaderPanduanPengamatan.setVisibility(View.GONE);
            holder.ivHeaderPanduanPengamatan.setVisibility(View.GONE);
        }else {
            holder.cvHeaderPanduanPengamatan.setVisibility(View.VISIBLE);
            holder.ivHeaderPanduanPengamatan.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView).load(isiHalamanPanduanPengamatanArrayList.get(position).getImageURL()).apply(options).into(holder.ivHeaderPanduanPengamatan);
        }

        holder.tvHeaderPanduanPengamatan.setText(isiHalamanPanduanPengamatanArrayList.get(position).getHeader());
        String isi = isiHalamanPanduanPengamatanArrayList.get(position).getIsi();
        holder.tvIsiPanduanPengamatan.setText(isi);


    }

    @Override
    public int getItemCount() {
        return isiHalamanPanduanPengamatanArrayList.size();
    }
}
