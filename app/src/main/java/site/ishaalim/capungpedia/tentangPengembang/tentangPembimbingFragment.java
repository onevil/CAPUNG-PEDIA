package site.ishaalim.capungpedia.tentangPengembang;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import site.ishaalim.capungpedia.R;

public class tentangPembimbingFragment extends Fragment {

    private CardView icUNY;


    public tentangPembimbingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tentang_pembimbing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();
        setEvents();
    }

    private void initUI() {

        icUNY = getView().findViewById(R.id.cv_iv_uny);


    }

    private void setEvents() {

        icUNY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURL("http://staff.uny.ac.id/dosen/yuni-wibowo-mpd");
            }
        });

    }

    private void gotoURL(String url) {

        Uri UriUrl = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, UriUrl);
        startActivity(intent);

    }
}
