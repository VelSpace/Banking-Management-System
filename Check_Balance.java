package BankingManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Check_Balance extends JFrame implements ActionListener {
    JFrame frame;
    JTextField password_Field;
    JTextField display_field;
    JTextField ACNumber;
    JButton confirm;
    JButton exit;
    JButton Back;

    // connect the database
    static Connection con;
    static PreparedStatement pst;

    public void connect(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/abc_bank", "root","Mani@11235813");
            System.out.println("SuccessFully connected to MySql");
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    Check_Balance(){
      frame = new JFrame("Balance");
      frame.setSize(600,500);
      frame.setLayout(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setBackground(Color.lightGray);
      frame.setVisible(true);

        JLabel title = new JLabel("Balance");
        title.setFont(new Font("Times New Roman",Font.BOLD,40));
        title.setBounds(200,10,600,50);
        title.setForeground(Color.BLACK);
        frame.add(title);

        // Account number label and textfield
        JLabel AccountNumberLabel = new JLabel("Account Number");
        AccountNumberLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        AccountNumberLabel.setBounds(50,100,220,30);
        frame.add(AccountNumberLabel);

        ACNumber = new JTextField();
        ACNumber.setBounds(250,100,280,40);
        ACNumber.setFont(new Font("Times New Roman",Font.PLAIN,30));
        frame.add(ACNumber);

        JLabel password = new JLabel("Password");
        password.setFont(new Font("Times New Roman",Font.PLAIN,30));
        password.setBounds(50, 150,150,30);
        frame.add(password);

        password_Field = new JPasswordField();
        password_Field.setBounds(250,150,280,40);
        password_Field.setFont(new Font("Times New Roman",Font.BOLD,30));
        password_Field.setOpaque(true);
        frame.add(password_Field);

        confirm = new JButton("Confirm");
        confirm.setFont(new Font("Times New Roman",Font.BOLD,30));
        confirm.setBounds(250,200,150,50);
        confirm.setFocusable(false);
        confirm.addActionListener(this);
        frame.add(confirm);

        display_field = new JTextField();
        display_field.setBounds(100,280,350,40);
        display_field.setFont(new Font("Times New Roman",Font.PLAIN,30));
        frame.add(display_field);


        // back button
        Back = new JButton("GoBack");
        Back.setFont(new Font("Times New Roman",Font.PLAIN,30));
        Back.setBounds(100,350,150,35);
        Back.setFocusable(false);
        Back.addActionListener(this);
        Back.setForeground(Color.RED);
        frame.add(Back);

        //exit button
        exit = new JButton("Exit");
        exit.setFont(new Font("Times New Roman",Font.PLAIN,30));
        exit.setBounds(400,350,150,35);
        exit.setFocusable(false);
        exit.addActionListener(this);
        exit.setForeground(Color.RED);
        frame.add(exit);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit){
            System.exit(1);
        }else if(e.getSource() == Back){
            DashBoard board = new DashBoard();
        }else if(e.getSource() == confirm){
            String accountNumber = ACNumber.getText();
            String Password = password_Field.getText();
            try {
                // from login page
                String amount = getAuth(accountNumber,Password);
                if(amount != null){
                double ans = Double.parseDouble(amount);
                display_field.setText(String.valueOf(ans));
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
    public static String getAuth(String accountNumber, String Password) throws SQLException {
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/abc_bank", "root","Mani@11235813");
            System.out.println("SuccessFully connected to MySql");
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        String query = "SELECT * FROM checkBalance where Account_Number='"+accountNumber+"' and Password='"+Password+"'";
        pst = con.prepareStatement(query);
        ResultSet x=pst.executeQuery();
        if(x.next()){
            JOptionPane.showMessageDialog(null, "Authentication Successfull");
        }else{
            return "";
        }
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost/abc_bank", "root","Mani@11235813");
        query = "select amount from checkBalance where Account_Number='"+accountNumber+"'";
        Statement sta=connection.createStatement();
        x=sta.executeQuery(query);
        System.out.println(x.next());
        String balance = x.getString("Amount");
        System.out.println("hello "+balance);
        return balance;
    }
}
