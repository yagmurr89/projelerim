package panel;

import islemler.KursSertifikaIslemleri;
import kurs.Kurs;
import kisi.Katilimci;

import javax.swing.*;
import java.awt.*;

public class KatilimciEklePanel extends JFrame {

    private JTextField txtAd;
    private JTextField txtTc;
    private JComboBox<String> cmbKurslar;

    public KatilimciEklePanel(KursSertifikaIslemleri islemler) {

        setTitle("Katılımcı Ekle");
        setSize(420, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Alanlar
        panel.add(new JLabel("Ad Soyad:"));
        txtAd = new JTextField();
        panel.add(txtAd);

        panel.add(new JLabel("TC Kimlik No:"));
        txtTc = new JTextField();
        panel.add(txtTc);

        panel.add(new JLabel("Kurs Seç:"));
        cmbKurslar = new JComboBox<>();
        panel.add(cmbKurslar);

        JButton btnKaydet = new JButton("Kaydet");
        JButton btnIptal = new JButton("İptal");

        panel.add(btnKaydet);
        panel.add(btnIptal);

        add(panel);

        // 🔥 Kursları combobox'a doldur
        int index = 0;
        for (Kurs k : islemler.getKurslar()) {
            cmbKurslar.addItem(index + " - " + k.toString());
            index++;
        }

        // 🔥 Kaydet
        btnKaydet.addActionListener(e -> {
            try {
                String ad = txtAd.getText();
                String tc = txtTc.getText();
                int kursIndex = cmbKurslar.getSelectedIndex();

                if (ad.isEmpty() || tc.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Alanlar boş olamaz!");
                    return;
                }

                Katilimci k = new Katilimci(ad, tc);
                islemler.katilimciEkle(k);
                islemler.katilimciyiKursaEkle(kursIndex, k);

                JOptionPane.showMessageDialog(this, "Katılımcı eklendi!");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Hata oluştu!");
            }
        });

        btnIptal.addActionListener(e -> dispose());
    }
}

