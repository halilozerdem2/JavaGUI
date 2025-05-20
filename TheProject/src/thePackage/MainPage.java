package thePackage;

import javax.swing.*;
import java.awt.Color;


public class MainPage extends JFrame {
    public MainPage() {
        setTitle("Main Page");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(173, 216, 230)); // Açık mavi

        JLabel welcomeLabel = new JLabel("WELCOME");
        welcomeLabel.setBounds(150, 30, 120, 30);
        add(welcomeLabel);

        JButton loginButton = new JButton("Log In");
        loginButton.setBounds(130, 80, 120, 30);
        add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(130, 130, 120, 30);
        add(registerButton);

        loginButton.addActionListener(e -> {
            dispose();
            new LoginPage();
        });

        registerButton.addActionListener(e -> {
            dispose();
            new RegisterPage();
        });

        setVisible(true);
    }
}