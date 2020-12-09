package com.dicle.dicle.hafiza_oyunu;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GorselHafiza extends AppCompatActivity {
Button btnKolay,btnOrta,btnZor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gorsel_hafiza);
        btnKolay = (Button)findViewById(R.id.rbKolay);
        btnOrta =(Button)findViewById(R.id.rbOrta);
        btnZor =(Button)findViewById(R.id.rbZor);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "font/fonty.ttf");
        btnKolay.setTypeface(custom_font);
        btnOrta.setTypeface(custom_font);
        btnZor.setTypeface(custom_font);
    }

    public void btnKolay(View view) {
        Intent kolay=new Intent(GorselHafiza.this,Gorsel_Islem.class);
        kolay.putExtra("seviye",1);
        startActivity(kolay);

    }

    public void btnOrta(View view) {
        Intent orta=new Intent(GorselHafiza.this,Gorsel_Islem.class);
        orta.putExtra("seviye",2);
        startActivity(orta);

    }

    public void btnZor(View view) {
        Intent zor=new Intent(GorselHafiza.this,Gorsel_Islem.class);
        zor.putExtra("seviye",3);
        startActivity(zor);

    }
}
