package com.example.deneme1;

import com.google.type.Date;

public class siparis {

    private int  yurtID, siparisID;
    private String text_yer_adi, siparisOlusturanAdi;
    private String cikisTarihi;
    private int min_fiyat;

    public siparis(String siparisOlusturanAdi, String text_yer_adi, int min_fiyat) {
        this.txt_yer_adi = txt_yer_adi;
        this.cikisTarihi = cikisTarihi;
        this.min_fiyat = min_fiyat;
    }
    String txt_yer_adi;

    public String getSiparisOlusturanAdi() {
        return siparisOlusturanAdi;
    }

    public void setSiparisOlusturanAdi(String siparisOlusturanAdi) {
        this.siparisOlusturanAdi = siparisOlusturanAdi;
    }

    public int getSiparisID() {
        return siparisID;
    }

    public void setSiparisID(int siparisID) {
        this.siparisID = siparisID;
    }

    public int getYurtID() {
        return yurtID;
    }

    public void setYurtID(int yurtID) {
        this.yurtID = yurtID;
    }

    public String getText_yer_adi() {
        return text_yer_adi;
    }

    public void setText_yer_adi(String text_yer_adi) {
        this.text_yer_adi = text_yer_adi;
    }

    public String getCikisTarihi() {
        return cikisTarihi;
    }

    public void setCikisTarihi(String cikisTarihi) {
        this.cikisTarihi = cikisTarihi;
    }

    public int getMin_fiyat() {
        return min_fiyat;
    }

    public void setMin_fiyat(int min_fiyat) {
        this.min_fiyat = min_fiyat;
    }

    public String getTxt_yer_adi() {
        return txt_yer_adi;
    }

    public void setTxt_yer_adi(String txt_yer_adi) {
        this.txt_yer_adi = txt_yer_adi;
    }



}
