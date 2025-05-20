package thePackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame {
    private JTextField userText;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPage() {
        setTitle("Login Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(144, 238, 144)); // Açık yeşil

        JLabel userLabel = new JLabel("Username: ");
        userLabel.setBounds(50, 50, 100, 25);
        add(userLabel);

        userText = new JTextField();
        userText.setBounds(160, 50, 165, 25);
        add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 90, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 90, 165, 25);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(160, 130, 100, 25);
        add(loginButton);

        JButton goToRegister = new JButton("Go to Register Page");
        goToRegister.setBounds(50, 180, 120, 25);
        add(goToRegister);

        JButton backToMain = new JButton("Go to Main Page");
        backToMain.setBounds(200, 180, 120, 25);
        add(backToMain);
        
        
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String kullanıcıAdı = userText.getText().trim();
                String şifre = new String(passwordField.getPassword()).trim();

                // Kullanıcının veritabanındaki şifresi
                String kayıtlıŞifre = DBHelper.getPasswordForUser(kullanıcıAdı);

                if (kayıtlıŞifre == null) {
                    JOptionPane.showMessageDialog(LoginPage.this, "User couldn't found", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Girişte girilen şifreyi şifrele (Sezar algoritmasıyla)
                String şifreliGiriş = caesarEncrypt(şifre, 3);

                if (şifreliGiriş.equals(kayıtlıŞifre)) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Succesfull");
                    new DashboardPage();
                    // Giriş başarılıysa burada başka bir pencereyi açabilirsin
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Wrong password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        goToRegister.addActionListener(e -> {
            dispose();
            new RegisterPage();
        });

        backToMain.addActionListener(e -> {
            dispose();
            new MainPage();
        });


        setVisible(true);
    }

    // Caesar Şifreleme Fonksiyonu
    private String caesarEncrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                result.append((char) ('A' + (ch - 'A' + shift) % 26));
            } else if (Character.isLowerCase(ch)) {
                result.append((char) ('a' + (ch - 'a' + shift) % 26));
            } else if (Character.isDigit(ch)) {
                result.append((char) ('0' + (ch - '0' + shift) % 10));
            } else {
                result.append((char) (ch + shift));
            }
        }

        return result.toString();
    }
}