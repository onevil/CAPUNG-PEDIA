package site.ishaalim.capungpedia.mengenalDesa.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import maes.tech.intentanim.CustomIntent;
import site.ishaalim.capungpedia.MapsActivity;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.mengenalDesa.model.isiHalamanMengenalDesa;
import site.ishaalim.capungpedia.mengenalDesa.viewholder.MengenalDesaViewHoder;

public class MengenalDesaAdapter extends RecyclerView.Adapter<MengenalDesaViewHoder> {
    private Context context;
    ArrayList<isiHalamanMengenalDesa> isiHalamanMengenalDesaArrayList;
    RequestOptions options;

    public MengenalDesaAdapter(Context context, ArrayList<isiHalamanMengenalDesa> isiHalamanMengenalDesaArrayList) {
        this.context = context;
        this.isiHalamanMengenalDesaArrayList = isiHalamanMengenalDesaArrayList;
        options = new RequestOptions().centerCrop();
    }

    @NonNull
    @Override
    public MengenalDesaViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_rv_mengenal_desa, parent, false);
        return new MengenalDesaViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MengenalDesaViewHoder holder, final int position) {
        if(isiHalamanMengenalDesaArrayList.get(position).getImageURL() == null){
            holder.cvHeaderMengenalDesa.setVisibility(View.GONE);
            holder.ivHeaderMengenalDesa.setVisibility(View.GONE);
        }else {
            holder.cvHeaderMengenalDesa.setVisibility(View.VISIBLE);
            holder.ivHeaderMengenalDesa.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView).load(isiHalamanMengenalDesaArrayList.get(position).getImageURL()).apply(options).into(holder.ivHeaderMengenalDesa);
        }

        if (isiHalamanMengenalDesaArrayList.get(position).getHeader() != null){
            holder.tvHeaderMengenalDesa.setText(Html.fromHtml(isiHalamanMengenalDesaArrayList.get(position).getHeader()));
        }else {
            holder.tvHeaderMengenalDesa.setVisibility(View.GONE);
        }

        if (isiHalamanMengenalDesaArrayList.get(position).getIsi() != null){
            holder.tvIsiMengenalDesa.setText(Html.fromHtml(isiHalamanMengenalDesaArrayList.get(position).getIsi()));
        }else {
            holder.tvIsiMengenalDesa.setVisibility(View.GONE);
        }
        if (isiHalamanMengenalDesaArrayList.get(position).getImageCaption() != null){
            holder.tvCaptions.setText(Html.fromHtml(isiHalamanMengenalDesaArrayList.get(position).getImageCaption()));
        }else {
            holder.tvCaptions.setVisibility(View.GONE);
        }

        if (isiHalamanMengenalDesaArrayList.get(position).getLokasi() != null){
            holder.cvLihatLokasi.setVisibility(View.VISIBLE);
            holder.btnLihatLokasi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MapsActivity.class);
                    context.startActivity(intent);
                    CustomIntent.customType(context, "left-to-right");
                    Toast.makeText(context,"Lokasi", Toast.LENGTH_LONG).show();
                }
            });

        }else {
            holder.cvLihatLokasi.setVisibility(View.GONE);
        }

        holder.ivHeaderMengenalDesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String caption, fotografer;
                caption = isiHalamanMengenalDesaArrayList.get(position).getImageCaption();
                fotografer = isiHalamanMengenalDesaArrayList.get(position).getImagePhotographer();
                showImage(holder.ivHeaderMengenalDesa.getDrawable() ,caption, fotografer);
            }
        });


    }

    @Override
    public int getItemCount() {
        return isiHalamanMengenalDesaArrayList.size();
    }

    private void showImage(Drawable drawable, String caption, String fotografer) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        View mView = LayoutInflater.from(context).inflate(R.layout.dialog_photoview, null);
        PhotoView photoView = mView.findViewById(R.id.imageView);
        TextView tvCaption = mView.findViewById(R.id.tv_caption);
        TextView tvFotofrafer = mView.findViewById(R.id.tv_terima_kasih);
        TextView tvFotoby = mView.findViewById(R.id.tv_photoby);

        photoView.setImageDrawable(drawable);

        if(caption == null){
            tvCaption.setVisibility(View.GONE);
        }else {
            tvCaption.setText(caption);
        }

        if(fotografer == null){
            tvFotofrafer.setVisibility(View.GONE);
        }else {
            tvFotoby.setVisibility(View.VISIBLE);
            tvFotofrafer.setText(fotografer);
        }

        mBuilder.setView(mView);
        AlertDialog mDialog = mBuilder.create();
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();

    }
}
