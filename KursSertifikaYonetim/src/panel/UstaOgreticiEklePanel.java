package panel;

import islemler.KursSertifikaIslemleri;
import kisi.UstaOgretici;

import javax.swing.*;
import java.awt.*;

public class UstaOgreticiEklePanel extends JFrame {

    private JTextField txtAd;
    private JTextField txtTc;
    private JTextField txtBrans;

    public UstaOgreticiEklePanel(KursSertifikaIslemleri islemler) {

        setTitle("Usta Öğretici Ekle");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblAd = new JLabel("Ad Soyad:");
        txtAd = new JTextField();

        JLabel lblTc = new JLabel("TC Kimlik No:");
        txtTc = new JTextField();

        JLabel lblBrans = new JLabel("Branş:");
        txtBrans = new JTextField();

        JButton btnKaydet = new JButton("Kaydet");
        JButton btnIptal = new JButton("İptal");

        panel.add(lblAd);
        panel.add(txtAd);

        panel.add(lblTc);
        panel.add(txtTc);

        panel.add(lblBrans);
        panel.add(txtBrans);

        panel.add(btnKaydet);
        panel.add(btnIptal);

        add(panel);

        // 🔥 BUTON OLAYLARI
        btnKaydet.addActionListener(e -> {
            try {
                String ad = txtAd.getText();
                String tc = txtTc.getText();
                String brans = txtBrans.getText();

                if (ad.isEmpty() || tc.isEmpty() || brans.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Tüm alanlar doldurulmalıdır!");
                    return;
                }

                UstaOgretici usta = new UstaOgretici(ad, tc, brans);
                islemler.ustaOgreticiEkle(usta);

                JOptionPane.showMessageDialog(this, "Usta öğretici eklendi!");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Hata oluştu!");
            }
        });

        btnIptal.addActionListener(e -> dispose());
    }
}
