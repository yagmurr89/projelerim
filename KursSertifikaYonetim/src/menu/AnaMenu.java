package menu;

import javax.swing.JOptionPane;
import islemler.KursSertifikaIslemleri;
import kurs.Kurs;
import kisi.Katilimci;

public class AnaMenu {

    public static void menuGoster() {

        KursSertifikaIslemleri islemler = new KursSertifikaIslemleri();
        int secim = -1;

        do {
            String secimStr = JOptionPane.showInputDialog(
                "=== KURS VE SERTİFİKA YÖNETİM SİSTEMİ ===\n"
              + "1- Kurs Aç\n"
              + "2- Kursları Listele\n"
              + "3- Kursa Usta Öğretici Ata\n"
              + "4- Katılımcı Ekle\n"
              + "5- Devam Bilgisi Gir\n"
              + "6- Sınav Notu Gir\n"
              + "7- Kurs–Katılımcı–Sertifika Raporu\n"
              + "0- Çıkış\n"
              + "Seçiminiz:"
            );

            if (secimStr == null) break;

            try {
                secim = Integer.parseInt(secimStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Lütfen sayısal bir değer giriniz!");
                continue;
            }

            switch (secim) {

                case 1 -> {
                    try {
                        String kursAdi = JOptionPane.showInputDialog("Kurs adını giriniz:");
                        int sure = Integer.parseInt(
                            JOptionPane.showInputDialog("Kurs süresini (saat):")
                        );

                        Kurs yeniKurs = new Kurs(kursAdi, sure);
                        islemler.kursAc(yeniKurs);

                        JOptionPane.showMessageDialog(null, "Kurs başarıyla açıldı!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Geçersiz giriş!");
                    }
                }

                case 2 -> {
                    islemler.kurslariListele();
                    JOptionPane.showMessageDialog(null, "Kurslar konsola yazdırıldı.");
                }

                case 3 -> {
                    try {
                        int kursIndex = Integer.parseInt(
                            JOptionPane.showInputDialog("Kurs numarası (0'dan başlar):")
                        );
                        int ogreticiIndex = Integer.parseInt(
                            JOptionPane.showInputDialog("Usta öğretici numarası (0'dan başlar):")
                        );

                        boolean basarili = islemler.kursaEgitmenAta(kursIndex, ogreticiIndex);

                        JOptionPane.showMessageDialog(
                            null,
                            basarili ? "Eğitmen kursa atandı!" : "Hatalı index!"
                        );
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Geçersiz giriş!");
                    }
                }

                case 4 -> {
                    try {
                        String ad = JOptionPane.showInputDialog("Katılımcı adı:");
                        String tc = JOptionPane.showInputDialog("TC Kimlik No:");
                        int kursIndex = Integer.parseInt(
                            JOptionPane.showInputDialog("Katılacağı kurs numarası (0'dan başlar):")
                        );

                        Katilimci k = new Katilimci(ad, tc);

                        islemler.katilimciEkle(k);
                        boolean eklendi = islemler.katilimciyiKursaEkle(kursIndex, k);

                        JOptionPane.showMessageDialog(
                            null,
                            eklendi ? "Katılımcı kursa eklendi!" : "Hatalı kurs numarası!"
                        );
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Geçersiz giriş!");
                    }
                }

                case 5 -> {
                    try {
                        int kursIndex = Integer.parseInt(
                            JOptionPane.showInputDialog("Kurs numarası:")
                        );
                        int katilimciIndex = Integer.parseInt(
                            JOptionPane.showInputDialog("Katılımcı numarası:")
                        );
                        int gun = Integer.parseInt(
                            JOptionPane.showInputDialog("Katıldığı gün sayısı:")
                        );

                        boolean ok = islemler.devamBilgisiGir(
                            kursIndex, katilimciIndex, gun
                        );

                        JOptionPane.showMessageDialog(
                            null,
                            ok ? "Devam bilgisi girildi!" : "Hatalı bilgi!"
                        );
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Geçersiz giriş!");
                    }
                }

                // 🔥 SINAV NOTU GİRME
                case 6 -> {
                    try {
                        int kursIndex = Integer.parseInt(
                            JOptionPane.showInputDialog("Kurs numarası:")
                        );
                        int katilimciIndex = Integer.parseInt(
                            JOptionPane.showInputDialog("Katılımcı numarası:")
                        );

                        double s1 = Double.parseDouble(
                            JOptionPane.showInputDialog("1. Sınav Notu:")
                        );
                        double s2 = Double.parseDouble(
                            JOptionPane.showInputDialog("2. Sınav Notu:")
                        );

                        boolean ok = islemler.sinavNotuGir(
                            kursIndex, katilimciIndex, s1, s2
                        );

                        JOptionPane.showMessageDialog(
                            null,
                            ok ? "Sınav notları girildi!" : "Hatalı bilgi!"
                        );
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Geçersiz giriş!");
                    }
                }

                // 🔥 RAPOR + SERTİFİKA
                case 7 -> {
                    islemler.kursKatilimciRaporu();
                    JOptionPane.showMessageDialog(
                        null,
                        "Kurs, katılımcı ve sertifika bilgileri konsola yazdırıldı."
                    );
                }

                case 0 -> JOptionPane.showMessageDialog(null, "Çıkış yapılıyor...");

                default -> JOptionPane.showMessageDialog(null, "Hatalı seçim!");
            }

        } while (secim != 0);
    }
}
