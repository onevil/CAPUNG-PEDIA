package site.ishaalim.capungpedia.UI.Beranda.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarteist.autoimageslider.SliderViewAdapter;

import site.ishaalim.capungpedia.R;

public class SliderViewHolder extends SliderViewAdapter.ViewHolder {

    public View itemView;
    public TextView tvHeader;
    public ImageView imageViewBackGround;

    public SliderViewHolder(View itemView) {
        super(itemView);
        tvHeader = itemView.findViewById(R.id.tv_header);
        imageViewBackGround = itemView.findViewById(R.id.iv_auto_image_slider);
        this.itemView = itemView;
    }
}
