package com.registraion;

import com.registraion.condition.password.CapitalLetterException;
import com.registraion.condition.password.DigitException;
import com.registraion.condition.password.LengthException;
import com.registraion.condition.password.SmallLetterException;
import com.registraion.condition.password.SpecialCharacterException;
import com.registraion.condition.username.FirstLetterException;
import com.registraion.condition.username.SpaceException;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
    private final Map<String, String> users = new HashMap<>();

    public LoginFrame() {
        setTitle("Registration and Login");
        setSize(400, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Register", createRegistrationPanel());
        tabs.addTab("Login", createLoginPanel());
        add(tabs);
    }

    private JPanel createRegistrationPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton registerButton = new JButton("Register");

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(registerButton);

        registerButton.addActionListener(event -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            try {
                validateUsername(username);
                validatePassword(password);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage(), "Invalid registration",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (users.containsKey(username)) {
                JOptionPane.showMessageDialog(this, "That username is already registered.",
                        "Registration error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            users.put(username, password);
            usernameField.setText("");
            passwordField.setText("");
            JOptionPane.showMessageDialog(this, "Registration successful.");
        });

        return panel;
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        loginButton.addActionListener(event -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String registeredPassword = users.get(username);

            if (registeredPassword != null && registeredPassword.equals(password)) {
                JOptionPane.showMessageDialog(this, "Login successful.");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.",
                        "Login error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private void validateUsername(String username)
            throws FirstLetterException, SpaceException {
        if (username.isEmpty()) {
            throw new FirstLetterException();
        }
        if (!Character.isAlphabetic(username.charAt(0))) {
            throw new FirstLetterException();
        }
        if (username.contains(" ")) {
            throw new SpaceException();
        }
    }

    private void validatePassword(String password)
            throws LengthException, CapitalLetterException, SmallLetterException,
            DigitException, SpecialCharacterException {
        if (password.length() < 8) {
            throw new LengthException();
        }

        boolean hasCapital = false;
        boolean hasSmall = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char character : password.toCharArray()) {
            if (Character.isUpperCase(character)) {
                hasCapital = true;
            } else if (Character.isLowerCase(character)) {
                hasSmall = true;
            } else if (Character.isDigit(character)) {
                hasDigit = true;
            } else {
                hasSpecial = true;
            }
        }

        if (!hasCapital) {
            throw new CapitalLetterException();
        }
        if (!hasSmall) {
            throw new SmallLetterException();
        }
        if (!hasDigit) {
            throw new DigitException();
        }
        if (!hasSpecial) {
            throw new SpecialCharacterException();
        }
    }
}
