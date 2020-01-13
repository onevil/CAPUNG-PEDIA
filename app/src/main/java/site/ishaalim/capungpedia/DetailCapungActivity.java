package site.ishaalim.capungpedia;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DetailCapungActivity extends AppCompatActivity {

    private TextView tvnamaSpesies, tvfamilli, tvfillum, tvkingdom, tvkelas, tvordo, tvdeskripsi, tvkepalaJantan, tvkepalaBetina, tvbadanJantan, tvbadanBetina, tvperutJantan, tvperutBetina, tvkakiJantan, tvkakiBetina, tvsayapJantan, tvsayapBetina, tvembelanJantan, tvembelanBetina;
    private ImageView ivImage1, ivImage2, ivImage3;
    Toolbar toolbar;

    RequestOptions options;

    private String namaSpesies, familli, fillum, kingdom, kelas, ordo, deskripsi, image1, image2, image3, kepalaJantan, kepalaBetina, badanJantan, badanBetina, perutJantan, perutBetina, kakiJantan, kakiBetina, sayapJantan, sayapBetina, embelanJantan, embelanBetina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_capung);
        getExtras();
        options = new RequestOptions().centerCrop();

        toolbar = findViewById(R.id.toolbar_detail_capung);
        setSupportActionBar(toolbar);
        setUpToolbar();


        initUI();
        Log.d(TAG,"Image URL : " + image1);
        Log.d(TAG,"Image URL2 : " + image2);
        Log.d(TAG,"Image URL3 : " + image3);
        loadContent();

    }

    private void setUpToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


        private void loadContent() {
        tvnamaSpesies.setText(namaSpesies);
        tvfamilli.setText(familli);
        tvkingdom.setText(kingdom);
        tvfillum.setText(fillum);
        tvkelas.setText(kelas);
        tvordo.setText(ordo);
        tvdeskripsi.setText(deskripsi);
        tvkepalaJantan.setText(kepalaJantan);
        tvkepalaBetina.setText(kepalaBetina);
        tvbadanJantan.setText(badanJantan);
        tvbadanBetina.setText(badanBetina);
        tvperutJantan.setText(perutJantan);
        tvperutBetina.setText(perutBetina);
        tvkakiJantan.setText(kakiJantan);
        tvkakiBetina.setText(kakiBetina);
        tvsayapJantan.setText(sayapJantan);
        tvsayapBetina.setText(sayapBetina);
        tvembelanJantan.setText(embelanJantan);
        tvembelanBetina.setText(embelanBetina);

        Glide.with(getApplicationContext()).load(image1).apply(options).into(ivImage1);
        Glide.with(getApplicationContext()).load(image2).apply(options).into(ivImage2);
        Glide.with(getApplicationContext()).load(image3).apply(options).into(ivImage3);

    }

    private void getExtras() {
        namaSpesies = getIntent().getExtras().getString("namaSpesies");
        familli = getIntent().getExtras().getString("familli");
        fillum = getIntent().getExtras().getString("fillum");
        kingdom = getIntent().getExtras().getString("kingdom");
        kelas = getIntent().getExtras().getString("kelas");
        ordo = getIntent().getExtras().getString("ordo");
        deskripsi = getIntent().getExtras().getString("deskripsi");
        image1 = getIntent().getExtras().getString("image1");
        image2 = getIntent().getExtras().getString("image2");
        image3 = getIntent().getExtras().getString("image3");
        kepalaJantan = getIntent().getExtras().getString("kepalaJantan");
        kepalaBetina = getIntent().getExtras().getString("kepalaBetina");
        badanJantan = getIntent().getExtras().getString("badanJantan");
        badanBetina = getIntent().getExtras().getString("badanBetina");
        perutJantan = getIntent().getExtras().getString("perutJantan");
        perutBetina = getIntent().getExtras().getString("perutBetina");
        kakiJantan = getIntent().getExtras().getString("kakiJantan");
        kakiBetina = getIntent().getExtras().getString("kakiBetina");
        sayapJantan = getIntent().getExtras().getString("sayapJantan");
        sayapBetina = getIntent().getExtras().getString("sayapBetina");
        embelanJantan = getIntent().getExtras().getString("embelanJantan");
        embelanBetina = getIntent().getExtras().getString("embelanBetina");


    }

    private void initUI() {
        tvnamaSpesies = findViewById(R.id.tv_nama_spesies);
        tvfamilli = findViewById(R.id.tv_nama_famili);
        tvkingdom = findViewById(R.id.tv_isi_kingdom);
        tvfillum = findViewById(R.id.tv_isi_fillum);
        tvkelas = findViewById(R.id.tv_isi_kelas);
        tvordo = findViewById(R.id.tv_isi_ordo);
        tvdeskripsi = findViewById(R.id.tv_isi_deskripsi);
        tvkepalaJantan = findViewById(R.id.tv_kepala_jantan);
        tvkepalaBetina = findViewById(R.id.tv_kepala_betina);
        tvbadanJantan = findViewById(R.id.tv_badan_jantan);
        tvbadanBetina = findViewById(R.id.tv_badan_betina);
        tvperutJantan = findViewById(R.id.tv_perut_jantan);
        tvperutBetina = findViewById(R.id.tv_perut_betina);
        tvkakiJantan = findViewById(R.id.tv_kaki_jantan);
        tvkakiBetina = findViewById(R.id.tv_kaki_betina);
        tvsayapJantan = findViewById(R.id.tv_sayap_jantan);
        tvsayapBetina = findViewById(R.id.tv_sayap_betina);
        tvembelanJantan = findViewById(R.id.tv_embelan_jantan);
        tvembelanBetina = findViewById(R.id.tv_embelan_betina);

        ivImage1 = findViewById(R.id.iv_detail_capung);
        ivImage2 = findViewById(R.id.iv_capung1);
        ivImage3 = findViewById(R.id.iv_capung2);

    }

}
