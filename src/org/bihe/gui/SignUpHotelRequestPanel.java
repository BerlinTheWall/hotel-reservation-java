package org.bihe.gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JSlider;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

public class SignUpHotelRequestPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField firstnameTextField;
	private JTextField lastnameTextField;
	private JTextField hotelnameTextField;
	private JTextField cityTextField;
	private JTextField phoneNoTextField;
	private JButton sendRequestButton;
	private JCheckBox swimmingPoolCheckBox;
	private JCheckBox gymCheckBox;
	private JCheckBox wifiCheckBox;
	private JCheckBox breakfastCheckBox;
	private JCheckBox lunchCheckBox;
	private JCheckBox dinnerCheckBox;
	private JSpinner roomNumberSpinner;
	private JSpinner starSpinner;
	private JButton backButton;
	private JTextField addressTextField;
	private JTextField passwordTextField;
	private JTextField usernameTextField;
	private JLabel lblNationalId;
	private JTextField nationalIdTextField;

	// -----------------------------Accessor-----------------------

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JTextField getFirstnameTextField() {
		return firstnameTextField;
	}

	public JTextField getLastnameTextField() {
		return lastnameTextField;
	}

	public JTextField getHotelnameTextField() {
		return hotelnameTextField;
	}

	public JTextField getCityTextField() {
		return cityTextField;
	}

	public JTextField getPasswordTextField() {
		return passwordTextField;
	}

	public JTextField getUsernameTextField() {
		return usernameTextField;
	}

	public JTextField getPhoneNoTextField() {
		return phoneNoTextField;
	}

	public JButton getSendRequestButton() {
		return sendRequestButton;
	}

	public JCheckBox getSwimmingPoolCheckBox() {
		return swimmingPoolCheckBox;
	}

	public JCheckBox getGymCheckBox() {
		return gymCheckBox;
	}

	public JCheckBox getWifiCheckBox() {
		return wifiCheckBox;
	}

	public JCheckBox getBreakfastCheckBox() {
		return breakfastCheckBox;
	}

	public JCheckBox getLunchCheckBox() {
		return lunchCheckBox;
	}

	public JTextField getNationalIdTextField() {
		return nationalIdTextField;
	}

	public JCheckBox getDinnerCheckBox() {
		return dinnerCheckBox;
	}

	public JSpinner getRoomNumberSpinner() {
		return roomNumberSpinner;
	}

	public JSpinner getStarSpinner() {
		return starSpinner;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public JTextField getAddressTextField() {
		return addressTextField;
	}

	/**
	 * Create the panel.
	 */
	public SignUpHotelRequestPanel() {
		setBackground(new Color(47, 79, 79));
		setLayout(null);

		swimmingPoolCheckBox = new JCheckBox("Swimming pool");
		swimmingPoolCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		swimmingPoolCheckBox.setBounds(245, 396, 135, 39);
		add(swimmingPoolCheckBox);

		gymCheckBox = new JCheckBox("Gym");
		gymCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gymCheckBox.setBounds(245, 454, 135, 39);
		add(gymCheckBox);

		breakfastCheckBox = new JCheckBox("Breakfast");
		breakfastCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		breakfastCheckBox.setBounds(49, 396, 135, 39);
		add(breakfastCheckBox);

		lunchCheckBox = new JCheckBox("Lunch");
		lunchCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lunchCheckBox.setBounds(49, 454, 135, 39);
		add(lunchCheckBox);

		dinnerCheckBox = new JCheckBox("Dinner");
		dinnerCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dinnerCheckBox.setBounds(49, 514, 135, 39);
		add(dinnerCheckBox);

		wifiCheckBox = new JCheckBox("WIFI");
		wifiCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		wifiCheckBox.setBounds(245, 514, 135, 39);
		add(wifiCheckBox);

		firstnameTextField = new JTextField();
		firstnameTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		firstnameTextField.setColumns(10);
		firstnameTextField.setBounds(158, 66, 209, 39);
		add(firstnameTextField);

		lastnameTextField = new JTextField();
		lastnameTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		lastnameTextField.setColumns(10);
		lastnameTextField.setBounds(158, 116, 209, 39);
		add(lastnameTextField);

		hotelnameTextField = new JTextField();
		hotelnameTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		hotelnameTextField.setColumns(10);
		hotelnameTextField.setBounds(158, 166, 209, 39);
		add(hotelnameTextField);

		cityTextField = new JTextField();
		cityTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		cityTextField.setColumns(10);
		cityTextField.setBounds(158, 216, 209, 39);
		add(cityTextField);

		phoneNoTextField = new JTextField();
		phoneNoTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		phoneNoTextField.setColumns(10);
		phoneNoTextField.setBounds(158, 266, 209, 39);
		add(phoneNoTextField);

		roomNumberSpinner = new JSpinner();
		roomNumberSpinner.setModel(new SpinnerNumberModel(15, 15, 1000, 1));
		roomNumberSpinner.setFont(new Font("Tahoma", Font.PLAIN, 20));
		roomNumberSpinner.setBounds(603, 216, 75, 39);
		add(roomNumberSpinner);

		starSpinner = new JSpinner();
		starSpinner.setModel(new SpinnerNumberModel(1, 1, 8, 1));
		starSpinner.setFont(new Font("Tahoma", Font.PLAIN, 20));
		starSpinner.setBounds(603, 266, 75, 39);
		add(starSpinner);

		sendRequestButton = new JButton("Send request form");
		sendRequestButton.setBackground(new Color(255, 215, 0));
		sendRequestButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		sendRequestButton.setBounds(586, 396, 241, 83);
		add(sendRequestButton);

		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		backButton.setBounds(584, 510, 243, 46);
		add(backButton);

		addressTextField = new JTextField();
		addressTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		addressTextField.setColumns(10);
		addressTextField.setBounds(158, 316, 209, 39);
		add(addressTextField);

		JLabel lblNewLabel = new JLabel("Please fill all the forms");
		lblNewLabel.setForeground(new Color(235, 235, 235));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(10, 11, 292, 46);
		add(lblNewLabel);

		JLabel firstnameLabel = new JLabel("Firstname: ");
		firstnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		firstnameLabel.setForeground(new Color(235, 235, 235));
		firstnameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		firstnameLabel.setBounds(20, 62, 128, 46);
		add(firstnameLabel);

		JLabel lastnameLabel = new JLabel("Lastname: ");
		lastnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lastnameLabel.setForeground(new Color(235, 235, 235));
		lastnameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lastnameLabel.setBounds(20, 112, 128, 46);
		add(lastnameLabel);

		JLabel hotelNameLabel = new JLabel("Hotel name:");
		hotelNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hotelNameLabel.setForeground(new Color(235, 235, 235));
		hotelNameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		hotelNameLabel.setBounds(20, 162, 128, 46);
		add(hotelNameLabel);

		JLabel cityLabel = new JLabel("City:");
		cityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cityLabel.setForeground(new Color(235, 235, 235));
		cityLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		cityLabel.setBounds(20, 212, 128, 46);
		add(cityLabel);

		JLabel phoneNoLabel = new JLabel("Phone No:");
		phoneNoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		phoneNoLabel.setForeground(new Color(235, 235, 235));
		phoneNoLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		phoneNoLabel.setBounds(20, 262, 128, 46);
		add(phoneNoLabel);

		JLabel numberOfRoomsLabel = new JLabel("Number of rooms:");
		numberOfRoomsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		numberOfRoomsLabel.setForeground(new Color(235, 235, 235));
		numberOfRoomsLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		numberOfRoomsLabel.setBounds(429, 212, 164, 46);
		add(numberOfRoomsLabel);

		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addressLabel.setForeground(new Color(235, 235, 235));
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		addressLabel.setBounds(30, 299, 128, 46);
		add(addressLabel);

		JLabel starLabel = new JLabel("Stars:");
		starLabel.setHorizontalAlignment(SwingConstants.CENTER);
		starLabel.setForeground(new Color(235, 235, 235));
		starLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		starLabel.setBounds(440, 262, 128, 46);
		add(starLabel);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.YELLOW);
		separator.setBounds(0, 366, 945, 2);
		add(separator);

		JLabel optionsLabel = new JLabel("Options");
		optionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		optionsLabel.setForeground(Color.YELLOW);
		optionsLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		optionsLabel.setBounds(0, 358, 128, 46);
		add(optionsLabel);
		
		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setForeground(new Color(235, 235, 235));
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		usernameLabel.setBounds(440, 62, 128, 46);
		add(usernameLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(new Color(235, 235, 235));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setBounds(440, 112, 128, 46);
		add(lblPassword);
		
		passwordTextField = new JTextField();
		passwordTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(603, 116, 209, 39);
		add(passwordTextField);
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		usernameTextField.setColumns(10);
		usernameTextField.setBounds(603, 66, 209, 39);
		add(usernameTextField);
		
		lblNationalId = new JLabel("National ID:");
		lblNationalId.setHorizontalAlignment(SwingConstants.CENTER);
		lblNationalId.setForeground(new Color(235, 235, 235));
		lblNationalId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNationalId.setBounds(440, 162, 128, 46);
		add(lblNationalId);
		
		nationalIdTextField = new JTextField();
		nationalIdTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		nationalIdTextField.setColumns(10);
		nationalIdTextField.setBounds(603, 166, 209, 39);
		add(nationalIdTextField);
	}
}
