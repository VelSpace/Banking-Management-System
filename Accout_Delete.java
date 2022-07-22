package BankingManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Accout_Delete extends JFrame implements ActionListener {
    JFrame frame;
    JTextField acNumber;
    JPasswordField passwordField;
    JButton confirm;

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
    Accout_Delete(){
        connect();
        frame = new JFrame("Balance");
        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.lightGray);
        frame.setVisible(true);

        //title
        // label for title
        JLabel title = new JLabel("Account Delete");
        title.setFont(new Font("Times New Roman",Font.BOLD,40));
        title.setBounds(70,20,300,30);
        title.setForeground(Color.BLUE);
        frame.add(title);

        // account number label
        JLabel aclabel = new JLabel("Account Number");
        aclabel.setFont(new Font("Times New Roman",Font.BOLD,30));
        aclabel.setBounds(80,70,300,30);
        //aclabel.setForeground(Color.BLUE);
        frame.add(aclabel);

        //account number
        acNumber = new JTextField();
        acNumber.setBounds(50,110,300,40);
        acNumber.setFont(new Font("Times New Roman",Font.PLAIN,30));
        frame.add(acNumber);

        // password label and field
        JLabel pslabel = new JLabel("Password");
        pslabel.setFont(new Font("Times New Roman",Font.BOLD,30));
        pslabel.setBounds(80,160,300,30);
        //aclabel.setForeground(Color.BLUE);
        frame.add(pslabel);

        //account number
        passwordField = new JPasswordField();
        passwordField.setBounds(50,200,300,40);
        passwordField.setFont(new Font("Times New Roman",Font.PLAIN,30));
        frame.add(passwordField);

        //confirm button
        confirm = new JButton("Confirm");
        confirm.setFont(new Font("Times New Roman",Font.BOLD,30));
        confirm.setBounds(100,260,150,50);
        confirm.setFocusable(false);
        confirm.addActionListener(this);
        frame.add(confirm);

    }

    public static void main(String[] args) {
        Accout_Delete obj = new Accout_Delete();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String acno = acNumber.getText();
        if(e.getSource() == confirm){
            try {
                pst = con.prepareStatement("delete from checkBalance where Account_Number = ?");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                pst.setString(1, acno);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                pst.executeUpdate();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                pst = con.prepareStatement("delete from accountcreated where Account_Number = ?");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                pst.setString(1, acno);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                pst.executeUpdate();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(null, "Account Deleteeeeeed!!!!!");
        }
    }
}
