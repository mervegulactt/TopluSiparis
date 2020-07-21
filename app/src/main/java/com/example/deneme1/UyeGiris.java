package com.example.deneme1;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class UyeGiris extends AppCompatActivity {


    private FirebaseAuth.AuthStateListener mauthStateListener;
    private FirebaseDatabase db;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private Button butonGiris;
    private EditText kul_mail, kul_sifre;
    private TextView kayitOl;
    private String Mail;
    private String Sifre;



    // Geri tuşuna basılma durumu
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent i = new Intent(UyeGiris.this,IlkEkran.class);
            startActivity(i);
        }
        return super.onKeyDown(keyCode, event); 
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_giris);

        kul_mail = findViewById(R.id.loginMail);
        kul_sifre = findViewById(R.id.loginSifre);
        butonGiris = findViewById(R.id.uyeGiris);
        kayitOl = findViewById(R.id.kayitOl);

        // txtRegister = (TextView) findViewById(R.id.txtRegister);

        db = FirebaseDatabase.getInstance(); //db oluşturuldu.
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser(); // Doğrulanmış kullanıcı


        if (firebaseUser != null) {

            Toast.makeText(UyeGiris.this, "Lütfen gerekli alanları doldurunuz!", Toast.LENGTH_SHORT);
        }
        butonGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Mail = kul_mail.getText().toString();
                Sifre = kul_sifre.getText().toString();
                if (Mail.isEmpty() || Sifre.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Lütfen gerekli alanları doldurunuz!", Toast.LENGTH_SHORT).show();

                } else {
//
                    Loginislemi(Mail, Sifre);
                    //  Girisİslemi();
                }
            }
        });

        kayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UyeGiris.this, KayıtOl.class);
                startActivity(i);
            }
        });



    }



    public void Loginislemi(final String Mail, String Sifre) {
        mAuth.signInWithEmailAndPassword(Mail, Sifre).addOnCompleteListener(UyeGiris.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UyeGiris.this, "Giriş Yapıldı...", Toast.LENGTH_SHORT);
                            Intent i = new Intent(UyeGiris.this, AnaSayfa.class);
                            startActivity(i);
                            finish();


                        } else {
                            Toast.makeText(getApplicationContext(), "E-posta veya Şifre hatası", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}

