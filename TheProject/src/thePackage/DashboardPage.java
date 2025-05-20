package thePackage;

import javax.swing.*;
import java.awt.*;

public class DashboardPage extends JFrame {
    public DashboardPage() {
        setTitle("Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Ortada açılması için

        // Panel ve Layout ayarları
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Mesaj Label
        JLabel messageLabel = new JLabel(
            "We are learning well thanks to the Mr. HAYDER ALI ABDULLAH MOHAMMEDQASIM",
            SwingConstants.CENTER
        );
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setForeground(Color.DARK_GRAY);
        panel.add(messageLabel, BorderLayout.NORTH);

        // GIF ekleme (kaynağı package içinden alır)
        try {
            ImageIcon gifIcon = new ImageIcon(getClass().getResource("learning.gif"));
            JLabel gifLabel = new JLabel(gifIcon);
            gifLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(gifLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("GIF yüklenemedi");
            errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(errorLabel, BorderLayout.CENTER);
        }

        add(panel);
        setVisible(true);
    }
}
