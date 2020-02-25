package site.ishaalim.capungpedia.Evaluasi;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

import site.ishaalim.capungpedia.Evaluasi.model.isiSoal;
import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.SharedPref.SharedPref;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class QuizActivity extends AppCompatActivity {
    private Button btn_answer1, btn_answer2, btn_answer3, btn_answer4, btn_answer5, btnSelesai;
    private TextView tv_indicator, tv_soal, tvNilaiAkhir, tvNilai;
    Toolbar tb_quiz;

    Dialog nilaiDialog;

    FirebaseFirestore firestore;

    ArrayList<isiSoal> isiSoalArrayList, tempisiSoalArrayList;
    ArrayList<String> JawabanArrayList, tempJawabanArrayList;

    String paket, btn1, btn2, btn3, btn4, btn5, idsoal, jawabanbenar;
    int jumlahsoal, score, posisisoal, nilaiakhir;

    SharedPref sharedpref;

    Drawable backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);
        checkTheme();
        super.onCreate(savedInstanceState);

        getExtra();
        score = 0;
        posisisoal = 0;

        setContentView(R.layout.activity_quiz);
        setUpFirestore();
        isiSoalArrayList = new ArrayList<>();
        JawabanArrayList = new ArrayList<>();
        tempisiSoalArrayList = new ArrayList<>();
        tempJawabanArrayList = new ArrayList<>();
        initUI();
        nilaiDialog = new Dialog(this);
        paket = "1";
        tb_quiz.setTitle("Evaluasi");
        setUpToolbar();
        setSupportActionBar(tb_quiz);
        loadSoal();

        btn_answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "jawaban benar :" + jawabanbenar + "jawaban : " + btn1);
                if (posisisoal != (jumlahsoal - 1)) {
                    if (btn1.equals(jawabanbenar)) {
                        posisisoal += 1;
                        setSoal(posisisoal);
                        score += 10;
                    } else {
                        posisisoal += 1;
                        setSoal(posisisoal);
                    }

                } else {
                    if (btn1.equals(jawabanbenar)) {
                        score += 10;

                    } else {

                    }
                    showHasilAkhirDialog();
                }
                Log.d(TAG, "posisi" + posisisoal);
                Log.d(TAG, "jumlah soal" + jumlahsoal);

            }
        });

        btn_answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "jawaban benar :" + jawabanbenar + "jawaban : " + btn3);
                if (posisisoal != (jumlahsoal - 1)) {
                    if (btn2.equals(jawabanbenar)) {
                        posisisoal += 1;
                        setSoal(posisisoal);
                        score += 10;
                    } else {
                        posisisoal += 1;
                        setSoal(posisisoal);
                    }

                } else {
                    if (btn2.equals(jawabanbenar)) {
                        score += 10;

                    } else {

                    }
                    showHasilAkhirDialog();
                }
                Log.d(TAG, "posisi" + posisisoal);
                Log.d(TAG, "jumlah soal" + jumlahsoal);

            }
        });

        btn_answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "jawaban benar :" + jawabanbenar + "jawaban : " + btn3);
                if (posisisoal != (jumlahsoal - 1)) {
                    if (btn3.equals(jawabanbenar)) {
                        posisisoal += 1;
                        setSoal(posisisoal);
                        score += 10;
                    } else {
                        posisisoal += 1;
                        setSoal(posisisoal);
                    }

                } else {
                    if (btn3.equals(jawabanbenar)) {
                        score += 10;

                    } else {

                    }
                    showHasilAkhirDialog();
                }
                Log.d(TAG, "posisi" + posisisoal);
                Log.d(TAG, "jumlah soal" + jumlahsoal);

            }
        });

        btn_answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "jawaban benar :" + jawabanbenar + "jawaban : " + btn4);
                if (posisisoal != (jumlahsoal - 1)) {
                    if (btn4.equals(jawabanbenar)) {
                        posisisoal += 1;
                        setSoal(posisisoal);
                        score += 10;
                    } else {
                        posisisoal += 1;
                        setSoal(posisisoal);
                    }

                } else {
                    if (btn4.equals(jawabanbenar)) {
                        score += 10;

                    } else {

                    }
                    showHasilAkhirDialog();
                }

                Log.d(TAG, "posisi" + posisisoal);
                Log.d(TAG, "jumlah soal" + jumlahsoal);

            }
        });

        btn_answer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "jawaban benar :" + jawabanbenar + "jawaban : " + btn5);
                if (posisisoal != (jumlahsoal - 1)) {
                    if (btn5.equals(jawabanbenar)) {
                        posisisoal += 1;
                        setSoal(posisisoal);
                        score += 10;
                    } else {
                        posisisoal += 1;
                        setSoal(posisisoal);
                    }

                } else {
                    if (btn5.equals(jawabanbenar)) {
                        score += 10;

                    } else {

                    }
                    showHasilAkhirDialog();

                }
                Log.d(TAG, "posisi" + posisisoal);
                Log.d(TAG, "jumlah soal" + jumlahsoal);

            }
        });
    }

    private void checkTheme(){

        if(sharedpref.loadNightModeState()==true) {
            setTheme(R.style.DarkTheme);
        }
        else  {
            sharedpref.setNightModeState(false);
            setTheme(R.style.AppTheme);
        }

        setUpIcons();

    }

    private void setUpIcons(){
        backIcon = getDrawable(R.drawable.ic_back);

        if (sharedpref.loadNightModeState() == true){
            setTintWhite(backIcon);
        }else {
            setTintBlack(backIcon);

        }
    }

    private void setTintBlack(Drawable drawable){
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, getResources().getColor(R.color.black));
    }

    private void setTintWhite(Drawable drawable){
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, getResources().getColor(R.color.white));
    }

    private void showHasilAkhirDialog() {
        nilaiDialog.setContentView(R.layout.dialog_nilai_akhir);
        btnSelesai = nilaiDialog.findViewById(R.id.btn_selesai);
        tvNilai = nilaiDialog.findViewById(R.id.tv_nilai);
        tvNilaiAkhir = nilaiDialog.findViewById(R.id.tv_nilai_akhir);
        nilaiakhir = (score / jumlahsoal) * 10;
        String Nilai = Integer.toString(nilaiakhir);
        tvNilai.setText(Nilai);

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nilaiDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        nilaiDialog.setCanceledOnTouchOutside(false);
        nilaiDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    finish();
                }
                return true;
            }
        });
        nilaiDialog.show();

    }



    private void getExtra() {
        jumlahsoal = getIntent().getExtras().getInt("jumlahsoal");
        Log.d(TAG, "jumlah soall :" + jumlahsoal);
        idsoal = getIntent().getExtras().getString("idsoal");
        Log.d(TAG, "idsoal :" + idsoal);
    }

    private void setSoal(int posisi) {
        JawabanArrayList.clear();
        jawabanbenar = isiSoalArrayList.get(posisi).getJawabanbenar();
        Log.d(TAG, "jawaban" + jawabanbenar);
        tv_indicator.setText("Soal " + (posisi + 1) + " dari " + jumlahsoal);
        tv_soal.setText(isiSoalArrayList.get(posisi).getSoal());


        JawabanArrayList = isiSoalArrayList.get(posisi).getJawaban();
        Collections.shuffle(JawabanArrayList);
        if (JawabanArrayList != null) {
            btn1 = JawabanArrayList.get(0);
            btn_answer1.setText(btn1);
            Log.d(TAG, btn1);
            btn2 = JawabanArrayList.get(1);
            btn_answer2.setText(btn2);
            Log.d(TAG, btn2);
            btn3 = JawabanArrayList.get(2);
            btn_answer3.setText(btn3);
            Log.d(TAG, btn3);
            btn4 = JawabanArrayList.get(3);
            btn_answer4.setText(btn4);
            Log.d(TAG, btn4);
            btn5 = JawabanArrayList.get(4);
            btn_answer5.setText(btn5);
            Log.d(TAG, btn5);
        }


    }

    void GetArray(ArrayList<isiSoal> tempisiSoalArrayList) {
        isiSoalArrayList = tempisiSoalArrayList;
        Collections.shuffle(isiSoalArrayList);
        Log.d(TAG, "arraylist :" + isiSoalArrayList);
        setSoal(posisisoal);

    }

    private void loadSoal() {
        isiSoalArrayList.clear();
        CollectionReference firestoreRef = firestore.collection("Soal").document(idsoal).collection("isiSoal");
        Query queryLoadSoal = firestoreRef.orderBy("soal", Query.Direction.ASCENDING);
        queryLoadSoal.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot querySnapshotLoadSoal : task.getResult()) {
                    if (task.getResult() != null) {
                        isiSoal isiSoal = querySnapshotLoadSoal.toObject(isiSoal.class);
                        tempisiSoalArrayList.add(isiSoal);
                    } else {
                        Log.d(TAG, "No such Document");
                    }
                }
                GetArray(tempisiSoalArrayList);
                Log.d(TAG, "queryarray" + tempisiSoalArrayList);
            }
        });

    }

    private void setUpFirestore() {
        firestore = FirebaseFirestore.getInstance();
    }

    private void setUpToolbar() {
        tb_quiz.setNavigationIcon(R.drawable.ic_back);
    }

    private void initUI() {
        btn_answer1 = findViewById(R.id.btn_answer1);
        btn_answer2 = findViewById(R.id.btn_answer2);
        btn_answer3 = findViewById(R.id.btn_answer3);
        btn_answer4 = findViewById(R.id.btn_answer4);
        btn_answer5 = findViewById(R.id.btn_answer5);

        tv_soal = findViewById(R.id.tv_soal_quiz);
        tv_indicator = findViewById(R.id.tv_indicator_quiz);
        tb_quiz = findViewById(R.id.toolbar_quiz);
    }
}
