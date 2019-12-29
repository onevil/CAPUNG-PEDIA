package site.ishaalim.capungpedia.mengenalDesa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

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
    public void onBindViewHolder(@NonNull MengenalDesaViewHoder holder, int position) {
        if(isiHalamanMengenalDesaArrayList.get(position).getImageURL() == null){
            holder.cvHeaderMengenalDesa.setVisibility(View.GONE);
            holder.ivHeaderMengenalDesa.setVisibility(View.GONE);
        }else {
            holder.cvHeaderMengenalDesa.setVisibility(View.VISIBLE);
            holder.ivHeaderMengenalDesa.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView).load(isiHalamanMengenalDesaArrayList.get(position).getImageURL()).apply(options).into(holder.ivHeaderMengenalDesa);
        }

        holder.tvHeaderMengenalDesa.setText(isiHalamanMengenalDesaArrayList.get(position).getHeader());
        String isi = isiHalamanMengenalDesaArrayList.get(position).getIsi();
        isi.replace("\n","\n");
        holder.tvIsiMengenalDesa.setText(isi);


    }

    @Override
    public int getItemCount() {
        return isiHalamanMengenalDesaArrayList.size();
    }
}
