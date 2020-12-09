package com.dicle.dicle.hafiza_oyunu;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Gorsel_Islem extends AppCompatActivity {

    Random rnd;
    ArrayList<Integer> resim = null;
    ArrayList<ImageButton> imgbtn = null;
    ArrayList<Integer> benzersizresimler;
    ImageButton imgView;
    TableLayout table;

    ImageButton button;
    int sat = 2;
    int sut = 2;

    int acilanresim = 0;
    ImageView acilanView = null;
    int indis1 = 0;
    int indis2 = 0;
    int dogru = 0;
    int dogru1 = 0;
    int seviyeadim = 0;
    int seviyedegeri;
    int seviye = 0;
    TextView tvSayac;
    int saniye = 0;
    CountDownTimer ctd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gorselislem);
        imgbtn = new ArrayList<>();
        seviyedegeri = 0;

        tvSayac = (TextView) findViewById(R.id.tvSayac);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                seviyedegeri = 0;
            } else {
                seviyedegeri = extras.getInt("seviye");//1 2 3
            }
        } else {
            seviyedegeri = new Integer((String) savedInstanceState.getSerializable("seviye"));
        }
        seviye = seviyedegeri;

        rnd = new Random();
        metotlar();
    }


    private void metotlar() {
        seviyeayarla(seviye);
        tablosifirle();
        butonolusutur(sat, sut);
        butondoldur();
        lockButtonSizes(sat, sut);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgbtnKapat();
                butonAyarla(true);
                sayac();

            }
        }, 1500);


    }

    private void butondoldur() {
        resim = new ArrayList<Integer>(Arrays.asList(//liste halinde eklemeyi sağlamaktadır.
                R.color.colorAccent,
                R.color.colorPrimaryDark,
                R.color.matsecimarka,
                R.color.gorselsecim,
                R.color.selectHakkinda,
                R.color.x,
                R.color.SelectGorsel
        ));

        Collections.shuffle(resim);//her seferinde id'leri karıştırmaktadır.
        boolean ayni = false;
        int counter = 0;
        int tutulan = 0;
        benzersizresimler = new ArrayList<>();
        butonAyarla(false);

        for (int k = 0; k < sat * sut; k++) {

            ayni = false;

            do {
                counter = 0;
                tutulan = rnd.nextInt(sat * sut / 2);
                for (int i = 0; i < benzersizresimler.size(); i++) {
                    if (resim.get(tutulan).equals(benzersizresimler.get(i))) {
                        counter++;
                    }

                }
                if (counter >= 2) {
                    ayni = true;
                } else ayni = false;

            } while (ayni == true);

            benzersizresimler.add(resim.get(tutulan));
        }
        for (int a = 0; a < benzersizresimler.size(); a++) {
            imgView = imgbtn.get(a);
            imgView.setImageResource(benzersizresimler.get(a));
        }
    }

    ImageButton btnView;

    public void seviyeayarla(int seviye) {
        switch (seviye) {

            case 1:
                sat = 3;
                sut = 2;
                break;
            case 2:
                sat = 4;
                sut = 2;
                break;
            case 3:
                sat = 4;
                sut = 3;
                break;
        }
    }

    private void lockButtonSizes(int satir, int sutun) {//butonumuzun bulunduğu konumda sabit klaması için
        //gerekli methoddur. Bir butonu tıklayıp kaybolduğun diğer buto bunun yerine geçmesin
        int t = 0;
        for (int row = 0; row < satir; row++) {
            for (int col = 0; col < sutun; col++) {
                button = imgbtn.get(t);
                t++;
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int height = displayMetrics.heightPixels;//ekranın yüksekliğini alır
                int width = displayMetrics.widthPixels;//ekranın genişliğini alır

                int heightbol = height / 2 - 20;//ekranı ikiye bölüp 20 şer px çıkartıyor
                int widthbol = width / 2 - 20;
                button.setMinimumWidth(widthbol);
                button.setMaxHeight(width);

                button.setMinimumHeight(heightbol);
                button.setMaxHeight(height);
            }
        }
    }

    int a = 0;
    int tiklanan = 0;

    private void butonolusutur(int satir, int sutun) {
        table = (TableLayout) findViewById(R.id.tblImage);

        for (int row = 0; row < satir; row++) {//satır ve sütün sayısını seviye ayarla methoundan almaktadır.
            final TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT,//genişlik
                    TableLayout.LayoutParams.MATCH_PARENT,//yükseklik
                    1.0f));

            table.addView(tableRow);

            for (int col = 0; col < sutun; col++) {
                final int FINAL_COL = col;
                final int FINAL_ROW = row;
                button = new ImageButton(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                button.setId(a);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imgbtncvp(v);
                    }
                });
                a++;
                button.setPadding(9, 9, 9, 9);
                tableRow.addView(button);
                imgbtn.add(button);
                button.setScaleType(ImageView.ScaleType.FIT_XY);
            }

        }
    }

    public void tablosifirle() {
        table = (TableLayout) findViewById(R.id.tblImage);
        table.removeAllViews();
    }

    private void imgbtnKapat() {
        for (int i = 0; i < imgbtn.size(); i++) {

            imgbtn.get(i).setImageResource(R.drawable.aa);

        }
    }

    private void butonAyarla(Boolean bool) {
        for (int i = 0; i < imgbtn.size(); i++) {
            imgbtn.get(i).setClickable(bool);
        }
    }

    ImageButton img;

    public void imgbtncvp(View view) {

        img = (ImageButton) view;

        if (acilanresim == 0) {
            acilanView = (ImageButton) view;
            for (indis2 = 0; indis2 < imgbtn.size(); indis2++) {
                //ilk açılan buttonumuz

                if (acilanView.getId() == imgbtn.get(indis2).getId()) {
                    break;
                }
            }
            if (imgbtn.size() > 0)/**/
                imgbtn.get(indis2).setClickable(false);//açılan butona çift tıklamayı önlemek amacıyla yazılmıştır
        }

        for (indis1 = 0; indis1 < imgbtn.size(); indis1++) {
            //2.açılan butonumuz
            if (img.getId() == imgbtn.get(indis1).getId()) {
                break;
            }
        }
        if (imgbtn.size() > 0)/**/
            imgbtn.get(indis1).setClickable(false);//açılan butona çift tıklamayı önlemek amacıyla yazılmıştır

        acilanresim++;
        if (imgbtn.size() > 0)/**/
            imgbtn.get(indis1).setImageResource(benzersizresimler.get(indis1));
        if (acilanresim >= 2) {
            if (benzersizresimler.get(indis1).equals(benzersizresimler.get(indis2))) {
                dogru += 2;//ywni imagebutton gelmesi için gerekmektedir
                dogru1 += 2;//toplam puanı hesaplamak için kullanılmaktadır.
                acilanView.setBackgroundColor(Color.TRANSPARENT);
                img.setBackgroundColor(Color.TRANSPARENT);
                acilanView.setImageResource(android.R.color.transparent);
                img.setImageResource(android.R.color.transparent);

                img.setClickable(false);
                acilanView.setClickable(false);
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        img.setImageResource(R.drawable.aa);
                        acilanView.setImageResource(R.drawable.aa);
                        imgbtn.get(indis1).setClickable(true);
                        imgbtn.get(indis2).setClickable(true);
                    }
                }, 1000);
            }
            if (dogru >= sat * sut) {
                dogru = 0;
                seviyeadim++;
                ctd.cancel();//CountDownTimer iptal et
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        metotlar();//1 sn sonra metotlar() methodunu tekrardan çalıştır.
                    }
                }, 1000);
                seviyeadim = 0;
                this.benzersizresimler.clear();//benzersiz resimler ArrayListini temizlemekteyiz
                this.imgbtn.clear();//imgbtn ArrayList'inin temizlemektedir.
            } else if (tvSayac.getText().equals("0")) {
                DialogBuilder();
            }
            acilanresim = 0;
        }
    }

    private void sayac() {
        if (seviye == 1) {
            saniye = 6000;
        } else if (seviye == 2) {
            saniye = 8000;
        } else {
            saniye = 10000;
        }
        ctd = new CountDownTimer(saniye, 1000) {

            public void onTick(long millisUntilFinished) {
                tvSayac.setText("" + millisUntilFinished / 1000);
            }
            public void onFinish() {
                DialogBuilder();
            }
        }.start();
    }

    private void DialogBuilder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sonucunuz");
        builder.setMessage("toplam puanınız: " + dogru1 * seviyedegeri);
        builder.setCancelable(false);
        builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ctd.cancel();
                dialog.dismiss();
                Intent i = new Intent(getApplicationContext(), SelectPage.class);
                startActivity(i);
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
