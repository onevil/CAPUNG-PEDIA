package site.ishaalim.capungpedia.UI.profile;

public class profile {
    String nama, organisasi, email, telpon;

    public profile() {
    }

    public profile(String nama, String organisasi, String email, String telpon) {
        this.nama = nama;
        this.organisasi = organisasi;
        this.email = email;
        this.telpon = telpon;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getOrganisasi() {
        return organisasi;
    }

    public void setOrganisasi(String organisasi) {
        this.organisasi = organisasi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelpon() {
        return telpon;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    }
}
