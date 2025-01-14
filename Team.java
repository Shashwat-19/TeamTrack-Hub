package TeamTrack;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Team extends JFrame implements ActionListener {
    private JTextField nameField, idField, projectNameField, projectIdField, projectDescField;
    private JButton addButton, displayButton, deleteButton, clearButton;
    private JTable table;
    private DefaultTableModel model;

    public Team() {
        applyDarkMode();

        setTitle("Team-Track Hub");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Employee Details"));

        inputPanel.add(new JLabel("Employee Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Employee ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Project Name:"));
        projectNameField = new JTextField();
        inputPanel.add(projectNameField);

        inputPanel.add(new JLabel("Project ID:"));
        projectIdField = new JTextField();
        inputPanel.add(projectIdField);

        inputPanel.add(new JLabel("Project Description:"));
        projectDescField = new JTextField();
        inputPanel.add(projectDescField);

        addButton = new JButton("Add Employee");
        addButton.addActionListener(this);
        inputPanel.add(addButton);

        clearButton = new JButton("Clear Fields");
        clearButton.addActionListener(_ -> clearFields());
        inputPanel.add(clearButton);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Employee List"));

        model = new DefaultTableModel(new String[]{"Name", "ID", "Project Name", "Project ID", "Description"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        displayButton = new JButton("Display Employees");
        displayButton.addActionListener(this);
        buttonPanel.add(displayButton);

        deleteButton = new JButton("Delete Selected");
        deleteButton.addActionListener(_ -> deleteSelectedRow());
        buttonPanel.add(deleteButton);

        tablePanel.add(buttonPanel, BorderLayout.SOUTH);

        add(inputPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addEmployee();
        } else if (e.getSource() == displayButton) {
            JOptionPane.showMessageDialog(this, "Displaying all employees in the table!");
        }
    }

    private void addEmployee() {
        String name = nameField.getText().trim();
        String id = idField.getText().trim();
        String projectName = projectNameField.getText().trim();
        String projectId = projectIdField.getText().trim();
        String projectDesc = projectDescField.getText().trim();

        if (name.isEmpty() || id.isEmpty() || projectName.isEmpty() || projectId.isEmpty() || projectDesc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        model.addRow(new Object[]{name, id, projectName, projectId, projectDesc});
        clearFields();
        JOptionPane.showMessageDialog(this, "Employee added successfully!");
    }

    private void deleteSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            model.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        idField.setText("");
        projectNameField.setText("");
        projectIdField.setText("");
        projectDescField.setText("");
    }

    private void applyDarkMode() {
        UIManager.put("control", new Color(50, 50, 50));  
        UIManager.put("info", new Color(60, 60, 60));
        UIManager.put("nimbusBase", new Color(18, 30, 49));
        UIManager.put("nimbusBlueGrey", new Color(50, 50, 50));
        UIManager.put("nimbusLightBackground", new Color(30, 30, 30));
        UIManager.put("text", Color.WHITE);
        UIManager.put("nimbusSelectionBackground", new Color(75, 110, 175));
        UIManager.put("nimbusSelectedText", Color.WHITE);
        UIManager.put("controlText", Color.WHITE);
        UIManager.put("menuText", Color.WHITE);

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Team app = new Team();
            app.setVisible(true);
        });
    }
}
