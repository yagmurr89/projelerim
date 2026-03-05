package panel;

import islemler.KursSertifikaIslemleri;
import kurs.Kurs;
import kisi.Katilimci;

import javax.swing.*;
import java.awt.*;

public class DevamTakibiPanel extends JFrame {

    private JComboBox<Kurs> cmbKurslar;
    private JComboBox<Katilimci> cmbKatilimcilar;
    private JTextField txtGun;

    public DevamTakibiPanel(KursSertifikaIslemleri islemler) {

        setTitle("Devam Takibi");
        setSize(450, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblKurs = new JLabel("Kurs Seç:");
        JLabel lblKatilimci = new JLabel("Katılımcı Seç:");
        JLabel lblGun = new JLabel("Katıldığı Gün:");

        cmbKurslar = new JComboBox<>();
        cmbKatilimcilar = new JComboBox<>();
        txtGun = new JTextField();

        JButton btnKaydet = new JButton("Kaydet");
        JButton btnIptal = new JButton("İptal");

        // 🔹 Kursları doldur
        for (Kurs k : islemler.getKurslar()) {
            cmbKurslar.addItem(k);
        }

        // 🔹 Kurs seçilince katılımcıları doldur
        cmbKurslar.addActionListener(e -> {
            cmbKatilimcilar.removeAllItems();

            Kurs secilenKurs = (Kurs) cmbKurslar.getSelectedItem();
            if (secilenKurs != null) {
                for (Katilimci k : secilenKurs.getKatilimcilar()) {
                    cmbKatilimcilar.addItem(k);
                }
            }
        });

        // 🔹 Kaydet butonu
        btnKaydet.addActionListener(e -> {
            try {
                int kursIndex = cmbKurslar.getSelectedIndex();
                int katilimciIndex = cmbKatilimcilar.getSelectedIndex();
                int gun = Integer.parseInt(txtGun.getText());

                if (kursIndex < 0 || katilimciIndex < 0) {
                    JOptionPane.showMessageDialog(this, "Seçim yapınız!");
                    return;
                }

                boolean sonuc = islemler.devamBilgisiGir(
                        kursIndex,
                        katilimciIndex,
                        gun
                );

                if (sonuc) {
                    JOptionPane.showMessageDialog(this, "Devam bilgisi girildi ✔");
                    txtGun.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "İşlem başarısız!");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Gün sayısı sayısal olmalıdır!");
            }
        });

        btnIptal.addActionListener(e -> dispose());

        panel.add(lblKurs);
        panel.add(cmbKurslar);
        panel.add(lblKatilimci);
        panel.add(cmbKatilimcilar);
        panel.add(lblGun);
        panel.add(txtGun);
        panel.add(btnKaydet);
        panel.add(btnIptal);

        add(panel);
    }
}

