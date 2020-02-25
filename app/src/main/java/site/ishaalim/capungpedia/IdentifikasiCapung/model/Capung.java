package site.ishaalim.capungpedia.IdentifikasiCapung.model;

public class Capung {
    String namaSpesies;
    String namaIndo;
    String namaIng;
    String famili;
    String deskripsi;
    String kebiasaan;
    String sosial;
    String infoLain;
    String imageURL;
    String imageCaption;
    String imagePhotographer;
    String ukuran;


    public Capung() {
    }

    public Capung(String namaSpesies, String namaIndo,
                  String namaIng, String famili,
                  String deskripsi, String kebiasaan,
                  String sosial, String infoLain,
                  String imageURL, String imageCaption,
                  String imagePhotographer, String ukuran) {
        this.namaSpesies = namaSpesies;
        this.namaIndo = namaIndo;
        this.namaIng = namaIng;
        this.famili = famili;
        this.deskripsi = deskripsi;
        this.kebiasaan = kebiasaan;
        this.sosial = sosial;
        this.infoLain = infoLain;
        this.imageURL = imageURL;
        this.imageCaption = imageCaption;
        this.imagePhotographer = imagePhotographer;
        this.ukuran = ukuran;
    }

    public String getNamaSpesies() {
        return namaSpesies;
    }

    public void setNamaSpesies(String namaSpesies) {
        this.namaSpesies = namaSpesies;
    }

    public String getNamaIndo() {
        return namaIndo;
    }

    public void setNamaIndo(String namaIndo) {
        this.namaIndo = namaIndo;
    }

    public String getNamaIng() {
        return namaIng;
    }

    public void setNamaIng(String namaIng) {
        this.namaIng = namaIng;
    }

    public String getFamili() {
        return famili;
    }

    public void setFamili(String famili) {
        this.famili = famili;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKebiasaan() {
        return kebiasaan;
    }

    public void setKebiasaan(String kebiasaan) {
        this.kebiasaan = kebiasaan;
    }

    public String getSosial() {
        return sosial;
    }

    public void setSosial(String sosial) {
        this.sosial = sosial;
    }

    public String getInfoLain() {
        return infoLain;
    }

    public void setInfoLain(String infoLain) {
        this.infoLain = infoLain;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageCaption() {
        return imageCaption;
    }

    public void setImageCaption(String imageCaption) {
        this.imageCaption = imageCaption;
    }

    public String getImagePhotographer() {
        return imagePhotographer;
    }

    public void setImagePhotographer(String imagePhotographer) {
        this.imagePhotographer = imagePhotographer;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }
}
