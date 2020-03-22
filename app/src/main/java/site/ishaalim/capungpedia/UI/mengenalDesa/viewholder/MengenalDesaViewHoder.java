package site.ishaalim.capungpedia.UI.mengenalDesa.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import site.ishaalim.capungpedia.R;

public class MengenalDesaViewHoder extends RecyclerView.ViewHolder {
    public ImageView ivHeaderMengenalDesa;
    public TextView tvHeaderMengenalDesa, tvIsiMengenalDesa, tvCaptions;
    public CardView cvHeaderMengenalDesa, cvLihatLokasi;
    public Button btnLihatLokasi;
    public MengenalDesaViewHoder(@NonNull View itemView) {
        super(itemView);

        ivHeaderMengenalDesa = itemView.findViewById(R.id.iv_header_mengenal_desa);
        tvHeaderMengenalDesa = itemView.findViewById(R.id.tv_header_mengenal_desa);
        tvIsiMengenalDesa = itemView.findViewById(R.id.tv_isi_mengenal_desa);
        tvCaptions = itemView.findViewById(R.id.tv_caption);
        cvHeaderMengenalDesa = itemView.findViewById(R.id.cv_iv_header_mengenal_desa);
        cvLihatLokasi = itemView.findViewById(R.id.cv_btn_lihat_lokasi);
        btnLihatLokasi = itemView.findViewById(R.id.btn_lihat_lokasi);

        cvHeaderMengenalDesa.setVisibility(View.GONE);
        cvLihatLokasi.setVisibility(View.GONE);
        ivHeaderMengenalDesa.setVisibility(View.GONE);
    }
}
