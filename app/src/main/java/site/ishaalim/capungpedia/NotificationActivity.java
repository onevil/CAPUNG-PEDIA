package site.ishaalim.capungpedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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

    }

    private void setEvents(){

    }

    private void loadURL() {
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        WebSettings webSettings = webView.getSettings();
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
