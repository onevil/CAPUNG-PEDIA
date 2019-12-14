package site.ishaalim.capungpedia.Materi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import site.ishaalim.capungpedia.Materi.model.listMateri;
import site.ishaalim.capungpedia.Materi.viewholder.ListMateriViewHolder;
import site.ishaalim.capungpedia.R;

public class ListMateriAdapter extends RecyclerView.Adapter<ListMateriViewHolder> {

    private Context context;
    ArrayList<listMateri> listMateriArrayList;
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
    public void onBindViewHolder(@NonNull ListMateriViewHolder holder, int position) {
        holder.tvJudul.setText(listMateriArrayList.get(position).getJudul());
        holder.tvDeskripsi.setText(listMateriArrayList.get(position).getDeskripsi());

        Glide.with(holder.itemView)
                .load(listMateriArrayList.get(position).getImageURL())
                .apply(options)
                .into(holder.ivMateri);

    }

    @Override
    public int getItemCount() {
        return listMateriArrayList.size();
    }
}
