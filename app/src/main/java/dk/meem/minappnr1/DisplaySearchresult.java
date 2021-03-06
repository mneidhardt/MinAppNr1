package dk.meem.minappnr1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class DisplaySearchresult extends AppCompatActivity {
    private static final String baseURL =
            "http://services.kortforsyningen.dk/?servicename=RestGeokeys_v2&method=stedv2";
    private static final int maxhits = 10000;
    private String stednavn = null;
    private String kommunenavn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_searchresult);

        Intent intent = getIntent();
        this.stednavn = intent.getStringExtra(MainActivity.STEDNAVN);
        this.kommunenavn = intent.getStringExtra(MainActivity.KOMMUNENAVN);
        String login = intent.getStringExtra(MainActivity.LOGIN);
        String password = intent.getStringExtra(MainActivity.PASSWORD);
        String coordsys = intent.getStringExtra(MainActivity.COORDSYS);

        if (haveNetwork()) {
            String URL = baseURL +
                    "&hits="     + maxhits +
                    "&stednavn=" + stednavn +
                    "&login=" + login +
                    "&password=" + password +
                    "&outgeoref=" + coordsys;

            new DownloadXmlTask().execute(URL);
        } else {
            setContentView(R.layout.activity_display_searchresult);
            // Displays the HTML string in the UI via a WebView
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadData("<p>No network.</p>", "text/html", null);
        }
    }

    public boolean haveNetwork() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    // Implementation of AsyncTask used to download XML feed from stackoverflow.com.
    private class DownloadXmlTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return loadXmlFromNetwork(urls[0]);
            } catch (IOException e) {
                return getResources().getString(R.string.connection_error);
            }
        }

        @Override
        protected void onPostExecute(String result) {
            setContentView(R.layout.activity_display_searchresult);
            // Displays the HTML string in the UI via a WebView
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadData(result, "text/html; charset=utf-8", "utf-8");
        }
    }

    // Uploads XML from stackoverflow.com, parses it, and combines it with
    // HTML markup. Returns HTML string.
    private String loadXmlFromNetwork(String urlString) throws IOException {
        InputStream stream = null;
        StednavneJSONParser stjsonParser = new StednavneJSONParser();
        List<Feature> features = null;
        String title = null;
        String url = null;
        String summary = null;
        Calendar rightNow = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("dd. MMM H:mm");

        try {
            stream = downloadUrl(urlString);
            features = stjsonParser.readJsonStream(stream, this.kommunenavn);
            Collections.sort(features);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        StringBuilder htmlString = new StringBuilder();
        htmlString.append("<h3>Stednavne fra Kortforsyningen</h3>");
        htmlString.append("<em>Hentede " + features.size() + " resultater. " + formatter.format(rightNow.getTime()) + "</em>");
        htmlString.append("<p/>" + "<table><tr><td><b>Stednavn</b></td><td><b>Kategori</b></td><td><b>Kommune</b></td><td>BBox</td></tr>");
        for (Feature feature : features) {
            htmlString.append("<tr>" +
                    "<td>" + feature.getStednavn() + "</td>" +
                    "<td>" + feature.getKategori() + "</td>" +
                    "<td>" + feature.getKommune_navn() + "</td>" +
                    "<td>" + feature.bboxAsString() + "</td>" +
                    "</tr>");

        }
        htmlString.append("</table>");
        return htmlString.toString();
    }

    // Given a string representation of a URL, sets up a connection and gets
    // an input stream.
    private InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
        InputStream stream = conn.getInputStream();
        return stream;
    }
}
