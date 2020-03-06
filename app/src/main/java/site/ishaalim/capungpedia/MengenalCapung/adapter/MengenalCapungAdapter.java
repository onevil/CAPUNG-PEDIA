package site.ishaalim.capungpedia.MengenalCapung.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import site.ishaalim.capungpedia.MengenalCapung.model.isiHalamanMengenalCapung;
import site.ishaalim.capungpedia.MengenalCapung.viewholder.MengenalCapungViewHoder;
import site.ishaalim.capungpedia.R;

public class MengenalCapungAdapter extends RecyclerView.Adapter<MengenalCapungViewHoder> {
    private Context context;
    ArrayList<isiHalamanMengenalCapung> isiHalamanMengenalCapungArrayList;
    RequestOptions options;

    public MengenalCapungAdapter(Context context, ArrayList<isiHalamanMengenalCapung> isiHalamanMengenalCapungArrayList) {
        this.context = context;
        this.isiHalamanMengenalCapungArrayList = isiHalamanMengenalCapungArrayList;
        options = new RequestOptions().centerCrop();
    }

    @NonNull
    @Override
    public MengenalCapungViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_rv_mengenal_capung, parent, false);
        return new MengenalCapungViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MengenalCapungViewHoder holder, final int position) {
        if(isiHalamanMengenalCapungArrayList.get(position).getImageURL() == null){
            holder.cvHeaderMengenalCapung.setVisibility(View.GONE);
            holder.ivHeaderMengenalCapung.setVisibility(View.GONE);
        }else {
            holder.cvHeaderMengenalCapung.setVisibility(View.VISIBLE);
            holder.ivHeaderMengenalCapung.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView).load(isiHalamanMengenalCapungArrayList.get(position).getImageURL()).apply(options).into(holder.ivHeaderMengenalCapung);
        }

        if (isiHalamanMengenalCapungArrayList.get(position).getHeader() != null){
            holder.tvHeaderMengenalCapung.setText(Html.fromHtml(isiHalamanMengenalCapungArrayList.get(position).getHeader()));
        }else {
            holder.tvHeaderMengenalCapung.setVisibility(View.GONE);
        }

        if (isiHalamanMengenalCapungArrayList.get(position).getIsi() != null){
            holder.tvIsiMengenalCapung.setText(Html.fromHtml(isiHalamanMengenalCapungArrayList.get(position).getIsi()));
        }else {
            holder.tvIsiMengenalCapung.setVisibility(View.GONE);
        }

        if (isiHalamanMengenalCapungArrayList.get(position).getImageCaption() != null){
            holder.tvCaption.setText(Html.fromHtml(isiHalamanMengenalCapungArrayList.get(position).getImageCaption()));
        }else {
            holder.tvCaption.setVisibility(View.GONE);
        }

        holder.ivHeaderMengenalCapung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String caption, fotografer;
                caption = isiHalamanMengenalCapungArrayList.get(position).getImageCaption();
                fotografer = isiHalamanMengenalCapungArrayList.get(position).getImagePhotographer();
                showImage(holder.ivHeaderMengenalCapung.getDrawable() ,caption, fotografer);
            }
        });

    }

    @Override
    public int getItemCount() {
        return isiHalamanMengenalCapungArrayList.size();
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
