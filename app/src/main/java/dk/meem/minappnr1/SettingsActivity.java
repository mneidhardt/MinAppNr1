package dk.meem.minappnr1;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by mine on 29/12/2016.
 */


public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}