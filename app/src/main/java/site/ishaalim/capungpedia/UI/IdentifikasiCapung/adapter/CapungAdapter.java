package site.ishaalim.capungpedia.UI.IdentifikasiCapung.adapter;

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
import site.ishaalim.capungpedia.UI.IdentifikasiCapung.DetailCapungActivity;
import site.ishaalim.capungpedia.UI.IdentifikasiCapung.model.Capung;
import site.ishaalim.capungpedia.UI.IdentifikasiCapung.viewholder.CapungViewHolder;
import site.ishaalim.capungpedia.R;

public class CapungAdapter extends RecyclerView.Adapter<CapungViewHolder> {

    private Context context;
    private ArrayList<Capung> capungArrayList;
    RequestOptions options;


    public CapungAdapter(Context context, ArrayList<Capung> capungArrayList) {
        this.context = context;
        this.capungArrayList = capungArrayList;
        options = new RequestOptions().centerCrop();
    }

    @NonNull
    @Override
    public CapungViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_rv_capung, parent, false);
        return new CapungViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CapungViewHolder holder, final int position) {
        holder.tvNamaSpesies.setText(Html.fromHtml(capungArrayList.get(position).getNamaSpesies()));
        holder.tvnamaFamili.setText(capungArrayList.get(position).getFamili());

        Glide.with(holder.itemView).load(capungArrayList.get(position).getImageURL())
                .apply(options).into(holder.ivCapung);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailCapungActivity.class);
                intent.putExtra("namaSpesies", capungArrayList.get(position).getNamaSpesies());
                intent.putExtra("namaIndonesia", capungArrayList.get(position).getNamaIndo());
                intent.putExtra("namaInggris", capungArrayList.get(position).getNamaIng());
                intent.putExtra("familli", capungArrayList.get(position).getFamili());
                intent.putExtra("deskripsi", capungArrayList.get(position).getDeskripsi());
                intent.putExtra("kebiasaan", capungArrayList.get(position).getKebiasaan());
                intent.putExtra("sosial", capungArrayList.get(position).getSosial());
                intent.putExtra("informasiLain", capungArrayList.get(position).getInfoLain());
                intent.putExtra("image1", capungArrayList.get(position).getImageURL());
                intent.putExtra("image1Caption", capungArrayList.get(position).getImageCaption());
                intent.putExtra("image1Photographer", capungArrayList.get(position).getImagePhotographer());
                intent.putExtra("ukuran", capungArrayList.get(position).getUkuran());
                intent.putExtra("habitat", capungArrayList.get(position).getHabitat());
                intent.putExtra("images", capungArrayList.get(position).getImages());
                intent.putExtra("fotoOleh", capungArrayList.get(position).getFotoOleh());
                intent.putExtra("captions", capungArrayList.get(position).getCaptions());
                context.startActivity(intent);
                CustomIntent.customType(context, "left-to-right");

            }
        });



    }

    @Override
    public int getItemCount() {
        return capungArrayList.size();
    }

}
