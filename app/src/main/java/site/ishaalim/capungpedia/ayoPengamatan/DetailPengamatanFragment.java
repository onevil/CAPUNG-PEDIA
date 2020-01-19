package site.ishaalim.capungpedia.ayoPengamatan;


import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import site.ishaalim.capungpedia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailPengamatanFragment extends Fragment {
    Toolbar toolbar;
    String namaPengamat, Habitat, Cuaca, Aktifiktas, Deskripsi, Hasil, imageFilePath;
    Button btnSimpan;
    ImageView ivDetailPengamatan;

    File mPhotoFile;

    RequestOptions options;

    Uri imageUri;

    EditText edtNamaPengamat, edtHabitat, edtPukul, edtCuaca, edtAktifitas, edtDeskripsi, edtHasil;

    private DetailPengamatanListener listener;
    private static final int MY_CAMERA_REQUEST_CODE = 1;
    private static final int STORAGE_REQUEST_CODE = 2;

    public interface DetailPengamatanListener{
        void addArraylist(String namapengamat, String habitat, String cuaca, String aktifiktas, String deskripsi, String hasil, Uri imageUri);
    }


    public DetailPengamatanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_pengamatan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        options = new RequestOptions().centerCrop();
        initUI();
        setHasOptionsMenu(true);
        setupToolbar();

    }

    private void setupToolbar() {
    }

    private void initUI() {
        toolbar = getView().findViewById(R.id.toolbar_detail_pengamatan);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

        edtNamaPengamat = getView().findViewById(R.id.edt_nama_pengamat);
        edtAktifitas = getView().findViewById(R.id.edt_aktifitas);
        edtHabitat = getView().findViewById(R.id.edt_habitat);
        edtCuaca = getView().findViewById(R.id.edt_cuaca);
        edtDeskripsi = getView().findViewById(R.id.edt_deskripsi);
        edtHasil = getView().findViewById(R.id.edt_hasil_identifikasi);
        btnSimpan = getView().findViewById(R.id.btn_simpan);
        ivDetailPengamatan = getView().findViewById(R.id.iv_detail_pengamatan);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namaPengamat = edtNamaPengamat.getText().toString();
                Aktifiktas = edtAktifitas.getText().toString();
                Habitat = edtHabitat.getText().toString();
                Cuaca = edtCuaca.getText().toString();
                Deskripsi = edtDeskripsi.getText().toString();
                Hasil = edtHasil.getText().toString();

                listener.addArraylist(namaPengamat, Habitat, Cuaca, Aktifiktas, Deskripsi, Hasil, imageUri);

                removeFragment();
            }
        });

        ivDetailPengamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });


    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getContext());
        pictureDialog.setTitle("Pilih Aksi : ");
        String[] pictureDialogItems = {
                "Lihat Gambar",
                "Ambil Gambar"
        };

        pictureDialog.setItems(pictureDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which){
                    case 0:
                        showImage();
                        break;
                    case 1:
                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                                takePhotoFromCamera();
                            }else {
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_REQUEST_CODE);
                            }

                            break;
                        }else
                        {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);

                        }
                        break;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (resultCode == getActivity().RESULT_OK) {

                Glide.with(getView()).load(imageUri).apply(options).into(ivDetailPengamatan);
            } else if (resultCode == getActivity().RESULT_CANCELED) {
                Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_LONG).show();
            }
        }

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
    }

    private void back() {
        removeFragment();
    }
}
