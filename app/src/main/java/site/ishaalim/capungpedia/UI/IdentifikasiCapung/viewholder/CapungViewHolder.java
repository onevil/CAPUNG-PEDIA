package site.ishaalim.capungpedia.UI.IdentifikasiCapung.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import site.ishaalim.capungpedia.R;

public class CapungViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNamaSpesies, tvnamaFamili;
    public ImageView ivCapung;

    public CapungViewHolder(@NonNull View itemView) {
        super(itemView);

        ivCapung = itemView.findViewById(R.id.iv_capung);
        tvNamaSpesies = itemView.findViewById(R.id.tv_namaSpesies);
        tvnamaFamili = itemView.findViewById(R.id.tv_namaFamili);

    }
}
