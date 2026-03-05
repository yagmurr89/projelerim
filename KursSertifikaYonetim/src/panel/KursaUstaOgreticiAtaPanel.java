package panel;

import islemler.KursSertifikaIslemleri;
import kurs.Kurs;
import kisi.UstaOgretici;

import javax.swing.*;
import java.awt.*;

public class KursaUstaOgreticiAtaPanel extends JFrame {

    private JComboBox<Kurs> cmbKurslar;
    private JComboBox<UstaOgretici> cmbOgreticiler;

    public KursaUstaOgreticiAtaPanel(KursSertifikaIslemleri islemler) {

        setTitle("Kursa Usta Öğretici Ata");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblKurs = new JLabel("Kurs Seç:");
        JLabel lblOgretici = new JLabel("Usta Öğretici Seç:");

        // 🔽 COMBOBOXLAR
        cmbKurslar = new JComboBox<>();
        cmbOgreticiler = new JComboBox<>();

        // 🔥 KURSLARI DOLDUR
        for (Kurs k : islemler.getKurslar()) {
            cmbKurslar.addItem(k);
        }

        // 🔥 USTA ÖĞRETİCİLERİ DOLDUR
        for (UstaOgretici u : islemler.getUstaOgreticiler()) {
            cmbOgreticiler.addItem(u);
        }

        JButton btnAta = new JButton("Ata");
        JButton btnIptal = new JButton("İptal");

        panel.add(lblKurs);
        panel.add(cmbKurslar);

        panel.add(lblOgretici);
        panel.add(cmbOgreticiler);

        panel.add(btnAta);
        panel.add(btnIptal);

        add(panel);

        // 🔥 BUTON OLAYLARI
        btnAta.addActionListener(e -> {

            if (cmbKurslar.getSelectedItem() == null ||
                cmbOgreticiler.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(this,
                        "Kurs ve öğretici seçilmelidir!");
                return;
            }

            Kurs secilenKurs = (Kurs) cmbKurslar.getSelectedItem();
            UstaOgretici secilenOgretici =
                    (UstaOgretici) cmbOgreticiler.getSelectedItem();

            secilenKurs.setEgitmen(secilenOgretici);

            JOptionPane.showMessageDialog(this,
                    "Usta öğretici kursa başarıyla atandı!");
        });

        btnIptal.addActionListener(e -> dispose());
    }
}
