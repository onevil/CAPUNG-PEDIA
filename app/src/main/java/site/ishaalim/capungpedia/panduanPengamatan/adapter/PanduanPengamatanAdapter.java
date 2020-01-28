package site.ishaalim.capungpedia.panduanPengamatan.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.panduanPengamatan.model.isiHalamanPanduanPengamatan;
import site.ishaalim.capungpedia.panduanPengamatan.viewholder.PanduanPengamatanViewHoder;

public class PanduanPengamatanAdapter extends RecyclerView.Adapter<PanduanPengamatanViewHoder> {
    private Context context;
    ArrayList<isiHalamanPanduanPengamatan> isiHalamanPanduanPengamatanArrayList;
    RequestOptions options;

    public PanduanPengamatanAdapter(Context context, ArrayList<isiHalamanPanduanPengamatan> isiHalamanPanduanPengamatanArrayList) {
        this.context = context;
        this.isiHalamanPanduanPengamatanArrayList = isiHalamanPanduanPengamatanArrayList;
        options = new RequestOptions().centerCrop();
    }

    @NonNull
    @Override
    public PanduanPengamatanViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_rv_panduan_pengamatan, parent, false);
        return new PanduanPengamatanViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final PanduanPengamatanViewHoder holder, int position) {
        if(isiHalamanPanduanPengamatanArrayList.get(position).getImageURL() == null){
            holder.cvHeaderPanduanPengamatan.setVisibility(View.GONE);
            holder.ivHeaderPanduanPengamatan.setVisibility(View.GONE);
        }else {
            holder.cvHeaderPanduanPengamatan.setVisibility(View.VISIBLE);
            holder.ivHeaderPanduanPengamatan.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView).load(isiHalamanPanduanPengamatanArrayList.get(position).getImageURL()).apply(options).into(holder.ivHeaderPanduanPengamatan);
        }

        holder.tvHeaderPanduanPengamatan.setText(isiHalamanPanduanPengamatanArrayList.get(position).getHeader());
        String isi = isiHalamanPanduanPengamatanArrayList.get(position).getIsi();
        holder.tvIsiPanduanPengamatan.setText(isi);

        holder.ivHeaderPanduanPengamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(holder.ivHeaderPanduanPengamatan.getDrawable());
            }
        });


    }

    @Override
    public int getItemCount() {
        return isiHalamanPanduanPengamatanArrayList.size();
    }

    private void showImage(Drawable drawable) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        View mView = LayoutInflater.from(context).inflate(R.layout.dialog_photoview, null);
        PhotoView photoView = mView.findViewById(R.id.imageView);

        photoView.setImageDrawable(drawable);


        mBuilder.setView(mView);
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }
}
