package site.ishaalim.capungpedia.IdentifikasiCapung.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import maes.tech.intentanim.CustomIntent;
import site.ishaalim.capungpedia.IdentifikasiCapung.DetailCapungActivity;
import site.ishaalim.capungpedia.IdentifikasiCapung.model.Capung;
import site.ishaalim.capungpedia.IdentifikasiCapung.viewholder.CapungViewHolder;
import site.ishaalim.capungpedia.IdentifikasiCapung.viewholder.ImageSlideViewHolder;
import site.ishaalim.capungpedia.R;

public class ImageSlideAdapter extends RecyclerView.Adapter<ImageSlideViewHolder> {
    private ArrayList<String> ImageURL;
    private ArrayList<String> captions;
    private ArrayList<String> photographers;
    private String namaIndo;
    private String namaIng;
    RequestOptions options;
    Context context;

    public ImageSlideAdapter(ArrayList<String> imageURL, ArrayList<String> captions, ArrayList<String> photographers, String namaIndo, String namaIng, Context context) {
        ImageURL = imageURL;
        this.captions = captions;
        this.photographers = photographers;
        this.namaIndo = namaIndo;
        this.namaIng = namaIng;
        this.context = context;
        options = new RequestOptions().fitCenter();
    }

    @NonNull
    @Override
    public ImageSlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater =  LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_dialog_photoview_capung, parent, false);
        return new ImageSlideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSlideViewHolder holder, int position) {
        Glide.with(context).load(ImageURL.get(position)).apply(options).into(holder.CapungPV);
        holder.CaptionTV.setText(Html.fromHtml(captions.get(position)));
        holder.PhotoGraferTV.setText(photographers.get(position));
        holder.namaIndo.setText(Html.fromHtml(namaIndo));
        holder.namaIng.setText(Html.fromHtml(namaIng));

    }

    @Override
    public int getItemCount() {
        return ImageURL.size();
    }
}