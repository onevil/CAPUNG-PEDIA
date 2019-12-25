package site.ishaalim.capungpedia.IdentifikasiCapung.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

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
    public void onBindViewHolder(@NonNull CapungViewHolder holder, int position) {
        holder.tvNamaSpesies.setText(capungArrayList.get(position).getNamaSpesies());
        holder.tvnamaFamili.setText(capungArrayList.get(position).getFamili());

        Glide.with(holder.itemView).load(capungArrayList.get(position).getImageURL())
                .apply(options).into(holder.ivCapung);

    }

    @Override
    public int getItemCount() {
        return capungArrayList.size();
    }
}
