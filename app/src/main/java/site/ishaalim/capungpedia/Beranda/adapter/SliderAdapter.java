package site.ishaalim.capungpedia.Beranda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

import site.ishaalim.capungpedia.Beranda.viewholder.SliderViewHolder;
import site.ishaalim.capungpedia.R;

public class SliderAdapter extends SliderViewAdapter<SliderViewHolder> {

    private Context context;
    ArrayList<String> linkimage;
    RequestOptions options;

    public SliderAdapter(Context context, ArrayList<String> linkimage) {
        this.context = context;
        this.linkimage = linkimage;
        options = new RequestOptions().centerCrop();
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.image_header_slider_layout_item, parent,false);

        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {

        switch(position){
            case 0:
                viewHolder.tvHeader.setText("Capung Endemik");
                Glide.with(viewHolder.itemView)
                        .load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Header_Beranda1.jpg?alt=media&token=26629a86-cf6c-445e-9e2f-3bffcda9a28f")
                        .apply(options)
                        .into(viewHolder.imageViewBackGround);
                break;
            case 1:
                viewHolder.tvHeader.setText("Royal River Cruiser");
                Glide.with(viewHolder.itemView)
                        .load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Header_Beranda2.jpg?alt=media&token=dc663eff-fc1c-4c4b-b2f3-b99a799e4217")
                        .apply(options)
                        .into(viewHolder.imageViewBackGround);
                break;
            case 2:
                viewHolder.tvHeader.setText("Blue-Eyed Darner");
                Glide.with(viewHolder.itemView)
                        .load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Header_Beranda3.JPG?alt=media&token=2153c5ac-4035-4507-a368-983d204891d9")
                        .apply(options)
                        .into(viewHolder.imageViewBackGround);
                break;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
