package site.ishaalim.capungpedia.MengenalCapung.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import site.ishaalim.capungpedia.R;

public class MengenalCapungViewHoder extends RecyclerView.ViewHolder {
    public ImageView ivHeaderMengenalCapung;
    public TextView tvHeaderMengenalCapung, tvIsiMengenalCapung, tvCaption;
    public CardView cvHeaderMengenalCapung;
    public MengenalCapungViewHoder(@NonNull View itemView) {
        super(itemView);

        ivHeaderMengenalCapung = itemView.findViewById(R.id.iv_header_mengenal_capung);
        tvHeaderMengenalCapung = itemView.findViewById(R.id.tv_header_mengenal_capung);
        tvIsiMengenalCapung = itemView.findViewById(R.id.tv_isi_mengenal_capung);
        tvCaption = itemView.findViewById(R.id.tv_caption);
        cvHeaderMengenalCapung = itemView.findViewById(R.id.cv_iv_header_mengenal_capung);

        cvHeaderMengenalCapung.setVisibility(View.GONE);
        ivHeaderMengenalCapung.setVisibility(View.GONE);
    }
}
