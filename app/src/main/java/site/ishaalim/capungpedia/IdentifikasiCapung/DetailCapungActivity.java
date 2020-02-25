package site.ishaalim.capungpedia.IdentifikasiCapung;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;

import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.SharedPref.SharedPref;

public class DetailCapungActivity extends AppCompatActivity {

    private TextView tvnamaSpesies, tvnamaIndonesia, tvnamaInggris, tvfamilli, tvDeskripsi, tvKebiasaan, tvSosial, tvInformasiLain;
    private ImageView ivImage1, ivUkuran;
    Toolbar toolbar;
    private SharedPref sharedpref;

    RequestOptions options, options2;

    private String namaSpesies, namaIndonesia, namaInggris,
            deskripsi, imagecaption1, imagephotografer1, image1,
            familli, kebiasaan, sosial, informasiLain, ukuran ;
    private Uri uri2, uri3;

    Drawable backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);
        checkTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_capung);
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
        ivImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivImage1.getDrawable() != null){
                    showImage(ivImage1.getDrawable(), imagecaption1, imagephotografer1);
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
        toolbar = findViewById(R.id.toolbar_detail_capung);
        ivUkuran = findViewById(R.id.iv_ukuran);
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
