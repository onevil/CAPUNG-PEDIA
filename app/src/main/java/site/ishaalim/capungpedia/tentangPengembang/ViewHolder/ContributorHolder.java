package site.ishaalim.capungpedia.tentangPengembang.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import site.ishaalim.capungpedia.R;

public class ContributorHolder extends RecyclerView.ViewHolder {
    public TextView tvNama;
    public ContributorHolder(@NonNull View itemView) {
        super(itemView);

        tvNama = itemView.findViewById(R.id.tv_list_view);
    }
}
