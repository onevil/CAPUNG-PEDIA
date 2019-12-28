package site.ishaalim.capungpedia.panduanPengamatan.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import site.ishaalim.capungpedia.R;

public class PanduanPengamatanViewHoder extends RecyclerView.ViewHolder {
    public ImageView ivHeaderPanduanPengamatan;
    public TextView tvHeaderPanduanPengamatan, tvIsiPanduanPengamatan;
    public CardView cvHeaderPanduanPengamatan;
    public PanduanPengamatanViewHoder(@NonNull View itemView) {
        super(itemView);

        ivHeaderPanduanPengamatan = itemView.findViewById(R.id.iv_header_panduan_pengamatan);
        tvHeaderPanduanPengamatan = itemView.findViewById(R.id.tv_header_panduan_pengamatan);
        tvIsiPanduanPengamatan = itemView.findViewById(R.id.tv_isi_panduan_pengamatan);
        cvHeaderPanduanPengamatan = itemView.findViewById(R.id.cv_iv_header_panduan_pengamatan);

        cvHeaderPanduanPengamatan.setVisibility(View.GONE);
        ivHeaderPanduanPengamatan.setVisibility(View.GONE);
    }
}
