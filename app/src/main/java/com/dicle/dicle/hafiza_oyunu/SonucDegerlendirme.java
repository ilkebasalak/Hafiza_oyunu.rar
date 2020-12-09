package com.dicle.dicle.hafiza_oyunu;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SonucDegerlendirme extends AppCompatActivity {

    int puan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonucdegerlendirme);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "font/fonty.ttf");
          if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                puan = 0;
            } else {
                puan = extras.getInt("dogrusayisi");
            }
        }
    }

    public void tekrarOyna(View view) {
        startActivity(new Intent(this, MatematikselIslem.class));
    }

    public void paylas(View view) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Oyun Puanım ve Başarım");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, puan);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));

    }
}
