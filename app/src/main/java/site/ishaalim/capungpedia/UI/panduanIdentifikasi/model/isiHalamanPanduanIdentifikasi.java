package site.ishaalim.capungpedia.UI.panduanIdentifikasi.model;

public class isiHalamanPanduanIdentifikasi {
    String header;
    String isi;
    String id;
    String imageURL;
    String imageCaption;
    String imagePhotographer;
    String quotes;
    String author;

    public isiHalamanPanduanIdentifikasi() {
    }

    public isiHalamanPanduanIdentifikasi(String header, String isi, String id, String imageURL, String imageCaption, String imagePhotographer, String quotes, String author) {
        this.header = header;
        this.isi = isi;
        this.id = id;
        this.imageURL = imageURL;
        this.imageCaption = imageCaption;
        this.imagePhotographer = imagePhotographer;
        this.quotes = quotes;
        this.author = author;
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

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
