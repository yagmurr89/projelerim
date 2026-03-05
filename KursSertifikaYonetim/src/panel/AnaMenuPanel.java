package panel;

import islemler.KursSertifikaIslemleri;

import javax.swing.*;
import java.awt.*;

// 🔗 PANELLER
import panel.KursEklePanel;
import panel.KatilimciEklePanel;
import panel.UstaOgreticiEklePanel;
import panel.KursaUstaOgreticiAtaPanel;
import panel.DevamTakibiPanel;
import panel.SinavNotuGirPanel;
import panel.SertifikaRaporPanel;   // ✅ EKLENDİ

public class AnaMenuPanel extends JFrame {

    // 🔥 TEK SERVİS NESNESİ
    private KursSertifikaIslemleri islemler;

    public AnaMenuPanel() {

        islemler = new KursSertifikaIslemleri();

        setTitle("Kurs ve Sertifika Yönetim Sistemi");
        setSize(450, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel anaPanel = new JPanel(new BorderLayout(10, 10));
        anaPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel baslik = new JLabel("ANA MENÜ", SwingConstants.CENTER);
        baslik.setFont(new Font("Arial", Font.BOLD, 20));
        anaPanel.add(baslik, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton btnKurs = new JButton("Kurs Aç");
        JButton btnKatilimci = new JButton("Katılımcı Ekle");
        JButton btnOgreticiEkle = new JButton("Usta Öğretici Ekle");
        JButton btnOgreticiAta = new JButton("Kursa Usta Öğretici Ata");
        JButton btnDevam = new JButton("Devam Takibi");
        JButton btnSinav = new JButton("Sınav Notu Gir");
        JButton btnRapor = new JButton("Rapor / Sertifika");

        // 🔥 PANEL BAĞLANTILARI
        btnKurs.addActionListener(e ->
            new KursEklePanel(islemler).setVisible(true)
        );

        btnKatilimci.addActionListener(e ->
            new KatilimciEklePanel(islemler).setVisible(true)
        );

        btnOgreticiEkle.addActionListener(e ->
            new UstaOgreticiEklePanel(islemler).setVisible(true)
        );

        btnOgreticiAta.addActionListener(e ->
            new KursaUstaOgreticiAtaPanel(islemler).setVisible(true)
        );

        btnDevam.addActionListener(e ->
            new DevamTakibiPanel(islemler).setVisible(true)
        );

        // ✅ SINAV PANELİ (TEK ve DOĞRU)
        btnSinav.addActionListener(e ->
            new SinavNotuGirPanel(islemler).setVisible(true)
        );

        // ✅ SERTİFİKA / RAPOR PANELİ (SON ADIM)
        btnRapor.addActionListener(e ->
            new SertifikaRaporPanel(islemler).setVisible(true)
        );

        formPanel.add(btnKurs);
        formPanel.add(btnKatilimci);
        formPanel.add(btnOgreticiEkle);
        formPanel.add(btnOgreticiAta);
        formPanel.add(btnDevam);
        formPanel.add(btnSinav);
        formPanel.add(btnRapor);

        anaPanel.add(formPanel, BorderLayout.CENTER);
        add(anaPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new AnaMenuPanel().setVisible(true)
        );
    }
}
