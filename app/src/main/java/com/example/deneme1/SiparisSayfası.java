package com.example.deneme1;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

public class SiparisSayfası extends AppCompatActivity implements SearchView.OnQueryTextListener {

    public final static String LOCATION_NAME = "LOCATION_NAME";
    public final static String PUBLISH_DATE = "PUBLISH_DATE";
    public final static String MIN_PRICE = "MIN_PRICE";
    public final static String ID = "ORDER_ID";

    RecyclerView recyclerView;
    Button siparis_ara, siparis_olustur;
    SearchView searchh;
    ArrayList siparisListem;
//
//    Date simdi = new Date();
//    SimpleDateFormat dakika = new SimpleDateFormat(" HH:mm");

    public ArrayList<siparis> siparis = new ArrayList<>();// Verilerin alınacağı yer.
//    ArrayList<siparis> siparisSearch = new ArrayList<>();
    Context context = this;
    CustomAdapter customAdapter;
    FirebaseFirestore db;

    CollectionReference siparisler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siparis_sayfasi);



        Button siparisOlustur = findViewById(R.id.btn_siparisOlustur);
        siparisOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SiparisSayfası.this, SiparisBilgiOlustur.class);
                startActivity(i);

            }
        });

        searchh = findViewById(R.id.search);
        searchh.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                customAdapter.getFilter().filter(newText);

                return false;
            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL); // verileri dikey olarak hizala.
        linearLayoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true); // performansı arttırıyor.


//        Toast.makeText(this, dakika.format(simdi), Toast.LENGTH_LONG).show();
//
//        siparis.add(new siparis("KFC", simdi.toString(), 50));


        Collections.reverse(siparis); // Listeyi ters çeviriyor.
      //  customAdapter = new CustomAdapter(); //1.si verilerin alınacağı, 2. çalıştırılacağı yer.
        recyclerView.setAdapter(customAdapter);


//        // Elle girdiğim datalar
//        CollectionReference siparisler = db.collection("siparisler");
//        Map<String, CharSequence> siparis1 = new HashMap<>();
//        siparis1.put("siparisIsmi", "KFC");
//        siparis1.put("cıkısTarihi", "10.04");
//        siparis1.put("minFiyat", "50");
//        siparis1.put("yurtAdi", "A yurdu");
//        siparisler.document().set(siparis1);
//
//
//        Map<String, CharSequence> siparis2 = new HashMap<>();
//        siparis2.put("siparisIsmi", "Burger King");
//        siparis2.put("cıkısTarihi", "11.10");
//        siparis2.put("minFiyat", "60");
//        siparis2.put("yurtAdi", "A yurdu");
//        siparisler.document().set(siparis2);
//
//
//        db.collection("siparisler")
//                .add(siparis1)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(SiparisSayfası.this, "eklendi", Toast.LENGTH_SHORT);
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(SiparisSayfası.this, "hata", Toast.LENGTH_SHORT);
//                    }
//                });

        // Yurt adı A yurdu olan siparişleri firestoredan alır.

        db.collection("siparisler")
//                .whereEqualTo("yurtAdi", "A yurdu")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() { // tamamını eklemek için
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) { //  sorgu dökümanı tamamlandığında
                                Log.d("siparissayfasi", documentSnapshot.getId() + " => " + documentSnapshot.getData());
                            }
                        } else {
                            Log.d("siparissayfası", "Dökümanlar alınırken hata oluştu", task.getException());
                        }
                    }
                });





    }
    void siparisleriyaz (final ArrayList<String> siparisList)
    {
        final CollectionReference siparis = db.collection("siparisler");
        siparis.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if ( task.isSuccessful()) {
                    QuerySnapshot doc = task.getResult();
                    StringBuilder alanAdları = new StringBuilder("");
                    alanAdları.append("Mail:").append(doc.getQuery("yurtAdi"));
                    alanAdları.append("siparisIsmi").append(doc.getQuery("siparisIsmi"));
                    alanAdları.append("MinFiyat").append(doc.getQuery("minFiyat"));
                    alanAdları.append("cikisTarihi").append(doc.getQuery("cıkısTarihi"));

                    siparisList.add("alanAdları");
                    doc.toObjects(com.example.deneme1.siparis.class);

                    siparisListem = siparisList(alanAdları);



                }

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


    }

    private ArrayList siparisList(StringBuilder alanAdları) {
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
