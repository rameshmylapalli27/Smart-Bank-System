package com.bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.dbconnection.DBConnection;
import com.rowset.JdbcRowSetConn;

public class Deposit extends JFrame implements ActionListener {
	// fields
	private JLabel lblDeposit, lblImage;
	private JTextField tfDepositAmount;
	private JButton btnDeposit, btnBack;
	private String pin;

	public Deposit(String pin) {
		this.pin = pin;
		setLayout(null);

		// Background image setup
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
		Image scaledImage = i1.getImage().getScaledInstance(900, 790, Image.SCALE_DEFAULT);
		ImageIcon i2 = new ImageIcon(scaledImage);
		lblImage = new JLabel(i2);
		lblImage.setBounds(0, 0, 900, 790);
		add(lblImage);

		lblDeposit = new JLabel("Enter amount you want to deposit");
		lblDeposit.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblDeposit.setForeground(Color.white);
		lblDeposit.setBounds(210, 275, 400, 30);
		lblImage.add(lblDeposit);

		tfDepositAmount = new JTextField();
		tfDepositAmount.setFont(new Font("Segoe UI", Font.BOLD, 15));
		tfDepositAmount.setBounds(210, 310, 250, 25);
		lblImage.add(tfDepositAmount);

		// Styling of frame
		Font btnFont = new Font("Segoe UI", Font.BOLD, 11);
		Color actionBtnBg = Color.WHITE;
		Color actionBtnFg = Color.BLACK;
		Color backBtnBg = new Color(225, 29, 72); // Red
		Color backBtnFg = Color.WHITE;

		btnDeposit = new JButton("DEPOSIT");
		btnDeposit.setFont(btnFont);
		btnDeposit.setBackground(actionBtnBg);
		btnDeposit.setForeground(actionBtnFg);
		btnDeposit.setFocusPainted(false);
		btnDeposit.setBounds(350, 430, 140, 27);
		lblImage.add(btnDeposit);
		btnDeposit.addActionListener(this);

		btnBack = new JButton("BACK");
		btnBack.setFont(btnFont);
		btnBack.setBackground(backBtnBg);
		btnBack.setForeground(backBtnFg);
		btnBack.setFocusPainted(false);
		btnBack.setBounds(350, 480, 140, 27);
		lblImage.add(btnBack);
		btnBack.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		String amount = tfDepositAmount.getText().trim();
		Date dat = new Date();
		java.sql.Timestamp dateTime = new java.sql.Timestamp(dat.getTime());
		String type = "DEPOSIT";

		if (ae.getSource().equals(btnDeposit)) {
			try {
				// Basic validation for empty or invalid input
				if (amount.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a valid deposit amount.");
					return;
				}

				Double depositAmount = Double.parseDouble(amount);
				Double currentBalance = 0.0;

				JdbcRowSet jr = JdbcRowSetConn.getJdbcRowSet();
				jr.setCommand("Select balance from transactions where pin =? ORDER BY date DESC limit 1");
				jr.setString(1, pin);
				jr.execute();
				if (jr.next()) {
					currentBalance = jr.getDouble("balance");
				}
				jr.close();
				Double newBalance = currentBalance + depositAmount;

				Connection connection = DBConnection.getConnection();
				connection.setAutoCommit(false);
				String query = "INSERT into transactions values(?,?,?,?,?)";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, pin);
				statement.setTimestamp(2, dateTime);
				statement.setString(3, type);
				statement.setString(4, amount);
				statement.setDouble(5, newBalance);
				statement.executeUpdate();
				connection.commit();
				
				JOptionPane.showMessageDialog(null, "Rs. " + amount + " is Deposited Successfully");
				
				// Clear the input field after successful deposit
				tfDepositAmount.setText("");

			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Please enter a numeric value for the deposit amount.");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (ae.getSource().equals(btnBack)) {
			this.setVisible(false);
			Transactions obj = new Transactions(pin);
			obj.setTitle("Transactions");
			obj.setSize(900, 1000);
			obj.setLocation(200, 0);
			obj.getContentPane().setBackground(Color.WHITE);
			obj.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
			obj.setUndecorated(true);
			obj.setVisible(true);
		}
	}
}