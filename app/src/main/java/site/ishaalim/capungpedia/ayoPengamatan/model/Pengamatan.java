package site.ishaalim.capungpedia.ayoPengamatan.model;

public class Pengamatan {
    String namaPengamat, habitat, cuaca, aktifiktas, deskripsi, hasil;

    public Pengamatan() {
    }

    public Pengamatan(String namaPengamat, String habitat, String cuaca, String aktifiktas, String deskripsi, String hasil) {
        this.namaPengamat = namaPengamat;
        this.habitat = habitat;
        this.cuaca = cuaca;
        this.aktifiktas = aktifiktas;
        this.deskripsi = deskripsi;
        this.hasil = hasil;
    }

    public String getNamaPengamat() {
        return namaPengamat;
    }

    public void setNamaPengamat(String namaPengamat) {
        this.namaPengamat = namaPengamat;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getCuaca() {
        return cuaca;
    }

    public void setCuaca(String cuaca) {
        this.cuaca = cuaca;
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

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }
}
