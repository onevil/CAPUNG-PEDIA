package site.ishaalim.capungpedia.authentication;


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

import site.ishaalim.capungpedia.MainActivity;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.ayoPengamatan.AyoPengamatanFragment;

public class LoginFragment extends Fragment {
    EditText edtEmail, edtPassword;
    Button btnLogIn;
    TextView tvSignIn;

    String email, password;

    FirebaseAuth firebaseAuth;


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
        initUI();
        setEvents();
    }

    private void setUpFirebase(){
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void initUI(){
        edtEmail = getView().findViewById(R.id.edt_email);
        edtPassword = getView().findViewById(R.id.edt_password);
        tvSignIn = getView().findViewById(R.id.tv_bergabung);
        btnLogIn = getView().findViewById(R.id.btn_logIn);
    }

    private void setEvents(){
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                String TAG = "ayo_pengamatan";
                                AyoPengamatanFragment fragment = new AyoPengamatanFragment();
                               changeFragment(TAG, fragment);
                            }else {
                                Toast.makeText(getContext(), "Email atau Password salah!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void changeFragment(String TAG, Fragment fragment){
        ((MainActivity) getActivity()).setFragment(fragment, TAG);
    }
}
