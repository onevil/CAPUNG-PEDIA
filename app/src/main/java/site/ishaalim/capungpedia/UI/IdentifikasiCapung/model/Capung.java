package site.ishaalim.capungpedia.UI.IdentifikasiCapung.model;

import java.util.ArrayList;

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
    ArrayList<String> habitat;
    ArrayList<String> images;
    ArrayList<String> fotoOleh;
    ArrayList<String> captions;



    public Capung() {
    }

    public Capung(String namaSpesies, String namaIndo,
                  String namaIng, String famili,
                  String deskripsi, String kebiasaan,
                  String sosial, String infoLain,
                  String imageURL, String imageCaption,
                  String imagePhotographer, String ukuran,
                  ArrayList habitat, ArrayList images,
                  ArrayList fotoOleh, ArrayList captions) {
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
        this.habitat = habitat;
        this.images = images;
        this.fotoOleh = fotoOleh;
        this.captions = captions;
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

    public ArrayList<String> getHabitat() {
        return habitat;
    }

    public void setHabitat(ArrayList<String> habitat) {
        this.habitat = habitat;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public ArrayList<String> getFotoOleh() {
        return fotoOleh;
    }

    public void setFotoOleh(ArrayList<String> fotoOleh) {
        this.fotoOleh = fotoOleh;
    }

    public ArrayList<String> getCaptions() {
        return captions;
    }

    public void setCaptions(ArrayList<String> captions) {
        this.captions = captions;
    }
}
