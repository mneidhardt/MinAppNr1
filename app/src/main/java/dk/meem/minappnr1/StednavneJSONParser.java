package dk.meem.minappnr1;

/**
 * Created by mine on 18/12/2016.
 */

import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mine on 16/12/2016.
 */

public class StednavneJSONParser {
    private String kommunenavn;

    public List<Feature> readJsonStream(InputStream in, String kommunenavn) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Feature> messages = new ArrayList<Feature>();
        this.kommunenavn = kommunenavn;

        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equalsIgnoreCase("features")) {
                    messages = readFeaturesArray(reader);
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();

            return messages;
        } finally {
            reader.close();
        }
    }

    public List<Feature> readFeaturesArray(JsonReader reader) throws IOException {
        List<Feature> messages = new ArrayList<Feature>();

        reader.beginArray();

        while (reader.hasNext()) {
            Feature feature = readFeature(reader);
            if (this.kommunenavn == null ||
                    this.kommunenavn.isEmpty() ||
                    feature.getKommune_navn() == null ||
                    feature.getKommune_navn().isEmpty() ||
                    feature.getKommune_navn().trim().equalsIgnoreCase(kommunenavn.trim())) {
                messages.add(feature);
            }
        }
        reader.endArray();
        return messages;
    }

    public Feature readFeature(JsonReader reader) throws IOException {
        String name;
        Feature feature = new Feature();

        reader.beginObject();

        while (reader.hasNext()) {
            name = reader.nextName();

            if (name.equals("properties")) {
                feature = readProperties(reader);
            } else {
                reader.skipValue();
            }
        }

        reader.endObject();
        return feature;
    }

    public Feature readProperties(JsonReader reader) throws IOException {
        String kategori = null;
        String kommune_navn = null;
        String stednavn = null;
        String name = null;

        reader.beginObject();

        while (reader.hasNext()) {
            name = reader.nextName();

            if (name.equals("kategori")) {
                kategori = reader.nextString();
            } else if (name.equals("kommune_navn")) {
                kommune_navn = reader.nextString();
            } else if (name.equals("stednavneliste") && reader.peek() != JsonToken.NULL) {
                stednavn = readStednavneArray(reader);
            } else {
                reader.skipValue();
            }
        }

        reader.endObject();
        return new Feature(kategori, kommune_navn, stednavn);

    }

    public String readStednavneArray(JsonReader reader) throws IOException {
        String stednavne = "";

        reader.beginArray();
        while (reader.hasNext()) {
            stednavne += readStednavn(reader) + " ";
        }
        reader.endArray();

        return stednavne;
    }

    public String readStednavn(JsonReader reader) throws IOException {
        String stednavn = "";
        String status = "";

        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equalsIgnoreCase("navn")) {
                stednavn = reader.nextString();
            } else if (name.equalsIgnoreCase("status")) {
                status = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();

        return stednavn + "/" + status;

    }
}
