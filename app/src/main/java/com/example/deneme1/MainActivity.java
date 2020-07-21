 package com.example.deneme1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        //Giriş yap butonundan login sayfasına geçiş
        Button ilk_ekran= findViewById(R.id.giris_yap);
        ilk_ekran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this, UyeGiris.class);
                startActivity(i);
            }
        });

        //Kayıt ol butonundan kayıt olma formuna geçiş
        Button kayit_ol= findViewById(R.id.btn_kayit_ol);
        kayit_ol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this, KayıtOl.class);
                startActivity(i);
            }
        });

*/
    }

/*    //geri tuşuna basılma durumunu yakalıyoruz
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this,AlertDialog.THEME_HOLO_DARK);
            alert.setTitle("Çıkmak İstediğinizden Emin misiniz ?");
            alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    finish();
                }
            });
            alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
            alert.show();
        }
        return super.onKeyDown(keyCode, event);
    }

*/

}


