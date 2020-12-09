package com.dicle.dicle.hafiza_oyunu;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);
    }

    public void GorselHafiza(View view) {
        Intent i = new Intent(SelectPage.this, GorselHafiza.class);
        startActivity(i);
        finish();
    }

    public void MatematikselHafiza(View view) {
        Intent m = new Intent(SelectPage.this, MatematikselHafizaSecimSayfasi.class);
        startActivity(m);
        finish();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());//arka planda çalışmayı bitiriyor
    }
}
