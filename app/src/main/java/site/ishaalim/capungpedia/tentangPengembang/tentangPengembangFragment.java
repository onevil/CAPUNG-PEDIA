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


public class tentangPengembangFragment extends Fragment {

    private CardView icFacebook, icInstagram, icTwitter, icWebsite;

    public tentangPengembangFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tentang_pengembang, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();
        setEvents();

    }

    private void setEvents() {

        icFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURL("https://www.facebook.com/ishaalim13");
            }
        });

        icInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURL("https://www.instagram.com/ishaalim/");
            }
        });

        icTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURL("https://twitter.com/ishaalim13");
            }
        });

        icWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURL("http://www.ishaalim.site/");
            }
        });

    }

    private void initUI() {

        icFacebook = getView().findViewById(R.id.cv_iv_facebook);
        icInstagram = getView().findViewById(R.id.cv_iv_instagram);
        icTwitter = getView().findViewById(R.id.cv_iv_twitter);
        icWebsite = getView().findViewById(R.id.cv_iv_website);

    }

    private void gotoURL(String url) {

        Uri UriUrl = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, UriUrl);
        startActivity(intent);

    }


}
