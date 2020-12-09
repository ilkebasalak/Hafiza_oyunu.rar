package com.dicle.dicle.hafiza_oyunu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
public class MatematikselHafizaSecimSayfasi extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matematiksel_hafiza);
    }


    public void btnKolay(View view) {
        Intent kolay = new Intent(MatematikselHafizaSecimSayfasi.this, MatematikselIslem.class);
        kolay.putExtra("seviye", 1);//bu diğer sayfaya veri göndermek için kullanılır.
        startActivity(kolay);
        finish();

    }

    public void btnOrta(View view) {
        Intent kolay = new Intent(MatematikselHafizaSecimSayfasi.this, MatematikselIslem.class);
        kolay.putExtra("seviye", 2);
        startActivity(kolay);
        finish();

    }

    public void btnZor(View view) {
        Intent kolay = new Intent(MatematikselHafizaSecimSayfasi.this, MatematikselIslem.class);
        kolay.putExtra("seviye", 3);
        startActivity(kolay);
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), SelectPage.class);
        startActivity(i);
        finish();

    }
}
