package com.bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.dbconnection.DBConnection;
import com.rowset.JdbcRowSetConn;

public class FastCash extends JFrame implements ActionListener {
	// fields
	private JLabel lblTransaction, lblImage;
	private JButton btnOneHund, btnFiveHund, btnThousand, btnTwoThou, btnFiveThou, btnTenThou, btnBack;
	private String pin;

	public FastCash(String pin) {
		this.pin = pin;
		setLayout(null);

		// Background image setup
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
		Image scaledImage = i1.getImage().getScaledInstance(900, 790, Image.SCALE_DEFAULT);
		ImageIcon i2 = new ImageIcon(scaledImage);
		lblImage = new JLabel(i2);
		lblImage.setBounds(0, 0, 900, 790);
		add(lblImage);

		lblTransaction = new JLabel("Please Select Withdrawal Amount");
		lblTransaction.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTransaction.setForeground(Color.white);
		lblTransaction.setBounds(240, 275, 400, 30);
		lblImage.add(lblTransaction);

		// Button styling configurations to match Transactions, Deposit, and Withdrawal frames
		Font btnFont = new Font("Segoe UI", Font.BOLD, 11);
		Color actionBtnBg = Color.WHITE;
		Color actionBtnFg = Color.BLACK;
		Color backBtnBg = new Color(225, 29, 72); // Red
		Color backBtnFg = Color.WHITE;

		btnOneHund = new JButton("Rs 100");
		btnOneHund.setFont(btnFont);
		btnOneHund.setBackground(actionBtnBg);
		btnOneHund.setForeground(actionBtnFg);
		btnOneHund.setFocusPainted(false);
		btnOneHund.setBounds(170, 330, 110, 25);
		lblImage.add(btnOneHund);
		btnOneHund.addActionListener(this);

		btnFiveHund = new JButton("Rs 500");
		btnFiveHund.setFont(btnFont);
		btnFiveHund.setBackground(actionBtnBg);
		btnFiveHund.setForeground(actionBtnFg);
		btnFiveHund.setFocusPainted(false);
		btnFiveHund.setBounds(350, 330, 140, 25);
		lblImage.add(btnFiveHund);
		btnFiveHund.addActionListener(this);

		btnThousand = new JButton("Rs 1000");
		btnThousand.setFont(btnFont);
		btnThousand.setBackground(actionBtnBg);
		btnThousand.setForeground(actionBtnFg);
		btnThousand.setFocusPainted(false);
		btnThousand.setBounds(170, 380, 110, 25);
		lblImage.add(btnThousand);
		btnThousand.addActionListener(this);

		btnTwoThou = new JButton("Rs 2000");
		btnTwoThou.setFont(btnFont);
		btnTwoThou.setBackground(actionBtnBg);
		btnTwoThou.setForeground(actionBtnFg);
		btnTwoThou.setFocusPainted(false);
		btnTwoThou.setBounds(350, 380, 140, 25);
		lblImage.add(btnTwoThou);
		btnTwoThou.addActionListener(this);

		btnFiveThou = new JButton("Rs 5000");
		btnFiveThou.setFont(btnFont);
		btnFiveThou.setBackground(actionBtnBg);
		btnFiveThou.setForeground(actionBtnFg);
		btnFiveThou.setFocusPainted(false);
		btnFiveThou.setBounds(170, 430, 110, 25);
		lblImage.add(btnFiveThou);
		btnFiveThou.addActionListener(this);

		btnTenThou = new JButton("Rs 10000");
		btnTenThou.setFont(btnFont);
		btnTenThou.setBackground(actionBtnBg);
		btnTenThou.setForeground(actionBtnFg);
		btnTenThou.setFocusPainted(false);
		btnTenThou.setBounds(350, 430, 140, 25);
		lblImage.add(btnTenThou);
		btnTenThou.addActionListener(this);

		btnBack = new JButton("BACK");
		btnBack.setFont(btnFont);
		btnBack.setBackground(backBtnBg);
		btnBack.setForeground(backBtnFg);
		btnBack.setFocusPainted(false);
		btnBack.setBounds(370, 480, 100, 25);
		lblImage.add(btnBack);
		btnBack.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource().equals(btnBack)) {
			this.setVisible(false);
			Transactions obj = new Transactions(pin);
			obj.setTitle("Transactions");
			obj.setSize(900, 1000);
			obj.setLocation(200, 0);
			obj.getContentPane().setBackground(Color.WHITE);
			obj.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
			obj.setUndecorated(true);
			obj.setVisible(true);
			return;
		}

		// Amount based on which button was clicked
		String amountStr = "";
		if (ae.getSource().equals(btnOneHund)) {
			amountStr = "100";
		} else if (ae.getSource().equals(btnFiveHund)) {
			amountStr = "500";
		} else if (ae.getSource().equals(btnThousand)) {
			amountStr = "1000";
		} else if (ae.getSource().equals(btnTwoThou)) {
			amountStr = "2000";
		} else if (ae.getSource().equals(btnFiveThou)) {
			amountStr = "5000";
		} else if (ae.getSource().equals(btnTenThou)) {
			amountStr = "10000";
		}

		JdbcRowSet jr = null;
		try {
			double fastCashAmount = Double.parseDouble(amountStr);
			double currentBalance = 0.0;

			// 1. Fetch latest balance
			jr = JdbcRowSetConn.getJdbcRowSet();
			jr.setAutoCommit(false);
			jr.setCommand("Select balance from transactions where pin = ? ORDER BY date DESC limit 1");
			jr.setString(1, pin);
			jr.execute();
			
			if (jr.next()) {
				currentBalance = jr.getDouble("balance");
			}

			// 2. Check for sufficient balance
			if (fastCashAmount > currentBalance) {
				JOptionPane.showMessageDialog(null, "Insufficient Balance");
				jr.close();
				return;
			}

			double newBalance = currentBalance - fastCashAmount;
			Date dat = new Date(); 
			java.sql.Timestamp dateTime = new java.sql.Timestamp(dat.getTime());

			// 3. Insert fast cash transaction record
			Connection connection = DBConnection.getConnection();
			connection.setAutoCommit(false);
			String query = "INSERT into transactions values(?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, pin);
			statement.setTimestamp(2, dateTime);
			statement.setString(3, "WITHDRAWAL");
			statement.setString(4, amountStr);
			statement.setDouble(5, newBalance);
			statement.executeUpdate();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Rs. " + amountStr + " is Withdrawn Successfully");

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
	}
}