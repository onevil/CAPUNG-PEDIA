package site.ishaalim.capungpedia.MengenalCapung.adapter;

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
    public void onBindViewHolder(@NonNull final MengenalCapungViewHoder holder, int position) {
        if(isiHalamanMengenalCapungArrayList.get(position).getImageURL() == null){
            holder.cvHeaderMengenalCapung.setVisibility(View.GONE);
            holder.ivHeaderMengenalCapung.setVisibility(View.GONE);
        }else {
            holder.cvHeaderMengenalCapung.setVisibility(View.VISIBLE);
            holder.ivHeaderMengenalCapung.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView).load(isiHalamanMengenalCapungArrayList.get(position).getImageURL()).apply(options).into(holder.ivHeaderMengenalCapung);
        }

        holder.tvHeaderMengenalCapung.setText(isiHalamanMengenalCapungArrayList.get(position).getHeader());
        holder.tvIsiMengenalCapung.setText(isiHalamanMengenalCapungArrayList.get(position).getIsi());

        holder.ivHeaderMengenalCapung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(holder.ivHeaderMengenalCapung.getDrawable());
            }
        });

    }

    @Override
    public int getItemCount() {
        return isiHalamanMengenalCapungArrayList.size();
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
