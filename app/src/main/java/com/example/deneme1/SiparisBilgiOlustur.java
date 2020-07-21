package com.example.deneme1;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class SiparisBilgiOlustur extends AppCompatActivity {

    Button siparisOlustur;
    ImageView siparisFoto;
    EditText siparisAdi, siparisYerAdi, siparisMinTutar, siparisUrunu;

    private Object Context;
    private DatabaseReference mRef;
    FirebaseFirestore db;
    String tarih;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysiparisbilgiolustur);

        db = FirebaseFirestore.getInstance();

        Date date = new Date();

        tarih = date.toString();


        siparisAdi = findViewById(R.id.s_adi);
        siparisMinTutar = findViewById(R.id.siparis_min_tutar);
        siparisUrunu = findViewById(R.id.s_ürün);
        siparisYerAdi = findViewById(R.id.s_yer);
        siparisOlustur = findViewById(R.id.siparis_olustur_button);
        siparisOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SiparisBilgiOlustur.this, SiparisSayfası.class);
                intent.putExtra(SiparisSayfası.LOCATION_NAME, siparisYerAdi.getText().toString());
                intent.putExtra(SiparisSayfası.MIN_PRICE, siparisMinTutar.getText().toString());
                intent.putExtra(SiparisSayfası.PUBLISH_DATE, tarih);
                startActivity(intent);

// Elle girdiğim datalar
                CollectionReference siparisler = db.collection("siparisler");
                Map<String, CharSequence> siparis1 = new HashMap<>();
                siparis1.put("siparisIsmi", "KFC");
                siparis1.put("cıkısTarihi", "10.04");
                siparis1.put("minFiyat", "50");
                siparis1.put("yurtAdi", "A yurdu");
                siparisler.document().set(siparis1);


                Map<String, CharSequence> siparis2 = new HashMap<>();
                siparis2.put("siparisIsmi", "Burger King");
                siparis2.put("cıkısTarihi", "11.10");
                siparis2.put("minFiyat", "60");
                siparis2.put("yurtAdi", "A yurdu");
                siparisler.document().set(siparis2);


                db.collection("siparisler")
                        .add(siparis1)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(SiparisBilgiOlustur.this, "eklendi", Toast.LENGTH_SHORT);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SiparisBilgiOlustur.this, "hata", Toast.LENGTH_SHORT);
                            }
                        });
            }
        });


    }


}
