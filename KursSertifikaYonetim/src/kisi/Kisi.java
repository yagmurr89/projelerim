
/**
 * Kisi sınıfı sistemdeki tüm bireylerin temel özelliklerini tutar.
 * Abstract bir sınıftır ve doğrudan nesne üretilemez.
 *
 * @author SeninAdin
 */
package kisi;

public abstract class Kisi {

    protected String ad;
    protected String tc;

    /**
     * Kisi yapıcı metodu
     *
     * @param ad Kişinin adı
     * @param tc Kişinin TC kimlik numarası
     */
    public Kisi(String ad, String tc) {
        this.ad = ad;
        this.tc = tc;
    }

    /**
     * Kişinin sistemdeki durum bilgisini döndürür.
     * Alt sınıflar tarafından override edilmek zorundadır.
     *
     * @return durum bilgisi
     */
    public abstract String durumBilgisi();

    /**
     * Kişiye ait temel bilgileri ekrana yazdırır.
     */
    public void bilgileriYazdir() {
        System.out.println("Ad: " + ad);
        System.out.println("TC: " + tc);
    }

    /**
     * Kişinin adını döndürür.
     *
     * @return ad
     */
    public String getAd() {
        return ad;
    }
}


