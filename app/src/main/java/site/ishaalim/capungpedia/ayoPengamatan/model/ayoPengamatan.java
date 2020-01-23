package site.ishaalim.capungpedia.ayoPengamatan.model;



public class ayoPengamatan {
    String judulPengamatan,lokasiPengamatan,tanggalPengamatan ,imageURL;


    public ayoPengamatan() {
    }

    public ayoPengamatan(String judulPengamatan, String lokasiPengamatan, String tanggaPengamatan, String imageURL) {
        this.judulPengamatan = judulPengamatan;
        this.lokasiPengamatan = lokasiPengamatan;
        this.tanggalPengamatan = tanggaPengamatan;
        this.imageURL = imageURL;
    }

    public String getJudulPengamatan() {
        return judulPengamatan;
    }

    public void setJudulPengamatan(String judulPengamatan) {
        this.judulPengamatan = judulPengamatan;
    }

    public String getLokasiPengamatan() {
        return lokasiPengamatan;
    }

    public void setLokasiPengamatan(String lokasiPengamatan) {
        this.lokasiPengamatan = lokasiPengamatan;
    }

    public String getTanggalPengamatan() {
        return tanggalPengamatan;
    }

    public void setTanggalPengamatan(String tanggaPengamatan) {
        this.tanggalPengamatan = tanggaPengamatan;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
