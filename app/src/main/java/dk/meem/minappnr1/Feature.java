package dk.meem.minappnr1;

import java.util.Comparator;
import java.util.List;

class Feature implements Comparable<Feature> {
//public class Feature {
    private String kategori;
    private String kommune_navn;
    private String stednavn;
    private List<Double> bbox; // This is the bounding box of this feature.

    Feature() {
    }

    Feature(String kategori, String kommune_navn, String stednavn) {
        this.kategori = kategori;
        this.kommune_navn = kommune_navn;
        this.stednavn = stednavn;
    }

    String getKategori() {
        return kategori;
    }

    String getKommune_navn() {
        return kommune_navn;
    }

    String getStednavn() {
        return stednavn;
    }

    public List<Double> getBbox() {
        return bbox;
    }

    public String bboxAsString() {
        String res="[";

        for (double d : bbox) {
            res += d + ", ";
        }

        return res + "]";
    }

    public void setBbox(List<Double> bbox) {
        this.bbox = bbox;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feature feature = (Feature) o;

        if (kategori != null ? !kategori.equals(feature.kategori) : feature.kategori != null)
            return false;
        if (kommune_navn != null ? !kommune_navn.equals(feature.kommune_navn) : feature.kommune_navn != null)
            return false;
        if (stednavn != null ? !stednavn.equals(feature.stednavn) : feature.stednavn != null)
            return false;
        return bbox != null ? bbox.equals(feature.bbox) : feature.bbox == null;

    }

    @Override
    public int hashCode() {
        int result = kategori != null ? kategori.hashCode() : 0;
        result = 31 * result + (kommune_navn != null ? kommune_navn.hashCode() : 0);
        result = 31 * result + (stednavn != null ? stednavn.hashCode() : 0);
        result = 31 * result + (bbox != null ? bbox.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Feature f) {
        if (this.kommune_navn == null) {
            if (f.getKommune_navn() == null) {
                return 0;
            } else {
                return -1;
            }
        } else if (f.getKommune_navn() == null) {
            return 1;
        } else {
            return this.kommune_navn.compareTo(f.getKommune_navn());
        }
    }

}