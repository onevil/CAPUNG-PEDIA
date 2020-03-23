package site.ishaalim.capungpedia.UI.profile;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.SharedPref.usersPref;
import site.ishaalim.capungpedia.UI.MainActivity;
import site.ishaalim.capungpedia.UI.authentication.LoginFragment;
import site.ishaalim.capungpedia.UI.ayoPengamatan.ParentPengamatanFragment;


public class ProfileFragment extends Fragment {
    TextView tvNama, tvOrganisasi, tvEmail, tvNomer;
    Button btnLogout;

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore firestore;

    usersPref usersPref;

    ArrayList<profile> profileArrayList;
    String nama, organisasi, email, telpon;

    public ProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupFirebase();
        setupArray();
        initUI();
        setupEvents();
        checkProfilePref();
        loadProfile();
    }

    private void setupFirebase(){
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
    }

    private void setupArray(){
        profileArrayList = new ArrayList<>();
    }

    private void initUI(){
        tvNama = getView().findViewById(R.id.tv_nama);
        tvOrganisasi = getView().findViewById(R.id.tv_organisasi);
        tvEmail = getView().findViewById(R.id.tv_email);
        tvNomer = getView().findViewById(R.id.tv_nomer);
        btnLogout = getView().findViewById(R.id.btn_logout);
        usersPref = new usersPref(getContext());
    }

    private void setupEvents(){
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
                changeFragment();
            }
        });
    }

    private void checkProfilePref(){
        if (usersPref.usersLoaded()){
            loadProfilePref();
        }else {
            loadProfile();
        }
    }

    private void logOut(){
        auth.signOut();
        deleteUserData();
    }

    private void changeFragment(){
        LoginFragment fragment = new LoginFragment();
        String TAG = "login";
        ((MainActivity)getActivity()).setFragment(fragment, TAG);
    }

    private void loadProfilePref(){
        nama = usersPref.getUserNama();
        organisasi = usersPref.getUserOrganisasi();
        email = usersPref.getUserEmail();
        telpon = usersPref.getUserNomer();

        setProfile(nama, organisasi, email, telpon);
    }

    private void loadProfile(){
        profileArrayList.clear();
        String currentEmail = user.getEmail();
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
                nama = profileArrayList.get(0).getNama();
                organisasi = profileArrayList.get(0).getOrganisasi();
                email = profileArrayList.get(0).getEmail();
                telpon = profileArrayList.get(0).getTelpon();
                setProfile(nama, organisasi, email, telpon);
            }
        });
    }

    private void setProfile(String nama, String organisasi, String email, String telpon){
        tvNama.setText(nama);
        tvOrganisasi.setText(organisasi);
        tvEmail.setText(email);
        tvNomer.setText(telpon);

        if (!usersPref.usersLoaded()){
            saveUserData(nama, organisasi, email, telpon);
        }
    }

    private void saveUserData(String nama, String organisasi, String email, String telpon){
        usersPref.setUserProfile(nama, organisasi, email, telpon);
        usersPref.setUsersLoaded(true);
    }

    private void deleteUserData(){
        usersPref.setUserProfile("", "", "", "");
        usersPref.setUsersLoaded(false);
    }
}
