package site.ishaalim.capungpedia.laporBug.model;

public class Bug {
    String nama;
    String bug;

    public Bug() {
    }

    public Bug(String nama, String bug) {
        this.nama = nama;
        this.bug = bug;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getBug() {
        return bug;
    }

    public void setBug(String bug) {
        this.bug = bug;
    }
}
