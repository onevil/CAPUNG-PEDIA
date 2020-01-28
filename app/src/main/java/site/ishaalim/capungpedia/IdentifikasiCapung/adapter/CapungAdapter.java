package site.ishaalim.capungpedia.IdentifikasiCapung.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import maes.tech.intentanim.CustomIntent;
import site.ishaalim.capungpedia.DetailCapungActivity;
import site.ishaalim.capungpedia.Glosarium.viewholder.GlosariumViewHolder;
import site.ishaalim.capungpedia.IdentifikasiCapung.model.Capung;
import site.ishaalim.capungpedia.IdentifikasiCapung.viewholder.CapungViewHolder;
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
        holder.tvNamaSpesies.setText(capungArrayList.get(position).getNamaSpesies());
        holder.tvnamaFamili.setText(capungArrayList.get(position).getFamili());

        Glide.with(holder.itemView).load(capungArrayList.get(position).getImageURL())
                .apply(options).into(holder.ivCapung);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailCapungActivity.class);
                intent.putExtra("namaSpesies", capungArrayList.get(position).getNamaSpesies());
                intent.putExtra("familli", capungArrayList.get(position).getFamili());
                intent.putExtra("fillum", capungArrayList.get(position).getFillum());
                intent.putExtra("kingdom", capungArrayList.get(position).getKingdom());
                intent.putExtra("kelas", capungArrayList.get(position).getKelas());
                intent.putExtra("ordo", capungArrayList.get(position).getOrdo());
                intent.putExtra("deskripsi", capungArrayList.get(position).getDeskripsi());
                intent.putExtra("image1", capungArrayList.get(position).getImageURL());
                intent.putExtra("image2", capungArrayList.get(position).getImageURL2());
                intent.putExtra("image3", capungArrayList.get(position).getImageURL3());
                intent.putExtra("kepalaJantan", capungArrayList.get(position).getKepala_jantan());
                intent.putExtra("kepalaBetina", capungArrayList.get(position).getKepala_betina());
                intent.putExtra("badanJantan", capungArrayList.get(position).getBadan_jantan());
                intent.putExtra("badanBetina", capungArrayList.get(position).getBadan_betina());
                intent.putExtra("perutJantan", capungArrayList.get(position).getPerut_jantan());
                intent.putExtra("perutBetina", capungArrayList.get(position).getPerut_betina());
                intent.putExtra("kakiJantan", capungArrayList.get(position).getKaki_jantan());
                intent.putExtra("kakiBetina", capungArrayList.get(position).getKaki_betina());
                intent.putExtra("sayapJantan", capungArrayList.get(position).getSayap_jantan());
                intent.putExtra("sayapBetina", capungArrayList.get(position).getSayap_betina());
                intent.putExtra("embelanJantan", capungArrayList.get(position).getEmbelan_jantan());
                intent.putExtra("embelanBetina", capungArrayList.get(position).getEmbelan_betina());
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
