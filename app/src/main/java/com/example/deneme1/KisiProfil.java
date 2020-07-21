package com.example.deneme1;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.net.MailTo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class KisiProfil extends AppCompatActivity {

    public FirebaseAuth auth = FirebaseAuth.getInstance();
    private Button guncelle, cikisButonu;

    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisi_profil);

        TextView goruntule_mail=findViewById(R.id.ProfilMail);
        goruntule_mail.setText(auth.getCurrentUser().getEmail());


        Button guncelle = findViewById(R.id.guncelle);
        guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KisiProfil.this , GuncellemeSayfasi.class);
                startActivity(i);
            }
        });


        Button cikisButonu = findViewById(R.id.btn_cikis);
        cikisButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KisiProfil.this , UyeGiris.class);
                startActivity(i);

            }
        });

    }
}
