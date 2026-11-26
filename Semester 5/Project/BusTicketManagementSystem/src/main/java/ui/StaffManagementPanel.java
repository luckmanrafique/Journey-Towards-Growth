package ui;
import dao.UserDAO;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;
public class StaffManagementPanel extends JPanel {
    private final String dFont="Times New Roman";
    private final UserDAO userDAO=new UserDAO();

    private JTable staffTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    private final Color NAVY=new Color(20,39,70);
    private final Color BACKGROUND=new Color(245,247,250);
    private final Color WHITE=Color.WHITE;
    private final Color GRAY=Color.GRAY;
    private final Color BLACK=Color.BLACK;
    public StaffManagementPanel(){
        setLayout(new BorderLayout(15,15));
        setBackground(BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        createInterface();
        loadStaff();
    }
    private void createInterface(){
        JPanel topPanel=new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        JLabel heading = new JLabel("Staff Management");
        heading.setFont(new Font(dFont,Font.BOLD,27));
        heading.setForeground(NAVY);

        JLabel subtitle=new JLabel("Create, update and manage counter employees");
        subtitle.setFont(new Font(dFont,Font.PLAIN,14));
        subtitle.setForeground(GRAY);
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);

        titlePanel.add(heading);
        titlePanel.add(Box.createVerticalStrut(7));
        titlePanel.add(subtitle);

        topPanel.add(titlePanel, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        // TABLE

        String[] columns = {
                "User ID",
                "Username",
                "Full Name",
                "Status"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        staffTable = new JTable(tableModel);
        staffTable.setRowHeight(35);
        staffTable.setFont(new Font(dFont, Font.PLAIN, 13));
        staffTable.getTableHeader().setFont(
                new Font(dFont, Font.BOLD, 13)
        );

        staffTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane = new JScrollPane(staffTable);

        // SEARCH

        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setOpaque(false);

        searchField = new JTextField();
        searchField.setFont(new Font(dFont, Font.PLAIN, 14));
        searchField.setPreferredSize(new Dimension(250, 38));

        JLabel searchLabel = new JLabel("Search Staff:");
        searchLabel.setFont(new Font(dFont, Font.BOLD, 13));

        searchPanel.add(searchLabel, BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);

        TableRowSorter<DefaultTableModel> sorter =
                new TableRowSorter<>(tableModel);

        staffTable.setRowSorter(sorter);

        searchField.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {

                    private void filter() {

                        String text = searchField.getText().trim();

                        if (text.isEmpty()) {
                            sorter.setRowFilter(null);
                        } else {
                            sorter.setRowFilter(
                                    RowFilter.regexFilter(
                                            "(?i)" + java.util.regex.Pattern.quote(text)
                                    )
                            );
                        }
                    }

                    @Override
                    public void insertUpdate(
                            javax.swing.event.DocumentEvent e) {
                        filter();
                    }

                    @Override
                    public void removeUpdate(
                            javax.swing.event.DocumentEvent e) {
                        filter();
                    }

                    @Override
                    public void changedUpdate(
                            javax.swing.event.DocumentEvent e) {
                        filter();
                    }
                }
        );

        // BUTTONS

        JButton addButton = createButton("ADD STAFF", BLACK);
        JButton editButton = createButton("EDIT STAFF", NAVY);
        JButton passwordButton = createButton("RESET PASSWORD", new Color(80, 100, 160));
        JButton statusButton = createButton("ACTIVATE / DEACTIVATE", new Color(190, 120, 30));
        JButton refreshButton = createButton("REFRESH", new Color(90, 100, 110));

        addButton.addActionListener(e -> addStaff());
        editButton.addActionListener(e -> editStaff());
        passwordButton.addActionListener(e -> resetPassword());
        statusButton.addActionListener(e -> toggleStaffStatus());
        refreshButton.addActionListener(e -> loadStaff());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        buttonPanel.setOpaque(false);

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(passwordButton);
        buttonPanel.add(statusButton);
        buttonPanel.add(refreshButton);

        JPanel centerPanel = new JPanel(new BorderLayout(0, 12));
        centerPanel.setOpaque(false);

        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String title, Color color) {

        JButton button = new JButton(title);

        button.setBackground(color);
        button.setForeground(BLACK);
        button.setFont(new Font(dFont, Font.BOLD, 11));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    // LOAD STAFF

    private void loadStaff() {

        tableModel.setRowCount(0);

        List<User> staffList = userDAO.getAllStaff();

        for (User user : staffList) {

            boolean active = userDAO.isStaffActive(user.getUserID());

            tableModel.addRow(new Object[]{
                    user.getUserID(),
                    user.getUsername(),
                    user.getFullname(),
                    active ? "ACTIVE" : "DEACTIVATED"
            });
        }
    }

    // ADD STAFF

    private void addStaff() {

        JTextField usernameField = new JTextField();
        JTextField fullnameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        Object[] fields = {
                "Full Name:", fullnameField,
                "Username:", usernameField,
                "Password:", passwordField
        };

        int result = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Create Staff Account",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String fullname = fullnameField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (fullname.isEmpty() || username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "All fields are required.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (password.length() < 8) {

            JOptionPane.showMessageDialog(
                    this,
                    "Password must contain at least 8 characters."
            );

            return;
        }

        if (userDAO.usernameExists(username)) {

            JOptionPane.showMessageDialog(
                    this,
                    "This username already exists.",
                    "Duplicate Username",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        boolean success = userDAO.createUser(
                username,
                password,
                fullname,
                "STAFF"
        );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Staff account created successfully!"
            );

            loadStaff();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not create staff account."
            );
        }
    }

    // EDIT STAFF

    private void editStaff() {

        int row = staffTable.getSelectedRow();

        if (row == -1) {
            showSelectMessage();
            return;
        }

        int modelRow = staffTable.convertRowIndexToModel(row);

        int userId = (int) tableModel.getValueAt(modelRow, 0);

        String oldUsername = tableModel.getValueAt(modelRow, 1).toString();
        String oldFullname = tableModel.getValueAt(modelRow, 2).toString();

        JTextField usernameField = new JTextField(oldUsername);
        JTextField fullnameField = new JTextField(oldFullname);

        Object[] fields = {
                "Username:", usernameField,
                "Full Name:", fullnameField
        };

        int result = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Edit Staff Information",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String username = usernameField.getText().trim();
        String fullname = fullnameField.getText().trim();

        if (username.isEmpty() || fullname.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "All fields are required."
            );

            return;
        }

        if (userDAO.usernameExistsForOtherUser(username, userId)) {

            JOptionPane.showMessageDialog(
                    this,
                    "This username is already being used."
            );

            return;
        }

        if (userDAO.updateStaff(userId, username, fullname)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Staff information updated successfully!"
            );

            loadStaff();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not update staff information."
            );
        }
    }

    // RESET PASSWORD

    private void resetPassword() {

        int row = staffTable.getSelectedRow();

        if (row == -1) {
            showSelectMessage();
            return;
        }

        int modelRow = staffTable.convertRowIndexToModel(row);

        int userId = (int) tableModel.getValueAt(modelRow, 0);

        String username = tableModel.getValueAt(modelRow, 1).toString();

        JPasswordField passwordField = new JPasswordField();

        int result = JOptionPane.showConfirmDialog(
                this,
                new Object[]{
                        "Enter new password for " + username + ":",
                        passwordField
                },
                "Reset Staff Password",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String password = new String(passwordField.getPassword());

        if (password.length() < 8) {

            JOptionPane.showMessageDialog(
                    this,
                    "Password must contain at least 8 characters."
            );

            return;
        }

        if (userDAO.resetStaffPassword(userId, password)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Password reset successfully!"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Password reset failed."
            );
        }
    }

    // ACTIVATE / DEACTIVATE

    private void toggleStaffStatus() {

        int row = staffTable.getSelectedRow();

        if (row == -1) {
            showSelectMessage();
            return;
        }

        int modelRow = staffTable.convertRowIndexToModel(row);

        int userId = (int) tableModel.getValueAt(modelRow, 0);

        String username = tableModel.getValueAt(modelRow, 1).toString();

        boolean active = userDAO.isStaffActive(userId);

        String action = active ? "deactivate" : "activate";

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to " + action +
                        " " + username + "?",
                "Confirm Action",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        if (userDAO.setStaffActive(userId, !active)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Staff account " + action + "d successfully!"
            );

            loadStaff();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not update staff status."
            );
        }
    }

    private void showSelectMessage() {

        JOptionPane.showMessageDialog(
                this,
                "Please select a staff member first.",
                "No Staff Selected",
                JOptionPane.WARNING_MESSAGE
        );
    }
}
