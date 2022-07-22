package BankingManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class Deposit_withdrawl extends JFrame implements ActionListener {
    JFrame frame;
    JComboBox deposit_with;
    JTextField acNumber;
    JTextField response;
    JButton process;
    JTextField display_Balance;
    JButton Back;
    JButton exit;

    Connection con;
    PreparedStatement pst;
    public void connect(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/abc_bank", "root","Mani@11235813");
            System.out.println("SuccessFully connected to MySql");
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    Deposit_withdrawl(){

        //connect with database
       connect();

        frame = new JFrame("Deposit/Withdrawl");
        frame.setSize(500,700);
        frame.getContentPane().setBackground(new Color(3,26,50));
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // label for title
        JLabel title = new JLabel("Deposit/Withdrawal ?");
        title.setFont(new Font("Times New Roman",Font.BOLD,30));
        title.setBounds(100,30,300,30);
        title.setForeground(Color.red);
        frame.add(title);

        String [] idtype = {"Deposit", "Withdrawal"};
        deposit_with = new JComboBox(idtype);
        deposit_with.setBounds(100,80,300,40);
        deposit_with.setFont(new Font("Times New Roman",Font.PLAIN,23));
        deposit_with.addActionListener(this);
        frame.add(deposit_with);
        deposit_with.setEditable(true);

        JLabel Account_Number = new JLabel("Account Number");
        Account_Number.setFont(new Font("Times New Roman",Font.BOLD,30));
        Account_Number.setBounds(100,130,300,30);
        Account_Number.setForeground(Color.red);
        frame.add(Account_Number);

        acNumber = new JTextField();
        acNumber.setBounds(100,180,300,40);
        acNumber.setFont(new Font("Times New Roman",Font.PLAIN,30));
        frame.add(acNumber);

        JLabel Enter_Amount = new JLabel("Enter Amount");
        Enter_Amount.setFont(new Font("Times New Roman",Font.BOLD,30));
        Enter_Amount.setBounds(100,230,300,30);
        Enter_Amount.setForeground(Color.red);
        frame.add(Enter_Amount);

        response = new JTextField();
        response.setBounds(100,280,300,40);
        response.setFont(new Font("Times New Roman",Font.PLAIN,30));
        frame.add(response);

        process = new JButton("Process");
        process.setFont(new Font("Times New Roman",Font.BOLD,30));
        process.setBounds(150,330,150,50);
        process.setFocusable(false);
        process.addActionListener(this);
        frame.add(process);

        JLabel Balance_Label = new JLabel("Current Balance");
        Balance_Label.setFont(new Font("Times New Roman",Font.BOLD,30));
        Balance_Label.setBounds(100,390,300,30);
        Balance_Label.setForeground(Color.red);
        frame.add(Balance_Label);

        display_Balance = new JTextField();
        display_Balance.setBounds(100,440,300,40);
        display_Balance.setFont(new Font("Times New Roman",Font.PLAIN,30));
        frame.add(display_Balance);

        // back button
        Back = new JButton("Back");
        Back.setFont(new Font("Times New Roman",Font.PLAIN,30));
        Back.setBounds(100,510,120,35);
        Back.setFocusable(false);
        Back.addActionListener(this);
        frame.add(Back);

        //exit button
        exit = new JButton("Exit");
        exit.setFont(new Font("Times New Roman",Font.PLAIN,30));
        exit.setBounds(280,510,120,35);
        exit.setFocusable(false);
        exit.addActionListener(this);
        frame.add(exit);
        frame.setVisible(true);



        frame.setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Back){
            frame.dispose();
            DashBoard back = new DashBoard();
        }
        else if(e.getSource() == exit){
            System.exit(1);
        }else if(e.getSource() == process){
            String selected_Mode = (String) deposit_with.getSelectedItem();
            String Account_Number = acNumber.getText();
            long needed_Amount = Long.parseLong(response.getText());
            long curr_Balance;
                try {
                    String amount = getAmount(Account_Number);
                    curr_Balance = Long.parseLong(amount);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            if(Objects.equals(selected_Mode, "Deposit")){
                curr_Balance += needed_Amount;
                try {
                    add_Amount_DB(String.valueOf(curr_Balance));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null, "Amount Deposited Successfully");
            }else if(curr_Balance > needed_Amount){
                curr_Balance -= needed_Amount;
                try {
                    add_Amount_DB(String.valueOf(curr_Balance));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null, "Amount withdrawal Successfully");
            }else{
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
            }
            acNumber.setText("");
            response.setText("");
            display_Balance.setText(String.valueOf(curr_Balance));
        }
    }

    public void add_Amount_DB(String amount) throws SQLException {
        String query = "update checkBalance set Amount = ?";
        pst = con.prepareStatement(query);
        pst.setString(1,amount);
        pst.executeUpdate();
    }

    public String getAmount(String accountNumber) throws SQLException {
        String query = "select amount from checkBalance where Account_Number='"+accountNumber+"'";
        Statement sta=con.createStatement();
        ResultSet x=sta.executeQuery(query);
        System.out.println(x.next());
        String balance = x.getString("Amount");
        System.out.println("hello "+balance);
        return balance;
    }
}
