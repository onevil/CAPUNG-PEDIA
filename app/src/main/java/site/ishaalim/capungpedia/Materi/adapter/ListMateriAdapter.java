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
import androidx.recyclerview.widget.RecyclerView;
import maes.tech.intentanim.CustomIntent;
import site.ishaalim.capungpedia.DetailMateriActivity;
import site.ishaalim.capungpedia.Materi.model.listMateri;
import site.ishaalim.capungpedia.Materi.viewholder.ListMateriViewHolder;
import site.ishaalim.capungpedia.R;

public class ListMateriAdapter extends RecyclerView.Adapter<ListMateriViewHolder> {

    private Context context;
    ArrayList<listMateri> listMateriArrayList;
    String idmateri;
    RequestOptions options;

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
                Intent intent = new Intent(context, DetailMateriActivity.class);
                intent.putExtra("idMateri", listMateriArrayList.get(position).getId());
                intent.putExtra("judulMateri", listMateriArrayList.get(position).getJudul());
                intent.putExtra("deskripsiMateri", listMateriArrayList.get(position).getDeskripsi());
                intent.putExtra("imageURL", listMateriArrayList.get(position).getImageURL());
                intent.putExtra("jumlahHalaman", listMateriArrayList.get(position).getJumlahHalaman());
                context.startActivity(intent);
                CustomIntent.customType(context, "left-to-right");
            }
        });

    }

    @Override
    public int getItemCount() {
        return listMateriArrayList.size();
    }
}
