import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

class LoginForm extends JFrame {
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    JTextField tfUserId;
    JPasswordField pfPassword;
    public void initialize() {
        /*************** Form Panel ***************/
        JLabel lbLoginForm = new JLabel("Login Form", SwingConstants.CENTER);
        lbLoginForm.setFont(mainFont);

        JLabel lbUserId = new JLabel("User ID");
        lbUserId.setFont(mainFont);

        tfUserId = new JTextField();
        tfUserId.setFont(mainFont);

        JLabel lbPassword = new JLabel("Password");
        lbPassword.setFont(mainFont);

        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        formPanel.add(lbLoginForm);
        formPanel.add(lbUserId);
        formPanel.add(tfUserId);
        formPanel.add(lbPassword);
        formPanel.add(pfPassword);

        /*************** Buttons Panel ***************/
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(mainFont);
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String email = tfUserId.getText();
                String password = String.valueOf(pfPassword.getPassword());

                User user = getAuthenticatedUser(email, password);

                if (user != null) {
                    Modesel md=new Modesel();
                    md.initialize(email,password);
                    
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Login ID or Password Invalid",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(mainFont);
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                dispose();
            }
            
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonsPanel.add(btnLogin);
        buttonsPanel.add(btnCancel);



        /*************** Initialise the frame ***************/
        add(formPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);

        setTitle("Login Form");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setMinimumSize(new Dimension(350, 450));
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private User getAuthenticatedUser(String UserId, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/user";
        final String USERNAME = "sqluser";
        final String PASSWORD = "password";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String sql = "SELECT * FROM reservations WHERE loginid=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, UserId);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.UserId = resultSet.getString("loginid");
                user.train_no = resultSet.getString("train_no");
                user.train_name = resultSet.getString("train_name");
                user.classtype = resultSet.getString("classtype");
                user.date = resultSet.getString("date");
                user.from_station = resultSet.getString("from_station");
                user.destination = resultSet.getString("destination");
                user.pnr = resultSet.getString("pnr");

                user.password = resultSet.getString("password");
            }

            preparedStatement.close();
            conn.close();

        }catch(Exception e){
            System.out.println("Database connexion failed!");
        }


        return user;
    }
    public static void main(String[] args) {
        

        LoginForm loginForm = new LoginForm();
        loginForm.initialize();
    }
}
class User {
    public String UserId;
    public String train_no;
    public String train_name;
    public String classtype;
    public String date;
    public String from_station;
    public String destination;
    public String pnr;
    public String password;

    public User setAuthenticatedUser(String UserId, String password,String train_no,String train_name,String classtype,String date,String from_station,String destination,String pnr) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/user";
        final String USERNAME = "sqluser";
        final String PASSWORD = "password";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String sql = "INSERT INTO reservations(loginid, train_no, train_name, classtype, date, from_station, destination, pnr, password) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, UserId);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.UserId = resultSet.getString("loginid");
                user.train_no = resultSet.getString("train_no");
                user.train_name = resultSet.getString("train_name");
                user.classtype = resultSet.getString("classtype");
                user.date = resultSet.getString("date");
                user.from_station = resultSet.getString("from_station");
                user.destination = resultSet.getString("destination");
                user.pnr = resultSet.getString("pnr");

                user.password = resultSet.getString("password");
            }

            preparedStatement.close();
            conn.close();

        }catch(Exception e){
            System.out.println("Database connexion failed!");
        }


        return user;
    }
    public User delAuthenticatedUser(String UserId, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/user";
        final String USERNAME = "sqluser";
        final String PASSWORD = "password";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String sql = "SELECT * FROM reservations WHERE loginid=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, UserId);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.UserId = resultSet.getString("loginid");
                user.train_no = resultSet.getString("train_no");
                user.train_name = resultSet.getString("train_name");
                user.classtype = resultSet.getString("classtype");
                user.date = resultSet.getString("date");
                user.from_station = resultSet.getString("from_station");
                user.destination = resultSet.getString("destination");
                user.pnr = resultSet.getString("pnr");

                user.password = resultSet.getString("password");
            }

            preparedStatement.close();
            conn.close();

        }catch(Exception e){
            System.out.println("Database connexion failed!");
        }


        return user;
    }
}
class MainFrame extends JFrame {
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    public void initialize(User user) {
    
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 2, 5, 5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        
        JLabel pnrl = new JLabel("Enter PNR number: ");
        pnrl.setFont(mainFont);

        JTextField pnr = new JTextField();
        pnr.setFont(mainFont);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 30, 600));
        formPanel.add(pnrl);
        formPanel.add(pnr);
        formPanel.setVisible(true);

        JButton Enter = new JButton("Enter");
        Enter.setFont(mainFont);

        JPanel stats=new JPanel();
        stats.setLayout(new GridLayout(0, 1, 10, 10));
        stats.setBorder(BorderFactory.createEmptyBorder(60, 50, 90, 50));

        stats.add(new JLabel("Train Name: "));
        stats.add(new JLabel("                              "+user.train_name));

        stats.add(new JLabel("Class type: "));
        stats.add(new JLabel("                              "+user.classtype));

        stats.add(new JLabel("Date: "));
        stats.add(new JLabel("                              "+user.date));

        stats.add(new JLabel("Desitnation: "));
        stats.add(new JLabel("                              "+user.destination));

        add(stats, BorderLayout.LINE_END);
        stats.setVisible(false);
       
        add(infoPanel, BorderLayout.NORTH);

        Component[] labels = stats.getComponents();
        for (int i = 0; i < labels.length; i++) {
            labels[i].setFont(new Font("Segoe print", Font.BOLD, 18));
        }
        JButton Cancel = new JButton("Cancel Train Booking");
        Cancel.setFont(mainFont);
        Cancel.setVisible(false);
        Cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String usr=user.UserId;
                String pass=user.password;
                User user=delAuthenticatedUser(usr,pass);
                JOptionPane.showMessageDialog(MainFrame.this,
                            "Your journey has been Cancelled",
                            "Successful",
                            JOptionPane.PLAIN_MESSAGE);
                dispose();
            }
            
        });

        Enter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                formPanel.setVisible(false);
                stats.setVisible(true);
                Cancel.setVisible(true);
                Enter.setVisible(false);
            }
            
        });



        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 100, 50));
        buttonsPanel.add(Enter);
        buttonsPanel.add(Cancel);

        setTitle("Dashboard");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setVisible(true);
        add(formPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);
        

        



    }
    private User delAuthenticatedUser(String UserId, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/user";
        final String USERNAME = "sqluser";
        final String PASSWORD = "password";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String sql = "DELETE FROM reservations WHERE loginid=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, UserId);
            preparedStatement.setString(2, password);

            preparedStatement.executeLargeUpdate();

            preparedStatement.close();
            conn.close();

        }catch(Exception e){
            System.out.println("Database connexion failed!");
            e.printStackTrace();
        }


        return user;
    }
}
class Modesel extends JFrame{
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    public void initialize(String email, String password)
    {
        JButton btnDetails = new JButton("Cancellation Form");
        btnDetails.setFont(mainFont);

        JButton Booking = new JButton("Train Booking");
        Booking.setFont(mainFont);
        
        JLabel jl=new JLabel("Select operation: ");

        User user = getAuthenticatedUser(email, password);
        btnDetails.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                    mainFrame.initialize(user);
                    dispose();
            }
            
        });

        Booking.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Tbooking tb=new Tbooking();
                tb.initialize();
               dispose();
            }
            
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(330, 50, 330, 50));
        buttonsPanel.add(btnDetails,BorderLayout.CENTER);
        buttonsPanel.add(Booking,BorderLayout.CENTER);
        

        JPanel sel = new JPanel();
        sel.setLayout(new GridLayout(0, 1, 10, 10));
        sel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        add(sel, BorderLayout.NORTH);
        add(jl,BorderLayout.NORTH);
        
        add(buttonsPanel, BorderLayout.CENTER);
        setTitle("Login Form");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setMinimumSize(new Dimension(350, 450));
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private User getAuthenticatedUser(String UserId, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/user";
        final String USERNAME = "sqluser";
        final String PASSWORD = "password";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String sql = "SELECT * FROM reservations WHERE loginid=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, UserId);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.UserId = resultSet.getString("loginid");
                user.train_no = resultSet.getString("train_no");
                user.train_name = resultSet.getString("train_name");
                user.classtype = resultSet.getString("classtype");
                user.date = resultSet.getString("date");
                user.from_station = resultSet.getString("from_station");
                user.destination = resultSet.getString("destination");
                user.pnr = resultSet.getString("pnr");

                user.password = resultSet.getString("password");
            }

            preparedStatement.close();
            conn.close();

        }catch(Exception e){
            System.out.println("Database connexion failed!");
        }


        return user;
    }
    
    
}

class Tbooking extends JFrame{
    final private Font mainFont = new Font("Segoe print", Font.BOLD, 12);
    JTextField UserId,password,train_no, train_name, classtype,date, from_station, destination; 
    int pnr;
    public void initialize(){
        JPanel form=new JPanel();
        pnr=1109856;
        JLabel lbUserId = new JLabel("Login ID");
        lbUserId.setFont(mainFont);

        UserId = new JTextField();
        UserId.setFont(mainFont);

        JLabel lbpass = new JLabel("Password");
        lbpass.setFont(mainFont);

        password = new JTextField();
        password.setFont(mainFont);

        JLabel lbtno = new JLabel("Train no");
        lbtno.setFont(mainFont);

        train_no = new JTextField();
        train_no.setFont(mainFont);

        JLabel lbtname = new JLabel("Train name");
        lbtname.setFont(mainFont);

        train_name = new JTextField();
        train_name.setFont(mainFont);

        JLabel lbct = new JLabel("Class Type");
        lbct.setFont(mainFont);

        classtype = new JTextField();
        classtype.setFont(mainFont);

        JLabel lbdate = new JLabel("Date(YYYY-MM-DD)");
        lbdate.setFont(mainFont);

        date = new JTextField();
        date.setFont(mainFont);

        JLabel lbfrom = new JLabel("From");
        lbfrom.setFont(mainFont);

        from_station = new JTextField();
        from_station.setFont(mainFont);

        JLabel lbdst = new JLabel("To");
        lbdst.setFont(mainFont);

        destination = new JTextField();
        destination.setFont(mainFont);

        JButton Book=new JButton("Confirm Booking");
        Book.setFont(mainFont);
        Book.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String loginid=UserId.getText();
                int a=Integer.parseInt(loginid);
                String pass=password.getText();
                String tno=train_no.getText();
                int b=Integer.parseInt(tno);
                String tname=train_name.getText();
                String ctype=classtype.getText();
                String dte=date.getText();
                String from=from_station.getText();
                String dest=destination.getText();
                User user=setAuthenticatedUser(a,pass,b,tname,ctype,dte,from,dest,pnr);
                JOptionPane.showMessageDialog(Tbooking.this,
                "Congratulations! Ticket has been booked!! Note Your PNR: "+pnr,
                "Successful",
                JOptionPane.PLAIN_MESSAGE);
                dispose();
            }
            
        });

        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        formPanel.add(lbUserId);
        formPanel.add(UserId);
        formPanel.add(lbpass);
        formPanel.add(password);
        formPanel.add(lbct);
        formPanel.add(classtype);
        formPanel.add(lbdate);
        formPanel.add(date);
        formPanel.add(lbdst);
        formPanel.add(destination);
        formPanel.add(lbfrom);
        formPanel.add(from_station);
        formPanel.add(lbtname);
        formPanel.add(train_name);
        formPanel.add(lbtno);
        formPanel.add(train_no);
        formPanel.add(Book);
        

        add(formPanel, BorderLayout.NORTH);
        setTitle("Login Form");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1000, 1000);
        setMinimumSize(new Dimension(350, 450));
        setLocationRelativeTo(null);
        setVisible(true);
        
    }


    private User setAuthenticatedUser(int UserId, String password,int train_no,String train_name,String classtype,String date,String from_station,String destination,int pnr) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/user";
        final String USERNAME = "sqluser";
        final String PASSWORD = "password";

        try{
            
            Connection conn1 = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            String sql = "INSERT INTO reservations(loginid, train_no, train_name, classtype, date, from_station, destination, pnr, password) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn1.prepareStatement(sql);
            preparedStatement.setInt(1, UserId);
            preparedStatement.setInt(2, train_no);
            preparedStatement.setString(3, train_name);
            preparedStatement.setString(4, classtype);
            preparedStatement.setString(5, date);
            preparedStatement.setString(6, from_station);
            preparedStatement.setString(7, destination);
            preparedStatement.setInt(8, pnr);
            preparedStatement.setString(9, password);

            preparedStatement.executeLargeUpdate();
            preparedStatement.close();
            conn1.close();

        }catch(Exception e){
            System.out.println("Database connexion failed!");
            e.printStackTrace();
        }


        return user;
    }
}