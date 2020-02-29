package site.ishaalim.capungpedia.panduanIdentifikasi.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import site.ishaalim.capungpedia.R;

public class PanduanIdentifikasiViewHoder extends RecyclerView.ViewHolder {
    public ImageView ivHeaderPanduanIdentifikasi;
    public TextView tvHeaderPanduanIdentifikasi, tvIsiPanduanIdentifikasi, tvCaptions;
    public CardView cvHeaderPanduanIdentifikasi;
    public PanduanIdentifikasiViewHoder(@NonNull View itemView) {
        super(itemView);

        ivHeaderPanduanIdentifikasi = itemView.findViewById(R.id.iv_header_panduan_identifikasi);
        tvHeaderPanduanIdentifikasi = itemView.findViewById(R.id.tv_header_panduan_identifikasi);
        tvIsiPanduanIdentifikasi = itemView.findViewById(R.id.tv_isi_panduan_identifikasi);
        tvCaptions = itemView.findViewById(R.id.tv_caption);
        cvHeaderPanduanIdentifikasi = itemView.findViewById(R.id.cv_iv_header_panduan_identifikasi);

        cvHeaderPanduanIdentifikasi.setVisibility(View.GONE);
        ivHeaderPanduanIdentifikasi.setVisibility(View.GONE);
    }
}
