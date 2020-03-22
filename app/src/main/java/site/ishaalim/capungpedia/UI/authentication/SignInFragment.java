package site.ishaalim.capungpedia.UI.authentication;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Random;

import site.ishaalim.capungpedia.UI.MainActivity;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.UI.ayoPengamatan.ListPengamatanFragment;
import site.ishaalim.capungpedia.UI.ayoPengamatan.ParentPengamatanFragment;

public class SignInFragment extends Fragment {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;

    EditText edtNama, edtEmail, edtPassword, edtTelpon, edtOrganisasi;
    Button btnSignIn;

    String nama, email, password, telpon, organisasi;
    String Characters = "ABCDEFGHIJKMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz0123456789";
    String docID = "";
    int IDLength;

    public SignInFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupFirebase();
        initUI();
        setEvents();
    }

    private void setupFirebase(){
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    private  void  initUI(){
        edtNama = getView().findViewById(R.id.edt_nama);
        edtEmail = getView().findViewById(R.id.edt_email);
        edtPassword = getView().findViewById(R.id.edt_password);
        edtTelpon = getView().findViewById(R.id.edt_telp);
        edtOrganisasi = getView().findViewById(R.id.edt_organisasi);
        btnSignIn = getView().findViewById(R.id.btn_signIn);
    }

    private void setEvents(){
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateID();
                getData();
            }
        });
    }

    private void generateID(){
        Random random = new Random();

        char[] chars = new char[IDLength];

        for (int i = 0; i < IDLength; i++){
            chars[i] = Characters.charAt(random.nextInt(Characters.length()));
        }

        for (int i = 0; i < chars.length; i++){
            docID += chars[i];
        }
    }

    private void getData(){
        nama = edtNama.getText().toString();
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();
        telpon = edtTelpon.getText().toString();
        organisasi = edtOrganisasi.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(getContext(),"Email & Password kosong!", Toast.LENGTH_LONG).show();
        }else if (password.length() < 8){
            Toast.makeText(getContext(),"Password minimal 8 character", Toast.LENGTH_LONG).show();
        }else {
            register();
        }
    }

    private void register(){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    assert firebaseUser != null;
                    String userID = firebaseUser.getUid();
                    saveUserData(userID);
                }

            }
        });
    }

    private void saveUserData(String userID){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("nama", nama);
        hashMap.put("email", email);
        hashMap.put("telpon", telpon);
        hashMap.put("organisasi", organisasi);

        firestore.collection("users").document(userID)
                .set(hashMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getContext(),"Akun berhasil dibuat", Toast.LENGTH_SHORT).show();
                            String TAG = "parent_pengamatan";
                            ParentPengamatanFragment fragment = new ParentPengamatanFragment();
                            changeFragment(fragment, TAG);
                        }
                    }
                });
    }

    private  void changeFragment(Fragment fragment, String TAG){
        ((MainActivity) getActivity()).setFragment(fragment, TAG);
    }
}
