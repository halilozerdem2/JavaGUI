package thePackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterPage extends JFrame {
    private JTextField userText;
    private JPasswordField passwordField;
    private JButton registerButton, loginPageButton, homeButton;

    public RegisterPage() {
        setTitle("Register Page");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 248, 220));
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 100, 25);
        add(userLabel);

        userText = new JTextField();
        userText.setBounds(140, 30, 200, 25);
        add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 70, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 70, 200, 25);
        add(passwordField);

        registerButton = new JButton("Register");
        registerButton.setBounds(140, 110, 100, 25);
        add(registerButton);

        loginPageButton = new JButton("Login Page");
        loginPageButton.setBounds(250, 110, 100, 25);
        add(loginPageButton);

        homeButton = new JButton("Back to Home");
        homeButton.setBounds(140, 150, 150, 25);
        add(homeButton);

        registerButton.addActionListener(e -> {
            String username = userText.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!isValidPassword(password)) {
                JOptionPane.showMessageDialog(this,
                        "Password must be at least 8 characters long and include:\n" +
                        "- One uppercase letter\n" +
                        "- One lowercase letter\n" +
                        "- One digit\n" +
                        "- One special character",
                        "Invalid Password", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String encrypted = caesarEncrypt(password, 3);
            boolean inserted = DBHelper.insertUser(username, encrypted);

            if (inserted) {
                JOptionPane.showMessageDialog(this, "Registration successful!");
            } else {
                JOptionPane.showMessageDialog(this, "User already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginPageButton.addActionListener(e -> {
            dispose();
            new LoginPage();
        });

        homeButton.addActionListener(e -> {
            dispose();
            new MainPage();
        });

        setVisible(true);
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 8) return false;
        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else if (!Character.isLetterOrDigit(ch)) hasSpecial = true;
        }

        return hasUpper && hasLower && hasDigit && hasSpecial;
    }

    private String caesarEncrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                char c = (char) ('A' + (ch - 'A' + shift) % 26);
                result.append(c);
            } else if (Character.isLowerCase(ch)) {
                char c = (char) ('a' + (ch - 'a' + shift) % 26);
                result.append(c);
            } else if (Character.isDigit(ch)) {
                char c = (char) ('0' + (ch - '0' + shift) % 10);
                result.append(c);
            } else {
                result.append((char) (ch + shift));
            }
        }

        return result.toString();
    }
}


