package site.ishaalim.capungpedia.UI.notification.adapter;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import maes.tech.intentanim.CustomIntent;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.SharedPref.usersPref;
import site.ishaalim.capungpedia.UI.ayoPengamatan.ListPengamatanFragment;
import site.ishaalim.capungpedia.UI.notification.NotifListFragment;
import site.ishaalim.capungpedia.UI.notification.NotificationActivity;
import site.ishaalim.capungpedia.UI.notification.model.notification;
import site.ishaalim.capungpedia.UI.notification.viewHolder.notificationViewHolder;

public class notificationAdapter extends RecyclerView.Adapter<notificationViewHolder> {
    private ArrayList<notification> notificationArrayList;
    Context context;
    Date date;
    String body, URL, id;
    FirebaseFirestore firestore;
    ProgressDialog progressDialog;
    usersPref usersPref;
    onDeleteListener listener;

    public interface onDeleteListener{
        void onDelete();
    }


    public notificationAdapter(ArrayList<notification> notificationArrayList, Context context) {
        this.notificationArrayList = notificationArrayList;
        this.context = context;
        firestore = FirebaseFirestore.getInstance();
        setupProgressDialog();
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
        id = notificationArrayList.get(position).getId();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));

        holder.tvBody.setText(Html.fromHtml(body));
        holder.tvDate.setText(dateFormat.format(date));
        setEvents(holder.itemView, holder.ivDelete, id, URL);
    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (context instanceof onDeleteListener){
            listener = (onDeleteListener) context;
        }else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        listener = null;
    }

    private void setEvents(View itemView, ImageView ivDelete, final String id, final String url) {

        final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        deleteNotification(id);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NotificationActivity.class);
                intent.putExtra("URL",url);
                context.startActivity(intent);
                CustomIntent.customType(context, "left-to-right");
            }
        });

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Apakah ingin menghapus notifikasi ini?").setPositiveButton("Ya", dialogClickListener)
                        .setNegativeButton("Tidak", dialogClickListener).show();
            }
        });
    }


    private void setupProgressDialog(){
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Menghapus Pengamatan");
        progressDialog.setMessage("Mohon tunggu...");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private void deleteNotification(String id) {
        usersPref = new usersPref(context);
        firestore.collection("notification").document(usersPref.getUserEmail()).collection("notifications").document(id).delete()
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                progressDialog.dismiss();
                Toast.makeText(context, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
            }
        });

        listener.onDelete();
    }
}

