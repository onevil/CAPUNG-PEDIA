package site.ishaalim.capungpedia.petunjukPenggunaan.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import site.ishaalim.capungpedia.R;

public class PetunjukPenggunaanViewHoder extends RecyclerView.ViewHolder {
    public ImageView ivHeaderPetunjukPenggunaan;
    public TextView tvHeaderPetunjukPenggunaan, tvIsiPetunjukPenggunaan;
    public CardView cvHeaderPetunjukPenggunaan;
    public PetunjukPenggunaanViewHoder(@NonNull View itemView) {
        super(itemView);

        ivHeaderPetunjukPenggunaan = itemView.findViewById(R.id.iv_header_petunjuk_penggunaan);
        tvHeaderPetunjukPenggunaan = itemView.findViewById(R.id.tv_header_petunjuk_penggunaan);
        tvIsiPetunjukPenggunaan = itemView.findViewById(R.id.tv_isi_petunjuk_penggunaan);
        cvHeaderPetunjukPenggunaan = itemView.findViewById(R.id.cv_iv_header_petunjuk_penggunaan);

        cvHeaderPetunjukPenggunaan.setVisibility(View.GONE);
        ivHeaderPetunjukPenggunaan.setVisibility(View.GONE);
    }
}
