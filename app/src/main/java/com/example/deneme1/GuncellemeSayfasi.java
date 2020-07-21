package com.example.deneme1;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;


public class GuncellemeSayfasi extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText guncellemeSifre, guncellemeKullanıcıMail, guncellemeTelNo, guncellemeBlok, guncellemeKat, guncellemeOda;
    private Button guncelle;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    public final static String MAIL = "USER_MAIL";
    public final static String SIFRE = "USER_PASSWORD";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guncelleme_sayfasi);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        guncellemeSifre = findViewById(R.id.guncellemeSifre);
        guncellemeTelNo = findViewById(R.id.guncellemeTelNo);
        guncellemeBlok = findViewById(R.id.guncellemeKat);
        guncellemeKat = findViewById(R.id.guncellemeKat);
        guncellemeOda = findViewById(R.id.guncellemeOda);
        guncellemeKullanıcıMail = findViewById(R.id.guncellemeMail);
        guncelle = findViewById(R.id.btn_guncelleme);


        // Kayıt formundaki Şehir listesi
        Spinner sehir = findViewById(R.id.guncellemeSehir);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Şehir, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sehir.setAdapter(adapter);
        sehir.setOnItemSelectedListener(this);


        //Kayıt formundaki yurt isimleri
        Spinner yurt = findViewById(R.id.guncellemeYurt);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.yurt, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yurt.setAdapter(adapter2);
        sehir.setOnItemSelectedListener(this);

        Button btn_guncelle = findViewById(R.id.btn_guncelleme);
        btn_guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GuncellemeSayfasi.this, KisiProfil.class);
                startActivity(i);
                UpdateUser();
            }
        });

    }

    public void UpdateUser() {

        DocumentReference contact = db.collection("users").document("user");
        contact.update(MAIL, "guncellemeKullanıcıMail");
        contact.update(SIFRE, "guncellemeSifre")
                .addOnSuccessListener(new OnSuccessListener < Void > () {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(GuncellemeSayfasi.this, "Başarıyla güncellendi",
                                Toast.LENGTH_SHORT).show();
                    }
                });

//        String guncelmail = guncellemeKullanıcıMail.getText().toString().trim();
//        String guncelsifre = guncellemeSifre.getText().toString();
//        String telefonNumarası = guncellemeTelNo.getText().toString();
//        String katNo = guncellemeKat.getText().toString();
//        String Blok = guncellemeBlok.getText().toString();
//        String Oda = guncellemeOda.getText().toString();
//
//
//        // Hatalı mail girişleri için.
//        if (!Patterns.EMAIL_ADDRESS.matcher(guncelmail).matches()) {
//            guncellemeKullanıcıMail.setError("Uygun bir mail adresi giriniz..");
//            guncellemeKullanıcıMail.requestFocus();
//            return;
//
//        }
//
//        if (guncelsifre.length() < 6) {
//            guncellemeSifre.setError("Şifre uzunluğu min 6 olmalı");
//            guncellemeSifre.requestFocus();
//            return;
//        }

//    public void updateDescription(View v){
//
//        }
//        FirebaseUser kullanıcı = FirebaseAuth.getInstance().getCurrentUser();
//
//        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                .setDisplayName("Jane Q. User")
//
//                .build();
//
//        kullanıcı.updateProfile(profileUpdates)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Log.d("UserDataUpdate", "User profile updated.");
//                        }
//                    }
//                });

    }

    private void addOnSuccessListener(OnSuccessListener<Void> updated_successfully) {
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}