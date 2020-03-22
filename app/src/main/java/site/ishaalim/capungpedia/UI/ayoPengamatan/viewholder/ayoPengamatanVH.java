package site.ishaalim.capungpedia.UI.ayoPengamatan.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import site.ishaalim.capungpedia.R;

public class ayoPengamatanVH extends RecyclerView.ViewHolder {
    public TextView tvnamaPengamatan, tvTanggalPengamatan, tvTempatPengamatan;
    public ImageView ivAyoPengamatan;



    public ayoPengamatanVH(@NonNull View itemView) {
        super(itemView);

        tvnamaPengamatan = itemView.findViewById(R.id.tv_namaPengamatan);
        tvTanggalPengamatan = itemView.findViewById(R.id.tv_tanggalPengamatan);
        tvTempatPengamatan = itemView.findViewById(R.id.tv_tempatPengamatan);
        ivAyoPengamatan = itemView.findViewById(R.id.iv_ayo_pengamatan);



    }
}
