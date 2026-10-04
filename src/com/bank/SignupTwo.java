package com.bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.dbconnection.DBConnection;

public class SignupTwo extends JFrame implements ActionListener {
	// fields
	private JLabel lblApplicationForm, lblAdditionalDetails, lblReligion, lblCategory, lblIncome, lblEducation, lblOccupation, lblPanNumber,
			lblAadharNumber, lblSeniorCitizen, lblExistingAccount;
	private JTextField tfPan, tfAadhar;
	private JRadioButton rbnAccountYes, rbnAccountNo, rbnCitizenYes, rbnCitizenNo;
	private JButton btnNext;
	private JComboBox<String> jcbReligion, jcbCategory, jcbIncome, jcbEducation, jcbOccupation;

	private String formno;

	public SignupTwo(String formno) {
		this.formno = formno;
		// disable the default layout
		setLayout(null);

		// Header Panel (Matching Signup.java)
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

		lblAdditionalDetails = new JLabel("Page 2: Additional Details");
		lblAdditionalDetails.setFont(new Font("Raleway", Font.BOLD, 16));
		lblAdditionalDetails.setForeground(new Color(191, 219, 254));
		lblAdditionalDetails.setBounds(40, 45, 400, 25);
		headerPanel.add(lblAdditionalDetails);

		// Updated Label Style: Segoe UI, Bold, 12px, Dark Slate color
		Font labelFont = new Font("Segoe UI", Font.BOLD, 12);
		Color labelColor = new Color(44, 62, 80);

		lblReligion = new JLabel("Religion:");
		lblReligion.setFont(labelFont);
		lblReligion.setForeground(labelColor);
		lblReligion.setBounds(100, 110, 200, 30);
		add(lblReligion);

		String[] religion = { "Hindu", "Muslim", "Sikh", "Christian", "Other" };
		jcbReligion = new JComboBox<>(religion);
		jcbReligion.setFont(new Font("Raleway", Font.PLAIN, 14));
		jcbReligion.setBackground(Color.white);
		jcbReligion.setForeground(Color.BLACK);
		jcbReligion.setBounds(300, 110, 250, 27);
		add(jcbReligion);

		lblCategory = new JLabel("Category:");
		lblCategory.setFont(labelFont);
		lblCategory.setForeground(labelColor);
		lblCategory.setBounds(100, 150, 200, 30);
		add(lblCategory);

		String[] category = { "General", "OBC", "SC", "ST", "Other" };
		jcbCategory = new JComboBox<>(category);
		jcbCategory.setFont(new Font("Raleway", Font.PLAIN, 14));
		jcbCategory.setBackground(Color.white);
		jcbCategory.setForeground(Color.BLACK);
		jcbCategory.setBounds(300, 150, 250, 27);
		add(jcbCategory);

		lblIncome = new JLabel("Income:");
		lblIncome.setFont(labelFont);
		lblIncome.setForeground(labelColor);
		lblIncome.setBounds(100, 190, 200, 30);
		add(lblIncome);

		String[] income = { "Null", "<1,50,000", "<2,50,000", "<5,00,000", "upto 10,00,000", "above 10,00,000" };
		jcbIncome = new JComboBox<>(income);
		jcbIncome.setFont(new Font("Raleway", Font.PLAIN, 14));
		jcbIncome.setBackground(Color.white);
		jcbIncome.setForeground(Color.BLACK);
		jcbIncome.setBounds(300, 190, 250, 27);
		add(jcbIncome);

		lblEducation = new JLabel("Education:");
		lblEducation.setFont(labelFont);
		lblEducation.setForeground(labelColor);
		lblEducation.setBounds(100, 230, 200, 30);
		add(lblEducation);

		String[] education = { "Matriculation(10th)", "Higher Secondary(10+2)", "Under Graduate", "Graduate",
				"Post Graduate", "Doctorate", "Others" };
		jcbEducation = new JComboBox<>(education);
		jcbEducation.setFont(new Font("Raleway", Font.PLAIN, 14));
		jcbEducation.setBackground(Color.white);
		jcbEducation.setForeground(Color.BLACK);
		jcbEducation.setBounds(300, 230, 250, 27);
		add(jcbEducation);

		lblOccupation = new JLabel("Occupation:");
		lblOccupation.setFont(labelFont);
		lblOccupation.setForeground(labelColor);
		lblOccupation.setBounds(100, 270, 200, 30);
		add(lblOccupation);

		String[] occupation = { "Salaried", "Self-Employed", "Business", "Student", "Retired", "Others" };
		jcbOccupation = new JComboBox<>(occupation);
		jcbOccupation.setFont(new Font("Raleway", Font.PLAIN, 14));
		jcbOccupation.setBackground(Color.white);
		jcbOccupation.setForeground(Color.BLACK);
		jcbOccupation.setBounds(300, 270, 250, 27);
		add(jcbOccupation);

		lblPanNumber = new JLabel("PAN Number:");
		lblPanNumber.setFont(labelFont);
		lblPanNumber.setForeground(labelColor);
		lblPanNumber.setBounds(100, 310, 200, 30);
		add(lblPanNumber);

		tfPan = new JTextField();
		tfPan.setFont(new Font("Raleway", Font.PLAIN, 14));
		tfPan.setBounds(300, 310, 250, 27);
		add(tfPan);

		lblAadharNumber = new JLabel("Aadhar Number:");
		lblAadharNumber.setFont(labelFont);
		lblAadharNumber.setForeground(labelColor);
		lblAadharNumber.setBounds(100, 350, 200, 30);
		add(lblAadharNumber);

		tfAadhar = new JTextField();
		tfAadhar.setFont(new Font("Raleway", Font.PLAIN, 14));
		tfAadhar.setBounds(300, 350, 250, 27);
		add(tfAadhar);

		lblSeniorCitizen = new JLabel("Senior Citizen: ");
		lblSeniorCitizen.setFont(labelFont);
		lblSeniorCitizen.setForeground(labelColor);
		lblSeniorCitizen.setBounds(100, 390, 200, 30);
		add(lblSeniorCitizen);

		rbnCitizenYes = new JRadioButton("Yes");
		rbnCitizenYes.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbnCitizenYes.setBackground(Color.white);
		rbnCitizenYes.setBounds(300, 390, 100, 30);
		add(rbnCitizenYes);

		rbnCitizenNo = new JRadioButton("No");
		rbnCitizenNo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbnCitizenNo.setBackground(Color.white);
		rbnCitizenNo.setBounds(400, 390, 100, 30);
		add(rbnCitizenNo);

		ButtonGroup btnGroup1 = new ButtonGroup();
		btnGroup1.add(rbnCitizenYes);
		btnGroup1.add(rbnCitizenNo);

		lblExistingAccount = new JLabel("Existing Account:");
		lblExistingAccount.setFont(labelFont);
		lblExistingAccount.setForeground(labelColor);
		lblExistingAccount.setBounds(100, 430, 200, 30);
		add(lblExistingAccount);

		rbnAccountYes = new JRadioButton("Yes");
		rbnAccountYes.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbnAccountYes.setBackground(Color.white);
		rbnAccountYes.setBounds(300, 430, 100, 30);
		add(rbnAccountYes);

		rbnAccountNo = new JRadioButton("No");
		rbnAccountNo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rbnAccountNo.setBackground(Color.white);
		rbnAccountNo.setBounds(400, 430, 100, 30);
		add(rbnAccountNo);

		ButtonGroup btnGroup2 = new ButtonGroup();
		btnGroup2.add(rbnAccountYes);
		btnGroup2.add(rbnAccountNo);

		btnNext = new JButton("Next");
		btnNext.setBackground(new Color(30, 58, 138));
		btnNext.setForeground(Color.WHITE);
		btnNext.setBounds(570, 480, 80, 27);
		add(btnNext);
		btnNext.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String religion = (String) jcbReligion.getSelectedItem();
		String category = (String) jcbCategory.getSelectedItem();
		String income = (String) jcbIncome.getSelectedItem();
		String education = (String) jcbEducation.getSelectedItem();
		String occupation = (String) jcbOccupation.getSelectedItem();

		String pan = tfPan.getText().trim();
		String aadhar = tfAadhar.getText().trim();

		String scitizen = "";
		if (rbnCitizenYes.isSelected()) {
			scitizen = "Yes";
		} else if (rbnCitizenNo.isSelected()) {
			scitizen = "No";
		}

		String eaccount = "";
		if (rbnAccountYes.isSelected()) {
			eaccount = "Yes";
		} else if (rbnAccountNo.isSelected()) {
			eaccount = "No";
		}
		
		if (pan.isEmpty() || aadhar.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please fill in PAN and identification fields.");
			return;
		}
		
		if (e.getSource().equals(btnNext)) {
			try {
				Connection connection = DBConnection.getConnection();
				String query = "INSERT into signuptwo values(?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, formno);
				statement.setString(2, religion);
				statement.setString(3, category);
				statement.setString(4, income);
				statement.setString(5, education);
				statement.setString(6, occupation);
				statement.setString(7, pan);
				statement.setString(8, aadhar);
				statement.setString(9, scitizen);
				statement.setString(10, eaccount);
				statement.executeUpdate();

				this.setVisible(false);
				
				SignupThree obj = new SignupThree(formno);
				obj.setTitle("Account Details");
				obj.setSize(700, 600);
				obj.setLocation(300, 10);
				obj.getContentPane().setBackground(Color.WHITE);
				obj.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
				obj.setVisible(true);

			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
			}
		}
	}
}