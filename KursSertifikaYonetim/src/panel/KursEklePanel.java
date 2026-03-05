package panel;

import islemler.KursSertifikaIslemleri;
import kurs.Kurs;

import javax.swing.*;
import java.awt.*;

public class KursEklePanel extends JFrame {

    private JTextField txtKursAdi;
    private JTextField txtSure;

    public KursEklePanel(KursSertifikaIslemleri islemler) {

        setTitle("Kurs Açma Paneli");
        setSize(400, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Ana panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Bileşenler
        JLabel lblKursAdi = new JLabel("Kurs Adı:");
        txtKursAdi = new JTextField();

        JLabel lblSure = new JLabel("Kurs Süresi (Saat):");
        txtSure = new JTextField();

        JButton btnKaydet = new JButton("Kurs Aç");
        JButton btnIptal = new JButton("İptal");

        // Ekleme
        panel.add(lblKursAdi);
        panel.add(txtKursAdi);

        panel.add(lblSure);
        panel.add(txtSure);

        panel.add(btnKaydet);
        panel.add(btnIptal);

        add(panel);

        // 🔥 BUTON OLAYLARI
        btnKaydet.addActionListener(e -> {
            try {
                String kursAdi = txtKursAdi.getText();
                int sure = Integer.parseInt(txtSure.getText());

                if (kursAdi.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Kurs adı boş olamaz!");
                    return;
                }

                Kurs kurs = new Kurs(kursAdi, sure);
                islemler.kursAc(kurs);

                JOptionPane.showMessageDialog(this, "Kurs başarıyla açıldı!");
                txtKursAdi.setText("");
                txtSure.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Süre sayısal olmalıdır!");
            }
        });

        btnIptal.addActionListener(e -> dispose());
    }
}
