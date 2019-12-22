package site.ishaalim.capungpedia.Glosarium.model;

public class Glosarium {
    String kataKunci;
    String pengertian;

    public Glosarium() {
    }

    public Glosarium(String kataKunci, String pengertian) {
        this.kataKunci = kataKunci;
        this.pengertian = pengertian;
    }

    public String getKataKunci() {
        return kataKunci;
    }

    public void setKataKunci(String kataKunci) {
        this.kataKunci = kataKunci;
    }

    public String getPengertian() {
        return pengertian;
    }

    public void setPengertian(String pengertian) {
        this.pengertian = pengertian;
    }
}
