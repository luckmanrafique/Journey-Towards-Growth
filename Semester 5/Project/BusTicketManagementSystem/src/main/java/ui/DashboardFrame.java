package ui;
import model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;
public class DashboardFrame extends JFrame{
    private static final Log log = LogFactory.getLog(DashboardFrame.class);
    final String dFont="Times New Roman";

    private final User currentUser;
    private final CardLayout cardLayout=new CardLayout();
    private final JPanel contentPanel=new JPanel(cardLayout);
    private final Color NAVY=new Color(20,39,70);
    private final Color BACKGROUND=new Color(245,247,250);
    private final Color WHITE=Color.WHITE;

    public DashboardFrame(User user){
        this.currentUser=user;
        setTitle("Bus Ticket Management System");
        setSize(1200,750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1000,650));
        createDashboard();
    }
    public void createDashboard(){
        JPanel mainPanel=new JPanel(new BorderLayout());
        mainPanel.add(createSidebar(),BorderLayout.WEST);
        mainPanel.add(createMainContent(),BorderLayout.CENTER);
        add(mainPanel);
    }
    private JPanel createSidebar(){
        JPanel sidebar=new JPanel();
        sidebar.setPreferredSize(new Dimension(240,0));
        sidebar.setBackground(NAVY);
        sidebar.setLayout(new BoxLayout(sidebar,BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(25,15,20,15));

        JLabel logo=new JLabel("BUS TICKET");
        logo.setFont(new Font(dFont,Font.BOLD,23));
        logo.setForeground(WHITE);
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel subtitle=new JLabel("MANAGEMENT SYSTEM");
        subtitle.setFont(new Font(dFont,Font.PLAIN,11));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel roleLabel=new JLabel(currentUser.getRole()+" PANEL");
        roleLabel.setFont(new Font(dFont,Font.BOLD,12));
        roleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(subtitle);
        sidebar.add(Box.createVerticalStrut(30));
        sidebar.add(roleLabel);
        sidebar.add(Box.createVerticalStrut(20));

        addMenuButton(sidebar,"Dashboard","dashboard");

        if(currentUser.getRole().equalsIgnoreCase("ADMIN")){
            addMenuButton(sidebar,"Bus Management","buses");
            addMenuButton(sidebar,"Route Management","routes");
            addMenuButton(sidebar,"Trip Management","trips");
            addMenuButton(sidebar,"All Bookings","bookings");
            addMenuButton(sidebar,"Cancellation History","cancellations");
            addMenuButton(sidebar,"Sales Reports","reports");
            addMenuButton(sidebar,"Staff Management","staff");
        }else{
            addMenuButton(sidebar,"New Ticket Booking","newbooking");
            addMenuButton(sidebar,"Search Tickets","search");
            addMenuButton(sidebar,"Cancel Ticket","cancel");
            addMenuButton(sidebar,"My Booking History","mybookings");
            addMenuButton(sidebar,"My Cancellation History","mycancellations");
        }
        sidebar.add(Box.createVerticalGlue());
        JButton logoutButton=new JButton("LOGOUT");
        logoutButton.setMaximumSize(new Dimension(Integer.MAX_VALUE,42));

        logoutButton.setBackground(new Color(180,55,55));
        logoutButton.setForeground(NAVY);
        logoutButton.setFont(new Font(dFont,Font.BOLD,13));
        logoutButton.setFocusPainted(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        logoutButton.addActionListener(e->logout());
        sidebar.add(logoutButton);
        return sidebar;
    }
    private void addMenuButton(JPanel sidebar,String title,String cardName){
        JButton button= new JButton(title);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE,43));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBackground(NAVY);
        button.setForeground(Color.BLACK);
        button.setFont(new Font(dFont,Font.PLAIN,14));
        button.setBorder(BorderFactory.createEmptyBorder(10,12,10,5));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(e-> cardLayout.show(contentPanel,cardName));
        sidebar.add(button);
        sidebar.add(Box.createVerticalStrut(5));
    }
    private JPanel createMainContent(){
        JPanel main=new JPanel(new BorderLayout());
        main.setBackground(BACKGROUND);
        main.add(createHeader(),BorderLayout.NORTH);
        contentPanel.setBackground(BACKGROUND);
        contentPanel.add(createOverview(),"dashboard");

        if(currentUser.getRole().equalsIgnoreCase("ADMIN")){
            addPlaceholder("Bus Management","Manage your buses and seats configurations.","buses");
            addPlaceholder("Route Management","Manage destinations,distances and fares.","routes");
            addPlaceholder("Trip Management","Create and manage bus departure schedules.","trips");
            addPlaceholder("All Bookings","View and manage all passenger bookings.","bookings");
            addPlaceholder("Cancellation History","Monitor cancelled tickets and responsible staff. ","cancellations");
            addPlaceholder("Sales Reports","Review daily,weekly and monthly revenue.","reports");
            addPlaceholder("Staff Management","Create and manage counter employee accounts.","staff");

        }else{
            addPlaceholder("New Ticket Booking","Select  atrip and book passenger seats.","newbooking");
            addPlaceholder("Search Tickets","Find tickets using ticket number or phone.","search");
            addPlaceholder("Cancel Ticket","Cancel a confirmed passenger booking.","cancel");
            addPlaceholder("My Booking History","View tickets booked by your account.","mybookings");
            addPlaceholder("My Cancellation History","View tickets cancelled by your account.","mycancellations");
        }
        main.add(contentPanel,BorderLayout.CENTER);
        return main;
    }
    private JPanel createHeader(){
        JPanel header =new JPanel(new BorderLayout());
        header.setBackground(WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(18,25,18,25));

        JLabel title=new JLabel("Dashboard");
        title.setFont(new Font(dFont,Font.BOLD,23));
        title.setForeground(NAVY);

        JLabel userInfo=new JLabel(currentUser.getFullname()+" | "+currentUser.getRole());
        userInfo.setFont(new Font(dFont,Font.PLAIN,14));
        userInfo.setForeground(new Color(80,90,100));

        header.add(title,BorderLayout.WEST);
        header.add(userInfo,BorderLayout.EAST);
        return header;
    }
    private JPanel createOverview(){
        JPanel panel=new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        JPanel top=new JPanel();
        top.setLayout(new BoxLayout(top,BoxLayout.Y_AXIS));
        top.setOpaque(false);

        JLabel welcome=new JLabel("Welcome, "+currentUser.getFullname()+"!");
        welcome.setFont(new Font(dFont,Font.BOLD,27));
        welcome.setForeground(NAVY);

        JLabel description=new JLabel("Here is your bus ticket counter overview.");
        description.setFont(new Font(dFont,Font.PLAIN,15));
        description.setForeground(Color.GRAY);

        top.add(welcome);
        top.add(Box.createVerticalStrut(8));
        top.add(description);
        top.add(Box.createVerticalStrut(30));

        JPanel cards=new JPanel(new GridLayout(2,2,18,18));
        cards.setOpaque(false);

        if(currentUser.getRole().equalsIgnoreCase("ADMIN")){
            cards.add(createStartCard("Total Buses","--"));
            cards.add(createStartCard("Today's Trips","--"));
            cards.add(createStartCard("Tickets Sold Today","--"));
            cards.add(createStartCard("Cancelled Tickets","--"));

        }else{
            cards.add(createStartCard("Today's Bookings","--"));
            cards.add(createStartCard("Available Trips","--"));
            cards.add(createStartCard("My Cansellations","--"));
            cards.add(createStartCard("Today's Sales","--"));

        }
        JLabel note=new JLabel("Statistics will appear here after database integration.");
        note.setFont(new Font(dFont,Font.ITALIC,13));
        note.setForeground(Color.GRAY);

        JPanel center=new JPanel(new BorderLayout());
        center.setOpaque(false);
        center.add(cards,BorderLayout.NORTH);
        center.add(note,BorderLayout.CENTER);
        center.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        panel.add(top,BorderLayout.NORTH);
        panel.add(center,BorderLayout.CENTER);
        return panel;
    }
    private JPanel createStartCard(String title, String value){
        JPanel card=new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(WHITE);
        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(225,230,238)),
                        BorderFactory.createEmptyBorder(22,22,22,22)
                )
        );
        JLabel titleLabel=new JLabel(title);
        titleLabel.setFont(new Font(dFont,Font.PLAIN,15));
        titleLabel.setForeground(Color.GRAY);

        JLabel valueLabel=new JLabel(value);
        valueLabel.setFont(new Font(dFont,Font.BOLD,30));
        valueLabel.setForeground(NAVY);

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(15));
        card.add(valueLabel);

        return card;
    }
    private void addPlaceholder(String title,String description,String cardName){
        JPanel page=new JPanel();
        page.setLayout(new BoxLayout(page,BoxLayout.Y_AXIS));
        page.setBackground(BACKGROUND);
        page.setBorder(BorderFactory.createEmptyBorder(60,40,40,40));

        JLabel heading=new JLabel(title);
        heading.setFont(new Font(dFont,Font.BOLD,28));
        heading.setForeground(NAVY);
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel details=new JLabel(description);
        details.setFont(new Font(dFont,Font.PLAIN,16));
        details.setForeground(Color.GRAY);
        details.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel status=new JLabel("This module will be implemented soon.");
        status.setFont(new Font(dFont,Font.ITALIC,13));
        status.setForeground(new Color(30,130,100));
        status.setAlignmentX(Component.LEFT_ALIGNMENT);

        page.add(heading);
        page.add(Box.createVerticalStrut(12));
        page.add(details);
        page.add(Box.createVerticalStrut(30));
        page.add(status);

        contentPanel.add(page,cardName);
    }
    private void logout(){
        int choice=JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
        );
        if(choice==JOptionPane.YES_OPTION){
            dispose();
            new LoginFrame().setVisible(true);
        }
    }
}