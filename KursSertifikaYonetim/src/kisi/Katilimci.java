/**
 * Katilimci sınıfı kursa katılan bireyleri temsil eder.
 * Sertifika alabilme durumunu kontrol eder.
 */
package kisi;

public class Katilimci extends Kisi implements SertifikaAlabilir {

    private int devamGun;
    private double sinav1;
    private double sinav2;

    /**
     * Katilimci yapıcı metodu
     *
     * @param ad Katılımcı adı
     * @param tc Katılımcı TC kimlik numarası
     */
    public Katilimci(String ad, String tc) {
        super(ad, tc);
        this.devamGun = 0;
    }

    /**
     * Katılımcının devam gününü artırır.
     *
     * @param gun Katıldığı gün sayısı
     */
    public void devamEkle(int gun) {
        this.devamGun += gun;
    }

    /**
     * Sınav notlarını sisteme girer.
     *
     * @param s1 1. sınav notu
     * @param s2 2. sınav notu
     */
    public void sinavNotlariGir(double s1, double s2) {
        this.sinav1 = s1;
        this.sinav2 = s2;
    }

    /**
     * Sınav ortalamasını hesaplar.
     *
     * @return ortalama not
     */
    public double ortalamaHesapla() {
        return sinav1 * 0.3 + sinav2 * 0.7;
    }

    @Override
    public String durumBilgisi() {
        double ort = ortalamaHesapla();

        if (ort >= 85) return "UZMAN";
        else if (ort >= 65) return "YETKİN";
        else if (ort >= 50) return "GEÇERLİ";
        else return "BAŞARISIZ";
    }

    /**
     * Katılımcının sertifika alıp alamayacağını kontrol eder.
     *
     * @return sertifika alabilir mi
     */
    @Override
    public boolean sertifikaAlabilirMi() {
        return devamGun >= 20 && ortalamaHesapla() >= 50;
    }
    @Override
public String toString() {
    return getAd();
}
public double getOrtalama() {
    return ortalamaHesapla();
}



}
