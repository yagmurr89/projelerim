package panel;

import islemler.KursSertifikaIslemleri;
import kurs.Kurs;
import kisi.Katilimci;

import javax.swing.*;
import java.awt.*;

public class SinavNotuGirPanel extends JFrame {

    private JComboBox<Kurs> cmbKurslar;
    private JComboBox<Katilimci> cmbKatilimcilar;
    private JTextField txtSinav1;
    private JTextField txtSinav2;

    public SinavNotuGirPanel(KursSertifikaIslemleri islemler) {

        setTitle("Sınav Notu Girme");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        cmbKurslar = new JComboBox<>();
        cmbKatilimcilar = new JComboBox<>();
        txtSinav1 = new JTextField();
        txtSinav2 = new JTextField();

        JButton btnKaydet = new JButton("Notları Kaydet");
        JButton btnIptal = new JButton("İptal");

        // Kursları doldur
        for (Kurs k : islemler.getKurslar()) {
            cmbKurslar.addItem(k);
        }

        // Kurs seçilince katılımcıları doldur
        cmbKurslar.addActionListener(e -> {
            cmbKatilimcilar.removeAllItems();
            Kurs secilenKurs = (Kurs) cmbKurslar.getSelectedItem();
            if (secilenKurs != null) {
                for (Katilimci k : secilenKurs.getKatilimcilar()) {
                    cmbKatilimcilar.addItem(k);
                }
            }
        });

        panel.add(new JLabel("Kurs Seç:"));
        panel.add(cmbKurslar);

        panel.add(new JLabel("Katılımcı Seç:"));
        panel.add(cmbKatilimcilar);

        panel.add(new JLabel("1. Sınav (%30):"));
        panel.add(txtSinav1);

        panel.add(new JLabel("2. Sınav (%70):"));
        panel.add(txtSinav2);

        panel.add(btnKaydet);
        panel.add(btnIptal);

        add(panel);

        btnKaydet.addActionListener(e -> {
            try {
                Kurs kurs = (Kurs) cmbKurslar.getSelectedItem();
                Katilimci katilimci =
                        (Katilimci) cmbKatilimcilar.getSelectedItem();

                double s1 = Double.parseDouble(txtSinav1.getText());
                double s2 = Double.parseDouble(txtSinav2.getText());

                int kursIndex = islemler.getKurslar().indexOf(kurs);
                int katilimciIndex =
                        kurs.getKatilimcilar().indexOf(katilimci);

                boolean ok = islemler.sinavNotuGir(
                        kursIndex, katilimciIndex, s1, s2
                );

                if (ok) {
                    JOptionPane.showMessageDialog(this,
                        "Notlar kaydedildi\nOrtalama: "
                        + katilimci.getOrtalama()
                    );
                } else {
                    JOptionPane.showMessageDialog(this, "Hata oluştu!");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Notlar sayısal olmalıdır!");
            }
        });

        btnIptal.addActionListener(e -> dispose());
    }
}

