package ui;
import com.google.protobuf.Message;
import model.User;

import javax.swing.*;
import java.awt.*;
public class DashboardFrame extends JFrame {
    final String dFONT="Arial";
    public DashboardFrame(User user){
        setTitle("Bus Ticket Management System");
        setSize(1000,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel=new JPanel(new BorderLayout());
        JPanel header=new JPanel(new BorderLayout());
        header.setBackground(new Color(20,39,70));
        header.setBorder(BorderFactory.createEmptyBorder(20,25,20,25));

        JLabel title=new JLabel("BUS TICKET MANAGEMENT SYSTEM");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial",Font.BOLD,20));

        JLabel userInfo=new JLabel(
                user.getFullname()+"|"+user.getRole()
        );
        userInfo.setForeground(Color.WHITE);
        userInfo.setFont(new Font("Arial",Font.PLAIN,14));
        header.add(title,BorderLayout.WEST);
        header.add(userInfo,BorderLayout.EAST);

        JPanel content=new JPanel();
        content.setBackground(new Color(245,247,250));
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(80,50,50,50));

        JLabel welcome=new JLabel(
                "Welcome,"+user.getFullname()
        );
        welcome.setFont(new Font("Arial",Font.BOLD,30));
        welcome.setForeground(new Color(20,39,70));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel role=new JLabel(
                "Logged in as: "+user.getRole()
        );
        role.setFont(new Font("Arial",Font.PLAIN,18));
        role.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel message=new JLabel(
                "Your dashboard is ready!"
        );
        message.setFont(new Font(dFONT,Font.PLAIN,16));
        message.setForeground(Color.GRAY);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(welcome);
        content.add(Box.createVerticalStrut(15));
        content.add(role);
        content.add(Box.createVerticalStrut(30));
        content.add(message);

        JButton logoutButton=new JButton("LOGOUT");
        logoutButton.addActionListener(e->{
            dispose();
            new LoginFrame().setVisible(true);
        });
        JPanel footer=new JPanel();
        footer.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        footer.add(logoutButton);

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(content,BorderLayout.CENTER);
        mainPanel.add(footer,BorderLayout.SOUTH);
        add(mainPanel);

    }
}
