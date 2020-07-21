package com.example.deneme1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class KayıtOl extends AppCompatActivity  implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private EditText kayitMail, kayitSifre, telNo, blok, kat, oda;
    Spinner sehir, yurt;
    private Button kayitOl;
    private FirebaseAuth mAuth;
    private AlertDialog alert;
    private Object Context;
    private DatabaseReference mRef;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kayit_ol);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        kayitMail = findViewById(R.id.kayit_e_mail);
        kayitSifre = findViewById(R.id.kayit_kul_sifr);
        telNo = findViewById(R.id.tel_no);
        blok = findViewById(R.id.blok);
        kat = findViewById(R.id.kat);
        oda = findViewById(R.id.oda);
        sehir = findViewById(R.id.spinner1);
        yurt = findViewById(R.id.spinner2);


        
        // Kayıt formundaki Şehir listesi
        Spinner spinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Şehir, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        //Kayıt formundaki yurt isimleri
        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.yurt, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner1.setOnItemSelectedListener(this);

        kayitOl = findViewById(R.id.kayit_ol1);
        kayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

   }
    public void registerUser() {

        String mail = kayitMail.getText().toString().trim();
        String sifre = kayitSifre.getText().toString();
        String telefonNumarası = telNo.getText().toString();
        final String katNo = kat.getText().toString();
        String Blok = blok.getText().toString();
        String Oda = oda.getText().toString();


        // mail Alanı boş ise;
        if (mail.isEmpty()) {

            kayitMail.setError("E-mail gereklidir..");
            kayitMail.requestFocus();
            return;
        }

        // Hatalı mail girişleri için.
        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            kayitMail.setError("Uygun bir mail adresi giriniz..");
            kayitMail.requestFocus();
            return;

        }

        if (sifre.isEmpty()) {
            kayitSifre.setError("Şifre gerekli..");
            kayitSifre.requestFocus();
            return;

        }

        if (sifre.length() < 6) {
            kayitSifre.setError("Şifre uzunluğu min 6 olmalı");
            kayitSifre.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(mail, sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Kullanıcı başarılı bir şekilde kaydedildi.", Toast.LENGTH_SHORT).show();

                    Map<String, String> user = new HashMap<>(); // CharSequence çoğu tipi dönüştürebilir.
                    user.put("mail", kayitMail.getText().toString());
                    user.put("sifre", kayitSifre.getText().toString());
                    user.put("TelefonNumarası", telNo.getText().toString());


                    db.collection("users")
                            .document(mAuth.getUid())
                            .set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(KayıtOl.this, "eklendi", Toast.LENGTH_SHORT);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(KayıtOl.this, "hata", Toast.LENGTH_SHORT);
                                }
                            });

                } else {
                    Toast.makeText(getApplicationContext(), "Kullanıcı oluşturulamadı", Toast.LENGTH_LONG);
                }
            }

        });

        db.collection("sehir")
                .document("iller-array.json")
                .set(sehir)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(KayıtOl.this, "eklendi", Toast.LENGTH_SHORT);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(KayıtOl.this, "hata", Toast.LENGTH_SHORT);
                    }
                });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

    }
}


