package com.bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.dbconnection.DBConnection;

public class PinChange extends JFrame implements ActionListener {
	
	private JPasswordField pfNewPin, pfRePinField;
	private JLabel lblTitle, lblNewPin, lblRePin, lblImage;
	private JButton btnChange, btnBack;
	private String pin;

	public PinChange(String pin) {
		this.pin = pin;
		setLayout(null);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
		Image scaledImage = i1.getImage().getScaledInstance(900, 790, Image.SCALE_DEFAULT);
		ImageIcon i2 = new ImageIcon(scaledImage);
		lblImage = new JLabel(i2);
		lblImage.setBounds(0, 0, 900, 790);
		add(lblImage);

		lblTitle = new JLabel("CHANGE YOUR PIN");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTitle.setForeground(Color.white);
		lblTitle.setBounds(250, 275, 300, 35);
		lblImage.add(lblTitle);

		lblNewPin = new JLabel("NEW PIN:");
		lblNewPin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewPin.setForeground(Color.white);
		lblNewPin.setBounds(165, 320, 150, 25);
		lblImage.add(lblNewPin);

		pfNewPin = new JPasswordField();
		pfNewPin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		pfNewPin.setBounds(315, 320, 180, 25);
		lblImage.add(pfNewPin);

		lblRePin = new JLabel("RE-ENTER NEW PIN:");
		lblRePin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblRePin.setForeground(Color.white);
		lblRePin.setBounds(165, 360, 150, 25);
		lblImage.add(lblRePin);

		pfRePinField = new JPasswordField();
		pfRePinField.setFont(new Font("Segoe UI", Font.BOLD, 14));
		pfRePinField.setBounds(315, 360, 180, 25);
		lblImage.add(pfRePinField);

		// Button styling configurations to match Transactions, Deposit, Withdrawal, and FastCash frames
		Font btnFont = new Font("Segoe UI", Font.BOLD, 11);
		Color actionBtnBg = Color.WHITE;
		Color actionBtnFg = Color.BLACK;
		Color backBtnBg = new Color(225, 29, 72); // Red
		Color backBtnFg = Color.WHITE;

		btnChange = new JButton("CHANGE");
		btnChange.setFont(btnFont);
		btnChange.setBackground(actionBtnBg);
		btnChange.setForeground(actionBtnFg);
		btnChange.setFocusPainted(false);
		btnChange.setBounds(355, 430, 135, 25);
		lblImage.add(btnChange);
		btnChange.addActionListener(this);

		btnBack = new JButton("BACK");
		btnBack.setFont(btnFont);
		btnBack.setBackground(backBtnBg);
		btnBack.setForeground(backBtnFg);
		btnBack.setFocusPainted(false);
		btnBack.setBounds(355, 470, 135, 25);
		lblImage.add(btnBack);
		btnBack.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource().equals(btnChange)) {
			try {
				String newPin = new String(pfNewPin.getPassword()).trim();
				String rePin = new String(pfRePinField.getPassword()).trim();

				if (newPin.isEmpty() || rePin.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter the PIN.");
					return;
				}

				if (!newPin.equals(rePin)) {
					JOptionPane.showMessageDialog(null, "Entered PIN does not match.");
					return;
				}

				// Update PIN across relevant tables (e.g., login, signupthree, transactions, etc.)
				Connection connection = DBConnection.getConnection();
				connection.setAutoCommit(false);

				String q1 = "update signupthree set pin = ? where pin = ?";
				String q2 = "update login set pin = ? where pin = ?";
				String q3 = "update transactions set pin = ? where pin = ?";

				try {
					PreparedStatement ps1 = connection.prepareStatement(q1);
					PreparedStatement ps2 = connection.prepareStatement(q2);
				    PreparedStatement ps3 = connection.prepareStatement(q3);
					ps1.setString(1, newPin);
					ps1.setString(2, pin);
					ps1.executeUpdate();

					ps2.setString(1, newPin);
					ps2.setString(2, pin);
					ps2.executeUpdate();

					ps3.setString(1, newPin);
					ps3.setString(2, pin);
					ps3.executeUpdate();

					connection.commit();
					JOptionPane.showMessageDialog(null, "PIN Changed Successfully.");
					
					// Update current instance pin reference
					this.pin = newPin;
					
					// Clear fields
					pfNewPin.setText("");
					pfRePinField.setText("");

				} catch (SQLException ex) {
					connection.rollback();
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
				}

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
			obj.setUndecorated(true);
			obj.setVisible(true);
		}
	}

	
}