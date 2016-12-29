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
    public final static String KOMMUNENR = "dk.meem.minappnr1.kommunenr";
    public final static String LOGIN = "dk.meem.minappnr1.stednavn_login";
    public final static String PASSWORD = "dk.meem.minappnr1.stednavn_password";
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
    }

    /** Called when the user clicks the Send button */
    public void searchPlacename(View view) {
        Intent intent = new Intent(this, DisplaySearchresult.class);
        EditText editText1 = (EditText) findViewById(R.id.stednavn);
        EditText editText2 = (EditText) findViewById(R.id.kommunenr);
        intent.putExtra(STEDNAVN, editText1.getText().toString());
        intent.putExtra(KOMMUNENR, editText2.getText().toString());
        intent.putExtra(LOGIN, sharedPref.getString("stednavn_login", ""));
        intent.putExtra(PASSWORD, sharedPref.getString("stednavn_password", ""));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    // Handles the user's menu selection.
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                //Intent settingsFragment = new Intent(getBaseContext(), SettingsFragment.class);
                //startActivity(settingsFragment);
                getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    // Handles the user's menu selection.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent settingsActivity = new Intent(getBaseContext(), SettingsActivity.class);
                startActivity(settingsActivity);
                //getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
