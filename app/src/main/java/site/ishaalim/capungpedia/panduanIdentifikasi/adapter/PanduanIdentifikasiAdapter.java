package site.ishaalim.capungpedia.panduanIdentifikasi.adapter;

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
import site.ishaalim.capungpedia.panduanIdentifikasi.model.isiHalamanPanduanIdentifikasi;
import site.ishaalim.capungpedia.panduanIdentifikasi.viewholder.PanduanIdentifikasiViewHoder;

public class PanduanIdentifikasiAdapter extends RecyclerView.Adapter<PanduanIdentifikasiViewHoder> {
    private Context context;
    ArrayList<isiHalamanPanduanIdentifikasi> isiHalamanPanduanIdentifikasiArrayList;
    RequestOptions options;

    public PanduanIdentifikasiAdapter(Context context, ArrayList<isiHalamanPanduanIdentifikasi> isiHalamanPanduanIdentifikasiArrayList) {
        this.context = context;
        this.isiHalamanPanduanIdentifikasiArrayList = isiHalamanPanduanIdentifikasiArrayList;
        options = new RequestOptions().centerCrop();
    }

    @NonNull
    @Override
    public PanduanIdentifikasiViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_rv_panduan_identifikasi, parent, false);
        return new PanduanIdentifikasiViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final PanduanIdentifikasiViewHoder holder, int position) {
        if(isiHalamanPanduanIdentifikasiArrayList.get(position).getImageURL() == null){
            holder.cvHeaderPanduanIdentifikasi.setVisibility(View.GONE);
            holder.ivHeaderPanduanIdentifikasi.setVisibility(View.GONE);
        }else {
            holder.cvHeaderPanduanIdentifikasi.setVisibility(View.VISIBLE);
            holder.ivHeaderPanduanIdentifikasi.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView).load(isiHalamanPanduanIdentifikasiArrayList.get(position).getImageURL()).apply(options).into(holder.ivHeaderPanduanIdentifikasi);
        }

        holder.tvHeaderPanduanIdentifikasi.setText(isiHalamanPanduanIdentifikasiArrayList.get(position).getHeader());
        String isi = isiHalamanPanduanIdentifikasiArrayList.get(position).getIsi();
        holder.tvIsiPanduanIdentifikasi.setText(isi);

        holder.ivHeaderPanduanIdentifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(holder.ivHeaderPanduanIdentifikasi.getDrawable());
            }
        });


    }

    @Override
    public int getItemCount() {
        return isiHalamanPanduanIdentifikasiArrayList.size();
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
