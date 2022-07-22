package BankingManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpPage extends JFrame implements ActionListener {
    JFrame frame;
    JComboBox comboBox;
    JTextField IDNumber;
    JTextField ACNumber;
    JTextField EmailID;
    JTextField PhoneNumber;
    JTextField DateOfBirth;
    JTextField userTextField;
    JPasswordField passwordField;
    JButton RegisterButton;
    JButton BackButton;

    // connect the database
    Connection con;
    PreparedStatement pst;

    public void connect(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/abc_bank", "root","Mani@11235813");
            System.out.println("SuccessFully connected to MySql in signup page");
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public SignUpPage() throws HeadlessException {
        connect();
        // call the connector
        frame = new JFrame("SignUp");
        frame.setSize(750,800);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0, 102, 102));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // add heading
        JLabel title = new JLabel("New Registration Form");
        title.setFont(new Font("Times New Roman",Font.BOLD,30));
        title.setBounds(200,5,600,40);
        frame.add(title);

        JLabel Idlabel = new JLabel("ID Type");
        Idlabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        Idlabel.setBounds(100,60,150,30);
        frame.add(Idlabel);

        // comboboxes ffor id type
        String [] idtype = {"Adhaar Card", "Pan Card", "Voter ID", "Driving Licence"};
        comboBox = new JComboBox(idtype);
        comboBox.setBounds(350,60,300,40);
        comboBox.setFont(new Font("Times New Roman",Font.PLAIN,23));
        comboBox.addActionListener(this);
        frame.add(comboBox);
        comboBox.setEditable(true);

        //selected ID Number label and textfield
        JLabel IdNumber = new JLabel("Selected ID Number");
        IdNumber.setFont(new Font("Times New Roman",Font.PLAIN,25));
        IdNumber.setBounds(100,120,220,30);
        frame.add(IdNumber);

        IDNumber = new JTextField();
        IDNumber.setBounds(350,120,300,40);
        IDNumber.setFont(new Font("Times New Roman",Font.PLAIN,30));
        frame.add(IDNumber);

        // Account number label and textfield
        JLabel AccountNumberLabel = new JLabel("Account Number");
        AccountNumberLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        AccountNumberLabel.setBounds(100,180,220,30);
        frame.add(AccountNumberLabel);

        ACNumber = new JTextField();
        ACNumber.setBounds(350,180,300,40);
        ACNumber.setFont(new Font("Times New Roman",Font.PLAIN,30));
        frame.add(ACNumber);

        //Email Id label and textfield
        JLabel EmailIDLabel = new JLabel("EMail ID");
        EmailIDLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        EmailIDLabel.setBounds(100,240,220,30);
        frame.add(EmailIDLabel);

        EmailID = new JTextField();
        EmailID.setBounds(350,240,300,40);
        EmailID.setFont(new Font("Times New Roman",Font.PLAIN,30));
        frame.add(EmailID);

        //phone number label and textfield
        JLabel PhoneNumberLabel = new JLabel("Phone Number");
        PhoneNumberLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        PhoneNumberLabel.setBounds(100,300,220,30);
        frame.add(PhoneNumberLabel);

        PhoneNumber = new JTextField();
        PhoneNumber.setBounds(350,300,300,40);
        PhoneNumber.setFont(new Font("Times New Roman",Font.PLAIN,30));
        frame.add(PhoneNumber);

        //DateOfBirth label and textfield
        JLabel DOBLabel = new JLabel("Date-Of-Birth");
        DOBLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        DOBLabel.setBounds(100,360,220,30);
        frame.add(DOBLabel);

        DateOfBirth = new JTextField();
        DateOfBirth.setBounds(350,360,300,40);
        DateOfBirth.setFont(new Font("Times New Roman",Font.PLAIN,30));
        frame.add(DateOfBirth);

        //userName label and textField
        JLabel UserNameLabel = new JLabel("UserName");
        UserNameLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        UserNameLabel.setBounds(100,420,220,30);
        frame.add(UserNameLabel);

        userTextField = new JTextField();
        userTextField.setBounds(350,420,300,40);
        userTextField.setFont(new Font("Times New Roman",Font.PLAIN,30));
        frame.add(userTextField);

        // password field and label for passwords
        JLabel password = new JLabel("Password");
        password.setFont(new Font("Times New Roman",Font.PLAIN,30));
        password.setBounds(100,480,150,30);
        frame.add(password);

        passwordField = new JPasswordField();
        passwordField.setBounds(350,480,300,40);
        passwordField.setFont(new Font("Times New Roman",Font.BOLD,30));
        passwordField.addActionListener(this);
        frame.add(passwordField);

        //back and registration button
        BackButton = new JButton("Back to Login");
        BackButton.setFont(new Font("Times New Roman",Font.BOLD,30));
        BackButton.setBounds(100,540,250,50);
        BackButton.setFocusable(false);
        BackButton.addActionListener(this);
        frame.add(BackButton);

        RegisterButton = new JButton("Register");
        RegisterButton.setFont(new Font("Times New Roman",Font.BOLD,30));
        RegisterButton.setBounds(400,540,150,50);
        RegisterButton.setFocusable(false);
        RegisterButton.addActionListener(this);
        frame.add(RegisterButton);






        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == BackButton){
            frame.dispose();
            new LogInPage();
        }else if(e.getSource() == RegisterButton){
            String idtype, selectedIDNumber, AccountNumber, EmailId, phoneNumber, DateofBirth,userName,password;
                    idtype = (String)comboBox.getSelectedItem();
                    selectedIDNumber = IDNumber.getText();
                    AccountNumber = ACNumber.getText();
                    EmailId = EmailID.getText();
                    phoneNumber = PhoneNumber.getText();
                    DateofBirth = DateOfBirth.getText();
                    userName = userTextField.getText();
                    password = passwordField.getText();

                    try{
                        if(selectedIDNumber.length() == 0 || password.length() == 0 || userName.length() == 0
                        || EmailId.length() == 0 || AccountNumber.length() == 0){
                            JOptionPane.showMessageDialog(null,"TextFields should be non empty");
                        }
                        else if(DateofBirth.length()!=10){
                            JOptionPane.showMessageDialog(null,"Date Format should be 01/02/0304");
                        }else if(phoneNumber.length() != 10){
                            JOptionPane.showMessageDialog(null,"phoneNumber digits should be ten");
                        }
                        else{
                            String query = "insert into accountcreated values (?,?,?,?,?,?,?,?)";
                            pst = con.prepareStatement(query);
                            pst.setString(1,idtype);
                            pst.setString(2,selectedIDNumber);
                            pst.setString(3,AccountNumber);
                            pst.setString(4,EmailId);
                            pst.setString(5,phoneNumber);
                            pst.setString(6,DateofBirth);
                            pst.setString(7,userName);
                            pst.setString(8,password);
                            pst.executeUpdate();

                            // adding account number and password to the check balance table
                            query = "insert into checkBalance values (?,?,?)";
                            pst = con.prepareStatement(query);
                            pst.setString(1,AccountNumber);
                            pst.setString(2,password);
                            pst.setString(3,"1000");
                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Account Created!!");

                            IDNumber.setText("");
                            ACNumber.setText("");
                            EmailID.setText("");
                            PhoneNumber.setText("");
                            DateOfBirth.setText("");
                            userTextField.setText("");
                            passwordField.setText("");

                            pst.close();
                            con.close();
                        }
                    }catch (SQLException e1){
                        e1.printStackTrace();
                    }
        }
    }
}
