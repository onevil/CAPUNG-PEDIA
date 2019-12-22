package site.ishaalim.capungpedia.Glosarium.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import site.ishaalim.capungpedia.Glosarium.adapter.GlosariumAdapter;
import site.ishaalim.capungpedia.R;

public class GlosariumViewHolder extends RecyclerView.ViewHolder {
    public TextView tvKataKunci, tvPengertian;

    public GlosariumViewHolder(@NonNull View itemView) {
        super(itemView);

        tvKataKunci = itemView.findViewById(R.id.tv_kata_kunci_glosarium);
        tvPengertian = itemView.findViewById(R.id.tv_pengertian_glosarium);

    }
}
