package com.bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.dbconnection.DBConnection;

public class SignupThree extends JFrame implements ActionListener {
	// fields
	private JLabel lblApplicationForm, lblAccountDetails, l2, l3, l4, l5, l6, l7, l8, l9, l10;
	private JRadioButton rbnSaving, rbnFixDeposit, rbnCurrent, rbnRecurring;
	private JCheckBox cbAtm, cbInternet, cbMobile, cbEmail;
	private JButton btnSubmit, btnCancel; 
	private String formno;
	
	public SignupThree(String formno) {
        this.formno = formno;
		
		setLayout(null);

		// Header Panel (Matching Signup & SignupTwo)
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBackground(new Color(30, 58, 138));
		headerPanel.setBounds(0, 0, 700, 80);
		add(headerPanel);

		lblApplicationForm = new JLabel("Application Form :" + formno);
		lblApplicationForm.setFont(new Font("Raleway", Font.BOLD, 25));
		lblApplicationForm.setForeground(Color.WHITE);
		lblApplicationForm.setBounds(40, 15, 400, 30);
		headerPanel.add(lblApplicationForm);

		lblAccountDetails = new JLabel("Page 3: Account Details");
		lblAccountDetails.setFont(new Font("Raleway", Font.BOLD, 16));
		lblAccountDetails.setForeground(new Color(191, 219, 254));
		lblAccountDetails.setBounds(40, 45, 400, 25);
		headerPanel.add(lblAccountDetails);	

		// Updated Label Style: Segoe UI, Bold, 12px, Dark Slate color
		Font labelFont = new Font("Segoe UI", Font.BOLD, 12);
		Color labelColor = new Color(44, 62, 80);

		l2 = new JLabel("Account Type:");	
		l2.setFont(labelFont);
		l2.setForeground(labelColor);
		l2.setBounds(100, 100, 200, 30);
		add(l2);
		
		rbnSaving = new JRadioButton("Saving Account");
		rbnSaving.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbnSaving.setBackground(Color.white);
		rbnSaving.setBounds(100, 130, 200, 30);
		add(rbnSaving);
		
		rbnFixDeposit = new JRadioButton("Fixed Deposit Account");
		rbnFixDeposit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbnFixDeposit.setBackground(Color.white);
		rbnFixDeposit.setBounds(350, 130, 200, 30);
		add(rbnFixDeposit);
		
		rbnCurrent = new JRadioButton("Current Account");
		rbnCurrent.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbnCurrent.setBackground(Color.white);
		rbnCurrent.setBounds(100, 160, 200, 30);
		add(rbnCurrent);
		
		rbnRecurring = new JRadioButton("Recurring Deposit Account");
		rbnRecurring.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbnRecurring.setBackground(Color.white);
		rbnRecurring.setBounds(350, 160, 200, 30);
		add(rbnRecurring);
		
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(rbnSaving);
		bg1.add(rbnFixDeposit);
		bg1.add(rbnCurrent);
		bg1.add(rbnRecurring);

		l3 = new JLabel("Card Number:");	
		l3.setFont(labelFont);
		l3.setForeground(labelColor);
		l3.setBounds(100, 205, 200, 30);
		add(l3);	
		
		l4 = new JLabel("XXXX-XXXX-XXXX-7856");	
		l4.setFont(new Font("Segoe UI", Font.BOLD, 13));
		l4.setBounds(300, 205, 250, 30);
		add(l4);
		
		l5 = new JLabel("Your 16-Digit Card Number");	
		l5.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		l5.setForeground(Color.GRAY);
		l5.setBounds(100, 225, 200, 20);
		add(l5);
		
		l6 = new JLabel("It would appear on ATM Card/Cheque Book");	
		l6.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		l6.setForeground(Color.GRAY);
		l6.setBounds(300, 225, 300, 20);
		add(l6);
		
		l7 = new JLabel("PIN Number:");	
		l7.setFont(labelFont);
		l7.setForeground(labelColor);
		l7.setBounds(100, 265, 200, 30);
		add(l7);
		
		l8 = new JLabel("XXXX");	
		l8.setFont(new Font("Segoe UI", Font.BOLD, 13));
		l8.setBounds(300, 265, 250, 30);
		add(l8);
		
		l9 = new JLabel("Your 4-Digit Password");	
		l9.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		l9.setForeground(Color.GRAY);
		l9.setBounds(100, 285, 200, 20);
		add(l9);
		
		l10 = new JLabel("Services Required:");
		l10.setFont(labelFont);
		l10.setForeground(labelColor);
		l10.setBounds(100, 335, 200, 30);
		add(l10);
		
		cbAtm = new JCheckBox("ATM CARD");
		cbAtm.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cbAtm.setBackground(Color.white);
		cbAtm.setBounds(100, 365, 200, 30);
		add(cbAtm);
		
		cbInternet = new JCheckBox("Internet Banking");
		cbInternet.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cbInternet.setBackground(Color.white);
		cbInternet.setBounds(350, 365, 200, 30);
		add(cbInternet);
		
		cbMobile = new JCheckBox("Mobile Banking");
		cbMobile.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cbMobile.setBackground(Color.white);
		cbMobile.setBounds(100, 395, 200, 30);
		add(cbMobile);
		
		cbEmail = new JCheckBox("EMAIL ALERTS");
		cbEmail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cbEmail.setBackground(Color.white);
		cbEmail.setBounds(350, 395, 200, 30);
		add(cbEmail);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnSubmit.setBounds(200, 460, 90, 30);
		btnSubmit.setBackground(new Color(30, 58, 138));
		btnSubmit.setForeground(Color.WHITE);
		add(btnSubmit);
		btnSubmit.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCancel.setBounds(350, 460, 90, 30);
		btnCancel.setBackground(new Color(200, 200, 200));
		btnCancel.setForeground(Color.DARK_GRAY);
		add(btnCancel);
		btnCancel.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae) {
		String atype = "";
		if(rbnSaving.isSelected()) {
			atype = "Saving Account";
		} else if(rbnFixDeposit.isSelected()) {
			atype = "Fixed Deposit Account";
		} else if(rbnCurrent.isSelected()) {
			atype = "Current Account";
		} else if(rbnRecurring.isSelected()) {
			atype = "Recurring Deposit Account";
		}
		
		// Generating Random card no and pin no
		Random random = new Random();
		long number = Math.abs(random.nextLong() % 90000000L) + 5040936000000000L;
		String cardNum = String.valueOf(number);
		
		Random random2 = new Random();
		int number2 = Math.abs(random2.nextInt(1000, 9999));
		String pinNum = String.valueOf(number2);
		
		String facility = "";
		if(cbAtm.isSelected()) {
			facility += "ATM CARD ";
		}
		if(cbInternet.isSelected()) {
			facility += "Internet Banking ";
		}
		if(cbMobile.isSelected()) {
			facility += "Mobile Banking ";
		}
		if(cbEmail.isSelected()) {
			facility += "Email Alerts ";
		}
		
		if (ae.getSource().equals(btnSubmit)) {
			if (atype.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please select an Account Type.");
				return;
			}

			try {
				Connection connection = DBConnection.getConnection();
				String query1 = "INSERT into signupthree values(?,?,?,?,?)";
				String query2 = "INSERT into login values(?,?,?)";
				PreparedStatement statement1 = connection.prepareStatement(query1);
				PreparedStatement statement2 = connection.prepareStatement(query2);		

				statement1.setString(1, formno);
				statement1.setString(2, atype);
				statement1.setString(3, cardNum);
			 	statement1.setString(4, pinNum);
				statement1.setString(5, facility);
				
				statement2.setString(1, formno);
				statement2.setString(2, cardNum);
				statement2.setString(3, pinNum);

				statement1.executeUpdate();
				statement2.executeUpdate();

				JOptionPane.showMessageDialog(null, "Successfully Registered \nCard Number: " + cardNum + "\nPin Number: " + pinNum);
			
				this.setVisible(false);
				Deposit obj = new Deposit(pinNum);
				obj.setTitle("DEPOSIT");
				obj.setSize(900, 1000);
				obj.setLocation(200, 5);
				obj.getContentPane().setBackground(Color.WHITE);
				obj.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
				obj.setUndecorated(true);
				obj.setVisible(true);

			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
			}
		}
		
		if (ae.getSource().equals(btnCancel)) {   
			this.setVisible(false);
			Login obj = new Login();
			obj.setTitle("Login");
			obj.setSize(750, 500);
			obj.setLocation(300, 50);
			obj.getContentPane().setBackground(Color.WHITE);
			obj.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
			obj.setVisible(true);
		}
	}
}