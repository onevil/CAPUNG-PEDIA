package site.ishaalim.capungpedia.UI.ayoPengamatan.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import maes.tech.intentanim.CustomIntent;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.UI.IdentifikasiCapung.DetailCapungActivity;
import site.ishaalim.capungpedia.UI.IdentifikasiCapung.model.Capung;
import site.ishaalim.capungpedia.UI.IdentifikasiCapung.viewholder.CapungViewHolder;
import site.ishaalim.capungpedia.UI.ayoPengamatan.DetailPengamatanFragment;

public class RekomendasiAdapter extends RecyclerView.Adapter<RekomendasiViewHolder> {

    private Context context;
    private ArrayList<Capung> capungArrayList;
    RequestOptions options;

    private setnama listener;

    public interface setnama{
        void setEditText(String nama);
    }

    public RekomendasiAdapter(Context context, ArrayList<Capung> capungArrayList) {
        this.context = context;
        this.capungArrayList = capungArrayList;
        options = new RequestOptions().centerCrop();
    }

    @NonNull
    @Override
    public RekomendasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_rv_rekomendasi, parent, false);
        return new RekomendasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RekomendasiViewHolder holder, final int position) {
        holder.tvNamaSpesies.setText(Html.fromHtml(capungArrayList.get(position).getNamaSpesies()));
        holder.tvnamaFamili.setText(capungArrayList.get(position).getFamili());

        Glide.with(holder.itemView).load(capungArrayList.get(position).getImageURL())
                .apply(options).into(holder.ivCapung);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setEditText(capungArrayList.get(position).getNamaSpesies());
            }
        });
    }

    public String getNama(String nama){
        return nama;
    }

    @Override
    public int getItemCount() {
        return capungArrayList.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (context instanceof setnama){
           listener = (setnama) context;
        }else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        listener = null;
    }
}
