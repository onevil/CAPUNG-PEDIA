package site.ishaalim.capungpedia.UI.ayoPengamatan.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.UI.ayoPengamatan.adapter.tambahPengamatanAdapter;

public class tambahPengamatanVH extends RecyclerView.ViewHolder {
    public TextView tvTambahNamaPengamat, tvTambahDeskripsi;
    public ImageView ivTambahPengamtan, ivDeletePengamatan;



    public tambahPengamatanVH(@NonNull View itemView, final tambahPengamatanAdapter.OnDeletePengamatan listener) {
        super(itemView);

        tvTambahNamaPengamat = itemView.findViewById(R.id.tv_tambah_nama_pengamat);
        tvTambahDeskripsi = itemView.findViewById(R.id.tv_tambah_deskripsi_pengamtan);
        ivTambahPengamtan = itemView.findViewById(R.id.iv_tambah_pengamatan);
        ivDeletePengamatan = itemView.findViewById(R.id.iv_delete_pengamatan);


    }
}
