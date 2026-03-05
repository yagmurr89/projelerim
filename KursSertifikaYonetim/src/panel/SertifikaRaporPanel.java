package panel;

import islemler.KursSertifikaIslemleri;
import kurs.Kurs;
import kisi.Katilimci;

import javax.swing.*;
import java.awt.*;

public class SertifikaRaporPanel extends JFrame {

    private JComboBox<Kurs> cmbKurslar;
    private JTextArea txtRapor;

    public SertifikaRaporPanel(KursSertifikaIslemleri islemler) {

        setTitle("Sertifika / Rapor Paneli");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel ustPanel = new JPanel(new BorderLayout(10, 10));
        ustPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ÜST
        JPanel secimPanel = new JPanel(new BorderLayout(5, 5));
        cmbKurslar = new JComboBox<>();

        for (Kurs k : islemler.getKurslar()) {
            cmbKurslar.addItem(k);
        }

        secimPanel.add(new JLabel("Kurs Seç:"), BorderLayout.WEST);
        secimPanel.add(cmbKurslar, BorderLayout.CENTER);

        // ORTA (Rapor Alanı)
        txtRapor = new JTextArea();
        txtRapor.setEditable(false);
        txtRapor.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(txtRapor);

        // ALT
        JButton btnKapat = new JButton("Kapat");

        ustPanel.add(secimPanel, BorderLayout.NORTH);
        ustPanel.add(scroll, BorderLayout.CENTER);
        ustPanel.add(btnKapat, BorderLayout.SOUTH);

        add(ustPanel);

        // 🔥 KURS SEÇİLİNCE RAPOR OLUŞTUR
        cmbKurslar.addActionListener(e -> {
            txtRapor.setText("");
            Kurs secilenKurs = (Kurs) cmbKurslar.getSelectedItem();

            if (secilenKurs == null) return;

            txtRapor.append("KURS: " + secilenKurs + "\n");
            txtRapor.append("----------------------------------\n");

            if (secilenKurs.getKatilimcilar().isEmpty()) {
                txtRapor.append("Bu kursta katılımcı yok.\n");
                return;
            }

            for (Katilimci k : secilenKurs.getKatilimcilar()) {
                txtRapor.append(
                        "Ad: " + k.getAd() +
                        " | Ortalama: " + k.getOrtalama() +
                        " | Sertifika: " +
                        (k.sertifikaAlabilirMi() ? "ALABİLİR ✅" : "ALAMAZ ❌")
                        + "\n"
                );
            }
        });

        btnKapat.addActionListener(e -> dispose());
    }
}
