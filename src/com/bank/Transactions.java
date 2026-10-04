package com.bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.rowset.JdbcRowSetConn;

public class Transactions extends JFrame implements ActionListener {
	// fields
	private JLabel lblTransaction;
	private JButton btnDeposit, btnWithdraw, btnFastCash, btnMiniStatement, btnPinChange, btnBalanceCheck, btnExit;
	private String pin;

	public Transactions(String pin) {
		this.pin = pin;
		setLayout(null);

		// Background image
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
		Image scaledImage = i1.getImage().getScaledInstance(900, 790, Image.SCALE_DEFAULT);
		ImageIcon i2 = new ImageIcon(scaledImage);
		JLabel lblImage = new JLabel(i2);
		lblImage.setBounds(0, 0, 900, 790);
		add(lblImage);

		lblTransaction = new JLabel("Please Select Transaction");
		lblTransaction.setFont(new Font("Raleway", Font.BOLD, 15));
		lblTransaction.setForeground(Color.white);
		lblTransaction.setBackground(Color.black);
		lblTransaction.setBounds(240, 275, 400, 30);
		lblImage.add(lblTransaction); // add it onto the lblImage

		// Button styling configuration as requested (White background, Black text; Exit button Red)
		Font btnFont = new Font("Segoe UI", Font.BOLD, 11);
		Color actionBtnBg = Color.WHITE;
		Color actionBtnFg = Color.BLACK;
		Color exitBtnBg = new Color(225, 29, 72); // Red
		Color exitBtnFg = Color.WHITE;

		btnDeposit = new JButton("DEPOSIT");
		btnDeposit.setFont(btnFont);
		btnDeposit.setBackground(actionBtnBg);
		btnDeposit.setForeground(actionBtnFg);
		btnDeposit.setFocusPainted(false);
		btnDeposit.setBounds(170, 330, 110, 25);
		lblImage.add(btnDeposit);
		btnDeposit.addActionListener(this);

		btnWithdraw = new JButton("WITHDRAWAL");
		btnWithdraw.setFont(btnFont);
		btnWithdraw.setBackground(actionBtnBg);
		btnWithdraw.setForeground(actionBtnFg);
		btnWithdraw.setFocusPainted(false);
		btnWithdraw.setBounds(350, 330, 140, 25);
		lblImage.add(btnWithdraw);
		btnWithdraw.addActionListener(this);

		btnFastCash = new JButton("FAST CASH");
		btnFastCash.setFont(btnFont);
		btnFastCash.setBackground(actionBtnBg);
		btnFastCash.setForeground(actionBtnFg);
		btnFastCash.setFocusPainted(false);
		btnFastCash.setBounds(170, 380, 110, 25);
		lblImage.add(btnFastCash);
		btnFastCash.addActionListener(this);

		btnMiniStatement = new JButton("MINI STATEMENT");
		btnMiniStatement.setFont(new Font("Segoe UI", Font.BOLD, 10)); // Sized down slightly for text fit
		btnMiniStatement.setBackground(actionBtnBg);
		btnMiniStatement.setForeground(actionBtnFg);
		btnMiniStatement.setFocusPainted(false);
		btnMiniStatement.setBounds(350, 380, 140, 25);
		lblImage.add(btnMiniStatement);
		btnMiniStatement.addActionListener(this);

		btnPinChange = new JButton("PIN CHANGE");
		btnPinChange.setFont(btnFont);
		btnPinChange.setBackground(actionBtnBg);
		btnPinChange.setForeground(actionBtnFg);
		btnPinChange.setFocusPainted(false);
		btnPinChange.setBounds(170, 430, 110, 25);
		lblImage.add(btnPinChange);
		btnPinChange.addActionListener(this);

		btnBalanceCheck = new JButton("BALANCE CHECK");
		btnBalanceCheck.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnBalanceCheck.setBackground(actionBtnBg);
		btnBalanceCheck.setForeground(actionBtnFg);
		btnBalanceCheck.setFocusPainted(false);
		btnBalanceCheck.setBounds(350, 430, 140, 25);
		lblImage.add(btnBalanceCheck);
		btnBalanceCheck.addActionListener(this);

		btnExit = new JButton("EXIT");
		btnExit.setFont(btnFont);
		btnExit.setBackground(exitBtnBg);
		btnExit.setForeground(exitBtnFg);
		btnExit.setFocusPainted(false);
		btnExit.setBounds(370, 480, 100, 25);
		lblImage.add(btnExit);
		btnExit.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource().equals(btnDeposit)) {
			this.setVisible(false);
			Deposit obj = new Deposit(pin);
			obj.setTitle("Deposit");
			obj.setSize(900, 1000);
			obj.setLocation(200, 0);
			obj.getContentPane().setBackground(Color.WHITE);
			obj.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
			obj.setUndecorated(true);
			obj.setVisible(true);
		} else if (ae.getSource().equals(btnExit)) {
			this.setVisible(false);
			Login obj = new Login();
			obj.setTitle("Login");
			obj.setSize(750, 500);
			obj.setLocation(300, 50);
			obj.getContentPane().setBackground(Color.WHITE);
			obj.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
			obj.setVisible(true);
		} else if (ae.getSource().equals(btnWithdraw)) {
			this.setVisible(false);
			Withdrawal obj = new Withdrawal(pin);
			obj.setTitle("WITHDRAWAL");
			obj.setSize(900, 1000);
			obj.setLocation(200, 5);
			obj.getContentPane().setBackground(Color.WHITE);
			obj.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
			obj.setUndecorated(true);
			obj.setVisible(true);
		} else if (ae.getSource().equals(btnBalanceCheck)) {
			JdbcRowSet jr = null;
			try {
				Double currentBalance = 0.0;
				jr = JdbcRowSetConn.getJdbcRowSet();
				jr.setAutoCommit(false);
				jr.setCommand("Select balance from transactions where pin =? ORDER BY date DESC limit 1");
				jr.setString(1, pin);
				jr.execute();
				jr.commit();
				if (jr.next()) {
					currentBalance = jr.getDouble("balance");
					JOptionPane.showMessageDialog(null, "Current Balance is " + currentBalance + " Rs");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (jr != null) {
						jr.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else if (ae.getSource().equals(btnFastCash)) {
			this.setVisible(false);
			FastCash obj = new FastCash(pin);
			obj.setTitle("Transactions");
			obj.setSize(900, 1000);
			obj.setLocation(200, 5);
			obj.setUndecorated(true);
			obj.getContentPane().setBackground(Color.WHITE);
			obj.setVisible(true);
		} else if (ae.getSource().equals(btnMiniStatement)) {
			JdbcRowSet jr = null;
			try {
				jr = JdbcRowSetConn.getJdbcRowSet();
				jr.setCommand("Select * from transactions where pin = ? ORDER BY date DESC LIMIT 5");
				jr.setString(1, pin);
				jr.execute();

				StringBuilder sbObj = new StringBuilder();
				sbObj.append("=== SMART BANK MINI STATEMENT ===\n\n");

				boolean hasRecords = false;
				while (jr.next()) {
					hasRecords = true;
					String date = jr.getString("date");
					String type = jr.getString("type");
					String amount = jr.getString("amount");
					double balance = jr.getDouble("balance");

					sbObj.append("Date   : ").append(date).append("\n")
					   .append("Type   : ").append(type).append("\n")
					   .append("Amount : Rs. ").append(amount).append("\n")
					   .append("Balance: Rs. ").append(balance).append("\n")
					   .append("--------------------------------------------------\n");
				}

				if (!hasRecords) {
					sbObj.append("No recent transactions found.");
				}

				JOptionPane.showMessageDialog(null, sbObj.toString());

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error fetching mini statement: " + e.getMessage());
			} finally {
				try {
					if (jr != null) {
						jr.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else if (ae.getSource().equals(btnPinChange)) {
			this.setVisible(false);
			PinChange obj = new PinChange(pin);
			obj.setTitle("PIN Change");
			obj.setSize(900, 1000);
			obj.setLocation(200, 0);
			obj.getContentPane().setBackground(Color.WHITE);
			obj.setUndecorated(true);
			obj.setVisible(true);
		}
	}
}