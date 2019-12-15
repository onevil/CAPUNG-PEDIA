package site.ishaalim.capungpedia.Materi.model;

public class isiMateri {
    String id;
    String headerMateri;
    String bahasanMateri;
    String imageURL;

    public isiMateri() {
    }

    public isiMateri(String id, String headerMateri, String bahasanMateri, String imageURL) {
        this.id = id;
        this.headerMateri = headerMateri;
        this.bahasanMateri = bahasanMateri;
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeaderMateri() {
        return headerMateri;
    }

    public void setHeaderMateri(String headerMateri) {
        this.headerMateri = headerMateri;
    }

    public String getBahasanMateri() {
        return bahasanMateri;
    }

    public void setBahasanMateri(String bahasanMateri) {
        this.bahasanMateri = bahasanMateri;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
