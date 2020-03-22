package site.ishaalim.capungpedia.UI.Pendahuluan.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import site.ishaalim.capungpedia.R;

public class PendahuluanViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivHeaderPendahuluan;
    public TextView tvHeaderPendahuluan, tvIsiPendahuluan, tvQuotes, tvAuthor, tvCaptions;
    public CardView cvHeaderPendahuluan;
    public PendahuluanViewHolder(@NonNull View itemView) {
        super(itemView);

        ivHeaderPendahuluan = itemView.findViewById(R.id.iv_header_pendahuluan);
        tvHeaderPendahuluan = itemView.findViewById(R.id.tv_header_pendahuluan);
        tvIsiPendahuluan = itemView.findViewById(R.id.tv_isi_pendahuluan);
        tvQuotes = itemView.findViewById(R.id.tv_quotes);
        tvAuthor = itemView.findViewById(R.id.tv_quotes_author);
        tvCaptions = itemView.findViewById(R.id.tv_caption);
        cvHeaderPendahuluan = itemView.findViewById(R.id.cv_iv_header_pendahuluan);

        cvHeaderPendahuluan.setVisibility(View.GONE);
        ivHeaderPendahuluan.setVisibility(View.GONE);
    }
}
