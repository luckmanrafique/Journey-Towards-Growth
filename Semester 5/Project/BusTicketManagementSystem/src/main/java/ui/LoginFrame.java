package ui;
import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;
public class LoginFrame extends JFrame {
    final String dFONT="Times New Roman";

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPassword;
    private JButton loginButton;
    private final UserDAO userDAO=new UserDAO();
    public LoginFrame(){
        setTitle("Bus Ticket Management System");
        setSize(850,520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        createInterface();
    }
    private void createInterface(){
        JPanel mainPanel=new JPanel(new GridLayout(1,2));

        JPanel leftPanel=new JPanel();
        leftPanel.setBackground(new Color(20,39,70));
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(90,35,40,35));

        JLabel logo=new JLabel("BUS TICKET");
        logo.setFont(new Font(dFONT,Font.BOLD,30));
        logo.setForeground(Color.WHITE);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel subtitle=new JLabel("MANAGEMENT SYSTEM");
        subtitle.setFont(new Font(dFONT,Font.PLAIN,15));
        subtitle.setForeground(new Color(190,205,225));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel description=new JLabel(
                "<html><center>Manage bus schedules,bookings and passengers from one place.</center></html>"
        );
        description.setFont(new Font(dFONT,Font.PLAIN,12));
        description.setForeground(Color.WHITE);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(logo);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(subtitle);
        leftPanel.add(Box.createVerticalStrut(55));
        leftPanel.add(description);

        JPanel rightPanel=new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(null);
        JLabel welcomeLabel=new JLabel("Welcome Back!");
        welcomeLabel.setFont(new Font(dFONT,Font.BOLD,28));
        welcomeLabel.setForeground(new Color(20,39,70));
        welcomeLabel.setBounds(55,65,300,40);
        JLabel instructionLabel=new JLabel("Login to access your account");
        instructionLabel.setFont(new Font(dFONT,Font.PLAIN,14));
        instructionLabel.setForeground(Color.GRAY);
        instructionLabel.setBounds(57,108,300,25);
        JLabel usernameLabel=new JLabel("Username");
        usernameLabel.setBounds(57,165,300,25);
        usernameLabel.setFont(new Font(dFONT,Font.BOLD,14));
        usernameField=new JTextField();
        usernameField.setBounds(57,193,320,42);
        usernameField.setFont(new Font(dFONT,Font.PLAIN,15));
        JLabel passwordLabel=new JLabel("Password");
        passwordLabel.setBounds(57,252,300,25);
        passwordLabel.setFont(new Font(dFONT,Font.BOLD,15));
        passwordField=new JPasswordField();
        passwordField.setBounds(57,280,320,42);
        passwordField.setFont(new Font(dFONT,Font.PLAIN,14));
        showPassword=new JCheckBox("Show Password");
        showPassword.setBounds(57,327,160,25);
        showPassword.setBackground(Color.WHITE);

        showPassword.addActionListener(e->{
            if(showPassword.isSelected()){
                passwordField.setEchoChar((char)0);
            }else{
                passwordField.setEchoChar('*');
            }
        });

        loginButton=new JButton("LOGIN");
        loginButton.setBounds(57,370,320,45);
        loginButton.setBackground(new Color(20,39,70));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font(dFONT,Font.BOLD,15));
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e->login());

        rightPanel.add(welcomeLabel);
        rightPanel.add(instructionLabel);
        rightPanel.add(usernameLabel);
        rightPanel.add(usernameField);
        rightPanel.add(passwordLabel);
        rightPanel.add(passwordField);
        rightPanel.add(showPassword);
        rightPanel.add(loginButton);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        add(mainPanel);
    }
    private void login(){
        String username=usernameField.getText().trim();
        String password=new String(passwordField.getPassword());
        if(username.isEmpty()||password.isEmpty()){
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password!",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        loginButton.setEnabled(false);
        User user=userDAO.authenticate(username,password);
        loginButton.setEnabled(true);
        if(user!=null){
            JOptionPane.showMessageDialog(
                    this,
                    "Welcome,"+user.getFullname()+"!",
                    "Login Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );
            dispose();
            new DashboardFrame(user).setVisible(true);
        }else{
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password!",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
            passwordField.setText("");
            passwordField.requestFocus();
        }
    }
}