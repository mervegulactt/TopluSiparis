package com.example.deneme1;

import android.content.res.Resources;
import android.util.Log;
import android.widget.Spinner;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.InputStreamReader;


class SehirlerList {
    SehirlerList sehirler = new SehirlerList();
    Spinner getSpinner1;
    FirebaseFirestore db;
public void sehir() {
    try {
        //Dosayı ekler.
        BufferedReader jsonReader = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.iller)));
        StringBuilder jsonBuilder = new StringBuilder();
        for (String line = null; (line = jsonReader.readLine()) != null; ) {
            jsonBuilder.append(line).append("\n"); // Null olmayan okunmuş verriyi ekliyor.
        }
  new JSONArray(jsonBuilder);
        Gson gson = new Gson(); //json’u parse etmek için Gson kütüphanesi
        sehirler = gson.fromJson(jsonBuilder.toString(), SehirlerList.class);
    }catch (Exception ex)
    {
        Log.e("jsonadd", "Dosya Bulunamadı");
    }
    db.collection("sehirler").document("sehir").set(JSONArray.class);

}
    private Resources getResources() {
        return null;
    }

}