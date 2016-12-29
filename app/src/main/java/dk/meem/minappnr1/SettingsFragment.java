package dk.meem.minappnr1;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by mine on 27/12/2016.
 */

public class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);
        }
}
