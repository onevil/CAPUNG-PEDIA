package site.ishaalim.capungpedia.UI.authentication;


import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import site.ishaalim.capungpedia.SharedPref.usersPref;
import site.ishaalim.capungpedia.UI.MainActivity;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.UI.ayoPengamatan.ParentPengamatanFragment;
import site.ishaalim.capungpedia.UI.profile.profile;

public class LoginFragment extends Fragment {
    EditText edtEmail, edtPassword;
    Button btnLogIn;
    TextView tvSignIn;
    ProgressDialog progressDialog;

    String UserNama, UserEmail, UserOrganisasi, UserTelpon, email, password;
    ArrayList<profile> profileArrayList;

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore firestore;
    usersPref usersPref;


    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpFirebase();
        setupArray();
        initUI();
        setupProgressDialog();
        setEvents();
    }

    private void setUpFirebase(){
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
    }

    private void setupArray(){
        profileArrayList = new ArrayList<>();
    }

    private void initUI(){
        edtEmail = getView().findViewById(R.id.edt_email);
        edtPassword = getView().findViewById(R.id.edt_password);
        tvSignIn = getView().findViewById(R.id.tv_bergabung);
        btnLogIn = getView().findViewById(R.id.btn_logIn);
        usersPref = new usersPref(getContext());
    }

    private void setupProgressDialog(){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Mohon tunggu...");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private void setEvents(){
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                getData();
                logIn();
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TAG = "signIn";
                SignInFragment fragment = new SignInFragment();
                changeFragment(TAG, fragment);
            }
        });
    }

    private void getData(){
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();
    }

    private void logIn(){
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(getContext(), "Anda belum memasukkan Email atau Password!", Toast.LENGTH_SHORT).show();
        }else {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                loadUserData();
                            }else {
                                Toast.makeText(getContext(), "Email atau Password salah!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void loadUserData(){
        profileArrayList.clear();
        String currentEmail = email;
        CollectionReference reference = firestore.collection("users");
        Query query = reference.whereEqualTo("email", currentEmail);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot querysnapshot : task.getResult()){
                            if (task.getResult() != null){
                                profile profile = querysnapshot.toObject(profile.class);
                                profileArrayList.add(profile);
                            }
                        }
                        UserNama = profileArrayList.get(0).getNama();
                        UserOrganisasi = profileArrayList.get(0).getOrganisasi();
                        UserEmail = profileArrayList.get(0).getEmail();
                        UserTelpon = profileArrayList.get(0).getTelpon();
                        saveUserData(UserNama, UserOrganisasi, email, UserTelpon);

                        progressDialog.dismiss();
                        ParentPengamatanFragment fragment = new ParentPengamatanFragment();
                        String TAG = "parent_pengamatan";
                        changeFragment(TAG, fragment);
                    }
                });
    }

    private void saveUserData(String nama, String organisasi, String email, String telpon){
        usersPref.setUserProfile(nama, organisasi, email, telpon);
        usersPref.setUsersLoaded(true);
    }

    private void changeFragment(String TAG, Fragment fragment){
        ((MainActivity) getActivity()).setFragment(fragment, TAG);
    }
}
