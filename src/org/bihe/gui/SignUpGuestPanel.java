package org.bihe.gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.SystemColor;

public class SignUpGuestPanel extends JPanel {
	private JTextField firstnameTextField;
	private JTextField lastnameTextField;
	private JTextField nationalIdTextField;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JTextField phoneNoTextField;
	private JButton signUpSubmitButton;
	private JButton backButton;
	private JTextField cityTextField;
	private JTextField addressTextField;

	// -----------------------------Accessor-----------------------

	
	public JTextField getFirstnameTextField() {
		return firstnameTextField;
	}

	public JTextField getAddressTextField() {
		return addressTextField;
	}

	public JTextField getLastnameTextField() {
		return lastnameTextField;
	}

	public JTextField getNationalIdTextField() {
		return nationalIdTextField;
	}

	public JTextField getUsernameTextField() {
		return usernameTextField;
	}

	public JTextField getPasswordTextField() {
		return passwordTextField;
	}

	public JTextField getPhoneNoTextField() {
		return phoneNoTextField;
	}

	public JButton getSignUpSubmitButton() {
		return signUpSubmitButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public JTextField getCityTextField() {
		return cityTextField;
	}

	/**
	 * Create the panel.
	 */
	public SignUpGuestPanel() {
		setLayout(null);
		setLayout(null);
		setBackground(new Color(47, 79, 79));

		firstnameTextField = new JTextField();
		firstnameTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		firstnameTextField.setBounds(158, 101, 220, 46);
		add(firstnameTextField);
		firstnameTextField.setColumns(10);

		lastnameTextField = new JTextField();
		lastnameTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		lastnameTextField.setColumns(10);
		lastnameTextField.setBounds(158, 177, 220, 46);
		add(lastnameTextField);

		nationalIdTextField = new JTextField();
		nationalIdTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		nationalIdTextField.setColumns(10);
		nationalIdTextField.setBounds(158, 249, 220, 46);
		add(nationalIdTextField);

		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		usernameTextField.setColumns(10);
		usernameTextField.setBounds(538, 84, 220, 46);
		add(usernameTextField);

		passwordTextField = new JTextField();
		passwordTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(538, 160, 220, 46);
		add(passwordTextField);

		phoneNoTextField = new JTextField();
		phoneNoTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		phoneNoTextField.setColumns(10);
		phoneNoTextField.setBounds(538, 233, 220, 46);
		add(phoneNoTextField);

		signUpSubmitButton = new JButton("Submit");
		signUpSubmitButton.setBackground(Color.YELLOW);
		signUpSubmitButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		signUpSubmitButton.setBounds(538, 339, 220, 50);
		add(signUpSubmitButton);

		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		backButton.setBounds(538, 400, 220, 46);
		add(backButton);

		cityTextField = new JTextField();
		cityTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		cityTextField.setColumns(10);
		cityTextField.setBounds(159, 331, 220, 46);
		add(cityTextField);

		JLabel cityLabel = new JLabel("City:");
		cityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cityLabel.setForeground(new Color(235, 235, 235));
		cityLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		cityLabel.setBounds(33, 331, 128, 46);
		add(cityLabel);

		JLabel lblNewLabel = new JLabel("Please fill all the forms");
		lblNewLabel.setForeground(new Color(235, 235, 235));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(33, 27, 292, 46);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBackground(SystemColor.activeCaption);
		lblNewLabel_1.setBounds(712, 11, -366, 31);
		add(lblNewLabel_1);

		addressTextField = new JTextField();
		addressTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addressTextField.setBounds(161, 400, 220, 50);
		add(addressTextField);

		JLabel firstnameLabel = new JLabel("Firstname: ");
		firstnameLabel.setForeground(new Color(235, 235, 235));
		firstnameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		firstnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		firstnameLabel.setBounds(33, 100, 128, 46);
		add(firstnameLabel);

		JLabel lastnameLabel = new JLabel("Lastname: ");
		lastnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lastnameLabel.setForeground(new Color(235, 235, 235));
		lastnameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lastnameLabel.setBounds(33, 176, 128, 46);
		add(lastnameLabel);

		JLabel nationalIdLabel = new JLabel("National ID:");
		nationalIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nationalIdLabel.setForeground(new Color(235, 235, 235));
		nationalIdLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		nationalIdLabel.setBounds(33, 248, 128, 46);
		add(nationalIdLabel);

		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setForeground(new Color(235, 235, 235));
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		usernameLabel.setBounds(400, 83, 128, 46);
		add(usernameLabel);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setToolTipText("");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setForeground(new Color(235, 235, 235));
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordLabel.setBounds(400, 159, 128, 46);
		add(passwordLabel);

		JLabel phoneNoLabel = new JLabel("Phone No:");
		phoneNoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		phoneNoLabel.setForeground(new Color(235, 235, 235));
		phoneNoLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		phoneNoLabel.setBounds(400, 232, 128, 46);
		add(phoneNoLabel);

		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addressLabel.setForeground(new Color(235, 235, 235));
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		addressLabel.setBounds(36, 400, 128, 46);
		add(addressLabel);
	}
}
