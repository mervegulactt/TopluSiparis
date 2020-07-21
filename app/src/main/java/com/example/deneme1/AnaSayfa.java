package com.example.deneme1;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class AnaSayfa extends AppCompatActivity {

    FirebaseFirestore db;


     // Geri basılma durumunu yakaliyoruz

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            AlertDialog.Builder alert = new AlertDialog.Builder(AnaSayfa.this);
            alert.setTitle("Çıkmak İstediğinizden Eminmisiniz ?");
            alert.setPositiveButton("Evet", new DialogInterface.OnClickListener(){
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
    Button profil, siparis;
    ImageView imgid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);
       setupUI();

    }

    private void setupUI() {

        Button siparis = findViewById(R.id.siparis);
        siparis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(AnaSayfa.this, SiparisSayfası.class);
                startActivity(i);

            }
        });

        Button profil = findViewById(R.id.profil);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnaSayfa.this, KisiProfil.class);
                startActivity(i);
            }
        });

    }
}
