import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class Main {

    public static void main(String[] args) {
        // Pencere oluşturma
        JFrame frame = new JFrame("Gez Gör Eğlen");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints koordinat = new GridBagConstraints();

        // Bileşenleri oluşturma
        JLabel labelBaslik = new JLabel("Giriş");
        labelBaslik.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel panelForm = new JPanel(new GridBagLayout());
        GridBagConstraints formKoordinat = new GridBagConstraints();
        formKoordinat.insets = new Insets(10, 10, 10, 10);

        JLabel labelAd = new JLabel("Ad:");
        JTextField textAd = new JTextField(15);
        textAd.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (textAd.getText().length() >= 25) // Karakter sınırı
                    e.consume();
            }
        });

        JLabel labelSoyad = new JLabel("Soyad:");
        JPasswordField textSoyad = new JPasswordField(15);
        textSoyad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (textSoyad.getText().length() >= 25) // Karakter sınırı
                    e.consume();
            }
        });

        JButton buttonEkle = new JButton("Ekle");
        buttonEkle.setEnabled(false); // Başlangıçta butonu devre dışı bırak

        // Text alanlarına DocumentListener ekleme
        textAd.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                toggleButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                toggleButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                toggleButtonState();
            }

            private void toggleButtonState() {
                buttonEkle.setEnabled(!textAd.getText().trim().isEmpty() && !new String(textSoyad.getPassword()).trim().isEmpty());
            }
        });

        textSoyad.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                toggleButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                toggleButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                toggleButtonState();
            }

            private void toggleButtonState() {
                buttonEkle.setEnabled(!textAd.getText().trim().isEmpty() && !new String(textSoyad.getPassword()).trim().isEmpty());
            }
        });

        // Başlık
        koordinat.gridx = 0;
        koordinat.gridy = 0;
        koordinat.gridwidth = 2;
        koordinat.anchor = GridBagConstraints.CENTER;
        frame.add(labelBaslik, koordinat);

        // Adı etiketi
        formKoordinat.gridx = 0;
        formKoordinat.gridy = 0;
        formKoordinat.anchor = GridBagConstraints.WEST;
        panelForm.add(labelAd, formKoordinat);

        // Adı metin kutusu
        formKoordinat.gridx = 1;
        formKoordinat.gridy = 0;
        formKoordinat.anchor = GridBagConstraints.WEST;
        panelForm.add(textAd, formKoordinat);

        // Soyad etiketi
        formKoordinat.gridx = 0;
        formKoordinat.gridy = 1;
        formKoordinat.anchor = GridBagConstraints.WEST;
        panelForm.add(labelSoyad, formKoordinat);

        // Soyad metin kutusu
        formKoordinat.gridx = 1;
        formKoordinat.gridy = 1;
        formKoordinat.anchor = GridBagConstraints.WEST;
        panelForm.add(textSoyad, formKoordinat);

        // Form paneline kenarlık ekleme
        JPanel panelCerceve = new JPanel(new GridBagLayout());
        panelCerceve.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), ""));

        // Form panelini ve butonu ekleme
        GridBagConstraints kenarlikKoordinat = new GridBagConstraints();
        kenarlikKoordinat.insets = new Insets(10, 10, 10, 10);
        kenarlikKoordinat.gridx = 0;
        kenarlikKoordinat.gridy = 0;
        kenarlikKoordinat.gridwidth = 2;
        kenarlikKoordinat.anchor = GridBagConstraints.CENTER;
        panelCerceve.add(panelForm, kenarlikKoordinat);

        kenarlikKoordinat.gridx = 1;
        kenarlikKoordinat.gridy = 1;
        kenarlikKoordinat.gridwidth = 1;
        kenarlikKoordinat.anchor = GridBagConstraints.EAST;
        panelCerceve.add(buttonEkle, kenarlikKoordinat);

        // Kenarlıklı paneli ekleme
        koordinat.gridx = 0;
        koordinat.gridy = 1;
        koordinat.gridwidth = 2;
        koordinat.anchor = GridBagConstraints.CENTER;
        frame.add(panelCerceve, koordinat);

        // Butona ActionListener ekleme
        buttonEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textAd.getText();
                String password = new String(textSoyad.getPassword());
                JOptionPane.showMessageDialog(frame, "Ad: " + username + "\nSoyad: " + password, "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Arka plan renkleri
        panelForm.setBackground(Color.gray);  // Ad soyad
        panelCerceve.setBackground(Color.gray); // Çerçeve ve içi
        frame.getContentPane().setBackground(Color.WHITE); // En arka

        // Bilgilendirme yazıları
        JLabel labelBilgilendirme = new JLabel("Adınızı ve soyadınızı eksiksiz giriniz.");
        labelBilgilendirme.setFont(new Font("Arial", Font.PLAIN, 12));
        labelBilgilendirme.setForeground(Color.RED);

        // Bilgilendirme yazısını ekleme

        koordinat.gridx = 0;
        koordinat.gridy = 2;
        koordinat.gridwidth = 2;
        koordinat.anchor = GridBagConstraints.SOUTHWEST;
        koordinat.insets = new Insets(10, 10, 10, 10);
        frame.add(labelBilgilendirme, koordinat);

        // Pencereyi görünür yapmak

        frame.setVisible(true);
    }
}
