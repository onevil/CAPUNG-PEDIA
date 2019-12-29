package site.ishaalim.capungpedia.mengenalDesa.model;

public class isiHalamanMengenalDesa {
    String header;
    String isi;
    String id;
    String imageURL;

    public isiHalamanMengenalDesa() {
    }

    public isiHalamanMengenalDesa(String header, String isi, String id, String imageURL) {
        this.header = header;
        this.isi = isi;
        this.id = id;
        this.imageURL = imageURL;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
