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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.dbconnection.DBConnection;
import com.toedter.calendar.JDateChooser;

public class Signup extends JFrame implements ActionListener {
	// fields
	private JLabel lblApplicationForm, lblPersonalDetails, lblName, lblFatherName, lblDOB, lblGender, lblEmail,
			lblMaritalStatus, lblAddress, lblCity, lblPinCode, lblState;
	private JTextField tfName, tfFatherName, tfEmail, tfAddress, tfCity, tfPinCode, tfState;
	private JRadioButton rbnMale, rbnFemale, rbnOthers1, rbnMarried, rbnUnmarried, rbnOthers2;
	private JButton btnNext;
	private JDateChooser dateChooser;
	private Integer number;

	public Signup() {
		// disable the default layout
		setLayout(null);

		Random random = new Random();
		number = random.nextInt(1000, 9999);

		// Header Panel
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBackground(new Color(30, 58, 138));
		headerPanel.setBounds(0, 0, 700, 80);
		add(headerPanel);

		lblApplicationForm = new JLabel("Application Form :" + number);
		lblApplicationForm.setFont(new Font("Raleway", Font.BOLD, 25));
		lblApplicationForm.setForeground(Color.WHITE);
		lblApplicationForm.setBounds(40, 15, 400, 30);
		headerPanel.add(lblApplicationForm);

		lblPersonalDetails = new JLabel("Personal Detials of Customer");
		lblPersonalDetails.setFont(new Font("Raleway", Font.BOLD, 16));
		lblPersonalDetails.setForeground(new Color(191, 219, 254));
		lblPersonalDetails.setBounds(40, 45, 400, 25);
		headerPanel.add(lblPersonalDetails);

		// Updated Label Style: Segoe UI, Bold, 12px, Dark Slate color
		Font labelFont = new Font("Segoe UI", Font.BOLD, 12);
		Color labelColor = new Color(44, 62, 80);

		lblName = new JLabel("Full Name:");
		lblName.setFont(labelFont);
		lblName.setForeground(labelColor);
		lblName.setBounds(100, 110, 200, 30);
		add(lblName);

		tfName = new JTextField();
		tfName.setFont(new Font("Raleway", Font.PLAIN, 14));
		tfName.setBounds(300, 110, 250, 27);
		add(tfName);

		lblFatherName = new JLabel("Father Name:");
		lblFatherName.setFont(labelFont);
		lblFatherName.setForeground(labelColor);
		lblFatherName.setBounds(100, 150, 200, 30);
		add(lblFatherName);

		tfFatherName = new JTextField();
		tfFatherName.setFont(new Font("Raleway", Font.PLAIN, 14));
		tfFatherName.setBounds(300, 150, 250, 27);
		add(tfFatherName);

		lblDOB = new JLabel("Date of Birth:");
		lblDOB.setFont(labelFont);
		lblDOB.setForeground(labelColor);
		lblDOB.setBounds(100, 190, 200, 30);
		add(lblDOB);

		dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Raleway", Font.PLAIN, 13));
		dateChooser.setBounds(300, 190, 200, 25);
		add(dateChooser);

		lblGender = new JLabel("Gender:");
		lblGender.setFont(labelFont);
		lblGender.setForeground(labelColor);
		lblGender.setBounds(100, 230, 200, 30);
		add(lblGender);

		rbnMale = new JRadioButton("Male");
		rbnMale.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbnMale.setBackground(Color.white);
		rbnMale.setBounds(300, 230, 100, 30);
		add(rbnMale);

		rbnFemale = new JRadioButton("Female");
		rbnFemale.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbnFemale.setBackground(Color.white);
		rbnFemale.setBounds(400, 230, 100, 30);
		add(rbnFemale);

		rbnOthers1 = new JRadioButton("Others");
		rbnOthers1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbnOthers1.setBackground(Color.white);
		rbnOthers1.setBounds(500, 230, 100, 30);
		add(rbnOthers1);

		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rbnMale);
		btnGroup.add(rbnFemale);
		btnGroup.add(rbnOthers1);

		lblEmail = new JLabel("Email ID:");
		lblEmail.setFont(labelFont);
		lblEmail.setForeground(labelColor);
		lblEmail.setBounds(100, 270, 200, 30);
		add(lblEmail);

		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Raleway", Font.PLAIN, 14));
		tfEmail.setBounds(300, 270, 250, 27);
		add(tfEmail);

		lblMaritalStatus = new JLabel("Marital Status:");
		lblMaritalStatus.setFont(labelFont);
		lblMaritalStatus.setForeground(labelColor);
		lblMaritalStatus.setBounds(100, 310, 200, 30);
		add(lblMaritalStatus);

		rbnMarried = new JRadioButton("Married");
		rbnMarried.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbnMarried.setBackground(Color.white);
		rbnMarried.setBounds(300, 310, 100, 30);
		add(rbnMarried);

		rbnUnmarried = new JRadioButton("Unmarried");
		rbnUnmarried.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbnUnmarried.setBackground(Color.white);
		rbnUnmarried.setBounds(400, 310, 100, 30);
		add(rbnUnmarried);

		rbnOthers2 = new JRadioButton("Others");
		rbnOthers2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbnOthers2.setBackground(Color.white);
		rbnOthers2.setBounds(500, 310, 100, 30);
		add(rbnOthers2);

		ButtonGroup btnGroup2 = new ButtonGroup();
		btnGroup2.add(rbnMarried);
		btnGroup2.add(rbnUnmarried);
		btnGroup2.add(rbnOthers2);

		lblAddress = new JLabel("Address:");
		lblAddress.setFont(labelFont);
		lblAddress.setForeground(labelColor);
		lblAddress.setBounds(100, 350, 200, 30);
		add(lblAddress);

		tfAddress = new JTextField();
		tfAddress.setFont(new Font("Raleway", Font.PLAIN, 14));
		tfAddress.setBounds(300, 350, 250, 27);
		add(tfAddress);

		lblCity = new JLabel("City: ");
		lblCity.setFont(labelFont);
		lblCity.setForeground(labelColor);
		lblCity.setBounds(100, 390, 200, 30);
		add(lblCity);

		tfCity = new JTextField();
		tfCity.setFont(new Font("Raleway", Font.PLAIN, 14));
		tfCity.setBounds(300, 390, 250, 27);
		add(tfCity);

		lblPinCode = new JLabel("Pin Code:");
		lblPinCode.setFont(labelFont);
		lblPinCode.setForeground(labelColor);
		lblPinCode.setBounds(100, 430, 200, 30);
		add(lblPinCode);

		tfPinCode = new JTextField();
		tfPinCode.setFont(new Font("Raleway", Font.PLAIN, 14));
		tfPinCode.setBounds(300, 430, 250, 27);
		add(tfPinCode);

		lblState = new JLabel("State:");
		lblState.setFont(labelFont);
		lblState.setForeground(labelColor);
		lblState.setBounds(100, 470, 200, 30);
		add(lblState);

		tfState = new JTextField();
		tfState.setFont(new Font("Raleway", Font.PLAIN, 14));
		tfState.setBounds(300, 470, 250, 27);
		add(tfState);

		btnNext = new JButton("Next");
		btnNext.setBackground(new Color(30, 58, 138));
		btnNext.setForeground(Color.WHITE);
		btnNext.setBounds(570, 510, 80, 27);
		add(btnNext);
		btnNext.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String formNum = String.valueOf(number);
		String name = tfName.getText();
		String fName = tfFatherName.getText();
		String dob = "";
		try {
			dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
		} catch (Exception ex) {
			dob = "";
		}

		String gender = "";
		if (rbnMale.isSelected()) {
			gender = "Male";
		}
		if (rbnFemale.isSelected()) {
			gender = "Female";
		}
		if (rbnOthers1.isSelected()) {
			gender = "Others";
		}

		String email = tfEmail.getText();
		String maritalStatus = "";
		if (rbnMarried.isSelected()) {
			maritalStatus = "Married";
		}
		if (rbnUnmarried.isSelected()) {
			maritalStatus = "Unmarried";
		}
		if (rbnOthers2.isSelected()) {
			maritalStatus = "Others";
		}

		String address = tfAddress.getText();
		String city = tfCity.getText();
		String pincode = tfPinCode.getText();
		String state = tfState.getText(); 

		if (e.getSource().equals(btnNext)) {
			try {
				Connection connection = DBConnection.getConnection();
				String query = "INSERT into signup values(?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, formNum);
				statement.setString(2, name);
				statement.setString(3, fName);
				statement.setString(4, dob);
				statement.setString(5, gender);
				statement.setString(6, email);
				statement.setString(7, maritalStatus);
				statement.setString(8, address);
				statement.setString(9, city);
				statement.setString(10, pincode);
				statement.setString(11, state);
				statement.executeUpdate();

				this.setVisible(false);

				SignupTwo obj = new SignupTwo(formNum);
				obj.setTitle("Additional Details");
				obj.setSize(700, 600);
				obj.setLocation(300, 10);
				obj.getContentPane().setBackground(Color.WHITE);
				obj.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
				obj.setVisible(true);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}