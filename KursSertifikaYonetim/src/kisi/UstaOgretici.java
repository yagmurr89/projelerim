
package kisi;

public class UstaOgretici extends Kisi {

    private String brans;

    public UstaOgretici(String ad, String tc, String brans) {
        super(ad, tc);
        this.brans = brans;
    }

    @Override
    public void bilgileriYazdir() {
        super.bilgileriYazdir();
        System.out.println("Branş: " + brans);
    }
    @Override
public String durumBilgisi() {
    return "EĞİTMEN";
}
@Override
public String toString() {
    return ad + " (" + brans + ")";
}


}

