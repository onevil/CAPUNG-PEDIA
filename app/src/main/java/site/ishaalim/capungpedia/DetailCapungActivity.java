package site.ishaalim.capungpedia;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;

import site.ishaalim.capungpedia.SharedPref.SharedPref;

public class DetailCapungActivity extends AppCompatActivity {

    private TextView tvnamaSpesies, tvfamilli, tvfillum, tvkingdom, tvkelas, tvordo, tvdeskripsi, tvkepalaJantan, tvkepalaBetina, tvbadanJantan, tvbadanBetina, tvperutJantan, tvperutBetina, tvkakiJantan, tvkakiBetina, tvsayapJantan, tvsayapBetina, tvembelanJantan, tvembelanBetina;
    private ImageView ivImage1, ivImage2, ivImage3;
    Toolbar toolbar;
    private SharedPref sharedpref;

    RequestOptions options;

    private String namaSpesies, familli, fillum, kingdom, kelas, ordo,
            deskripsi, imagecaption1, imagephotografer1, image1, imagecaption2, imagephotografer2, image2, imagecaption3, imagephotografer3,image3,  kepalaJantan, kepalaBetina,
            badanJantan, badanBetina, perutJantan, perutBetina, kakiJantan, kakiBetina,
            sayapJantan, sayapBetina, embelanJantan, embelanBetina;
    private Uri uri2, uri3;

    Drawable backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);
        checkTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_capung);

        getExtras();
        options = new RequestOptions().centerCrop();

        toolbar = findViewById(R.id.toolbar_detail_capung);
        setSupportActionBar(toolbar);
        setUpToolbar();


        initUI();
        if (image2 != null){
            uri2 = Uri.parse(image2);
        }

        if (image3 != null){
            uri3 = Uri.parse(image3);
        }

        loadContent();

        setEvents();

    }


    private void setEvents() {
        ivImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivImage1.getDrawable() != null){
                    showImage(ivImage1.getDrawable(), imagecaption1, imagephotografer1);
                }
            }
        });

        ivImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivImage2 != null){
                    showImageURL(image2, imagecaption2, imagephotografer2);
                }
            }
        });

        ivImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivImage3 != null){
                    showImageURL(image3, imagecaption2, imagephotografer2);
                }
            }
        });
    }

    private void checkTheme(){

        if(sharedpref.loadNightModeState()==true) {
            setTheme(R.style.DarkTheme);
        }
        else  {
            sharedpref.setNightModeState(false);
            setTheme(R.style.AppTheme);
        }

        setUpIcons();

    }

    private void setUpIcons(){
        backIcon = getDrawable(R.drawable.ic_back);

        if (sharedpref.loadNightModeState() == true){
            setTintWhite(backIcon);
        }else {
            setTintBlack(backIcon);

        }
    }

    private void setTintBlack(Drawable drawable){
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, getResources().getColor(R.color.black));
    }

    private void setTintWhite(Drawable drawable){
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, getResources().getColor(R.color.white));
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

    private void showImage(Drawable drawable, String caption, String fotografer) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_photoview, null);
        PhotoView photoView = mView.findViewById(R.id.imageView);
        TextView tvCaption = mView.findViewById(R.id.tv_caption);
        TextView tvFotofrafer = mView.findViewById(R.id.tv_terima_kasih);
        TextView tvFotoby = mView.findViewById(R.id.tv_photoby);

        photoView.setImageDrawable(drawable);

        if(caption == null){
            tvCaption.setVisibility(View.GONE);
        }else {
            tvCaption.setText(caption);
        }

        if(fotografer == null){
            tvFotofrafer.setVisibility(View.GONE);
        }else {
            tvFotoby.setVisibility(View.VISIBLE);
            tvFotofrafer.setText(fotografer);
        }

        mBuilder.setView(mView);
        AlertDialog mDialog = mBuilder.create();
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();

    }

    private void showImageURL(String imageURL, String caption, String fotografer) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_photoview, null);
        PhotoView photoView = mView.findViewById(R.id.imageView);
        TextView tvCaption = mView.findViewById(R.id.tv_caption);
        TextView tvFotofrafer = mView.findViewById(R.id.tv_terima_kasih);
        TextView tvFotoby = mView.findViewById(R.id.tv_photoby);

        Glide.with(this).load(imageURL).into(photoView);

        if(caption == null){
            tvCaption.setVisibility(View.GONE);
        }else {
            tvCaption.setText(caption);
        }

        if(fotografer == null){
            tvFotofrafer.setVisibility(View.GONE);
        }else {
            tvFotoby.setVisibility(View.VISIBLE);
            tvFotofrafer.setText(fotografer);
        }

        mBuilder.setView(mView);
        AlertDialog mDialog = mBuilder.create();
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();

    }

}
