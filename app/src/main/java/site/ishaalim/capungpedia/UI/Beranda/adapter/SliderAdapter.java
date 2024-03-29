package site.ishaalim.capungpedia.UI.Beranda.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

import site.ishaalim.capungpedia.UI.Beranda.viewholder.SliderViewHolder;
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
        String link1 = "https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Beranda_Header%201.jpg?alt=media&token=bc30d17d-5093-4c03-b829-d05e4b0d4493";
        String link2 = "https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Beranda_Header%202.jpg?alt=media&token=4e2d03fe-34e5-489d-b4ee-f7e5aa1f59cc";
        String link3 = "https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Beranda_Header%203.jpg?alt=media&token=fff9c7b9-d905-46d9-b6d4-34a92eacc003";

        switch(position){
            case 0:
                viewHolder.tvHeader.setText(Html.fromHtml("Capung-hutan pita-tunggal (<i><b>Drepanosticta gazella</b></i>) capung endemik Pulau Jawa"));
                Glide.with(viewHolder.itemView)
                        .load(link1)
                        .apply(options)
                        .into(viewHolder.imageViewBackGround);
                break;
            case 1:
                viewHolder.tvHeader.setText("Salah satu habitat capung di kawasan Desa Wisata Jatimulyo");
                Glide.with(viewHolder.itemView)
                        .load(link2)
                        .apply(options)
                        .into(viewHolder.imageViewBackGround);
                break;
            case 2:
                viewHolder.tvHeader.setText(Html.fromHtml("Capung-jarum gelap (<i><b>Prodasineura autumnalis</b></i>) pada posisi kopulasi"));
                Glide.with(viewHolder.itemView)
                        .load(link3)
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
