package site.ishaalim.capungpedia.petunjukPenggunaan.adapter;

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
    public void onBindViewHolder(@NonNull PetunjukPenggunaanViewHoder holder, int position) {
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


    }

    @Override
    public int getItemCount() {
        return isiHalamanPetunjukPenggunaanArrayList.size();
    }
}
