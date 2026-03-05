package islemler;

import java.util.ArrayList;
import kisi.Katilimci;
import kisi.UstaOgretici;
import kurs.Kurs;

/**
 * Kurs, katılımcı ve sertifika işlemlerinin
 * yönetildiği ana servis sınıfıdır.
 *
 * Kurs açma, katılımcı ekleme, devam takibi
 * ve sertifika raporlaması işlemlerini içerir.
 */
public class KursSertifikaIslemleri {

    private ArrayList<Katilimci> katilimcilar;
    private ArrayList<UstaOgretici> ustaOgreticiler;
    private ArrayList<Kurs> kurslar;

    /**
     * KursSertifikaIslemleri sınıfının yapıcı metodudur.
     * Tüm ArrayList yapıları burada başlatılır.
     */
    public KursSertifikaIslemleri() {
        katilimcilar = new ArrayList<>();
        ustaOgreticiler = new ArrayList<>();
        kurslar = new ArrayList<>();
    }

    /**
     * Sisteme yeni bir kurs ekler.
     */
    public void kursAc(Kurs kurs) {
        kurslar.add(kurs);
        System.out.println("Kurs başarıyla açıldı.");
    }

    /**
     * Katılımcıyı genel katılımcı listesine ekler.
     */
    public void katilimciEkle(Katilimci k) {
        katilimcilar.add(k);
        System.out.println("Katılımcı eklendi.");
    }

    /**
     * Sisteme usta öğretici ekler.
     */
    public void ustaOgreticiEkle(UstaOgretici u) {
        ustaOgreticiler.add(u);
        System.out.println("Usta öğretici eklendi.");
    }

    /**
     * Sistemde kayıtlı tüm kursları listeler.
     */
    public void kurslariListele() {
        if (kurslar.isEmpty()) {
            System.out.println("Henüz kurs yok.");
            return;
        }

        System.out.println("---- Açılan Kurslar ----");
        for (Kurs k : kurslar) {
            System.out.println(k);
        }
    }

    /**
     * Seçilen kursa usta öğretici atar.
     */
    public boolean kursaEgitmenAta(int kursIndex, int ogreticiIndex) {

        if (kurslar.isEmpty() || ustaOgreticiler.isEmpty()) {
            return false;
        }

        if (kursIndex < 0 || kursIndex >= kurslar.size()
                || ogreticiIndex < 0 || ogreticiIndex >= ustaOgreticiler.size()) {
            return false;
        }

        Kurs secilenKurs = kurslar.get(kursIndex);
        UstaOgretici secilenOgretici = ustaOgreticiler.get(ogreticiIndex);

        secilenKurs.setEgitmen(secilenOgretici);
        return true;
    }

    /**
     * Katılımcıyı belirtilen kursa ekler.
     */
    public boolean katilimciyiKursaEkle(int kursIndex, Katilimci k) {

        if (kursIndex < 0 || kursIndex >= kurslar.size()) {
            return false;
        }

        Kurs secilenKurs = kurslar.get(kursIndex);
        secilenKurs.katilimciEkle(k);

        return true;
    }

    /**
     * Katılımcının devam bilgisini günceller.
     */
    public boolean devamBilgisiGir(int kursIndex, int katilimciIndex, int gun) {

        if (kursIndex < 0 || kursIndex >= kurslar.size()) {
            return false;
        }

        Kurs kurs = kurslar.get(kursIndex);

        if (katilimciIndex < 0
                || katilimciIndex >= kurs.getKatilimcilar().size()) {
            return false;
        }

        Katilimci k = kurs.getKatilimcilar().get(katilimciIndex);
        k.devamEkle(gun);

        return true;
    }

    /**
     * Sınav notu girme işlemi.
     */
    public boolean sinavNotuGir(
            int kursIndex,
            int katilimciIndex,
            double sinav1,
            double sinav2
    ) {
        if (kursIndex < 0 || kursIndex >= kurslar.size()) {
            return false;
        }

        Kurs kurs = kurslar.get(kursIndex);

        if (katilimciIndex < 0
                || katilimciIndex >= kurs.getKatilimcilar().size()) {
            return false;
        }

        Katilimci k = kurs.getKatilimcilar().get(katilimciIndex);
        k.sinavNotlariGir(sinav1, sinav2);

        return true;
    }

    /**
     * Kurs – Katılımcı – Sertifika raporu.
     * İç içe döngü ve polymorphism içerir.
     */
    public void kursKatilimciRaporu() {

        for (Kurs kurs : kurslar) {
            System.out.println("\nKurs: " + kurs);

            for (Katilimci k : kurs.getKatilimcilar()) {
                System.out.println(
                        " - " + k.getAd()
                        + " | Durum: " + k.durumBilgisi()
                        + " | Sertifika: "
                        + (k.sertifikaAlabilirMi() ? "ALABİLİR" : "ALAMAZ")
                );
            }
        }
    }

    /**
     * Sistemdeki tüm kursları döndürür.
     * Encapsulation için getter.
     */
    public ArrayList<Kurs> getKurslar() {
        return kurslar;
    }

    /**
     * Sistemdeki tüm usta öğreticileri döndürür.
     * Encapsulation için getter.
     */
    public ArrayList<UstaOgretici> getUstaOgreticiler() {
        return ustaOgreticiler;
    }
}
