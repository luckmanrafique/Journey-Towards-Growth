import java.awt.*;
import java.io.*;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

    private static final String DATA_DIR = "data";
    private static final String ACCOUNT_FILE = DATA_DIR + "/accounts.dat";
    private static final String TRANSACTION_FILE = DATA_DIR + "/transactions.txt";

    private HashMap<String, Account> accounts = new HashMap<>();
    private Account currentAccount;

    private final Color BLUE = new Color(30, 70, 140);
    private final Color LIGHT = new Color(245, 247, 251);

    public Main() {
        setTitle("Simple Banking System");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        new File(DATA_DIR).mkdirs();
        loadAccounts();

        showLogin();
    }

    private void styleButton(JButton button) {
        button.setBackground(BLUE);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
    }

    private JTextField createField() {
        JTextField field = new JTextField(18);
        field.setFont(new Font("Arial", Font.PLAIN, 15));
        return field;
    }

    private void showLogin() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(LIGHT);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.WHITE);
        form.setBorder(new EmptyBorder(35, 45, 35, 45));

        JLabel title = new JLabel("SIMPLE BANKING SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 23));

        JLabel subtitle = new JLabel("Welcome! Please login or create an account.");
        subtitle.setForeground(Color.GRAY);

        JTextField accountField = createField();
        JPasswordField pinField = new JPasswordField(18);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Create Account");
        JButton adminButton = new JButton("Admin Login");

        styleButton(loginButton);
        styleButton(registerButton);
        styleButton(adminButton);

        form.add(title);
        form.add(Box.createVerticalStrut(12));
        form.add(subtitle);
        form.add(Box.createVerticalStrut(30));
        form.add(new JLabel("Account Number"));
        form.add(accountField);
        form.add(Box.createVerticalStrut(15));
        form.add(new JLabel("PIN"));
        form.add(pinField);
        form.add(Box.createVerticalStrut(25));
        form.add(loginButton);
        form.add(Box.createVerticalStrut(10));
        form.add(registerButton);
        form.add(Box.createVerticalStrut(10));
        form.add(adminButton);

        panel.add(form);

        setContentPane(panel);
        revalidate();
        repaint();

        loginButton.addActionListener(e -> {
            String number = accountField.getText().trim();
            String pin = new String(pinField.getPassword());

            Account account = accounts.get(number);

            if (account != null && account.pin.equals(hash(pin))) {
                currentAccount = account;
                showDashboard();
            } else {
                message("Invalid account number or PIN.");
            }
        });

        registerButton.addActionListener(e -> showRegistration());

        adminButton.addActionListener(e -> showAdminLogin());
    }

    private void showRegistration() {
        JTextField name = new JTextField();
        JTextField initial = new JTextField();
        JPasswordField pin = new JPasswordField();
        JPasswordField confirmPin = new JPasswordField();

        String[] types = {"Savings", "Current"};
        JComboBox<String> type = new JComboBox<>(types);

        Object[] fields = {
            "Full Name:", name,
            "Account Type:", type,
            "Initial Deposit:", initial,
            "Create PIN (minimum 4 digits):", pin,
            "Confirm PIN:", confirmPin
        };

        int result = JOptionPane.showConfirmDialog(
            this, fields, "Create Bank Account",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) return;

        try {
            String customerName = name.getText().trim();
            String pinValue = new String(pin.getPassword());
            String confirmValue = new String(confirmPin.getPassword());
            double amount = Double.parseDouble(initial.getText());

            double minimum = type.getSelectedIndex() == 0 ? 100 : 500;

            if (customerName.isEmpty()) {
                message("Name cannot be empty.");
                return;
            }

            if (!pinValue.matches("\\d{4,}")) {
                message("PIN must contain at least 4 digits.");
                return;
            }

            if (!pinValue.equals(confirmValue)) {
                message("PINs do not match.");
                return;
            }

            if (amount < minimum) {
                message("Minimum initial deposit is " + minimum);
                return;
            }

            String number = String.valueOf(
                1000000000L + accounts.size() + 1
            );

            while (accounts.containsKey(number)) {
                number = String.valueOf(
                    Long.parseLong(number) + 1
                );
            }

            Account account = new Account(
                number, customerName,
                type.getSelectedItem().toString(),
                hash(pinValue), amount
            );

            accounts.put(number, account);
            saveAccounts();

            addTransaction(number, "ACCOUNT CREATED", amount);

            message("Account created successfully!\nAccount Number: " + number);

        } catch (NumberFormatException ex) {
            message("Please enter a valid deposit amount.");
        } catch (Exception ex) {
            message("Something went wrong: " + ex.getMessage());
        }
    }

    private void showDashboard() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(LIGHT);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BLUE);
        header.setBorder(new EmptyBorder(20, 25, 20, 25));

        JLabel welcome = new JLabel("Welcome, " + currentAccount.name);
        welcome.setForeground(Color.WHITE);
        welcome.setFont(new Font("Arial", Font.BOLD, 21));

        JButton logout = new JButton("Logout");
        styleButton(logout);

        header.add(welcome, BorderLayout.WEST);
        header.add(logout, BorderLayout.EAST);

        JPanel content = new JPanel(new GridLayout(0, 2, 18, 18));
        content.setBorder(new EmptyBorder(35, 35, 35, 35));
        content.setBackground(LIGHT);

        JLabel balance = new JLabel(
            "<html><h2>Available Balance</h2><h1>৳ " +
            String.format("%.2f", currentAccount.balance) +
            "</h1></html>"
        );

        JLabel details = new JLabel(
            "<html><h2>Account Details</h2>" +
            "Account: " + currentAccount.number +
            "<br>Name: " + currentAccount.name +
            "<br>Type: " + currentAccount.type +
            "</html>"
        );

        content.add(createCard(balance));
        content.add(createCard(details));

        JButton deposit = new JButton("Deposit Money");
        JButton withdraw = new JButton("Withdraw Money");
        JButton transfer = new JButton("Transfer Money");
        JButton history = new JButton("Transaction History");

        JButton[] buttons = {deposit, withdraw, transfer, history};

        for (JButton button : buttons) {
            styleButton(button);
            content.add(button);
        }

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(content, BorderLayout.CENTER);

        setContentPane(mainPanel);
        revalidate();
        repaint();

        logout.addActionListener(e -> {
            currentAccount = null;
            showLogin();
        });

        deposit.addActionListener(e -> {
            transaction("DEPOSIT");
            showDashboard();
        });

        withdraw.addActionListener(e -> {
            transaction("WITHDRAW");
            showDashboard();
        });

        transfer.addActionListener(e -> {
            transferMoney();
            showDashboard();
        });

        history.addActionListener(e -> showHistory());
    }

    private JPanel createCard(JComponent component) {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));
        card.add(component);
        return card;
    }

    private void transaction(String operation) {
        String input = JOptionPane.showInputDialog(
            this, "Enter amount:"
        );

        if (input == null) return;

        try {
            double amount = Double.parseDouble(input);

            if (amount <= 0) {
                message("Amount must be greater than zero.");
                return;
            }

            if (operation.equals("DEPOSIT")) {
                currentAccount.balance += amount;
            } else {
                double minimum = currentAccount.type.equals("Savings")
                    ? 100 : 500;

                if (currentAccount.balance - amount < minimum) {
                    message("Insufficient balance. Minimum balance: " + minimum);
                    return;
                }

                currentAccount.balance -= amount;
            }

            saveAccounts();
            addTransaction(
                currentAccount.number, operation, amount
            );

            message(operation + " successful!");

        } catch (NumberFormatException ex) {
            message("Invalid amount.");
        }
    }

    private void transferMoney() {
        JTextField receiverField = new JTextField();
        JTextField amountField = new JTextField();

        Object[] fields = {
            "Receiver Account Number:", receiverField,
            "Amount:", amountField
        };

        int result = JOptionPane.showConfirmDialog(
            this, fields, "Transfer Money",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) return;

        try {
            String receiverNumber = receiverField.getText().trim();
            double amount = Double.parseDouble(amountField.getText());

            Account receiver = accounts.get(receiverNumber);

            if (receiver == null) {
                message("Receiver account not found.");
                return;
            }

            if (receiverNumber.equals(currentAccount.number)) {
                message("Cannot transfer to your own account.");
                return;
            }

            double minimum = currentAccount.type.equals("Savings")
                ? 100 : 500;

            if (amount <= 0 ||
                currentAccount.balance - amount < minimum) {
                message("Invalid amount or insufficient balance.");
                return;
            }

            currentAccount.balance -= amount;
            receiver.balance += amount;

            saveAccounts();

            addTransaction(
                currentAccount.number,
                "TRANSFER TO " + receiverNumber, amount
            );

            addTransaction(
                receiver.number,
                "TRANSFER FROM " + currentAccount.number, amount
            );

            message("Transfer successful!");

        } catch (NumberFormatException ex) {
            message("Invalid amount.");
        }
    }

    private void showHistory() {
        StringBuilder history = new StringBuilder();

        try (BufferedReader reader =
                 new BufferedReader(new FileReader(TRANSACTION_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 5);

                if (parts.length >= 5 &&
                    parts[0].equals(currentAccount.number)) {

                    history.append(parts[1])
                        .append(" | ")
                        .append(parts[2])
                        .append(" | ৳ ")
                        .append(parts[3])
                        .append(" | ")
                        .append(parts[4])
                        .append("\n");
                }
            }

        } catch (IOException ex) {
            history.append("No transaction history found.");
        }

        JTextArea area = new JTextArea(
            history.length() == 0
                ? "No transactions yet."
                : history.toString()
        );

        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(600, 350));

        JOptionPane.showMessageDialog(
            this, scroll, "Transaction History",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void showAdminLogin() {
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();

        Object[] fields = {
            "Admin Username:", username,
            "Admin Password:", password
        };

        int result = JOptionPane.showConfirmDialog(
            this, fields, "Admin Login",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) return;

        if (username.getText().equals("admin") &&
            new String(password.getPassword()).equals("admin123")) {
            showAdminDashboard();
        } else {
            message("Invalid admin credentials.");
        }
    }

    private void showAdminDashboard() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        double total = 0;

        StringBuilder details = new StringBuilder();

        for (Account account : accounts.values()) {
            total += account.balance;

            details.append(account.number)
                .append(" | ")
                .append(account.name)
                .append(" | ")
                .append(account.type)
                .append(" | ৳ ")
                .append(String.format("%.2f", account.balance))
                .append("\n");
        }

        JLabel summary = new JLabel(
            "<html><h2>Admin Dashboard</h2>" +
            "Total Accounts: " + accounts.size() +
            "<br><h2>Total Bank Balance: ৳ " +
            String.format("%.2f", total) +
            "</h2></html>"
        );

        JTextArea area = new JTextArea(details.toString());
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JButton back = new JButton("Back to Login");
        styleButton(back);

        panel.add(summary, BorderLayout.NORTH);
        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        panel.add(back, BorderLayout.SOUTH);

        setContentPane(panel);
        revalidate();
        repaint();

        back.addActionListener(e -> showLogin());
    }

    private void addTransaction(
        String number, String type, double amount
    ) {
        String date = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );

        try (FileWriter writer = new FileWriter(
            TRANSACTION_FILE, true
        )) {
            writer.write(
                number + "|" + date + "|" + type + "|" +
                String.format("%.2f", amount) + "|" +
                "Completed" + System.lineSeparator()
            );
        } catch (IOException ex) {
            message("Could not save transaction.");
        }
    }

    private void saveAccounts() {
        try (ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream(ACCOUNT_FILE)
        )) {
            out.writeObject(accounts);
        } catch (IOException ex) {
            message("Could not save accounts.");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadAccounts() {
        File file = new File(ACCOUNT_FILE);

        if (!file.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(
            new FileInputStream(file)
        )) {
            accounts = (HashMap<String, Account>) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            message("Could not load account data.");
        }
    }

    private String hash(String pin) {
        try {
            MessageDigest digest =
                MessageDigest.getInstance("SHA-256");

            byte[] bytes = digest.digest(
                pin.getBytes("UTF-8")
            );

            StringBuilder result = new StringBuilder();

            for (byte b : bytes) {
                result.append(String.format("%02x", b));
            }

            return result.toString();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void message(String text) {
        JOptionPane.showMessageDialog(this, text);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}

class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    String number;
    String name;
    String type;
    String pin;
    double balance;

    Account(
        String number,
        String name,
        String type,
        String pin,
        double balance
    ) {
        this.number = number;
        this.name = name;
        this.type = type;
        this.pin = pin;
        this.balance = balance;
    }
}
