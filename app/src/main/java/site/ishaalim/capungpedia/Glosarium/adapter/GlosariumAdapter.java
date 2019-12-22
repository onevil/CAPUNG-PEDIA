package site.ishaalim.capungpedia.Glosarium.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import site.ishaalim.capungpedia.Glosarium.model.Glosarium;
import site.ishaalim.capungpedia.Glosarium.viewholder.GlosariumViewHolder;
import site.ishaalim.capungpedia.R;

public class GlosariumAdapter extends RecyclerView.Adapter<GlosariumViewHolder> {
    private Context context;
    ArrayList<Glosarium> glosariumArrayList;

    public GlosariumAdapter(Context context, ArrayList<Glosarium> glosariumArrayList) {
        this.context = context;
        this.glosariumArrayList = glosariumArrayList;
    }

    @NonNull
    @Override
    public GlosariumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_rv_glosarium, parent, false);
        return new GlosariumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GlosariumViewHolder holder, int position) {
        holder.tvKataKunci.setText(glosariumArrayList.get(position).getKataKunci());
        holder.tvPengertian.setText(glosariumArrayList.get(position).getPengertian());
    }

    @Override
    public int getItemCount() {
        return glosariumArrayList.size();
    }
}
