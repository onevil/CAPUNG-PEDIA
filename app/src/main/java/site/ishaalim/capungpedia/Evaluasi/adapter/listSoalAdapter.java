package site.ishaalim.capungpedia.Evaluasi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import maes.tech.intentanim.CustomIntent;
import site.ishaalim.capungpedia.Evaluasi.model.listSoal;
import site.ishaalim.capungpedia.Evaluasi.viewholder.listSoalViewHolder;
import site.ishaalim.capungpedia.QuizActivity;
import site.ishaalim.capungpedia.R;

public class listSoalAdapter extends RecyclerView.Adapter<listSoalViewHolder> {
    private Context context;
    ArrayList<listSoal> listSoalArrayList;

    public listSoalAdapter(Context context, ArrayList<listSoal> listSoalArrayList) {
        this.context = context;
        this.listSoalArrayList = listSoalArrayList;
    }

    @NonNull
    @Override
    public listSoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_rv_list_soal, parent, false);
        return new listSoalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listSoalViewHolder holder, final int position) {
        holder.tvPaketSoal.setText(listSoalArrayList.get(position).getNamaSoal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuizActivity.class);
                intent.putExtra("jumlahsoal", listSoalArrayList.get(position).getJumlahsoal());
                intent.putExtra("idsoal", listSoalArrayList.get(position).getIdsoal());
                context.startActivity(intent);
                CustomIntent.customType(context, "left-to-right");
            }
        });

    }

    @Override
    public int getItemCount() {
        return listSoalArrayList.size();
    }
}
