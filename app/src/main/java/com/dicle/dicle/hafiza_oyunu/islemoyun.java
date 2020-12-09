package com.dicle.dicle.hafiza_oyunu;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class islemoyun extends AppCompatActivity {

    Button sayi1, sayi2, tvIslemturu, cevap;
    TextView tvdogrusayisi, tvSayac;
    Chronometer sayacChronometer;
    int sayi1Islem, sayi2Islem, dogrular = 0;
    Random rnd;
    int secilen;
    String[] islemler = {"+", "-", "*", "/"};
    ImageView dogru, yanlis;
    private int sdegeri;
    int i = 0;
    int sonuc, dogrusonuc;
    int seviyedegeri;
    CountDownTimer ctd;
    RelativeLayout rltv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_islemoyun);
        initialize();

        seviyedegeri = 0;

            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                seviyedegeri = 0;
            } else {
                seviyedegeri = extras.getInt("seviye");//1 2 3
            }

        sdegeri = seviyedegeri;
        degergetir();

    }

    private void initialize() {
        sayi1 = (Button) findViewById(R.id.btnSayiBir);
        sayi2 = (Button) findViewById(R.id.btnSayiIki);
        cevap = (Button) findViewById(R.id.tvCevap);
        tvIslemturu = (Button) findViewById(R.id.tvIslemType);
        tvdogrusayisi = (TextView) findViewById(R.id.tvDogruSayisi);
        dogru = (ImageView) findViewById(R.id.imgButtonDogru);
        yanlis = (ImageView) findViewById(R.id.imgButtonYanlis);
        tvSayac = (TextView) findViewById(R.id.tvSayac);
        rltv = (RelativeLayout) findViewById(R.id.rlislemoyun);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "font/fonty.ttf");
        sayi1.setTypeface(custom_font);
        sayi2.setTypeface(custom_font);

        cevap.setTypeface(custom_font);
        sayac();
    }

    public void randomrenk() {
        int i = 1;
        rnd = new Random();
        List<Integer> dizi = new ArrayList<>();

        dizi.add(R.color.renk1);
        dizi.add(R.color.renk2);
        dizi.add(R.color.renk3);
        dizi.add(R.color.renk4);
        dizi.add(R.color.renk5);
        dizi.add(R.color.renk6);
        dizi.add(R.color.renk7);
        dizi.add(R.color.renk8);


        Collections.shuffle(dizi);

        rltv.setBackgroundColor(rnd.nextInt(dizi.get(0)));

    }

    private void degergetir() {
        randomrenk();
        int[] deger = new int[2];
        rnd = new Random();
        secilen = 0;
        int a = 1;
        int b = 1;
        switch (sdegeri) {
            case 1:
                a = rnd.nextInt(9 * sdegeri + 1);
                b = rnd.nextInt(9 * sdegeri + 1);
                break;
            case 2:
                a = rnd.nextInt(9 * sdegeri + 6);
                b = rnd.nextInt(9 * sdegeri + 6);
                break;
            case 3:
                a = rnd.nextInt(9 * sdegeri + 11);
                b = rnd.nextInt(9 * sdegeri + 11);
                break;

        }
        sayi1.setText(a + "");
        sayi1Islem = Integer.parseInt(sayi1.getText().toString());


        sayi2.setText(b + "");
        sayi2Islem = Integer.parseInt(sayi2.getText().toString());

        secilen = rnd.nextInt(islemler.length);

        if (secilen == 0) {
            tvIslemturu.setText("+");
            sonuc = sayi1Islem + sayi2Islem;
            dogrusonuc = sayi1Islem + sayi2Islem;
            deger[0] = sonuc;
            deger[1] = sonuc + 2;
            cevap.setText("" + deger[rnd.nextInt(2)]);

        } else if (secilen == 1) {
            tvIslemturu.setText("-");
            sonuc = sayi1Islem - sayi2Islem;
            dogrusonuc = sayi1Islem - sayi2Islem;
            deger[0] = sonuc;
            deger[1] = sonuc + 2;
            cevap.setText("" + deger[rnd.nextInt(2)]);

        } else if (secilen == 2) {
            tvIslemturu.setText("*");
            sonuc = sayi1Islem * sayi2Islem;
            dogrusonuc = sayi1Islem * sayi2Islem;
            deger[0] = sonuc;
            deger[1] = sonuc + 2;
            cevap.setText("" + deger[rnd.nextInt(2)]);

        } else if (secilen == 3) {
            tvIslemturu.setText("/");
            sayi2Islem = carpanGetir(sayi1Islem);
            sayi2.setText(Integer.toString(sayi2Islem));

            if (sayi1Islem >= sayi2Islem) {
                sonuc = sayi1Islem / sayi2Islem;
                dogrusonuc = sayi1Islem / sayi2Islem;
                deger[0] = sonuc;
                deger[1] = sonuc + 2;
                cevap.setText("" + deger[rnd.nextInt(2)]);
            } else {
                degergetir();
            }
        }
    }

    private int carpanGetir(int sayi) {
        List<Integer> carpan = new ArrayList<>();
        for (int i = 1; i <= sayi; i++) {
            if (sayi % i == 0) {
                carpan.add(i);
            } else {
                carpan.add(i + 1);
            }
        }
        return carpan.get(rnd.nextInt(carpan.size()));
    }

    public void imgBtndogruCevap(View view) {
        if (dogrusonuc == Integer.parseInt(cevap.getText().toString())) {
            dogrular++;
            tvdogrusayisi.setText("" + dogrular);
            ctd.cancel();
            sayac();
            degergetir();
        } else {
            DialogBuilder();
        }

    }

    public void imgBtnYanlisCevap(View view) {
        if (dogrusonuc != Integer.parseInt(cevap.getText().toString())) {
            dogrular++;
            tvdogrusayisi.setText("" + dogrular);
            ctd.cancel();
            sayac();
            degergetir();
        } else {
            DialogBuilder();
        }
    }

    private void sayac() {

        ctd = new CountDownTimer(5000, 1000) {

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
        builder.setMessage("dogru cevabı :" + dogrusonuc + "\n" + "toplam puanınız: " + dogrular * seviyedegeri);
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
    public void onBackPressed() {
        super.onBackPressed();
        ctd.cancel();
        Intent i = new Intent(getApplicationContext(), matematikselHafiza.class);
        startActivity(i);
        this.finish();

    }
}
