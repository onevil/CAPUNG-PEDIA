package site.ishaalim.capungpedia.IdentifikasiCapung.model;

public class Capung {
    String namaSpesies;
    String famili;
    String subOrdo;
    String imageURL;

    public Capung() {
    }

    public Capung(String namaSpesies, String famili, String subOrdo, String imageURL) {
        this.namaSpesies = namaSpesies;
        this.famili = famili;
        this.subOrdo = subOrdo;
        this.imageURL = imageURL;
    }

    public String getNamaSpesies() {
        return namaSpesies;
    }

    public void setNamaSpesies(String namaSpesies) {
        this.namaSpesies = namaSpesies;
    }

    public String getFamili() {
        return famili;
    }

    public void setFamili(String famili) {
        this.famili = famili;
    }

    public String getSubOrdo() {
        return subOrdo;
    }

    public void setSubOrdo(String subOrdo) {
        this.subOrdo = subOrdo;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
