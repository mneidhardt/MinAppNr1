package dk.meem.minappnr1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String STEDNAVN = "dk.meem.minappnr1.stednavn";
    public final static String KOMMUNENAVN = "dk.meem.minappnr1.kommunenavn";
    public final static String LOGIN = "dk.meem.minappnr1.stednavn_login";
    public final static String PASSWORD = "dk.meem.minappnr1.stednavn_password";
    public final static String COORDSYS = "dk.meem.minappnr1.stednavn_coordsys";
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
    }

    /** Called when the user clicks the Send button */
    public void searchPlacename(View view) {
        //Intent intent = new Intent(this, DisplaySearchresult.class);
        Intent intent = new Intent(this, DisplaySearchResultOnMap.class);
        EditText editText1 = (EditText) findViewById(R.id.stednavn);
        EditText editText2 = (EditText) findViewById(R.id.kommunenavn);
        intent.putExtra(STEDNAVN, editText1.getText().toString());
        intent.putExtra(KOMMUNENAVN, editText2.getText().toString());
        intent.putExtra(LOGIN, sharedPref.getString("stednavn_login", ""));
        intent.putExtra(PASSWORD, sharedPref.getString("stednavn_password", ""));
        intent.putExtra(COORDSYS, sharedPref.getString("stednavn_coordsys", ""));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    // Handles the user's menu selection.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent settingsActivity = new Intent(getBaseContext(), SettingsActivity.class);
                startActivity(settingsActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
