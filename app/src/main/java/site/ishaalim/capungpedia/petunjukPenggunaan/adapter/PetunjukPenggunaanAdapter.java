package site.ishaalim.capungpedia.petunjukPenggunaan.adapter;

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
import site.ishaalim.capungpedia.petunjukPenggunaan.model.isiHalamanPetunjukPenggunaan;
import site.ishaalim.capungpedia.petunjukPenggunaan.viewholder.PetunjukPenggunaanViewHoder;

public class PetunjukPenggunaanAdapter extends RecyclerView.Adapter<PetunjukPenggunaanViewHoder> {
    private Context context;
    ArrayList<isiHalamanPetunjukPenggunaan> isiHalamanPetunjukPenggunaanArrayList;
    RequestOptions options;

    public PetunjukPenggunaanAdapter(Context context, ArrayList<isiHalamanPetunjukPenggunaan> isiHalamanPetunjukPenggunaanArrayList) {
        this.context = context;
        this.isiHalamanPetunjukPenggunaanArrayList = isiHalamanPetunjukPenggunaanArrayList;
        options = new RequestOptions().centerCrop();
    }

    @NonNull
    @Override
    public PetunjukPenggunaanViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_rv_petunjuk_penggunaan, parent, false);
        return new PetunjukPenggunaanViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final PetunjukPenggunaanViewHoder holder, int position) {
        if(isiHalamanPetunjukPenggunaanArrayList.get(position).getImageURL() == null){
            holder.cvHeaderPetunjukPenggunaan.setVisibility(View.GONE);
            holder.ivHeaderPetunjukPenggunaan.setVisibility(View.GONE);
        }else {
            holder.cvHeaderPetunjukPenggunaan.setVisibility(View.VISIBLE);
            holder.ivHeaderPetunjukPenggunaan.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView).load(isiHalamanPetunjukPenggunaanArrayList.get(position).getImageURL()).apply(options).into(holder.ivHeaderPetunjukPenggunaan);
        }

        holder.tvHeaderPetunjukPenggunaan.setText(isiHalamanPetunjukPenggunaanArrayList.get(position).getHeader());
        String isi = isiHalamanPetunjukPenggunaanArrayList.get(position).getIsi();
        holder.tvIsiPetunjukPenggunaan.setText(isi);

        holder.ivHeaderPetunjukPenggunaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(holder.ivHeaderPetunjukPenggunaan.getDrawable());
            }
        });


    }

    @Override
    public int getItemCount() {
        return isiHalamanPetunjukPenggunaanArrayList.size();
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
