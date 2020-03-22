package site.ishaalim.capungpedia.UI.Materi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import site.ishaalim.capungpedia.UI.Materi.model.isiMateri;
import site.ishaalim.capungpedia.UI.Materi.viewholder.IsiMateriViewHolder;
import site.ishaalim.capungpedia.R;

public class IsiMateriAdapter extends RecyclerView.Adapter<IsiMateriViewHolder> {

    private Context context;
    ArrayList<isiMateri> isiMateriArrayList;
    RequestOptions options;

    public IsiMateriAdapter(Context context, ArrayList<isiMateri> isiMateriArrayList) {
        this.context = context;
        this.isiMateriArrayList = isiMateriArrayList;
        options = new RequestOptions().centerCrop();
    }

    @NonNull
    @Override
    public IsiMateriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_rv_detail_materi, parent, false);
        return new IsiMateriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IsiMateriViewHolder holder, int position) {
        if(isiMateriArrayList.get(position).getImageURL() == null){
            holder.cvHeaderMateri.setVisibility(View.GONE);
            holder.ivDetailHeaderMateri.setVisibility(View.GONE);
        }else {
            holder.cvHeaderMateri.setVisibility(View.VISIBLE);
            holder.ivDetailHeaderMateri.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView).load(isiMateriArrayList.get(position).getImageURL()).apply(options).into(holder.ivDetailHeaderMateri);
        }

        holder.tvHeaderMateri.setText(isiMateriArrayList.get(position).getHeaderMateri());
        holder.tvBahasanMateri.setText(isiMateriArrayList.get(position).getBahasanMateri());


    }

    @Override
    public int getItemCount() {
        return isiMateriArrayList.size();
    }
}
