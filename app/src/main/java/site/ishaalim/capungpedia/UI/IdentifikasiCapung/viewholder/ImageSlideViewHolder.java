package site.ishaalim.capungpedia.UI.IdentifikasiCapung.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.chrisbanes.photoview.PhotoView;

import site.ishaalim.capungpedia.R;

public class ImageSlideViewHolder extends RecyclerView.ViewHolder {
    public PhotoView CapungPV;
    public TextView PhotoGraferTV, CaptionTV, namaIndo, namaIng;

    public ImageSlideViewHolder(@NonNull View itemView) {
        super(itemView);

        CapungPV = itemView.findViewById(R.id.capung_pv);
        PhotoGraferTV = itemView.findViewById(R.id.tv_photografer);
        CaptionTV = itemView.findViewById(R.id.tv_caption);
        namaIndo = itemView.findViewById(R.id.tv_nama_indonesia);
        namaIng = itemView.findViewById(R.id.tv_nama_inggris);

    }
}
