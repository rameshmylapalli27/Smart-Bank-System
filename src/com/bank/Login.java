package com.bank;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.bank.ValidationUtil;
import com.dbconnection.DBConnection;

public class Login extends JFrame implements ActionListener {
    // fields
    private JLabel lblWelcome, lblSubtitle, lblCardNumber, lblPinNumber;
    private JTextField tfCardNumber;
    private JPasswordField pfPinNumber;
    private JButton btnLogin, btnClear, btnSignup;
    private JPanel leftPanel;

    public Login() {
        // disable the default layout
        setLayout(null);

        // Left Branding/Decorative Panel
        leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBackground(new Color(30, 58, 138)); // Navy Blue
        leftPanel.setBounds(0, 0, 250, 500);
        add(leftPanel);

        JLabel lblBrand = new JLabel("Smart Bank");
        lblBrand.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblBrand.setForeground(Color.WHITE);
        lblBrand.setBounds(30, 200, 200, 30);
        leftPanel.add(lblBrand);

        JLabel lblBrandSub = new JLabel("Secure ATM Portal");
        lblBrandSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblBrandSub.setForeground(new Color(191, 219, 254));
        lblBrandSub.setBounds(30, 235, 200, 25);
        leftPanel.add(lblBrandSub);

        //Frame Area
        lblWelcome = new JLabel("Welcome Back!");
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblWelcome.setForeground(new Color(30, 30, 30));
        lblWelcome.setBounds(290, 50, 350, 35);
        add(lblWelcome);

        lblSubtitle = new JLabel("Please enter your card details to log in.");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSubtitle.setForeground(new Color(100, 100, 100));
        lblSubtitle.setBounds(290, 85, 350, 20);
        add(lblSubtitle);

        lblCardNumber = new JLabel("Card No:");
        lblCardNumber.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblCardNumber.setForeground(new Color(70, 70, 70));
        lblCardNumber.setBounds(290, 140, 100, 25);
        add(lblCardNumber);

        tfCardNumber = new JTextField();
        tfCardNumber.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tfCardNumber.setBounds(290, 170, 390, 35);
        add(tfCardNumber);

        lblPinNumber = new JLabel("PIN No:");
        lblPinNumber.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblPinNumber.setForeground(new Color(70, 70, 70));
        lblPinNumber.setBounds(290, 225, 100, 25);
        add(lblPinNumber);

        pfPinNumber = new JPasswordField();
        pfPinNumber.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pfPinNumber.setBounds(290, 255, 390, 35);
        add(pfPinNumber);

        // Buttons
        btnLogin = new JButton("LOGIN");
        styleButton(btnLogin, new Color(30, 58, 138), Color.WHITE);
        btnLogin.setBounds(290, 325, 120, 38);
        add(btnLogin);

        btnClear = new JButton("CLEAR");
        styleButton(btnClear, new Color(229, 231, 235), new Color(55, 65, 81));
        btnClear.setBounds(425, 325, 120, 38);
        add(btnClear);

        btnSignup = new JButton("SIGNUP");
        styleButton(btnSignup, new Color(243, 244, 246), new Color(30, 58, 138));
        btnSignup.setBounds(560, 325, 120, 38);
        add(btnSignup);

        // adding action listeners
        btnLogin.addActionListener(this);
        btnClear.addActionListener(this);
        btnSignup.addActionListener(this);
    }

    // Helper method to keep button styling clean and modular
    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().equals(btnLogin)) {
                String cardNum = tfCardNumber.getText().trim();
                String pin = pfPinNumber.getText().trim();

                Connection connection = DBConnection.getConnection();
                String query = "Select * from Login where cardNumber = ? and pin = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                //validation checks before cardNum set to read
            	ValidationUtil validate = new ValidationUtil();

				if (validate.isEmptyOrBlank(cardNum)) {
					if (validate.isValidCardNo(cardNum)) {
						
						statement.setString(1, cardNum);
						
					} else {
						JOptionPane.showMessageDialog(null, " ENTER THE VALID CARD NUMBER");
						return;
					}
				} else {
					JOptionPane.showMessageDialog(null, "ENTER THE CARD NUMBER");
					return; // come out of the method otherwise we'll get the exception and routed to login
				}
				
                
				if (validate.isEmptyOrBlank(pin)) {
					if (validate.isValidPin(pin)) {
						
		                statement.setString(2, pin);
						
					} else {
						JOptionPane.showMessageDialog(null, " ENTER THE VALID PIN NUMBER");
						return;
					}
				} else {
					JOptionPane.showMessageDialog(null, "ENTER THE PIN NUMBER");
					return; 
				}
				
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    this.setVisible(false);
                    Transactions obj = new Transactions(pin);
                    obj.setTitle("Transactions");
                    obj.setSize(900, 1000);
                    obj.setLocation(200, 5);
                    obj.setUndecorated(true);
                    obj.getContentPane().setBackground(Color.WHITE);
                    obj.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Card Number or PIN");
                }

            } else if (e.getSource().equals(btnClear)) {
                tfCardNumber.setText("");
                pfPinNumber.setText("");

            } else if (e.getSource().equals(btnSignup)) {
                this.setVisible(false);
                Signup obj = new Signup();
                obj.setTitle("Signup");
                obj.setSize(700, 600);
                obj.setLocation(300, 10);
                obj.getContentPane().setBackground(Color.WHITE);
                obj.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
                obj.setVisible(true);
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Login obj = new Login();
        obj.setTitle("Login - Smart Bank System");
        obj.setSize(730, 440);
        obj.setLocation(300, 50);
        obj.setResizable(false);
        obj.getContentPane().setBackground(Color.WHITE);
        obj.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
        obj.setVisible(true);
    }
}