package site.ishaalim.capungpedia.UI.Materi.model;

public class listMateri {
    String id;
    String judul;
    String deskripsi;
    String imageURL;
    int jumlahHalaman;

    public listMateri() {
    }

    public listMateri(String id, String judul, String deskripsi, String imageURL, int jumlahHalaman) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.imageURL = imageURL;
        this.jumlahHalaman = jumlahHalaman;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getJumlahHalaman() {
        return jumlahHalaman;
    }

    public void setJumlahHalaman(int jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }
}
