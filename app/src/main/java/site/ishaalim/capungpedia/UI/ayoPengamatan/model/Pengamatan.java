package site.ishaalim.capungpedia.UI.ayoPengamatan.model;

import android.net.Uri;

import java.util.Date;

public class Pengamatan {
    String namaSpesies, habitat, lokasi, aktifiktas, deskripsi, jumlah;
    Uri imageUri;
    Date pukul;

    public Pengamatan() {
    }

    public Pengamatan(String namaSpesies, String habitat, String lokasi, String aktifiktas, String deskripsi, String jumlah, Uri imageUri, Date pukul) {
        this.namaSpesies = namaSpesies;
        this.habitat = habitat;
        this.lokasi = lokasi;
        this.aktifiktas = aktifiktas;
        this.deskripsi = deskripsi;
        this.jumlah = jumlah;
        this.imageUri = imageUri;
        this.pukul = pukul;
    }

    public String getNamaSpesies() {
        return namaSpesies;
    }

    public void setNamaSpesies(String namaSpesies) {
        this.namaSpesies = namaSpesies;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getAktifiktas() {
        return aktifiktas;
    }

    public void setAktifiktas(String aktifiktas) {
        this.aktifiktas = aktifiktas;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public Date getPukul() {
        return pukul;
    }

    public void setPukul(Date pukul) {
        this.pukul = pukul;
    }
}
