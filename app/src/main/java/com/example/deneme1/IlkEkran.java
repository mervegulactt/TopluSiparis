package com.example.deneme1;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

public class IlkEkran extends Activity {

    private int gosterim_suresi = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilk_ekran);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(IlkEkran.this, UyeGiris.class);
                startActivity(i);

                finish();
            }
        }, gosterim_suresi);
    }
}