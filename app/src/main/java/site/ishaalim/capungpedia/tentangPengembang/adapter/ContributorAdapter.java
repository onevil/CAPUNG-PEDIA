package site.ishaalim.capungpedia.tentangPengembang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import site.ishaalim.capungpedia.Evaluasi.viewholder.listSoalViewHolder;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.tentangPengembang.ViewHolder.ContributorHolder;
import site.ishaalim.capungpedia.tentangPengembang.model.Contributor;


public class ContributorAdapter extends RecyclerView.Adapter<ContributorHolder> {
    ArrayList<Contributor> contributorArrayList;
    Context context;

    public ContributorAdapter(ArrayList<Contributor> contributorArrayList, Context context) {
        this.contributorArrayList = contributorArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ContributorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_list_view, parent, false);
        return new ContributorHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContributorHolder holder, int position) {
       holder.tvNama.setText(contributorArrayList.get(position).getNama());
    }

    @Override
    public int getItemCount() {
        return contributorArrayList.size();
    }
}
