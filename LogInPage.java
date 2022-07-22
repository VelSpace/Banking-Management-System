package BankingManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LogInPage extends JFrame implements ActionListener {
    JFrame frame;
    JLabel Imagelabel;
    ImageIcon image;
    JButton loginButton;
    JButton signupButton;
    JButton forgotPasswordButton;
    JButton exit;
    JTextField userTextField;
    JPasswordField passwordField;

    public LogInPage() throws HeadlessException {
        frame = new JFrame("LOGIN");
        frame.setSize(700,800);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        Imagelabel = new JLabel();
        image = new ImageIcon(getClass().getResource("bankImage.png"));
        Imagelabel.setBounds(-100,0,900,300);
        Imagelabel.setIcon(image);
        //Imagelabel.setOpaque(true);
        frame.add(Imagelabel);
        frame.setResizable(false);

        //set title for bank
        JLabel title = new JLabel("Welcome to ABC world's best BANK");
        title.setFont(new Font("Times New Roman",Font.BOLD,30));
        title.setBounds(80,310,600,30);
        frame.add(title);

        JLabel userName = new JLabel("UserName");
        userName.setFont(new Font("Times New Roman",Font.PLAIN,30));
        userName.setBounds(120,380,150,30);
        frame.add(userName);

        userTextField = new JTextField();
        userTextField.setBounds(300,375,300,40);
        userTextField.setFont(new Font("Times New Roman",Font.PLAIN,30));
        frame.add(userTextField);

        JLabel password = new JLabel("Password");
        password.setFont(new Font("Times New Roman",Font.PLAIN,30));
        password.setBounds(120,460,150,30);
        frame.add(password);

        passwordField = new JPasswordField();
        passwordField.setBounds(300,455,300,40);
        passwordField.setFont(new Font("Times New Roman",Font.BOLD,30));
        frame.add(passwordField);

        loginButton = new JButton("LogIn");
        loginButton.setFont(new Font("Times New Roman",Font.BOLD,30));
        loginButton.setBounds(150,540,150,50);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        frame.add(loginButton);

        signupButton = new JButton("SignUp");
        signupButton.setFont(new Font("Times New Roman",Font.BOLD,30));
        signupButton.setBounds(350,540,150,50);
        signupButton.setFocusable(false);
        signupButton.addActionListener(this);
        frame.add(signupButton);

        forgotPasswordButton = new JButton("ForgotPassword?");
        forgotPasswordButton.setFont(new Font("Times New Roman",Font.BOLD,27));
        forgotPasswordButton.setBounds(100,630,250,50);
        forgotPasswordButton.setFocusable(false);
        forgotPasswordButton.addActionListener(this);
        frame.add(forgotPasswordButton);

        //exit button
        exit = new JButton("Exit");
        exit.setFont(new Font("Times New Roman",Font.BOLD,30));
        exit.setBounds(380,630,150,50);
        exit.setFocusable(false);
        exit.addActionListener(this);
        frame.add(exit);
        frame.setVisible(true);



        frame.setVisible(true);
    }
    public int user = 0;
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit){
            System.exit(1);
        }
        if(e.getSource() == signupButton){
            frame.dispose();
            SignUpPage signUpPage = new SignUpPage();
        }else if(e.getSource() == loginButton){
            String User_Name = userTextField.getText();
            String Pass_word = passwordField.getText();
            // create method for authentication of details
            try {
                user = getAuth(User_Name,Pass_word);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if(user == -1){
                JOptionPane.showMessageDialog(null,"Invalid UserName And Password");
            }
            else{
                frame.dispose();
                JOptionPane.showMessageDialog(null,"LoggedIn successfully!!!");
                DashBoard dashBoard = new DashBoard();
            }
        }
    }
    Connection con;
    PreparedStatement pst;
    public int getAuth(String userName, String Password) throws SQLException {
            try{
                con = DriverManager.getConnection("jdbc:mysql://localhost/abc_bank", "root","Mani@11235813");
                System.out.println("SuccessFully connected to MySql in login page");
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        String query = "SELECT * FROM accountcreated where User_Name='"+userName+"' and Password='"+Password+"'";
        pst = con.prepareStatement(query);
        ResultSet x=pst.executeQuery();
        if(x.next()){
            userTextField.setText("");
            passwordField.setText("");
            return 1;
        }
       return -1;
    }
}

