package BankingManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashBoard extends JFrame implements ActionListener {
    JFrame frame = new JFrame("DASHBOARD");
    JButton Balance;
    JButton Payment_transfer;
    JButton Pay_Bills;
    JButton Deposit;
    JButton Delete_Account;
    JButton Back;
    JButton exit;
  DashBoard(){
      frame.setSize(400,500);
      frame.setLayout(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setBackground(Color.lightGray);
      frame.setVisible(true);

      JLabel title = new JLabel("DashBoard");
      title.setFont(new Font("Times New Roman",Font.BOLD,30));
      title.setBounds(120,10,600,30);
      title.setForeground(Color.BLACK);
      frame.add(title);
      // balance button
      Balance = new JButton("Check Balance");
      Balance.setFont(new Font("Times New Roman",Font.PLAIN,30));
      Balance.setBounds(70,70,250,50);
      Balance.setFocusable(false);
      Balance.addActionListener(this);
      Balance.setBackground(Color.DARK_GRAY);
      Balance.setForeground(Color.BLUE);
      frame.add(Balance);

      // payment transfer
      Payment_transfer = new JButton("Payment Transfer");
      Payment_transfer.setFont(new Font("Times New Roman",Font.PLAIN,30));
      Payment_transfer.setBounds(70,140,250,50);
      Payment_transfer.setFocusable(false);
      Payment_transfer.addActionListener(this);
      Payment_transfer.setBackground(Color.darkGray);
      Payment_transfer.setForeground(Color.BLUE);
      frame.add(Payment_transfer);

      Pay_Bills = new JButton("Pay Bills");
      Pay_Bills.setFont(new Font("Times New Roman",Font.PLAIN,30));
      Pay_Bills.setBounds(70,210,250,50);
      Pay_Bills.setFocusable(false);
      Pay_Bills.addActionListener(this);
      Pay_Bills.setBackground(Color.darkGray);
      Pay_Bills.setForeground(Color.BLUE);
      frame.add(Pay_Bills);


      // button for history
      Deposit = new JButton("Deposit&withdrawl");
      Deposit.setFont(new Font("Times New Roman",Font.PLAIN,27));
      Deposit.setBounds(70,280,250,50);
      Deposit.setFocusable(false);
      Deposit.addActionListener(this);
      Deposit.setBackground(Color.darkGray);
      Deposit.setForeground(Color.BLUE);
      frame.add(Deposit);

      //Delete Account
      Delete_Account = new JButton("Delete Account");
      Delete_Account.setFont(new Font("Times New Roman",Font.PLAIN,30));
      Delete_Account.setBounds(70,350,250,50);
      Delete_Account.setFocusable(false);
      Delete_Account.addActionListener(this);
      Delete_Account.setBackground(Color.darkGray);
      Delete_Account.setForeground(Color.BLUE);
      frame.add(Delete_Account);

      // back button
      Back = new JButton("GoBack");
      Back.setFont(new Font("Times New Roman",Font.PLAIN,20));
      Back.setBounds(5,5,100,30);
      Back.setFocusable(false);
      Back.addActionListener(this);
      Back.setBackground(Color.darkGray);
      Back.setForeground(Color.RED);
      frame.add(Back);

      //exit button
      exit = new JButton("Exit");
      exit.setFont(new Font("Times New Roman",Font.PLAIN,20));
      exit.setBounds(280,5,100,30);
      exit.setFocusable(false);
      exit.addActionListener(this);
      exit.setBackground(Color.darkGray);
      exit.setForeground(Color.RED);
      frame.add(exit);
  }

    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == Deposit){
          frame.dispose();
          Deposit_withdrawl newAmount = new Deposit_withdrawl();
      }
      else if(e.getSource() == Back){
          frame.dispose();
          LogInPage MainPage = new LogInPage();
      }else if(e.getSource() == exit){
          frame.dispose();
      }else if(e.getSource() == Balance){
          Check_Balance balance = new Check_Balance();
      }else if(e.getSource() == Delete_Account){
          Accout_Delete delete = new Accout_Delete();
      }
    }
}
