package site.ishaalim.capungpedia.UI.ayoPengamatan.model;


import java.util.Date;

public class ayoPengamatan {
    String judulPengamatan, nama, lokasiPengamatan,imageURL;
    Date tanggalPengamatan;


    public ayoPengamatan() {
    }

    public ayoPengamatan(String judulPengamatan, String nama, String lokasiPengamatan, String imageURL, Date tanggalPengamatan) {
        this.judulPengamatan = judulPengamatan;
        this.nama = nama;
        this.lokasiPengamatan = lokasiPengamatan;
        this.imageURL = imageURL;
        this.tanggalPengamatan = tanggalPengamatan;
    }

    public String getJudulPengamatan() {
        return judulPengamatan;
    }

    public void setJudulPengamatan(String judulPengamatan) {
        this.judulPengamatan = judulPengamatan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLokasiPengamatan() {
        return lokasiPengamatan;
    }

    public void setLokasiPengamatan(String lokasiPengamatan) {
        this.lokasiPengamatan = lokasiPengamatan;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Date getTanggalPengamatan() {
        return tanggalPengamatan;
    }

    public void setTanggalPengamatan(Date tanggalPengamatan) {
        this.tanggalPengamatan = tanggalPengamatan;
    }
}
