package dk.meem.minappnr1;

import java.util.Comparator;

/**
 * Created by mine on 18/12/2016.
 */


class Feature implements Comparable<Feature> {
//public class Feature {
    private String kategori;
    private String kommune_navn;
    private String stednavn;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feature feature = (Feature) o;

        if (!kategori.equals(feature.kategori)) return false;
        if (!kommune_navn.equals(feature.kommune_navn)) return false;
        return stednavn.equals(feature.stednavn);

    }

    @Override
    public int hashCode() {
        int result = kategori.hashCode();
        result = 31 * result + kommune_navn.hashCode();
        result = 31 * result + stednavn.hashCode();
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