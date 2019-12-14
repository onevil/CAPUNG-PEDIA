package site.ishaalim.capungpedia.Materi.model;

public class listMateri {
    String judul;
    String deskripsi;
    String imageURL;

    public listMateri() {
    }

    public listMateri(String judul, String deskripsi, String imageURL) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.imageURL = imageURL;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
