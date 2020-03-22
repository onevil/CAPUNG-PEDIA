package site.ishaalim.capungpedia.UI.referensi.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import site.ishaalim.capungpedia.R;

public class ReferensiViewHolder extends RecyclerView.ViewHolder {
    public TextView tvReferensi;

    public ReferensiViewHolder(@NonNull View itemView) {
        super(itemView);


        tvReferensi = itemView.findViewById(R.id.tv_referensi);

    }
}
