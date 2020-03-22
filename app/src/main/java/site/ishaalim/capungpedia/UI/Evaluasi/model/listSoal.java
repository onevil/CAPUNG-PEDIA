package site.ishaalim.capungpedia.UI.Evaluasi.model;

public class listSoal {

    String namaSoal;
    int jumlahsoal;
    String idsoal;

    public listSoal() {
    }

    public listSoal(String namaSoal, int jumlahsoal, String idsoal) {
        this.namaSoal = namaSoal;
        this.jumlahsoal = jumlahsoal;
        this.idsoal = idsoal;
    }

    public String getNamaSoal() {
        return namaSoal;
    }

    public void setNamaSoal(String namaSoal) {
        this.namaSoal = namaSoal;
    }

    public int getJumlahsoal() {
        return jumlahsoal;
    }

    public void setJumlahsoal(int jumlahsoal) {
        this.jumlahsoal = jumlahsoal;
    }

    public String getIdsoal() {
        return idsoal;
    }

    public void setIdsoal(String idsoal) {
        this.idsoal = idsoal;
    }
}
