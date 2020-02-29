package site.ishaalim.capungpedia.IdentifikasiCapung;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import site.ishaalim.capungpedia.IdentifikasiCapung.adapter.ImageSlideAdapter;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.SharedPref.SharedPref;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DetailCapungActivity extends AppCompatActivity {

    private TextView tvnamaSpesies, tvnamaIndonesia, tvnamaInggris, tvfamilli, tvDeskripsi, tvKebiasaan, tvSosial, tvInformasiLain;
    private ImageView ivImage1, ivImage2, ivUkuran, ivHabitat1, ivHabitat2, ivHabitat3, ivHabitat4, ivCapung;
    Toolbar toolbar;

    private SharedPref sharedpref;

    RequestOptions options, options2;

    private String namaSpesies, namaIndonesia, namaInggris,
            deskripsi, imagecaption1, imagephotografer1, image1,
            familli, kebiasaan, sosial, informasiLain, ukuran ;
    private Uri uri2, uri3;

    private ArrayList<String>habitat, images, fotoOleh, captions;

    Drawable backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);
        checkTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_capung);
        habitat = new ArrayList<>();
        getExtras();
        initUI();
        options = new RequestOptions().centerCrop();
        options2 = new RequestOptions().fitCenter();
        setSupportActionBar(toolbar);
        setUpToolbar();
        loadContent();
        setEvents();

    }


    private void setEvents() {
        ivCapung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageDialog();
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
        toolbar.setTitle(Html.fromHtml(namaSpesies));
    }

        private void loadContent() {
        tvnamaSpesies.setText(Html.fromHtml(namaSpesies));
        tvnamaIndonesia.setText(Html.fromHtml(namaIndonesia));
        tvnamaInggris.setText(Html.fromHtml(namaInggris));
        tvfamilli.setText(Html.fromHtml(familli));
        tvDeskripsi.setText(Html.fromHtml(deskripsi));
        tvKebiasaan.setText(Html.fromHtml(kebiasaan));
        tvSosial.setText(Html.fromHtml(sosial));
        tvInformasiLain.setText(Html.fromHtml(informasiLain));

        Glide.with(getApplicationContext()).load(image1).apply(options).into(ivImage1);
        Glide.with(getApplicationContext()).load(ukuran).into(ivUkuran);

        if (images != null){
            Glide.with(getApplicationContext()).load(images.get(0)).into(ivImage2);
        }

        if (habitat.size() == 1){
            loadHabitat1(habitat.get(0));
        } else if (habitat.size() == 2){
            loadHabitat1(habitat.get(0));
            loadHabitat2(habitat.get(1));
        } else if (habitat.size() == 3){
            loadHabitat1(habitat.get(0));
            loadHabitat2(habitat.get(1));
            loadHabitat3(habitat.get(2));
        } else if (habitat.size() == 4){
            loadHabitat1(habitat.get(0));
            loadHabitat2(habitat.get(1));
            loadHabitat3(habitat.get(2));
            loadHabitat4(habitat.get(3));
        }

    }

    private void getExtras() {
        namaSpesies = getIntent().getExtras().getString("namaSpesies");
        namaIndonesia = getIntent().getExtras().getString("namaIndonesia");
        namaInggris = getIntent().getExtras().getString("namaInggris");
        familli = getIntent().getExtras().getString("familli");
        deskripsi = getIntent().getExtras().getString("deskripsi");
        kebiasaan = getIntent().getExtras().getString("kebiasaan");
        sosial = getIntent().getExtras().getString("sosial");
        informasiLain = getIntent().getExtras().getString("informasiLain");
        image1 = getIntent().getExtras().getString("image1");
        ukuran = getIntent().getExtras().getString("ukuran");
        habitat = getIntent().getExtras().getStringArrayList("habitat");
        images = getIntent().getExtras().getStringArrayList("images");
        fotoOleh = getIntent().getExtras().getStringArrayList("fotoOleh");
        captions = getIntent().getExtras().getStringArrayList("captions");
        Log.d(TAG, "Habitat : " + habitat);
        Log.d(TAG, "Images : " + images);
        Log.d(TAG, "fotoOleh : " + fotoOleh);
        Log.d(TAG, "captions : " + captions);

    }

    private void initUI() {
        tvnamaSpesies = findViewById(R.id.tv_nama_spesies);
        tvnamaIndonesia = findViewById(R.id.tv_nama_indonesia);
        tvnamaInggris = findViewById(R.id.tv_nama_inggris);
        tvfamilli = findViewById(R.id.tv_famili);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);
        tvKebiasaan = findViewById(R.id.tv_kebiasaan);
        tvSosial = findViewById(R.id.tv_sosial);
        tvInformasiLain = findViewById(R.id.tv_informasi_lain);
        ivImage1 = findViewById(R.id.iv_detail_capung);
        ivImage2 = findViewById(R.id.iv_capung);
        toolbar = findViewById(R.id.toolbar_detail_capung);
        ivUkuran = findViewById(R.id.iv_ukuran);
        ivHabitat1 = findViewById(R.id.iv_hbt1);
        ivHabitat2 = findViewById(R.id.iv_hbt2);
        ivHabitat3 = findViewById(R.id.iv_hbt3);
        ivHabitat4 = findViewById(R.id.iv_hbt4);
        ivCapung = findViewById(R.id.iv_capung);
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

    private void loadHabitat1(String hbt){
        Log.d(TAG, "Load Habitat1 ");
            if (hbt.equals("home")){
                Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Habitat%2Fhome.png?alt=media&token=e31df86d-abf7-4633-8c9c-061699115a96")
                        .into(ivHabitat1);
                Log.d(TAG, "Habitat 1 ");
            }else if (hbt.equals("river")){
                Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Habitat%2Friver.png?alt=media&token=910521a4-7526-4e3d-9a2b-86e5db2fbdd6")
                        .into(ivHabitat1);
                Log.d(TAG, "Habitat 2 ");
            }else if (hbt.equals("fishpond")){
                Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Habitat%2Ffishpond.png?alt=media&token=6b1aa841-c719-483a-b6b3-a8d6c063092c")
                        .into(ivHabitat1);
                Log.d(TAG, "Habitat 3 ");
            }else if (hbt.equals("ricefields")){
                Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Habitat%2Fricefields.png?alt=media&token=69a8669c-36fd-4c0c-a8c9-c1c3476e7093")
                        .into(ivHabitat1);
                Log.d(TAG, "Habitat 4 ");
            }
    }

    private void loadHabitat2(String hbt){
        if (hbt.equals("home")){
            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Habitat%2Fhome.png?alt=media&token=e31df86d-abf7-4633-8c9c-061699115a96")
                    .into(ivHabitat2);
        }else if (hbt.equals("river")){
            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Habitat%2Friver.png?alt=media&token=910521a4-7526-4e3d-9a2b-86e5db2fbdd6")
                    .into(ivHabitat2);
        }else if (hbt.equals("fishpond")){
            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Habitat%2Ffishpond.png?alt=media&token=6b1aa841-c719-483a-b6b3-a8d6c063092c")
                    .into(ivHabitat2);
        }else if (hbt.equals("ricefields")){
            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Habitat%2Fricefields.png?alt=media&token=69a8669c-36fd-4c0c-a8c9-c1c3476e7093")
                    .into(ivHabitat2);
        }
    }

    private void loadHabitat3(String hbt){
        if (hbt.equals("home")){
            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Habitat%2Fhome.png?alt=media&token=e31df86d-abf7-4633-8c9c-061699115a96")
                    .into(ivHabitat3);
        }else if (hbt.equals("river")){
            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Habitat%2Friver.png?alt=media&token=910521a4-7526-4e3d-9a2b-86e5db2fbdd6")
                    .into(ivHabitat3);
        }else if (hbt.equals("fishpond")){
            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Habitat%2Ffishpond.png?alt=media&token=6b1aa841-c719-483a-b6b3-a8d6c063092c")
                    .into(ivHabitat3);
        }else if (hbt.equals("ricefields")){
            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Habitat%2Fricefields.png?alt=media&token=69a8669c-36fd-4c0c-a8c9-c1c3476e7093")
                    .into(ivHabitat3);
        }
    }

    private void loadHabitat4(String hbt){
        if (hbt.equals("home")){
            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Habitat%2Fhome.png?alt=media&token=e31df86d-abf7-4633-8c9c-061699115a96")
                    .into(ivHabitat4);
        }else if (hbt.equals("river")){
            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Habitat%2Friver.png?alt=media&token=910521a4-7526-4e3d-9a2b-86e5db2fbdd6")
                    .into(ivHabitat4);
        }else if (hbt.equals("fishpond")){
            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Habitat%2Ffishpond.png?alt=media&token=6b1aa841-c719-483a-b6b3-a8d6c063092c")
                    .into(ivHabitat4);
        }else if (hbt.equals("ricefields")){
            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/capung-pedia-cb932.appspot.com/o/Habitat%2Fricefields.png?alt=media&token=69a8669c-36fd-4c0c-a8c9-c1c3476e7093")
                    .into(ivHabitat4);
        }
    }

    private void showImageDialog(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_photoview_capung, null);
        ViewPager2 viewPager2 = mView.findViewById(R.id.VP_Capung);

        viewPager2.setAdapter(new ImageSlideAdapter(images, captions, fotoOleh, namaIndonesia, namaInggris, this));
        mBuilder.setView(mView);
        AlertDialog mDialog = mBuilder.create();
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }

}
