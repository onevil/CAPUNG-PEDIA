package site.ishaalim.capungpedia.notification.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import site.ishaalim.capungpedia.R;

public class notificationViewHolder extends RecyclerView.ViewHolder {
    public TextView tvBody, tvDate;

    public notificationViewHolder(@NonNull View itemView) {
        super(itemView);
        tvBody = itemView.findViewById(R.id.tv_notif_body);
        tvDate = itemView.findViewById(R.id.tv_date);
    }
}
