package kurs;

import kisi.UstaOgretici;
import java.util.ArrayList;
import kisi.Katilimci;

public class Kurs {

    private String kursAdi;
    private int sure;
    private UstaOgretici egitmen;
    private ArrayList<Katilimci> katilimcilar;

    public Kurs(String kursAdi, int sure) {
        this.kursAdi = kursAdi;
        this.sure = sure;
        this.katilimcilar = new ArrayList<>();
    }

    public void setEgitmen(UstaOgretici egitmen) {
        this.egitmen = egitmen;
    }

    public void katilimciEkle(Katilimci k) {
        katilimcilar.add(k);
    }

    public ArrayList<Katilimci> getKatilimcilar() {
        return katilimcilar;
    }

    @Override
    public String toString() {
        String egitmenBilgi = (egitmen != null)
                ? egitmen.getAd()
                : "Atanmadı";

        return kursAdi + " (" + sure + " saat)"
                + " | Eğitmen: " + egitmenBilgi
                + " | Katılımcı Sayısı: " + katilimcilar.size();
    }
    
    
}
