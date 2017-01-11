package dk.meem.minappnr1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FeatureTest {
    @Test
    public void sortTest() throws Exception {
        List<Feature> features = new ArrayList<>();

        Feature f = new Feature("By", "Glamsbjerg", "Nr. Åby");
        features.add(f);
        f = new Feature("Mose", "Middelfart", "Ejby Mose");
        features.add(f);
        f = new Feature("Institution", "Fåborg", "Fåborg Hospital");
        features.add(f);

        assertEquals("Glamsbjerg", features.get(0).getKommune_navn());
        assertEquals("Middelfart", features.get(1).getKommune_navn());
        assertEquals("Fåborg", features.get(2).getKommune_navn());

        Collections.sort(features);

        assertEquals("Fåborg", features.get(0).getKommune_navn());
        assertEquals("Glamsbjerg", features.get(1).getKommune_navn());
        assertEquals("Middelfart", features.get(2).getKommune_navn());
    }
}