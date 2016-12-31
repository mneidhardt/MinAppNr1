package dk.meem.minappnr1;

/**
 * Created by mine on 18/12/2016.
 */


public class Feature {
    private String kategori;
    private String kommune_navn;
    private String stednavn;

    public Feature() {
    }

    public Feature(String kategori, String kommune_navn, String stednavn) {
        this.kategori = kategori;
        this.kommune_navn = kommune_navn;
        this.stednavn = stednavn;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKommune_navn() {
        return kommune_navn;
    }

    public void setKommune_navn(String kommune_navn) {
        this.kommune_navn = kommune_navn;
    }

    public String getStednavn() {
        return stednavn;
    }

    public void setStednavn(String stednavn) {
        this.stednavn = stednavn;
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
}