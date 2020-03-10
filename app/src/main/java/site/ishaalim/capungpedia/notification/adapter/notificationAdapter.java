package site.ishaalim.capungpedia.notification.adapter;

import android.content.Context;
import android.content.Intent;
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

import maes.tech.intentanim.CustomIntent;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.notification.NotificationActivity;
import site.ishaalim.capungpedia.notification.model.notification;
import site.ishaalim.capungpedia.notification.viewHolder.notificationViewHolder;

public class notificationAdapter extends RecyclerView.Adapter<notificationViewHolder> {
    private ArrayList<notification> notificationArrayList;
    Context context;
    Date date;
    String body, URL;

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
        URL = notificationArrayList.get(position).getNotifUrl();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));

        holder.tvBody.setText(Html.fromHtml(body));
        holder.tvDate.setText(dateFormat.format(date));

        setEvents(holder.itemView, URL);
    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }

    private void setEvents(View itemView, final String url) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NotificationActivity.class);
                intent.putExtra("URL",url);
                context.startActivity(intent);
                CustomIntent.customType(context, "left-to-right");
            }
        });
    }
}

