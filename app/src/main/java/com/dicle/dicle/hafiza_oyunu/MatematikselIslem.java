package com.dicle.dicle.hafiza_oyunu;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MatematikselIslem extends AppCompatActivity {

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
        sdegeri = 2;
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
        DialogBuilderSecimSeviye();
        degergetir();


    }

    private void DialogBuilderSecimSeviye() {
        final Dialog builder = new Dialog(this);
        builder.setTitle("Seviye Seciniz");
        builder.setCancelable(false);
        builder.setContentView(R.layout.seviyesecim);
        Button btnKolay = (Button) builder.findViewById(R.id.btnSeviye1);
        Button btnOrta = (Button) builder.findViewById(R.id.btnSeviye2);
        Button btnZor = (Button) builder.findViewById(R.id.btnSeviye3);
        btnKolay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sdegeri = 1;
                sayac();
                builder.dismiss();
            }
        });
        btnOrta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sdegeri = 2;
                sayac();
                builder.dismiss();
            }
        });
        btnZor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sdegeri = 3;
                sayac();
                builder.dismiss();

            }
        });

        builder.show();
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
        int[] deger = {0, 1, 2};
        rnd = new Random();
        secilen = 0;
        int a = 1;
        int b = 1;
        switch (sdegeri) {
            case 0:
                a = rnd.nextInt(9 * sdegeri + 1);
                b = rnd.nextInt(9 * sdegeri + 1);
                break;
            case 1:
                a = rnd.nextInt(9 * sdegeri + 6);
                b = rnd.nextInt(9 * sdegeri + 6);
                break;
            case 2:
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

    int puan = dogrular * sdegeri;

    public void imgBtndogruCevap(View view) {
        if (dogrusonuc == Integer.parseInt(cevap.getText().toString())) {
            dogrular++;
            tvdogrusayisi.setText("" + dogrular);
            ctd.cancel();
            sayac();
            degergetir();
        } else {

            Handler a = new Handler();
            a.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MatematikselIslem.this, "doğru cevap" + dogru, Toast.LENGTH_SHORT).show();
                }
            }, 1000);
            ctd.cancel();
            Intent i = new Intent(this, SonucDegerlendirme.class);
            i.putExtra("dogrusayisi", dogrular);
            i.putExtra("puan", puan);
            startActivity(i);
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
            Handler a = new Handler();
            a.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MatematikselIslem.this, "doğru cevap" + dogru, Toast.LENGTH_SHORT).show();
                }
            }, 1000);
            ctd.cancel();
            Intent i = new Intent(this, SonucDegerlendirme.class);
            i.putExtra("dogrusayisi", dogrular);
            i.putExtra("puan", puan);
            startActivity(i);
        }
    }

    private void sayac() {

        ctd = new CountDownTimer(5000, 720) {

            public void onTick(long millisUntilFinished) {
                tvSayac.setText("" + millisUntilFinished / 720);
            }

            public void onFinish() {
                ctd.cancel();

                Intent i = new Intent(MatematikselIslem.this, SonucDegerlendirme.class);
                i.putExtra("dogrusayisi", dogrular);
                i.putExtra("puan", puan);
                startActivity(i);
            }
        }.start();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ctd.cancel();
        finish();

    }
}
