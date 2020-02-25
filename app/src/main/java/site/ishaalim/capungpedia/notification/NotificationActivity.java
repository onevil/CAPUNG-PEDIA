package site.ishaalim.capungpedia.notification;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import maes.tech.intentanim.CustomIntent;
import site.ishaalim.capungpedia.MainActivity;
import site.ishaalim.capungpedia.R;

public class NotificationActivity extends AppCompatActivity {
    private WebView webView;
    private Toolbar toolbar;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        getExtras();
        initUI();
        setToolbar();
        setEvents();
        loadURL();
    }

    private void getExtras() {
        url = getIntent().getExtras().getString("URL");
    }

    private void initUI() {
        webView = findViewById(R.id.wv_notifikasi);
        toolbar = findViewById(R.id.toolbar_notifikasi);
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
    }

    private void setEvents(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                CustomIntent.customType(getApplicationContext(), "right-to-left");
            }
        });
    }

    private void loadURL() {
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        WebSettings webSettings = webView.getSettings();
        String newUA= "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0";
        webView.getSettings().setUserAgentString(newUA);
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
