package site.ishaalim.capungpedia.mengenalDesa.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import site.ishaalim.capungpedia.R;

public class MengenalDesaViewHoder extends RecyclerView.ViewHolder {
    public ImageView ivHeaderMengenalDesa;
    public TextView tvHeaderMengenalDesa, tvIsiMengenalDesa;
    public CardView cvHeaderMengenalDesa;
    public MengenalDesaViewHoder(@NonNull View itemView) {
        super(itemView);

        ivHeaderMengenalDesa = itemView.findViewById(R.id.iv_header_mengenal_desa);
        tvHeaderMengenalDesa = itemView.findViewById(R.id.tv_header_mengenal_desa);
        tvIsiMengenalDesa = itemView.findViewById(R.id.tv_isi_mengenal_desa);
        cvHeaderMengenalDesa = itemView.findViewById(R.id.cv_iv_header_mengenal_desa);

        cvHeaderMengenalDesa.setVisibility(View.GONE);
        ivHeaderMengenalDesa.setVisibility(View.GONE);
    }
}
