package site.ishaalim.capungpedia.referensi.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import site.ishaalim.capungpedia.Glosarium.model.Glosarium;
import site.ishaalim.capungpedia.Glosarium.viewholder.GlosariumViewHolder;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.referensi.model.Referensi;
import site.ishaalim.capungpedia.referensi.viewholder.ReferensiViewHolder;

public class ReferensiAdapter extends RecyclerView.Adapter<ReferensiViewHolder> {
    private Context context;
    ArrayList<Referensi> referensiArrayList;

    public ReferensiAdapter(Context context, ArrayList<Referensi> referensiArrayList) {
        this.context = context;
        this.referensiArrayList = referensiArrayList;
    }

    @NonNull
    @Override
    public ReferensiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_rv_referensi, parent, false);
        return new ReferensiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReferensiViewHolder holder, int position) {
        String referensi = referensiArrayList.get(position).getReferensi();
        holder.tvReferensi.setText(Html.fromHtml(referensi));
    }

    @Override
    public int getItemCount() {
        return referensiArrayList.size();
    }
}
