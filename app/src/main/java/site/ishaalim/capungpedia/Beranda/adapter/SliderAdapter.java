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
    int size;
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
                viewHolder.tvHeader.setText("Capung-hutan pita-tunggal (Drepanosticta gazella) capung endemik Pulau Jawa");
                Glide.with(viewHolder.itemView)
                        .load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Beranda_Header%201.jpg?alt=media&token=e45befe3-6bd6-4ff1-a395-ee0e0c8061ad")
                        .apply(options)
                        .into(viewHolder.imageViewBackGround);
                break;
            case 1:
                viewHolder.tvHeader.setText("Salah satu habitat capung di kawasan Desa Wisata Jatimulyo");
                Glide.with(viewHolder.itemView)
                        .load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Beranda_Header%202.jpg?alt=media&token=ac4c48e0-0db5-4595-b2ce-c49c82bd3031")
                        .apply(options)
                        .into(viewHolder.imageViewBackGround);
                break;
            case 2:
                viewHolder.tvHeader.setText("Capung-jarum gelap (Prodasineura autumnalis) pada posisi kopulasi");
                Glide.with(viewHolder.itemView)
                        .load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Beranda_Header%203.jpg?alt=media&token=d530c794-fa98-465e-82f5-4db61cb6b685")
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
