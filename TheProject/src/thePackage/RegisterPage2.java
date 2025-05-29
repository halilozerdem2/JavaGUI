package thePackage;

import javax.swing.*;
import java.awt.*;

public class RegisterPage2 extends JFrame {
    private JTextField ageField, addressField;
    private JComboBox<String> departmentComboBox;
    private JRadioButton maleButton, femaleButton, otherButton;
    private JButton finishButton;
    private ButtonGroup genderGroup;

    public RegisterPage2(String username) {
        setTitle("Additional Information");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(30, 30, 100, 25);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(140, 30, 200, 25);
        add(ageField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(30, 70, 100, 25);
        add(genderLabel);

        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        otherButton = new JRadioButton("Other");

        genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(otherButton);

        maleButton.setBounds(140, 70, 60, 25);
        femaleButton.setBounds(200, 70, 80, 25);
        otherButton.setBounds(280, 70, 80, 25);

        add(maleButton);
        add(femaleButton);
        add(otherButton);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(30, 110, 100, 25);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(140, 110, 200, 25);
        add(addressField);

        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setBounds(30, 150, 100, 25);
        add(deptLabel);

        departmentComboBox = new JComboBox<>(new String[]{"IT", "HR", "Marketing", "Finance", "Sales"});
        departmentComboBox.setBounds(140, 150, 200, 25);
        add(departmentComboBox);

        finishButton = new JButton("Finish");
        finishButton.setBounds(140, 200, 120, 30);
        add(finishButton);

        finishButton.addActionListener(e -> {
            try {
                int age = Integer.parseInt(ageField.getText().trim());
                String gender = getSelectedGender();
                String address = addressField.getText().trim();
                String department = (String) departmentComboBox.getSelectedItem();

                if (gender == null || address.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                DBHelper.updateUserDetails(username, age, gender, address, department);
                JOptionPane.showMessageDialog(this, "Registration completed successfully!");
                dispose();
                new MainPage();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid age!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    private String getSelectedGender() {
        if (maleButton.isSelected()) return "Male";
        if (femaleButton.isSelected()) return "Female";
        if (otherButton.isSelected()) return "Other";
        return null;
    }
}
