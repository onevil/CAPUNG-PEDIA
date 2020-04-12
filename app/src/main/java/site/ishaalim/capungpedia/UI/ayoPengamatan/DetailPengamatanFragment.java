package site.ishaalim.capungpedia.UI.ayoPengamatan;


import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.StringValue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import site.ishaalim.capungpedia.SharedPref.usersPref;
import site.ishaalim.capungpedia.UI.IdentifikasiCapung.adapter.CapungAdapter;
import site.ishaalim.capungpedia.UI.IdentifikasiCapung.model.Capung;
import site.ishaalim.capungpedia.UI.MainActivity;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.UI.ayoPengamatan.adapter.RekomendasiAdapter;
import site.ishaalim.capungpedia.UI.tentangPengembang.adapter.ContributorAdapter;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailPengamatanFragment extends Fragment {
    Toolbar toolbar;
    String namaPengamat, Habitat, Lokasi, Aktifiktas, Deskripsi, Jumlah, imageFilePath;
    Button btnSimpan, btnPlus, btnMinus;
    ImageView ivDetailPengamatan;
    AlertDialog dialogRekomendasi;

    Date date;
    String pukul;
    RequestOptions options;
    usersPref usersPref;
    int jumlah = 0;
    private int GALERY = 2;
    ArrayList<Capung> capungArrayList = new ArrayList<>();

    FirebaseFirestore firestore;

    Uri imageUri, viewImageURI;

    EditText edtNamaSpesies, edtHabitat, edtPukul, edtLokasi, edtAktifitas, edtDeskripsi, edtJumlah;

    private DetailPengamatanListener listener;
    private static final int MY_CAMERA_REQUEST_CODE = 1;
    private static final int STORAGE_REQUEST_CODE = 2;

    public interface DetailPengamatanListener{
        void addArraylist(String namapengamat, String habitat, String cuaca, String aktifiktas, String deskripsi, String hasil, Uri imageUri, Date date);
    }


    public DetailPengamatanFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_pengamatan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        options = new RequestOptions().centerCrop();
        firestore = FirebaseFirestore.getInstance();
        initUI();
        setEvents();
        setHasOptionsMenu(true);
        setupToolbar();
        getPukul();
    }

    private void initUI() {
        toolbar = getView().findViewById(R.id.toolbar_detail_pengamatan);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        edtAktifitas = getView().findViewById(R.id.edt_aktifitas);
        edtHabitat = getView().findViewById(R.id.edt_habitat);
        edtLokasi = getView().findViewById(R.id.edt_lokasi);
        edtDeskripsi = getView().findViewById(R.id.edt_deskripsi);
        edtJumlah = getView().findViewById(R.id.edt_jumlah);
        edtNamaSpesies = getView().findViewById(R.id.edt_nama_spesies);
        edtPukul = getView().findViewById(R.id.edt_pukul_pengamatan);
        btnSimpan = getView().findViewById(R.id.btn_simpan);
        btnPlus = getView().findViewById(R.id.btn_plus);
        btnMinus = getView().findViewById(R.id.btn_minus);
        ivDetailPengamatan = getView().findViewById(R.id.iv_detail_pengamatan);
        usersPref = new usersPref(getContext());
    }

    private void setEvents(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namaPengamat = edtNamaSpesies.getText().toString();
                Aktifiktas = edtAktifitas.getText().toString();
                Habitat = edtHabitat.getText().toString();
                Lokasi = edtLokasi.getText().toString();
                Deskripsi = edtDeskripsi.getText().toString();
                Jumlah = edtJumlah.getText().toString();
                listener.addArraylist(namaPengamat, Habitat, Lokasi, Aktifiktas, Deskripsi, Jumlah, imageUri, date);
                removeFragment();
            }
        });

        ivDetailPengamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlah += 1;
                edtJumlah.setText(Integer.toString(jumlah));
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlah -= 1;
                edtJumlah.setText(Integer.toString(jumlah));
            }
        });

        edtNamaSpesies.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    showCapungDialog();
                }
            }
        });
    }

    private void setupToolbar() {
    }

    private void getPukul() {
        date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh : mm ");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        pukul = simpleDateFormat.format(date) + "WIB";
        edtPukul.setText(pukul);
        edtPukul.setClickable(false);
        edtPukul.setFocusable(false);
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getContext());
        pictureDialog.setTitle("Pilih Aksi : ");
        String[] pictureDialogItems = {
                "Lihat Gambar",
                "Ambil Gambar",
                "Ambil dari Galeri"
        };

        pictureDialog.setItems(pictureDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        showImage();
                        break;
                    case 1:
                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
                            break;

                        }else if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                        {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_REQUEST_CODE);
                            break;
                        }else {
                            takePhotoFromCamera();
                        }
                        break;
                    case 2:
                        takePhotoFromGalerry();
                }
            }
        });
        pictureDialog.show();
    }

    private void takePhotoFromCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
        imageUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraintent, MY_CAMERA_REQUEST_CODE);
    }

    private void takePhotoFromGalerry(){
        Intent galleryIntent =  new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, STORAGE_REQUEST_CODE);
    }

    private void  showCapungDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_search_capung, null);
        EditText edtSearch = mView.findViewById(R.id.edt_search);
        final ProgressBar progressBar = mView.findViewById(R.id.progressBar);
        final RecyclerView recyclerView = mView.findViewById(R.id.rv_capung);

        final RekomendasiAdapter adapter = new RekomendasiAdapter(getContext(), capungArrayList);
        adapter.notifyDataSetChanged();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                progressBar.setVisibility(View.VISIBLE);
                final String query = s.toString().toLowerCase().trim();
                if (!query.isEmpty()) {
                    capungArrayList.clear();
                    CollectionReference firestoreRef = firestore.collection("capung");
                    Query querySearchCapung = firestoreRef.orderBy("namaSpesies", Query.Direction.ASCENDING);
                    querySearchCapung.get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    capungArrayList.clear();
                                    for (DocumentSnapshot querySnapshotSearchMateri : task.getResult()) {
                                        if (task.getResult() != null) {
                                            if (task.getResult() != null) {
                                                String namaSpesies = querySnapshotSearchMateri.getString("namaSpesies").toLowerCase();
                                                String querySearch = query.toLowerCase();

                                                if (namaSpesies.contains(querySearch)) {
                                                    Capung capung = querySnapshotSearchMateri.toObject(Capung.class);
                                                    capungArrayList.add(capung);
                                                }
                                            }
                                            progressBar.setVisibility(View.GONE);
                                            recyclerView.setAdapter(adapter);
                                        } else {
                                            Log.d(TAG, "No such Document");
                                        }
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                    recyclerView.setAdapter(adapter);
                } else {
                    capungArrayList.clear();
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dialog.setView(mView);
        dialogRekomendasi = dialog.create();
        dialogRekomendasi.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogRekomendasi.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (resultCode == getActivity().RESULT_OK) {

                Glide.with(getView()).load(imageUri).apply(options).into(ivDetailPengamatan);
                setViewImageURI(imageUri);

            } else if (resultCode == getActivity().RESULT_CANCELED) {
                Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_LONG).show();
            }
        }else if (requestCode == STORAGE_REQUEST_CODE){
            if (resultCode == getActivity().RESULT_OK){
                if (data != null){
                    imageUri = data.getData();
                    ContentResolver contentResolver = getContext().getContentResolver();
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Glide.with(getView()).load(bitmap).apply(options).into(ivDetailPengamatan);
                    setViewImageURI(imageUri);
                }
            }
        }
    }

    private void setViewImageURI(Uri imageUri) {
        viewImageURI = imageUri;
    }

    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

    private void showImage() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        View mView = getLayoutInflater().inflate(R.layout.dialog_photoview, null);
        PhotoView photoView = mView.findViewById(R.id.imageView);
        photoView.setImageURI(viewImageURI);
        mBuilder.setView(mView);
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    public void setEditText(String nama){
        dialogRekomendasi.dismiss();
        edtNamaSpesies.setText(Html.fromHtml(nama));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof DetailPengamatanListener) {
            listener = (DetailPengamatanListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private void removeFragment(){
        getFragmentManager().beginTransaction().remove(this).commit();
        String TAG = "detail_pengamatan";
        ((MainActivity) getActivity()).RemoveFragment(TAG);
    }

    private void back() {
        removeFragment();
    }
}
