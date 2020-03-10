package site.ishaalim.capungpedia.notification;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.notification.adapter.notificationAdapter;
import site.ishaalim.capungpedia.notification.model.notification;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotifListFragment extends Fragment {
    RecyclerView recyclerView;
    FirebaseFirestore firestore;
    Toolbar toolbar;

    private ArrayList<notification> notificationArrayList;
    notificationAdapter notificationAdapter;

    public NotifListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notif_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupArraylist();
        setUpFirestore();
        initUI();
        setUpToolbar();
        setUpRecyclerView();
        loadNotification();
    }

    private void setupArraylist(){
        notificationArrayList = new ArrayList<>();
    }

    private  void setUpFirestore(){
      firestore = FirebaseFirestore.getInstance();
    }

    private void initUI(){
        recyclerView = getView().findViewById(R.id.rv_notif);
        toolbar = getView().findViewById(R.id.toolbar);
    }

    private void setUpToolbar(){
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setUpRecyclerView(){
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

    }

    private void loadNotification(){
        notificationArrayList.clear();
        CollectionReference reference = firestore.collection("notification");
        final Query queryNotif = reference.orderBy("notifDate", Query.Direction.DESCENDING);
        queryNotif.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot querySnapNotif : task.getResult()){
                            if (task.getResult() != null){
                                notification notif = querySnapNotif.toObject(notification.class);
                                        notificationArrayList.add(notif);
                            }
                            else {
                                Toast.makeText(getContext(), "Tidak ada notifikasi", Toast.LENGTH_SHORT).show();
                            }
                        }
                        notificationAdapter = new notificationAdapter(notificationArrayList, getContext());
                        recyclerView.setAdapter(notificationAdapter);
                        recyclerView.smoothScrollToPosition(notificationAdapter.getItemCount());
                    }
                });
    }
}
