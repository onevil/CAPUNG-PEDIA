package site.ishaalim.capungpedia.Materi.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import site.ishaalim.capungpedia.R;

public class IsiMateriViewHolder extends RecyclerView.ViewHolder {

    public ImageView  ivDetailHeaderMateri;
    public TextView tvHeaderMateri, tvBahasanMateri;
    public CardView cvHeaderMateri;

    public IsiMateriViewHolder(@NonNull View itemView) {
        super(itemView);

        ivDetailHeaderMateri = itemView.findViewById(R.id.iv_header_materi);
        tvHeaderMateri = itemView.findViewById(R.id.tv_detail_header_materi);
        tvBahasanMateri = itemView.findViewById(R.id.tv_detail_isi_materi);
        cvHeaderMateri = itemView.findViewById(R.id.cv_iv_header_materi);

        cvHeaderMateri.setVisibility(View.GONE);
        ivDetailHeaderMateri.setVisibility(View.GONE);
    }
}
