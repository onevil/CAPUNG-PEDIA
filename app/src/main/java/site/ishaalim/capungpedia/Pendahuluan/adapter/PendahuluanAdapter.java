package site.ishaalim.capungpedia.Pendahuluan.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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

import site.ishaalim.capungpedia.Pendahuluan.model.isiHalamanPendahuluan;
import site.ishaalim.capungpedia.Pendahuluan.viewholder.PendahuluanViewHolder;
import site.ishaalim.capungpedia.R;

public class PendahuluanAdapter extends RecyclerView.Adapter<PendahuluanViewHolder> {
    private Context context;
    ArrayList<isiHalamanPendahuluan> isiHalamanPendahuluanArrayList;
    RequestOptions options;

    public PendahuluanAdapter(Context context, ArrayList<isiHalamanPendahuluan> isiHalamanPendahuluanArrayList) {
        this.context = context;
        this.isiHalamanPendahuluanArrayList = isiHalamanPendahuluanArrayList;
        options = new RequestOptions().centerCrop();
    }

    @NonNull
    @Override
    public PendahuluanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_rv_pendahuluan, parent, false);
        return new PendahuluanViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final PendahuluanViewHolder holder, final int position) {
        if(isiHalamanPendahuluanArrayList.get(position).getImageURL() == null){
            holder.cvHeaderPendahuluan.setVisibility(View.GONE);
            holder.ivHeaderPendahuluan.setVisibility(View.GONE);
        }else {
            holder.cvHeaderPendahuluan.setVisibility(View.VISIBLE);
            holder.ivHeaderPendahuluan.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView).load(isiHalamanPendahuluanArrayList.get(position).getImageURL()).apply(options).into(holder.ivHeaderPendahuluan);
        }

        holder.tvCaptions.setText(isiHalamanPendahuluanArrayList.get(position).getImageCaption());

        if(isiHalamanPendahuluanArrayList.get(position).getHeader() == null){
            holder.tvHeaderPendahuluan.setVisibility(View.GONE);
        }else {
            holder.tvHeaderPendahuluan.setVisibility(View.VISIBLE);
            holder.tvHeaderPendahuluan.setText(isiHalamanPendahuluanArrayList.get(position).getHeader());
        }

        if(isiHalamanPendahuluanArrayList.get(position).getIsi() == null){
            holder.tvIsiPendahuluan.setVisibility(View.GONE);
        }else {
            holder.tvIsiPendahuluan.setVisibility(View.VISIBLE);
            holder.tvIsiPendahuluan.setText(isiHalamanPendahuluanArrayList.get(position).getIsi());
        }

        if(isiHalamanPendahuluanArrayList.get(position).getQuotes() == null){
            holder.tvQuotes.setVisibility(View.GONE);
        }else {
            holder.tvQuotes.setVisibility(View.VISIBLE);
            String quotes = isiHalamanPendahuluanArrayList.get(position).getQuotes();
            holder.tvQuotes.setText('"' + quotes + '"');
        }

        if(isiHalamanPendahuluanArrayList.get(position).getAuthor() == null){
            holder.tvAuthor.setVisibility(View.GONE);
        }else {
            holder.tvAuthor.setVisibility(View.VISIBLE);
            holder.tvAuthor.setText("- " + isiHalamanPendahuluanArrayList.get(position).getAuthor());
        }


        holder.ivHeaderPendahuluan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String caption, fotografer;
                caption = isiHalamanPendahuluanArrayList.get(position).getImageCaption();
                fotografer = isiHalamanPendahuluanArrayList.get(position).getImagePhotographer();
                showImage(holder.ivHeaderPendahuluan.getDrawable() ,caption, fotografer);
            }
        });

    }

    @Override
    public int getItemCount() {
        return isiHalamanPendahuluanArrayList.size();
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
