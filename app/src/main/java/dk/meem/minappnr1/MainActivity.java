package dk.meem.minappnr1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String STEDNAVN = "dk.meem.minappnr1.stednavn";
    public final static String KOMMUNENR = "dk.meem.minappnr1.kommunenr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Send button */
    public void searchPlacename(View view) {
        Intent intent = new Intent(this, DisplaySearchresult.class);
        EditText editText1 = (EditText) findViewById(R.id.stednavn);
        EditText editText2 = (EditText) findViewById(R.id.kommunenr);
        intent.putExtra(STEDNAVN, editText1.getText().toString());
        intent.putExtra(KOMMUNENR, editText2.getText().toString());
        startActivity(intent);
    }
}
