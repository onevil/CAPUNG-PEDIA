package site.ishaalim.capungpedia.UI.notification.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import site.ishaalim.capungpedia.R;

public class notificationViewHolder extends RecyclerView.ViewHolder {
    public TextView tvBody, tvDate;
    public ImageView ivDelete;

    public notificationViewHolder(@NonNull View itemView) {
        super(itemView);
        tvBody = itemView.findViewById(R.id.tv_notif_body);
        tvDate = itemView.findViewById(R.id.tv_date);
        ivDelete = itemView.findViewById(R.id.iv_delete);
    }
}
