package site.ishaalim.capungpedia.UI.Evaluasi.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import site.ishaalim.capungpedia.R;

public class listSoalViewHolder extends RecyclerView.ViewHolder {
    public TextView tvPaketSoal;

    public listSoalViewHolder(@NonNull View itemView) {
        super(itemView);

        tvPaketSoal = itemView.findViewById(R.id.tv_paket_soal);

    }
}
