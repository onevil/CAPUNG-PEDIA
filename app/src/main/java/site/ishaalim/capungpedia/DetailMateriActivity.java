package site.ishaalim.capungpedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import io.opencensus.tags.Tag;
import maes.tech.intentanim.CustomIntent;
import site.ishaalim.capungpedia.Materi.adapter.IsiMateriAdapter;
import site.ishaalim.capungpedia.Materi.model.isiMateri;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import static androidx.constraintlayout.widget.Constraints.TAG;

import java.util.ArrayList;

public class DetailMateriActivity extends AppCompatActivity {

    private ImageView ivDetailMateri;
    private TextView tvDetailJudul,tvDetailDeskripsi;

    private RecyclerView rvDetailMateri;

    private ArrayList<isiMateri> isiMateriArrayList;

    FirebaseFirestore firestore;

    RequestOptions options;

    IsiMateriAdapter isiMateriAdapter;

    String materiId, materiJudul, materiDeskripsi, materiImageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_materi);

        isiMateriArrayList = new ArrayList<>();
        options = new RequestOptions().centerCrop();


        initUI();

        setUpFirestore();
        getExtra();
        loadContent();
        setUpDetailMateriRV();
        loadDetailMateriRV();

    }




    public void loadDetailMateriRV() {
        isiMateriArrayList.clear();
        CollectionReference firestoreRef = firestore.collection("materi").document(materiId).collection("isiMateri");
        Query queryMateri = firestoreRef.orderBy("id", Query.Direction.ASCENDING);
        queryMateri.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot queryDetailMateriSnapshot : task.getResult()){
                    if (task.getResult() != null){
                        isiMateri isiMateri = queryDetailMateriSnapshot.toObject(isiMateri.class);

                        isiMateriArrayList.add(isiMateri);
                    }else {
                        Log.d(TAG,"No such Document");
                    }
                }

                loadmateri();

            }
        });
    }

    private void loadmateri() {
        isiMateriAdapter = new IsiMateriAdapter(this, isiMateriArrayList);
        rvDetailMateri.setAdapter(isiMateriAdapter);
        rvDetailMateri.smoothScrollToPosition(rvDetailMateri.getAdapter().getItemCount());
    }

    private void setUpDetailMateriRV() {

        rvDetailMateri = findViewById(R.id.rv_detail_materi);
        rvDetailMateri.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvDetailMateri.setLayoutManager(layoutManager);
    }

    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void finish() {
        super.finish();
        CustomIntent.customType(this,"right-to-left");
    }

    private void loadContent() {
        tvDetailJudul.setText(materiJudul);
        tvDetailDeskripsi.setText(materiDeskripsi);
        Glide.with(getApplicationContext()).load(materiImageURL).apply(options).into(ivDetailMateri);
    }

    private void getExtra() {
        materiId = getIntent().getExtras().getString("idMateri");
        materiJudul = getIntent().getExtras().getString("judulMateri");
        materiDeskripsi = getIntent().getExtras().getString("deskripsiMateri");
        materiImageURL = getIntent().getExtras().getString("imageURL");
    }

    private void initUI() {
        ivDetailMateri = findViewById(R.id.iv_detail_materi);
        tvDetailJudul = findViewById(R.id.tv_detail_judul_materi);
        tvDetailDeskripsi = findViewById(R.id.tv_detail_deskripsi_materi);
    }
}
