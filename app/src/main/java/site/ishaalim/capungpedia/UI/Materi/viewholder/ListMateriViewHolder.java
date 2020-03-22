package site.ishaalim.capungpedia.UI.Materi.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import site.ishaalim.capungpedia.R;

public class ListMateriViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivMateri;
    public TextView tvJudul,tvDeskripsi;

    public ListMateriViewHolder(@NonNull View itemView) {
        super(itemView);

        ivMateri = itemView.findViewById(R.id.iv_materi);
        tvJudul = itemView.findViewById(R.id.tv_judul_materi);
        tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi_materi);
    }
}
