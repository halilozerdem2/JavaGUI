package thePackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DatabasePage extends JFrame {
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JButton refreshButton;

    public DatabasePage() {
        setTitle("Database Viewer");
        setSize(800, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{
            "Username", "Password", "Age", "Gender", "Address", "Department"
        }, 0);
        userTable = new JTable(tableModel);

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadUsers());

        add(new JScrollPane(userTable), BorderLayout.CENTER);
        add(refreshButton, BorderLayout.SOUTH);

        loadUsers();
        setVisible(true);
    }

    private void loadUsers() {
        tableModel.setRowCount(0);
        List<DBHelper.User> users = DBHelper.getAllUsers();
        for (DBHelper.User user : users) {
            tableModel.addRow(new Object[]{
                user.username, user.password, user.age, user.gender, user.address, user.department
            });
        }
    }
}
