package site.ishaalim.capungpedia.notification.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.notification.model.notification;
import site.ishaalim.capungpedia.notification.viewHolder.notificationViewHolder;

public class notificationAdapter extends RecyclerView.Adapter<notificationViewHolder> {
    private ArrayList<notification> notificationArrayList;
    Context context;
    Date date;
    String body, notifDate;

    public notificationAdapter(ArrayList<notification> notificationArrayList, Context context) {
        this.notificationArrayList = notificationArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public notificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_rv_notif, parent, false);
        return new notificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull notificationViewHolder holder, int position) {
        body = notificationArrayList.get(position).getMessageBody();
        date = notificationArrayList.get(position).getNotifDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));

        holder.tvBody.setText(Html.fromHtml(body));
        holder.tvDate.setText(dateFormat.format(date));
    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }
}
