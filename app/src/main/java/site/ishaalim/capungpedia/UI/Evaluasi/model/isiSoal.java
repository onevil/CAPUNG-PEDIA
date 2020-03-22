package site.ishaalim.capungpedia.UI.Evaluasi.model;

import java.util.ArrayList;

public class isiSoal {
    String soal;
    String jawabanbenar;
    ArrayList<String> jawaban;

    public isiSoal() {
    }

    public isiSoal(String soal, String jawabanbenar, ArrayList<String> jawaban) {
        this.soal = soal;
        this.jawabanbenar = jawabanbenar;
        this.jawaban = jawaban;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getJawabanbenar() {
        return jawabanbenar;
    }

    public void setJawabanbenar(String jawabanbenar) {
        this.jawabanbenar = jawabanbenar;
    }

    public ArrayList<String> getJawaban() {
        return jawaban;
    }

    public void setJawaban(ArrayList<String> jawaban) {
        this.jawaban = jawaban;
    }
}
